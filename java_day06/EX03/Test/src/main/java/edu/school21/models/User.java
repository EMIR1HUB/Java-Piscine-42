package edu.school21.models;

import java.util.Objects;

public class User {
    private Long identifier;
    private String login;
    private String password;
    private Boolean status;

    public User(Long identifier, String login, String password, Boolean status){
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(identifier, user.identifier) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, login, password, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                "}\n";
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
