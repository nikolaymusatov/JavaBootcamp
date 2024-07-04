package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Message> {
    User getAuthor(Message message);
    List<Message> getLastMessagesInRoom(Chatroom chatroom);
}
