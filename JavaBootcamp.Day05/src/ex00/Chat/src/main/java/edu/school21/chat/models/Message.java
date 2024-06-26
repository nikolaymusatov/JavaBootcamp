package edu.school21.chat.models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Message {
  private int id;
  private User author;
  private Chatroom room;
  private String text;
  private Calendar date;

  public Message(User author, Chatroom room) {
    this.author = author;
    this.room = room;
    this.text = null;
    this.date = new GregorianCalendar();
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
    return "Message{"
        + "id=" + id + ", author=" + author + ", room=" + room + ", text='" + text + '\''
        + ", date=" + date + '}';
  }
}
