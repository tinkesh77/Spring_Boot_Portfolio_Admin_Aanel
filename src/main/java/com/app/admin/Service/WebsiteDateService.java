package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.WebsiteDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteDateService {

    private final WebsiteDateRepository websiteDateRepository;

    public WebsiteDateService(WebsiteDateRepository websiteDateRepository) {
        this.websiteDateRepository = websiteDateRepository;
    }

    public void create(WebsiteDate websiteDate) {
        websiteDateRepository.save(websiteDate);
    }
}

