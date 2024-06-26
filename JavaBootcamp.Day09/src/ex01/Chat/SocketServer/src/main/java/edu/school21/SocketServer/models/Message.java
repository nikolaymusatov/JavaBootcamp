package edu.school21.SocketServer.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private String text;
    private Timestamp datetime;
    
    public Message(User author, String text, Timestamp datetime) {
        this.author = author;
        this.text = text;
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
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Message message = (Message) object;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(text, message.text) && Objects.equals(datetime, message.datetime);
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
                ", datetime=" + datetime +
                '}';
    }
}
