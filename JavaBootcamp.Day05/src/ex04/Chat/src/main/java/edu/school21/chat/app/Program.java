package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import java.util.List;
import javax.sql.DataSource;

public class Program {
  public static void main(String[] args) {
    DataSource hikariDataSource = getDataSource();
    UsersRepository usersRepository = new UsersRepositoryJdbcImpl(hikariDataSource);
    List<User> usersList = usersRepository.findAll(1, 1);
    for (User u : usersList) {
      System.out.println(u);
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
