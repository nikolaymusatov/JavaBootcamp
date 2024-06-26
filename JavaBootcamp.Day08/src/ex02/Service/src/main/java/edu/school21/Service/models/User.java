package edu.school21.Service.models;

public class User {
    private long id;
    private String email;
    private String password;
    
    public User() {}
    
    public User(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
