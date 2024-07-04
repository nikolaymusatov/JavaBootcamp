package edu.school21.SocketServer.models;

import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private boolean authorized;
    
    public User() {}
    
    public User(String login, String password, boolean authorized) {
        this.login = login;
        this.password = password;
        this.authorized = authorized;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
    
    public boolean getAuthorized() {
        return this.authorized;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id == user.id && authorized == user.authorized && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authorized='" + authorized + '\'' +
                '}';
    }
}
