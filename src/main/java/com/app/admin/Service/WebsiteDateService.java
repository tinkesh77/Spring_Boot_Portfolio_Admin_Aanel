package com.app.admin.Service;


import com.app.admin.Model.Category;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Repository.WebsiteDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteDateService {

    private final WebsiteDateRepository websiteDateRepository;

    public WebsiteDateService(WebsiteDateRepository websiteDateRepository) {
        this.websiteDateRepository = websiteDateRepository;
    }

    public void create(WebsiteDate websiteDate) {
        websiteDateRepository.save(websiteDate);
    }

    public String delete(Long id){
        WebsiteDate websiteDate = websiteDateRepository.findById(id).orElse(null);
        if (websiteDate == null) {
            return  "Id Does not exist.";
        }

        websiteDateRepository.delete(websiteDate);
        return "Deleted Susses";
    }

    public List<WebsiteDate> get(){
        return websiteDateRepository.findAll();
    }

    public String update(Long id, WebsiteDate websiteDate) {
        WebsiteDate websiteDate1 = websiteDateRepository.findById(id).orElse(null);

        if (websiteDate1 == null) {
            return "No Item Found With this ID";
        }

        // Conditional updates
        if (websiteDate.getWebsiteText() != null) {
            websiteDate1.setWebsiteText(websiteDate.getWebsiteText());
        }
        if (websiteDate.getWebsiteImage() != null) {
            websiteDate1.setWebsiteImage(websiteDate.getWebsiteImage());
        }
        if (websiteDate.getCategory() != null) {
            websiteDate1.setCategory(websiteDate.getCategory());
        }

        websiteDateRepository.save(websiteDate1);
        return "Update Success";
    }

}

