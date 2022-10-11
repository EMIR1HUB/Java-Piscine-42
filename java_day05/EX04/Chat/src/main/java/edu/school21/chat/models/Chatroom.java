package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private final long id;
    private String name;
    private User creator;
    private List<Message> messages;

    public Chatroom(long id, String name, User creator, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.messages = messages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creator, messages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;

        return id == chatroom.getId() && name.equals(chatroom.getName())
                && creator.equals(chatroom.creator) && messages.equals(chatroom.messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", messages=" + messages +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
