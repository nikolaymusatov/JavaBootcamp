package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.sql.DataSource;

public class Program {
  public static void main(String[] args) {
    DataSource hikariDataSource = getDataSource();
    MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
    User creator = new User(8, "carisafi", "qwerty123");
    Chatroom room = new Chatroom(1, "diamonds_tribe", creator);
    Message message = new Message(
        0, creator, room, "Who wants to have lunch now?", Timestamp.valueOf(LocalDateTime.now()));
    messagesRepository.save(message);
    System.out.println(message.getId());
  }

  public static DataSource getDataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_chat");
    config.setUsername("nikolajmusatov");
    config.setPassword("balalayka");
    config.setDriverClassName("org.postgresql.Driver");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    return new HikariDataSource(config);
  }
}
