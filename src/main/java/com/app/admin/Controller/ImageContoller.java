package com.app.admin.Controller;

import com.app.admin.Model.ImageADS;
import com.app.admin.Service.ImageAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/images")
public class ImageContoller {

    private final ImageAdsService imageAdsService;

    @Autowired
    public ImageContoller(ImageAdsService imageAdsService){
        this.imageAdsService = imageAdsService;
    }

    @PostMapping
    public String create(@RequestBody ImageADS imageADS){
        return imageAdsService.create(imageADS);
    }

    @GetMapping
    public List<ImageADS> get(){
        return imageAdsService.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return imageAdsService.delete(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id , @RequestBody ImageADS imageADS){
        return imageAdsService.update(id , imageADS);
    }
}
