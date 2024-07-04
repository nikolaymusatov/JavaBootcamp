package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class UsersRepositoryHibernateImpl implements UsersRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<User> findByLogin(String login) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getResultStream()
                .findFirst();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
    
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
    
    @Override
    public void save(User entity) {
        entityManager.persist(entity);
    }
    
    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }
    
    @Override
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}