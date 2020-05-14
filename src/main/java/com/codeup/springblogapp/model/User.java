package com.codeup.springblogapp.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    // Setting the Primary key for users table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // username
    @Column(nullable = false, unique = true)
    private String username;

    // email
    @Column(nullable = false)
    private String email;

    // password
    @Column
    private String password;

    //User can have multiple ads
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Ad> ads;


    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
