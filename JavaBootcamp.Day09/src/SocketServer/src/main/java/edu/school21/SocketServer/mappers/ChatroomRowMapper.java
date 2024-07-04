package edu.school21.SocketServer.mappers;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChatroomRowMapper implements RowMapper<Chatroom> {
    private UsersRepository usersRepository;
    
    @Autowired
    public ChatroomRowMapper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    @Override
    public Chatroom mapRow(ResultSet rs, int rowNum) throws SQLException {
        Chatroom chatroom = new Chatroom();
        chatroom.setId(rs.getLong("id"));
        chatroom.setName(rs.getString("name"));
        
        long ownerId = rs.getLong("owner");
        User owner = this.usersRepository.findById(ownerId).orElse(null);
        chatroom.setOwner(owner);
        return chatroom;
    }
}
