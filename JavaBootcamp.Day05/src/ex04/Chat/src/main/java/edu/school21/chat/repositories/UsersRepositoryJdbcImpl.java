package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class UsersRepositoryJdbcImpl implements UsersRepository {
  private DataSource dataSource;

  public UsersRepositoryJdbcImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<User> findAll(int page, int size) {
    List<User> users = new ArrayList<>();
    List<Chatroom> chatroomList = new LinkedList<>();
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement ps = connection.prepareStatement("WITH users_paging_cte as "
          + "(SELECT u.id, u.login, u.password,\n"
          + "array_agg(DISTINCT (CONCAT_WS('-', c1.id, c1.name))) as created_rooms,\n"
          + "array_agg(DISTINCT (CONCAT_WS('-', c2.id, c2.name))) as entered_rooms,\n"
          + "ROW_NUMBER() OVER (ORDER BY u.id) as row\n"
          + "FROM users u FULL JOIN chatrooms c1 ON u.id = c1.owner\n"
          + "JOIN chatroom_users cu ON u.id = cu.user_id\n"
          + "JOIN chatrooms c2 ON c2.id = cu.chatroom_id\n"
          + "GROUP BY u.id) "
          + "SELECT * FROM users_paging_cte "
          + "WHERE row between ? and ?;");
      ps.setInt(1, (page - 1) * size + 1);
      ps.setInt(2, page * size);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        User user = new User(
            resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"));
        Array crRoomsArray = resultSet.getArray("created_rooms");
        Array enRoomsArray = resultSet.getArray("entered_rooms");
        user.setCreatedRooms(getRoomsFromArray(crRoomsArray, chatroomList));
        user.setEnteredRooms(getRoomsFromArray(enRoomsArray, chatroomList));
        users.add(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  private List<Chatroom> getRoomsFromArray(Array roomsArray, List<Chatroom> chatroomList)
      throws SQLException {
    List<Chatroom> roomsList = new LinkedList<>();
    String[] rooms = (String[]) roomsArray.getArray();
    Chatroom c = null;
    for (String s : rooms) {
      if (!s.isEmpty()) {
        String[] splitted = s.split("-");
        if ((c = findRoomInListById(chatroomList, Integer.parseInt(splitted[0]))) != null) {
          chatroomList.add(c);
        } else {
          c = new Chatroom(Integer.parseInt(splitted[0]), splitted[1], null);
          chatroomList.add(c);
        }
      }
      if (c != null) {
        roomsList.add(c);
      }
    }
    return roomsList;
  }

  private Chatroom findRoomInListById(List<Chatroom> chatroomList, int id) {
    Chatroom c = null;
    for (Chatroom room : chatroomList) {
      if (room.getId() == id) {
        c = room;
        break;
      }
    }
    return c;
  }
}
