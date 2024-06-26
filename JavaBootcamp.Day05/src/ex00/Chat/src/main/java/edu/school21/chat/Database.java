package edu.school21.chat;

import edu.school21.chat.models.User;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
  private Connection connection;

  public Database() {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/java_chat", "nikolajmusatov", "balalayka");
      if (connection != null) {
        System.out.println("Connection is established");
      } else {
        System.out.println("Connection failed");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    this.connection = connection;
  }

  public Connection getConnection() {
    return this.connection;
  }

  public void addUser(User user) {
    try {
      String query = String.format("INSERT INTO users VALUES (DEFAULT, \'%s\', \'%s\');",
          user.getLogin(), user.getPassword());
      this.connection.createStatement().executeUpdate(query);
      System.out.printf("INSERTED USER %s\n", user.getLogin());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteUser(User user) {
    try {
      String query = String.format("DELETE FROM users WHERE login = \'%s\';", user.getLogin());
      this.connection.createStatement().executeUpdate(query);
      System.out.printf("DELETED USER %s\n", user.getLogin());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
