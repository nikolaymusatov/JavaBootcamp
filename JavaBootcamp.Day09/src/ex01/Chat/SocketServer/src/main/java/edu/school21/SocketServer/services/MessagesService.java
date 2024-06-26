package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.User;

public interface MessagesService {
    void saveMessage(User author, String text);
}
