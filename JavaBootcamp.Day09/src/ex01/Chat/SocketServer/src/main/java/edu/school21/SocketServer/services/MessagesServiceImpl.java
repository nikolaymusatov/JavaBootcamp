package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class MessagesServiceImpl implements MessagesService {
    private MessagesRepository messagesRepository;
    
    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }
    
    @Override
    public void saveMessage(User author, String text) {
        Message message = new Message(author, text,
                Timestamp.valueOf(LocalDateTime.now()));
        this.messagesRepository.save(message);
    }
}
