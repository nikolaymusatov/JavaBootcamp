package edu.school21.SocketServer.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "day09ex02_messages")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;
    
    private String text;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom")
    private Chatroom chatroom;
    
    private Timestamp datetime;
    
    public Message() {}
    
    public Message(User author, String text, Chatroom chatroom,
                   Timestamp datetime) {
        this.author = author;
        this.text = text;
        this.chatroom = chatroom;
        this.datetime = datetime;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public Timestamp getDatetime() {
        return datetime;
    }
    
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Chatroom getChatroom() {
        return this.chatroom;
    }
    
    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Message message = (Message) object;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(text, message.text) && Objects.equals(chatroom, message.chatroom) && Objects.equals(datetime, message.datetime);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", chatroom=" + chatroom +
                ", datetime=" + datetime +
                '}';
    }
}
