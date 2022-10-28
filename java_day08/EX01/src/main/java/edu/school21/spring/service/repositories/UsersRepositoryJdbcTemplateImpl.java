package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional findById(Long id) {
        String SQL = "SELECT * FROM ex08.users WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.query(SQL, new UserMapper()));
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query("SELECT * FROM ex08.users", new UserMapper());
    }

    @Override
    public void save(User entity) {
        String SQL = "INSERT INTO ex08.users(email) VALUES (?)";
        jdbcTemplate.update(SQL, entity.getEmail());
    }

    @Override
    public void update(User entity) {
        String SQL = "UPDATE ex08.users SET email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM ex08.users WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String SQL = "SELECT * FROM ex08.users WHERE email = " + email;
        return Optional.of(jdbcTemplate.query(SQL, new UserMapper()).stream().findFirst().orElse(null));
    }
}
