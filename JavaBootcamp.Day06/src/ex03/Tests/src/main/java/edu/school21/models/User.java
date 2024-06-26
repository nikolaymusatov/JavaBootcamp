package edu.school21.models;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean authenticated;
    
    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticated = false;
    }
    
    public boolean getAuthenticated() {
        return this.authenticated;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
