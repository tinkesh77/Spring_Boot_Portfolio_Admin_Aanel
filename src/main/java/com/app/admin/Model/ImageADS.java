package com.app.admin.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "image_ads")
public class ImageADS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_ads_text")
    private String imageAdsText;

    @Column(nullable = false)
    private String imageAdsImageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public String getImageAdsText() {
        return imageAdsText;
    }

    public void setImageAdsText(String imageAdsText) {
        this.imageAdsText = imageAdsText;
    }

    public String getImageAdsImageUrl() {
        return imageAdsImageUrl;
    }

    public void setImageAdsImageUrl(String imageAdsImageUrl) {
        this.imageAdsImageUrl = imageAdsImageUrl;
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
