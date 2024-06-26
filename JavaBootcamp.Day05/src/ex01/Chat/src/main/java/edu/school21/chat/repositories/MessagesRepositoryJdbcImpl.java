package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import javax.sql.DataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
  private final DataSource dataSource;

  public MessagesRepositoryJdbcImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Optional<Message> findById(Long id) {
    int userId;
    int roomId;
    Optional<Message> message = Optional.empty();

    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM messages WHERE id = ?");
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        userId = resultSet.getInt("author");
        roomId = resultSet.getInt("room");
        message = Optional.of(new Message(resultSet.getInt("id"),
            (findUserById(userId).orElse(null)), (findRoomById(roomId).orElse(null)),
            resultSet.getString("text"), resultSet.getString("date")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;
  }

  public Optional<User> findUserById(int id) {
    Optional<User> user = Optional.empty();

    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        user = Optional.of(new User(
            resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public Optional<Chatroom> findRoomById(int id) {
    int ownerId;
    Optional<Chatroom> room = Optional.empty();

    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM chatrooms WHERE id = ?");
      ps.setLong(1, id);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        ownerId = resultSet.getInt("owner");
        room = Optional.of(new Chatroom(resultSet.getInt("id"), resultSet.getString("name"),
            (findUserById(ownerId).orElse(null))));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return room;
  }
}
