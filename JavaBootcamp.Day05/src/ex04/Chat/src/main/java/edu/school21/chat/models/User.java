package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {
  private int id;
  private String login;
  private String password;
  private List<Chatroom> createdRooms;
  private List<Chatroom> enteredRooms;

  public User(int id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.createdRooms = new LinkedList<>();
    this.enteredRooms = new LinkedList<>();
  }

  public String getLogin() {
    return this.login;
  }

  public String getPassword() {
    return this.password;
  }

  public int getId() {
    return this.id;
  }

  public void setCreatedRooms(List<Chatroom> createdRooms) {
    this.createdRooms = createdRooms;
  }

  public void setEnteredRooms(List<Chatroom> enteredRooms) {
    this.enteredRooms = enteredRooms;
  }

  public void addCreatedRoom(Chatroom chatroom) {
    this.createdRooms.add(chatroom);
  }

  public void addEnteredRoom(Chatroom chatroom) {
    this.enteredRooms.add(chatroom);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return id == user.id && Objects.equals(login, user.login);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "User{"
        + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\''
        + ",\n\tcreatedRooms=" + createdRooms + ",\n\tenteredRooms=" + enteredRooms + '}';
  }
}
