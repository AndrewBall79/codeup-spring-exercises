package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false, length = 100)
    private String type;

    // mapping

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Post(String title, String body, User user){
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(String title, String body, User user, long id){
        this.title = title;
        this.body = body;
        this.user = user;
        this.id = id;
    }

    public Post(){}

    public Post(long id, String type, String body, String title) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.type = type;
    }

    public Post(String title, String body, String type) {
        this.title = title;
        this.body = body;
        this.type = type;
    }

    public User getUser(){return user;}
    public User setUser(User author){return user;}



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
