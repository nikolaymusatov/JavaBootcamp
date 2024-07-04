package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;

import java.util.List;

public interface MessagesService {
    void saveMessage(Message message);
    User getAuthor(Message message);
    List<Message> getLastMessagesInRoom(Chatroom chatroom);
}
