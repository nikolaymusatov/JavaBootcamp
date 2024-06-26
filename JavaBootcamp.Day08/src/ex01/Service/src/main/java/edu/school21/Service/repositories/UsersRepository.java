package edu.school21.Service.repositories;

import edu.school21.Service.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
