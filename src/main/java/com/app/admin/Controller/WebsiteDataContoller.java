package com.app.admin.Controller;

import com.app.admin.Model.Category;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Service.WebsiteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

            if (websiteDate.getCategory() == null || websiteDate.getCategory().getId() == null){
                throw new IllegalArgumentException("Category ID must be provided");
            }
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

    @GetMapping
    public List<WebsiteDate> get(){
        return websiteDateService.get();
    }

    @DeleteMapping("/{id}")
    public String deleteWeb(@PathVariable Long id){
        return websiteDateService.delete(id);
    }

    @PutMapping("/{id}")
    public String updateData(@PathVariable Long id, @RequestBody WebsiteDate websiteDate) {
        return websiteDateService.update(id , websiteDate);
    }

}
