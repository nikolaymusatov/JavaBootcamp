package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
  private int id;
  private User author;
  private Chatroom room;
  private String text;
  private Timestamp date;

  public Message(int id, User author, Chatroom room, String text, Timestamp date) {
    this.id = id;
    this.author = author;
    this.room = room;
    this.text = text;
    this.date = date;
  }

  public int getId() {
    return this.id;
  }

  public User getAuthor() {
    return this.author;
  }

  public Chatroom getRoom() {
    return this.room;
  }

  public String getText() {
    return this.text;
  }

  public Timestamp getDate() {
    return this.date;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Message message = (Message) o;
    return id == message.id && Objects.equals(author, message.author)
        && Objects.equals(date, message.date);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Message : {"
        + "\n"
        + "\tid=" + id + ",\n"
        + "\tauthor=" + author + ",\n"
        + "\troom=" + room + ",\n"
        + "\ttext='" + text + '\'' + ",\n"
        + "\tdate=" + date + "\n" + '}';
  }
}
