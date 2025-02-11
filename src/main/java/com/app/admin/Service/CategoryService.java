package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.ImageADSRepository;
import com.app.admin.Repository.VideoADSRepository;
import com.app.admin.Repository.WebsiteDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    public List<Category> get(int pageNumber, int pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(p);
        return categoryPage.getContent();
    }

    public String update(String categoryName, String categoryImage, Long id) {
        Category existCategory = categoryRepository.findById(id).orElse(null);

        if (existCategory == null) {
            return "No Category Found with this ID";
        }

        if (categoryName != null) existCategory.setCategoryName(categoryName);
        if (categoryImage != null) existCategory.setCategoryImage(categoryImage);

        categoryRepository.save(existCategory);

        return "Update Success";
    }
}
