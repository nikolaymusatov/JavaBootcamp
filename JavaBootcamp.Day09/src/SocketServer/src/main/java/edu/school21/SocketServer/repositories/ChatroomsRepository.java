package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Chatroom;

import java.util.Optional;

public interface ChatroomsRepository extends CrudRepository<Chatroom> {
    Optional<Chatroom> findByName(String name);
}
