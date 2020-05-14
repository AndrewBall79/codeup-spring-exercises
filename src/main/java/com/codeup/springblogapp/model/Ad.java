package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {

    // Create a primary key fir the ads table in our DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    String title;


    @Column(nullable = false)
    String description;

    // each ad can only have one author/owner
    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ad")
    private List<AdImage> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            // name of the intermediate table
            name = "ads_categories",
            // name for the id of this model joinColumns
            joinColumns = {@JoinColumn(name = "ad_id")},
            // name for the id of the related model inverseJoinColumns
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    public Ad() {
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
