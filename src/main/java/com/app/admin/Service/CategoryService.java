package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.ImageADSRepository;
import com.app.admin.Repository.VideoADSRepository;
import com.app.admin.Repository.WebsiteDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final WebsiteDateRepository websiteDateRepository;
    private final ImageADSRepository imageADSRepository;
    private final VideoADSRepository videoADSRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository , WebsiteDateRepository websiteDateRepository , ImageADSRepository imageADSRepository , VideoADSRepository  videoADSRepository){
        this.categoryRepository = categoryRepository;
        this.websiteDateRepository = websiteDateRepository;
        this.imageADSRepository = imageADSRepository;
        this.videoADSRepository = videoADSRepository;
    }

    public void create(Category category){
        System.out.println(category.getCategoryName());
        categoryRepository.save(category);
    }

    public void delete(Long id){
        // need to delete all the associate data with category which is delete using orphanRemoval = True

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }

     public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public String update(Category category , Long id){
        Category existCategory = categoryRepository.findById(id).orElse(null);

        if(existCategory == null) {
            return "No Category Found with this ID";
        }

        existCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existCategory);

        return "Update Susses";
    }
}
