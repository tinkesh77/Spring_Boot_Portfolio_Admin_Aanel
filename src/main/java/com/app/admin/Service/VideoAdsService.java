package com.app.admin.Service;

import com.app.admin.Model.VideoADS;
import com.app.admin.Repository.VideoADSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoAdsService {

    private final VideoADSRepository videoADSRepository;

    @Autowired
    public VideoAdsService(VideoADSRepository videoADSRepository){
        this.videoADSRepository = videoADSRepository;
    }

    public String create(VideoADS videoADS){
        videoADSRepository.save(videoADS);
        return "Success";
    }

    public List<VideoADS> getAll(){
        return videoADSRepository.findAll();
    }

    public String delete(Long id){
        VideoADS videoADS = videoADSRepository.findById(id).orElse(null);

        if(videoADS == null){
            return  "No ID Found";
        }

        // if found
        videoADSRepository.deleteById(id);
        return "Deleted";
    }

    public String update(Long id , VideoADS videoADS){
        VideoADS existVideoAds = videoADSRepository.findById(id).orElse(null);
        if(existVideoAds == null) {
            return "No Id Found";
        }

        if (videoADS.getVideoAdsText() != null){
            existVideoAds.setVideoAdsText(videoADS.getVideoAdsText());
        }
        if(videoADS.getVideoAdsVideoUrl() != null){
            existVideoAds.setVideoAdsVideoUrl(videoADS.getVideoAdsVideoUrl());
        }
        if (videoADS.getCategory() != null) {
            existVideoAds.setCategory(videoADS.getCategory());
        }

        videoADSRepository.save(existVideoAds);

        return "updated";
    }
}
