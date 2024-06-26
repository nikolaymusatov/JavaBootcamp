package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
  private int id;
  private String name;
  private User owner;
  private List<Message> messages;

  public Chatroom(String name, User owner) {
    this.name = name;
    this.owner = owner;
    this.messages = null;
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
        + "id=" + id + ", name='" + name + '\'' + ", owner=" + owner + ", messages=" + messages
        + '}';
  }
}
