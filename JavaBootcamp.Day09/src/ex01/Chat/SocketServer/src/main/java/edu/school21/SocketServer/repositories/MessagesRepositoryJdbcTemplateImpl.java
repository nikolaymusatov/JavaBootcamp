package edu.school21.SocketServer.repositories;

import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component("messagesRepositoryJdbcTemplate")
public class MessagesRepositoryJdbcTemplateImpl implements MessagesRepository{
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MessagesRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Optional<Message> findById(Long id) {
        return this.jdbcTemplate.query(
                "SELECT * FROM day09ex01_messages WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Message.class)).stream().findAny();
    }
    
    @Override
    public List<Message> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM day09ex01_messages",
                new BeanPropertyRowMapper<>(Message.class));
    }
    
    @Override
    public void save(Message message) {
        String sql = "INSERT INTO day09ex01_messages (author, text, date) " +
                "VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, message.getAuthor().getId());
            ps.setString(2, message.getText());
            ps.setTimestamp(3, message.getDatetime());
            return ps;
        }, keyHolder);
        message.setId(((Number) keyHolder.getKeys().get("id")).longValue());
    }
    
    @Override
    public void update(Message message) {
        this.jdbcTemplate.update(
                "UPDATE day09ex01_messages SET author = ?, text = ?, date = ? " +
                        "WHERE id = ?",
                message.getAuthor().getId(), message.getText(),
                message.getDatetime(), message.getId());
    }
    
    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(
                "DELETE FROM day09ex01_messages WHERE id = ?", id);
    }
    
    @Override
    public User getAuthor(Message message) {
        return this.jdbcTemplate.query(
                "SELECT u.id, u.login, u.password, u.authorized " +
                        "FROM day09ex01_users u JOIN day09ex01_messages m ON u.id = " +
                        "m.author WHERE m.id = ?",
                new Object[]{message.getId()},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }
}
