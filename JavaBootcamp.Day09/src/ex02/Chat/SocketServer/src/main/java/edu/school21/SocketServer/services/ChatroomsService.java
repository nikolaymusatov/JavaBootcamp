package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.Chatroom;

import java.util.List;


public interface ChatroomsService {
    boolean isExists(String name);
    void createChatroom(Chatroom chatroom);
    List<Chatroom> getExistingChatrooms();
    Chatroom getChatroomById(Long id);
}
