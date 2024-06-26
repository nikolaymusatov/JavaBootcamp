package edu.school21.chat;

import edu.school21.chat.models.User;

public class App {
  public static void main(String[] args) {
    Database database = new Database();
    User elidacon = new User("elidacon", "spring90");
    database.addUser(elidacon);
    database.deleteUser(elidacon);
  }
}
