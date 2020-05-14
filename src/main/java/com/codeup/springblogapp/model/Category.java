package com.codeup.springblogapp.model;


import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;


    // many categories can be mapped to multiple ads, and vice versa

    @ManyToMany(mappedBy ="categories")
    private List<Ad> ads;

    public Category(String name, List<Ad> ads) {
        this.name = name;
        this.ads = ads;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
