package com.app.admin.Controller;


import com.app.admin.Model.Test;
import com.app.admin.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestContoller {

    private final TestService testService;

    @Autowired
    public TestContoller(TestService testService){
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Test test) {
        try {
            String result = testService.createTest(test);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the test");
        }
    }

}
