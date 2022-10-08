//package edu.school21.chat.repositories;
//
//import edu.school21.chat.models.Chatroom;
//import edu.school21.chat.models.Message;
//import edu.school21.chat.models.User;
//
//import java.sql.*;
//import java.util.Optional;
//
//public class MessageJDBCImpl implements MessageRepository {
//    private final Statement statement;
//
//    public MessageJDBCImpl(Connection dataSource) {
//        try {
//            statement = dataSource.createStatement();   // Создание Statement для отправки запроса БД
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Optional<Message> findById(Long id) throws SQLException {
//        ResultSet rs = statement.executeQuery("SElECT * FROM chat.messages WHERE id = " + id);  // Результирующий запрос
//        if (rs.next()) {
//            long messageId = rs.getLong("id");
//            long authorId = rs.getLong("author");
//            long roomId = rs.getLong("chatroom");
//            String messageText = rs.getString("text");
//            Timestamp timestamp = rs.getTimestamp("datetime");
//
//            ResultSet rs2 = statement.executeQuery("SElECT * FROM chat.users WHERE id = " + authorId);
//            rs2.next();
//            User user = new User(rs2.getLong("id"), rs2.getString("login"), rs2.getString("password"), null, null);
//
//            ResultSet rs3 = statement.executeQuery("SElECT * FROM chat.chatrooms WHERE id = " + roomId);
//            rs3.next();
//            Chatroom chatroom = new Chatroom(rs3.getLong("id"), rs3.getString("name"), null, null);
//            Optional<Message> optionalMessage = Optional.of(new Message(messageId, user, chatroom, messageText, timestamp));
//            return optionalMessage;
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    private User createUserObjectFromQuery(long authorId) {
//        try {
//            ResultSet resultSet = statement.executeQuery(USER_QUERY + authorId);
//            if (resultSet.next()) {
//                return new User(authorId, resultSet.getString("login"), resultSet.getString("password"), null, null);
//            }
//            return new User(authorId, null, null, null, null);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Chatroom createChatroomObjectFromQuery(long chatRoomId) {
//        try {
//            ResultSet resultSet = statement.executeQuery(CHATROOM_QUERY + chatRoomId);
//            if (resultSet.next()) {
//                return new Chatroom(chatRoomId, resultSet.getString("name"), null, null);
//            }
//            return new Chatroom(chatRoomId, null, null, null);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
