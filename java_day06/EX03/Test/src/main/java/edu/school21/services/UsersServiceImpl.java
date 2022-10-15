package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UsersServiceImpl implements UsersRepository {

    private final Connection connection;

    public UsersServiceImpl(Connection connection) {
        this.connection = connection;
    }


    public boolean authenticate(String login, String password) {
        User user = findByLogin(login);
        if(user.getStatus()){
            throw new AlreadyAuthenticatedException();
        }
        user.setStatus(Objects.equals(password, user.getPassword()));
        update(user);

        return user.getStatus();
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong("identifier"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    public void update(User user) {
        findByLogin(user.getLogin());
        String sql = "UPDATE users SET " +
                "login = ?, " +
                "password = ?, " +
                "status = ? " +
                "WHERE identifier = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getStatus());
            statement.setLong(4, user.getIdentifier());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
