package com.app.admin.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private List<WebsiteDate> website;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private List<ImageADS> images;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , orphanRemoval = true  )
    @JsonIgnore
    private List<VideoADS> video;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<WebsiteDate> getWebsite() {
        return website;
    }

    public void setWebsite(List<WebsiteDate> website) {
        this.website = website;
    }

    public List<ImageADS> getImages() {
        return images;
    }

    public void setImages(List<ImageADS> images) {
        this.images = images;
    }

    public List<VideoADS> getVideo() {
        return video;
    }

    public void setVideo(List<VideoADS> video) {
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", website=" + website +
                ", images=" + images +
                ", video=" + video +
                '}';
    }

    public Category(Long id, List<WebsiteDate> website, String categoryName, List<ImageADS> images, List<VideoADS> video) {
        this.id = id;
        this.website = website;
        this.categoryName = categoryName;
        this.images = images;
        this.video = video;
    }
    public Category(Long id) {
        this.id = id;
    }
    public Category() {}
}
