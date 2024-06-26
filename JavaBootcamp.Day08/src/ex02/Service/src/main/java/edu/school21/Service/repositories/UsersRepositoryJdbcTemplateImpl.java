package edu.school21.Service.repositories;

import edu.school21.Service.models.User;
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
    public Optional<User> findByEmail(String email) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("email", email);
        return this.namedParameterJdbcTemplate.query(
                "SELECT * FROM users_with_passwords WHERE email = :email;",
                paramsMap, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM users_with_passwords WHERE id = ?;",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public List<User> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM users_with_passwords",
                new BeanPropertyRowMapper<>(User.class));
    }
    
    @Override
    public void save(User entity) {
        this.jdbcTemplate.update("INSERT INTO users_with_passwords " +
                        "VALUES (?, ?, ?);",
                entity.getId(), entity.getEmail(), entity.getPassword());
    }
    
    @Override
    public void update(User entity) {
        this.jdbcTemplate.update(
                "UPDATE users_with_passwords SET email = ?, password = ? " +
                        "WHERE id = ?;",
                entity.getEmail(), entity.getPassword(), entity.getId());
    }
    
    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM users_with_passwords WHERE id = ?", id);
    }
}