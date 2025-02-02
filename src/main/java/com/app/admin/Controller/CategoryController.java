package com.app.admin.Controller;

import com.app.admin.Model.Category;
import com.app.admin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

   @GetMapping
   public ResponseEntity<List<Category>> getCategory(){
        try{
            List<Category> categories = null; categories = categoryService.getAll();
            return ResponseEntity.ok(categories);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
   }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Category category){
        try {
            categoryService.create(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sussess");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error :" + e.getMessage());
        }
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        try{
            // service methods
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sussess");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error :" + e.getMessage());
        }
    }
}
