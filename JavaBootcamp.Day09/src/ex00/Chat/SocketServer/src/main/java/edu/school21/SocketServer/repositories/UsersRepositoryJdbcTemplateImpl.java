package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public Optional<User> findByLogin(String login) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("login", login);
        return this.namedParameterJdbcTemplate.query(
                "SELECT * FROM day09_users WHERE login = :login;",
                paramsMap, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM day09_users WHERE id = ?;",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public List<User> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM day09_users",
                new BeanPropertyRowMapper<>(User.class));
    }
    
    @Override
    public void save(User entity) {
        this.jdbcTemplate.update("INSERT INTO day09_users " +
                        "VALUES (?, ?, ?);",
                entity.getId(), entity.getLogin(), entity.getPassword());
    }
    
    @Override
    public void update(User entity) {
        this.jdbcTemplate.update(
                "UPDATE day09_users SET login = ?, password = ? " +
                        "WHERE id = ?;",
                entity.getLogin(), entity.getPassword(), entity.getId());
    }
    
    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM day09_users WHERE id = ?", id);
    }
}