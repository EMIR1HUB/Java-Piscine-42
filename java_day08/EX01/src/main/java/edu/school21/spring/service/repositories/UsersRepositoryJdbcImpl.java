package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional findById(Long id) {
        User user = null;
        try (Connection connection = dataSource.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ex08.users WHERE id = " + id);

            if (rs.next()) {
                Long userId = rs.getLong("id");
                String email = rs.getString("email");
                user = new User(userId, email);
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List findAll() {
        List<User> users = null;
        try (Connection connection = dataSource.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ex08.users");
            users = new ArrayList<>();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String email = rs.getString("email");
                User user = new User(id, email);
                users.add(user);
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO ex08.users(email) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getEmail());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            Long id = rs.getLong("id");
            entity.setId(id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE ex08.users SET email = ? WHERE id = ?");
            stm.setString(1, entity.getEmail());
            stm.setLong(2, entity.getId());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM ex08.users WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM ex08.users WHERE email = " + email);

            if (rs.next()) {
                Long id = rs.getLong("id");
                String emailUser = rs.getString("email");
                optionalUser = Optional.of(new User(id, emailUser));
            }else {
                throw new IllegalException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }
}
