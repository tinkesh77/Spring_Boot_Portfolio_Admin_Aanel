package com.app.admin.Controller;

import com.app.admin.Model.Category;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Service.WebsiteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/website")
public class WebsiteDataContoller {


    private final WebsiteDateService websiteDateService;
    private final CategoryRepository categoryRepository;


    @Autowired
    public  WebsiteDataContoller(WebsiteDateService websiteDateService , CategoryRepository categoryRepository){
        this.websiteDateService = websiteDateService;
        this.categoryRepository =categoryRepository;
    }

    @PostMapping
    public String createData(@RequestBody WebsiteDate websiteDate) {
        try {
            Category category = categoryRepository.findById(websiteDate.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            websiteDate.setCategory(category);

            websiteDateService.create(websiteDate);

            return "Website data inserted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
