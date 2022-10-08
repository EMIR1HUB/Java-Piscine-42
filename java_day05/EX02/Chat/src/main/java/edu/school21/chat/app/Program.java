package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepository;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/data_sc21";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "3696";

    private static Connection createDataSourceConnection() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USERNAME);
            config.setPassword(DB_PASSWORD);

            HikariDataSource hikariDataSource = new HikariDataSource(config);
            if (hikariDataSource.getConnection() == null) {   //hikariDataSource.isClosed()
                throw new SQLException("Database connection failed");
            }
            System.out.println("Database successfully Connection");
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed from createDataSourceConnection: " + e);
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = createDataSourceConnection();
        User user = new User(3, "hamchur", "HxSQGYQ1", new ArrayList<>(), new ArrayList<>());
        Chatroom chatroom = new Chatroom(3, "memes", user, new ArrayList<>());
        Message message = new Message(0, user, chatroom, "Test", new Timestamp(System.currentTimeMillis()));

        MessageRepository messageRepository = new MessageRepositoryJdbcImpl(connection);
        System.out.println(messageRepository.save(message));
    }
}
