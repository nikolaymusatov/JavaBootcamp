package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;

public interface MessagesRepository extends CrudRepository<Message> {
    User getAuthor(Message message);
}
