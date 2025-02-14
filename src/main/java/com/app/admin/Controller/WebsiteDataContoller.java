package com.app.admin.Controller;

import com.app.admin.Model.Category;
import com.app.admin.Model.ImageADS;
import com.app.admin.Model.WebsiteDate;
import com.app.admin.Repository.CategoryRepository;
import com.app.admin.Service.WebsiteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
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
    public ResponseEntity<Map<String, String>> createData(@RequestParam("WebsiteName") String websiteName,
                                                          @RequestParam("WebsiteLink") String websiteLink,
                                                          @RequestParam("WebsiteImage") MultipartFile file,
                                                          @RequestParam("id") Long catId) {

        if (websiteName == null || file == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid data, website name or image is missing.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        return websiteDateService.create(websiteName, websiteLink ,file, catId);
    }

    @GetMapping
    public List<WebsiteDate> get(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        return websiteDateService.get(pageNumber , pageSize);
    }

    @DeleteMapping("/{id}")
    public String deleteWeb(@PathVariable Long id){
        return websiteDateService.delete(id);
    }

    @PutMapping("/{id}")
    public String updateData(
            @RequestParam("WebsiteId") Long websiteId ,
            @RequestParam("WebsiteName") String websiteTitle ,
            @RequestParam("WebsiteLink") String websiteLink ,
            @RequestParam (value = "WebsiteImage", required = false) MultipartFile file ,
            @RequestParam("id") Long categoryId
    ) {
        return websiteDateService.update(websiteId ,websiteTitle , websiteLink , file , categoryId);
    }

    @GetMapping("/total")
    public ResponseEntity<Long> totalCategories(){
        return websiteDateService.getTotal();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WebsiteDate>> getAll(){
        return websiteDateService.getAll();
    }

}


