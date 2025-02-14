package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Model.ImageADS;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.WebsiteDateRepository;
import com.app.admin.Service.impl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebsiteDateService {

    private final WebsiteDateRepository websiteDateRepository;

    private final CategoryRepository categoryRepository;

    private CloudinaryService cloudinaryService;


    @Autowired
    public WebsiteDateService(WebsiteDateRepository websiteDateRepository , CloudinaryService cloudinaryService
    , CategoryRepository categoryRepository) {
        this.websiteDateRepository = websiteDateRepository;
        this.cloudinaryService = cloudinaryService;
        this.categoryRepository =categoryRepository;
    }

    public ResponseEntity<Map<String, String>> create(String name , String link, MultipartFile file, Long id) {
        Map<String, String> response = new HashMap<>();

        // Save image in Cloudinary
        Map data = cloudinaryService.upload(file);
        String imageUrl = (String) data.get("url");

        WebsiteDate websiteDate = new WebsiteDate();

        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            response.put("message", "Category is null");
            return ResponseEntity.ok(response);
        }

        websiteDate.setWebsiteImage(imageUrl);
        websiteDate.setWebsiteText(name);
        websiteDate.setWebsiteLink(link);
        websiteDate.setCategory(category);
        websiteDateRepository.save(websiteDate);

        response.put("message", "Data is saved");
        return ResponseEntity.ok(response);
    }

    public String delete(Long id){
        WebsiteDate websiteDate = websiteDateRepository.findById(id).orElse(null);
        if (websiteDate == null) {
            return  "Id Does not exist.";
        }

        websiteDateRepository.delete(websiteDate);
        return "Deleted Susses";
    }

    public ResponseEntity<Long> getTotal(){
        Long count = websiteDateRepository.count();
        return ResponseEntity.ok(count);
    }

    public List<WebsiteDate> get(int pageNumber , int pageSize){
        Pageable p = PageRequest.of(pageNumber , pageSize);
        Page<WebsiteDate> websiteDatePage = websiteDateRepository.findAll(p);
        return websiteDatePage.getContent();
    }


    public String update(Long id ,  String websiteTitle , String websiteLink , MultipartFile file , Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if(category == null) { return  "Category Id Not Found"; }

        WebsiteDate existingData = websiteDateRepository.findById(id).orElse(null);

        if (existingData == null) {
            throw new RuntimeException("Invalid website Id");
        }
        if(websiteTitle != null) existingData.setWebsiteText(websiteTitle);
        if(websiteLink != null) existingData.setWebsiteLink(websiteLink);
        if (file != null && !file.isEmpty()) {
            Map m = cloudinaryService.upload(file);
            String imageUrl = (String) m.get("url");
            existingData.setWebsiteImage(imageUrl);
        }
        existingData.setCategory(category);
        // finally saving this data
        websiteDateRepository.save(existingData);
        return "Update susees";
    }

    public ResponseEntity<List<WebsiteDate>> getAll(){
        return ResponseEntity.ok(websiteDateRepository.findAll());
    }

}

