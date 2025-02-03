package com.app.admin.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "category_videos")
public class VideoADS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_ads_text")
    private String videoAdsText;

    @Column(nullable = false)
    private String videoAdsVideoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public VideoADS() {
    }

    public VideoADS(Long id, String videoAdsText, String videoAdsVideoUrl, Category category) {
        this.id = id;
        this.videoAdsText = videoAdsText;
        this.videoAdsVideoUrl = videoAdsVideoUrl;
        this.category = category;
    }

    public String getVideoAdsText() {
        return videoAdsText;
    }

    public void setVideoAdsText(String videoAdsText) {
        this.videoAdsText = videoAdsText;
    }

    public String getVideoAdsVideoUrl() {
        return videoAdsVideoUrl;
    }

    public void setVideoAdsVideoUrl(String videoAdsVideoUrl) {
        this.videoAdsVideoUrl = videoAdsVideoUrl;
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
