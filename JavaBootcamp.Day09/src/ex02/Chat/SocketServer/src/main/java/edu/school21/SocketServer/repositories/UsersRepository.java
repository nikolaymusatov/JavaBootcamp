package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByLogin(String login);
}
