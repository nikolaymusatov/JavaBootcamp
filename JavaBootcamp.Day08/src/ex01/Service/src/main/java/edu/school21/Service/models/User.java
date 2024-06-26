package edu.school21.Service.models;

public class User {
    private long id;
    private String email;
    
    public User() {}
    
    public User(long id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
