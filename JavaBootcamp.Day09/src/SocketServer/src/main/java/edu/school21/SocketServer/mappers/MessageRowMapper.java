package edu.school21.SocketServer.mappers;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.ChatroomsRepository;
import edu.school21.SocketServer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageRowMapper implements RowMapper<Message> {
    private UsersRepository usersRepository;
    private ChatroomsRepository chatroomsRepository;
    
    @Autowired
    public MessageRowMapper(UsersRepository usersRepository,
                            ChatroomsRepository chatroomsRepository) {
        this.usersRepository = usersRepository;
        this.chatroomsRepository = chatroomsRepository;
    }
    
    
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setText(rs.getString("text"));
        message.setDatetime(rs.getTimestamp("date"));
        
        User author = this.usersRepository
                .findById(rs.getLong("author")).orElse(null);
        Chatroom chatroom = this.chatroomsRepository.findById(rs.getLong(
                "chatroom")).orElse(null);
        
        message.setAuthor(author);
        message.setChatroom(chatroom);
        return message;
    }
}
