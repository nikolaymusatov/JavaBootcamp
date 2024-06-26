package edu.school21.Service.repositories;

import edu.school21.Service.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("email", email);
        return this.namedParameterJdbcTemplate.query(
                "SELECT * FROM users WHERE email = :email;",
                paramsMap, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return this.jdbcTemplate.query("SELECT * FROM users WHERE id = ?;",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
    
    @Override
    public List<User> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }
    
    @Override
    public void save(User entity) {
        this.jdbcTemplate.update("INSERT INTO users VALUES (?, ?);",
                entity.getId(), entity.getEmail());
    }
    
    @Override
    public void update(User entity) {
        this.jdbcTemplate.update("UPDATE users SET email = ? WHERE id = ?;",
                entity.getEmail(), entity.getId());
    }
    
    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}