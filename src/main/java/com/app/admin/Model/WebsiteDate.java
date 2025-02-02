package com.app.admin.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "website_data")
public class WebsiteDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "website_text")
    private String websiteText;

    private String websiteImage;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
        private Category category;

    public String getWebsiteText() {
        return websiteText;
    }

    public void setWebsiteText(String websiteText) {
        this.websiteText = websiteText;
    }

    public String getWebsiteImage() {
        return websiteImage;
    }

    public void setWebsiteImage(String websiteImage) {
        this.websiteImage = websiteImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
