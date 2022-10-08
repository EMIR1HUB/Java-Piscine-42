package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private final long id;
    private final String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;


    public User(long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> socializedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.socializedRooms = socializedRooms;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, login, password, createdRooms, socializedRooms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return id == user.getId() && login.equals(user.getLogin()) && password.equals(user.getPassword())
                && createdRooms.equals(user.getCreatedRooms()) && socializedRooms.equals(user.getSocializedRooms());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", socializedRooms=" + socializedRooms +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public List<Chatroom> getSocializedRooms() {
        return socializedRooms;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public void setSocializedRooms(List<Chatroom> socializedRooms) {
        this.socializedRooms = socializedRooms;
    }
}
