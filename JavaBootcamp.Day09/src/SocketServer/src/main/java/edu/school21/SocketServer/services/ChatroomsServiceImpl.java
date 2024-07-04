package edu.school21.SocketServer.services;

import edu.school21.SocketServer.exceptions.NoSuchRoomException;
import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.repositories.ChatroomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ChatroomsServiceImpl implements ChatroomsService {
    private ChatroomsRepository chatroomsRepository;
    
    @Autowired
    public ChatroomsServiceImpl(ChatroomsRepository chatroomsRepository) {
        this.chatroomsRepository = chatroomsRepository;
    }
    
    @Override
    public synchronized boolean isExists(String name) {
        return this.chatroomsRepository.findByName(name).isPresent();
    }
    
    @Override
    public synchronized void createChatroom(Chatroom chatroom) {
        this.chatroomsRepository.save(chatroom);
    }
    
    @Override
    public List<Chatroom> getExistingChatrooms() {
        return this.chatroomsRepository.findAll();
    }
    
    @Override
    public Chatroom getChatroomById(Long id) {
        Optional<Chatroom> optionalRoom = this.chatroomsRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            throw new NoSuchRoomException("No such room");
        }
        return optionalRoom.get();
    }
}
