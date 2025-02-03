package com.app.admin.Controller;


import com.app.admin.Model.VideoADS;
import com.app.admin.Service.VideoAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/videos")
public class VideoAdsController {

    private final VideoAdsService videoAdsService;

    @Autowired
    public VideoAdsController(VideoAdsService videoAdsService){
        this.videoAdsService = videoAdsService;
    }

    @PostMapping
    public String create(@RequestBody VideoADS videoADS){
        return videoAdsService.create(videoADS);
    }

    @GetMapping
    public List<VideoADS> get(){
        return videoAdsService.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return videoAdsService.delete(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id , @RequestBody VideoADS videoADS){
        return videoAdsService.update(id , videoADS);
    }
}
