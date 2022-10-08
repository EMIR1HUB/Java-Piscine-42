package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository {

    private final Connection connection;

    public MessageRepositoryJdbcImpl(Connection dataSource) {
        this.connection = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Statement statement = connection.createStatement();     // Создание Statement для отправки запроса БД

        ResultSet rs = statement.executeQuery("SElECT * FROM chat.messages WHERE id = " + id);  // Результирующий запрос
        if (rs.next()) {
            long messageId = rs.getLong("id");
            long authorId = rs.getLong("author");
            long roomId = rs.getLong("chatroom");
            String messageText = rs.getString("text");
            Timestamp timestamp = rs.getTimestamp("datetime");

            ResultSet rs2 = statement.executeQuery("SElECT * FROM chat.users WHERE id = " + authorId);
            rs2.next();
            User user = new User(rs2.getLong("id"), rs2.getString("login"), rs2.getString("password"), null, null);

            ResultSet rs3 = statement.executeQuery("SElECT * FROM chat.chatrooms WHERE id = " + roomId);
            rs3.next();
            Chatroom chatroom = new Chatroom(rs3.getLong("id"), rs3.getString("name"), null, null);
            Optional<Message> optionalMessage = Optional.of(new Message(messageId, user, chatroom, messageText, timestamp));
            return optionalMessage;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Long save(Message message) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chat.messages(author, chatroom, text, datetime) VALUES (?, ?, ?, ?);");
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();

            PreparedStatement idStatement = connection.prepareStatement("SELECT id FROM chat.messages WHERE datetime = ?;");
            idStatement.setTimestamp(1, message.getDateTime());
            ResultSet resultSet = idStatement.executeQuery();
            resultSet.next();

            return resultSet.getLong("id");
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public void update(Message message) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE chat.messages " +
                    "SET author = ?, chatroom = ?, text = ?, datetime = ? " +
                    "WHERE id = " + message.getId());
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }
}
