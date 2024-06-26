package edu.school21.SocketServer.models;

public class User {
    private long id;
    private String login;
    private String password;
    
    public User() {}
    
    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
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
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
