package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Chatroom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class ChatroomsRepositoryHibernateImpl implements ChatroomsRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<Chatroom> findByName(String name) {
        return entityManager.createQuery(
                "SELECT c FROM Chatroom c WHERE c.name = :name",
                        Chatroom.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }
    
    @Override
    public Optional<Chatroom> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Chatroom.class, id));
    }
    
    @Override
    public List<Chatroom> findAll() {
        return entityManager.createQuery(
                "SELECT c FROM Chatroom c", Chatroom.class)
                .getResultList();
    }
    
    @Override
    public void save(Chatroom entity) {
        entityManager.persist(entity);
    }
    
    @Override
    public void update(Chatroom entity) {
        entityManager.merge(entity);
    }
    
    @Override
    public void delete(Long id) {
        entityManager.createQuery(
                "DELETE FROM Chatroom c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
