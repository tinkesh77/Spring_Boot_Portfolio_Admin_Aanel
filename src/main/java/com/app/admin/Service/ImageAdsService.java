package com.app.admin.Service;

import com.app.admin.Model.ImageADS;
import com.app.admin.Repository.ImageADSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageAdsService {
    private final ImageADSRepository imageADSRepository;

    @Autowired
    public ImageAdsService(ImageADSRepository imageADSRepository){
        this.imageADSRepository = imageADSRepository;
    }

    public String create(ImageADS imageADS){
        imageADSRepository.save(imageADS);
        return "Susses";
    }

    public List<ImageADS> getAll(){
        return imageADSRepository.findAll();
    }

    public String delete(Long id){
        ImageADS imageADS = imageADSRepository.findById(id).orElse(null);

        if(imageADS == null) {
            return  "No Image Found IN ID";
        }

        imageADSRepository.deleteById(id);

        return "Delete Susses";
    }

    public String update(Long id , ImageADS imageADS){
        ImageADS existImage = imageADSRepository.findById(id).orElse(null);
        if (existImage == null){
            return "No ID Found";
        }
        if (imageADS.getImageAdsText() != null)existImage.setImageAdsText(imageADS.getImageAdsText());
        if (imageADS.getImageAdsImageUrl() != null)existImage.setImageAdsImageUrl(imageADS.getImageAdsImageUrl());
        if (imageADS.getCategory() != null)existImage.setCategory(imageADS.getCategory());

        imageADSRepository.save(existImage);

        return "Update Susses";
    }
}
