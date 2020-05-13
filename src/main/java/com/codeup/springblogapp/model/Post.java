package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String body;

    private String type;

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
