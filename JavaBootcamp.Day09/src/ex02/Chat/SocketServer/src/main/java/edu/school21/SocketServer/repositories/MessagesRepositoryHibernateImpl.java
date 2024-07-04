package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Chatroom;
import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MessagesRepositoryHibernateImpl implements MessagesRepository{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }
    
    @Override
    public List<Message> findAll() {
        return entityManager.createQuery(
                "SELECT m FROM Message m", Message.class)
                .getResultList();
    }
    
    @Override
    public void save(Message message) {
        entityManager.persist(message);
    }
    
    @Override
    public void update(Message message) {
        entityManager.merge(message);
    }
    
    @Override
    public void delete(Long id) {
        entityManager.createQuery(
                "DELETE FROM Message m WHERE m.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
    
    @Override
    public User getAuthor(Message message) {
        return entityManager.createQuery(
                        "SELECT u FROM User u JOIN Message m ON m.author = u " +
                                "WHERE m.id = :messageId", User.class)
                .setParameter("messageId", message.getId())
                .getResultStream().findAny().orElse(null);
    }
    
    @Override
    public List<Message> getLastMessagesInRoom(Chatroom chatroom) {
        return entityManager.createQuery(
                        "SELECT m FROM Message m WHERE m.chatroom.id = " +
                                ":chatroomId ORDER BY m.id DESC", Message.class)
                .setParameter("chatroomId", chatroom.getId())
                .setMaxResults(30)
                .getResultList();
    }
}
