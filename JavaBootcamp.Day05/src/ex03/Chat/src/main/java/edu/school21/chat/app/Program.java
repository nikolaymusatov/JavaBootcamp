package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.util.Optional;
import javax.sql.DataSource;

public class Program {
  public static void main(String[] args) {
    DataSource hikariDataSource = getDataSource();
    MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
    Optional<Message> optionalMessage = messagesRepository.findById(2L);
    if (optionalMessage.isPresent()) {
      Message message = optionalMessage.get();
      message.setText("This is my new message!");
      message.setDate(null);
      messagesRepository.update(message);
    }
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
