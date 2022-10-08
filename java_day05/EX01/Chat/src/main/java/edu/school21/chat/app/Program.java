package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message ID: ");
        if (!scanner.hasNextLong()) {
            System.out.println("Invalid id format");
            System.exit(-1);
        }
        Long id = scanner.nextLong();

        Connection connection = createDataSourceConnection();
        if (connection != null) {
            MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(connection);
            // isPresent() - метод возвращает ответ, существует ли искомый объект или нет, в виде Boolean
            //Message message = messageRepositoryJdbc.findById(id).isPresent() ? messageRepositoryJdbc.findById(id).get() : new Message();
            Message message = messageRepositoryJdbc.findById(id).orElse(new Message());
            System.out.println(message);
        }
    }
}
