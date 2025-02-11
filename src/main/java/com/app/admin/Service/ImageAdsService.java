package com.app.admin.Service;

import com.app.admin.Model.Category;
import com.app.admin.Model.ImageADS;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.ImageADSRepository;
import com.app.admin.Service.impl.CloudinaryService;
import com.app.admin.dto.ImageADSRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ImageAdsService {
    private final ImageADSRepository imageADSRepository;
    private final CloudinaryService cloudinary;
    private  final CategoryRepository categoryRepository;

    @Autowired
    public ImageAdsService(ImageADSRepository imageADSRepository , CloudinaryService cloudinary , CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.imageADSRepository = imageADSRepository;
        this.cloudinary = cloudinary;
    }

    public String create(MultipartFile file, Long id) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be null or empty");
        }

        return categoryRepository.findById(id)
                .map(category -> {
                    Map uploadResult = cloudinary.upload(file);
                    String imageUri = (String) uploadResult.get("url");

                    ImageADS ads = new ImageADS();
                    ads.setImageAdsImageUrl(imageUri);
                    ads.setCategory(category);
                    imageADSRepository.save(ads);

                    return "Success";
                })
                .orElseThrow(() -> new RuntimeException("Category not found for ID: " + id));
    }

    public List<ImageADS> get(int pageNumber , int pageSize){
        Pageable p = PageRequest.of(pageNumber , pageSize);
        Page<ImageADS> imageADSPage = imageADSRepository.findAll(p);
        return imageADSPage.getContent();
    }

    public String delete(Long id){
        ImageADS imageADS = imageADSRepository.findById(id).orElse(null);

        if(imageADS == null) {
            return  "No Image Found IN ID";
        }
        imageADSRepository.deleteById(id);
        return "Delete Susses";
    }

    public String update(Long catId , Long imgId , MultipartFile file)  {

        Category category = categoryRepository.findById(catId).orElse(null);
        ImageADS imageADS = imageADSRepository.findById(imgId).orElse(null);

        if(category == null || imageADS == null) {
            return "Update UnSuccessful";
        }

        Map uploadResult = cloudinary.upload(file);
        String imageUri = (String) uploadResult.get("url");

        imageADS.setCategory(category);
        imageADS.setImageAdsImageUrl(imageUri);


        imageADSRepository.save(imageADS);

        return "Update Success";
    }
}
