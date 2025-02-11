package com.app.admin.dto;

import com.app.admin.Model.Category;

public class ImageADSRequest {
    private Long id;
    private String imageAdsImageUrl;
    private Category category;

    public ImageADSRequest() {
    }

    public ImageADSRequest(Long id, String imageAdsImageUrl, Category category) {
        this.id = id;
        this.imageAdsImageUrl = imageAdsImageUrl;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}