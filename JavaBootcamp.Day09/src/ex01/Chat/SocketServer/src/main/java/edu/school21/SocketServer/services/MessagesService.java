package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;

public interface MessagesService {
    void saveMessage(Message message);
    User getAuthor(Message message);
}
