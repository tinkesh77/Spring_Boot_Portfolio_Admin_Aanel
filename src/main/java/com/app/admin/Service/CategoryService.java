package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void create(Category category){
        System.out.println(category.getCategoryName());
        categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

     public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
