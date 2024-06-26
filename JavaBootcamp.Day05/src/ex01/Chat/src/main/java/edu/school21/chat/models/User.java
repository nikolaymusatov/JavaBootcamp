package edu.school21.chat.models;

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
    this.createdRooms = null;
    this.enteredRooms = null;
  }

  public String getLogin() {
    return this.login;
  }

  public String getPassword() {
    return this.password;
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
        + ", createdRooms=" + createdRooms + ", enteredRooms=" + enteredRooms + '}';
  }
}
