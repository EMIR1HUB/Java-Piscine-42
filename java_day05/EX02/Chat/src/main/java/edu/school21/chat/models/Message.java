package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private Timestamp dateTime;

    public Message() {}

    public Message(long id, User author, Chatroom chatroom, String text, Timestamp dateTime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;

        return id == message.getId() && author.equals(message.getAuthor()) && chatroom.equals(message.getChatroom())
                && text.equals(message.getText()) && dateTime.equals(message.getDateTime());
    }

    @Override
    public String toString() {
        return "Message : {" +
                "\n\tid=" + id +
                ",\n\tauthor=" + author +
                ",\n\tchatroom=" + chatroom +
                ",\n\ttext='" + text + '\'' +
                ",\n\tdateTime=" + dateTime +
                "\n}";
    }

    public long getId() {
        return this.id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public String getText() {
        return text;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }


    public void setAuthor(User author) {
        this.author = author;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
