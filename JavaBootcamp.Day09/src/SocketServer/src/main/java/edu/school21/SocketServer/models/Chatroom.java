package edu.school21.SocketServer.models;

import edu.school21.SocketServer.server.ClientHandler;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "day09ex02_chatrooms")
public class Chatroom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User owner;
    
    @Transient
    private List<ClientHandler> clients = new ArrayList<>();
    
    public Chatroom() {}
    
    public Chatroom(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public synchronized List<ClientHandler> getClients () {
        return clients;
    }
    
    public synchronized void addClient(ClientHandler client) {
        this.clients.add(client);
    }
    
    public synchronized void removeClient(ClientHandler client) {
        this.clients.remove(client);
    }
    
    public synchronized void broadcastMessage(Message message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && Objects.equals(name, chatroom.name)
                && Objects.equals(owner, chatroom.owner);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
    @Override
    public String toString() {
        return "Chatroom{"
                + "id=" + id + ", name='" + name + '\'' + ", owner=" + owner
                + "}";
    }
}