package com.app.admin.Controller;

import com.app.admin.Model.ImageADS;
import com.app.admin.Service.ImageAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/images")
public class ImageContoller {

    private final ImageAdsService imageAdsService;

    @Autowired
    public ImageContoller(ImageAdsService imageAdsService){
        this.imageAdsService = imageAdsService;
    }

    @PostMapping
    public String create(@RequestParam("imageAds")MultipartFile file, @RequestParam("catId") Long catId){
        return imageAdsService.create(file , catId);
    }

    @GetMapping
    public List<ImageADS> get( @RequestParam(defaultValue = "0") int pageNumber,
                               @RequestParam(defaultValue = "5") int pageSize){
        return imageAdsService.get(pageNumber , pageSize);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return imageAdsService.delete(id);
    }

    @PutMapping
    public String update(@RequestParam("catId") Long catId ,
                         @RequestParam("imgId") Long imgId,
                         @RequestParam("newImg") MultipartFile file ){
        return imageAdsService.update(catId , imgId , file);
    }
}
