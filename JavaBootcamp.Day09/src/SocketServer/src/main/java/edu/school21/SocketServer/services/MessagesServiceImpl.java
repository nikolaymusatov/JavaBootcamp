package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    private MessagesRepository messagesRepository;
    
    @Autowired
    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }
    
    @Override
    public synchronized void saveMessage(Message message) {
        this.messagesRepository.save(message);
    }
    
    @Override
    public synchronized User getAuthor(Message message) {
        return this.messagesRepository.getAuthor(message);
    }
    
    @Override
    public List<Message> getLastMessagesInRoom(Chatroom chatroom) {
        return this.messagesRepository.getLastMessagesInRoom(chatroom);
    }
}
