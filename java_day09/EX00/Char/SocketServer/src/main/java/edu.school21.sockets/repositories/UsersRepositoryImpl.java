package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepository")
public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional findById(Long id) {
        String SQL = "SELECT * FROM ex09.users WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.query(SQL, new UserMapper()));
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query("SELECT * FROM ex09.users", new UserMapper());
    }

    @Override
    public void save(User entity) {
        String SQL = "INSERT INTO ex09.users(name, password) VALUES (?, ?)";
        jdbcTemplate.update(SQL, entity.getUserName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        String SQL = "UPDATE ex09.users SET name = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(SQL, entity.getUserName(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM ex09.users WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public Optional<User> findByName(String name) {
        String SQL = String.format("SELECT * FROM ex09.users WHERE name IN('%s');", name);
        return Optional.ofNullable(jdbcTemplate.query(SQL, new UserMapper()).stream().findFirst().orElse(null));
    }
}
