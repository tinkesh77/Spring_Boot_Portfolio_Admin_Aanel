package com.app.admin.Controller;

import com.app.admin.Model.Category;
import com.app.admin.Service.CategoryService;
import com.app.admin.Service.impl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    private CloudinaryService cloudinaryService;


    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PutMapping("/{id}")
    public String updateName(@RequestPart("categoryName") String categoryName,
                             @RequestPart(value = "categoryImage", required = false) MultipartFile categoryImage,
                             @PathVariable Long id) {
        Map data = this.cloudinaryService.upload(categoryImage);
        String imageUrl = (String) data.get("url");
        return categoryService.update(categoryName, imageUrl, id);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> totalCategories(){
        return categoryService.getTotal();
    }




    @GetMapping
    public ResponseEntity<List<Category>> getCategory(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "6") int pageSize) {

        try {
            List<Category> categories = categoryService.get(pageNumber, pageSize);
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public ResponseEntity<Map> create(@RequestParam("categoryName") String categoryName,
                                         @RequestParam("categoryImage") MultipartFile categoryImage){
        Map data = this.cloudinaryService.upload(categoryImage);
        String imageUrl = (String) data.get("url");
        //System.out.println(imageUrl);
        //System.out.println(categoryName);

        Category category = new Category();
        category.setCategoryName(categoryName); 
        category.setCategoryImage(imageUrl);

        categoryService.create(category);

        return new ResponseEntity<>(data  , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
