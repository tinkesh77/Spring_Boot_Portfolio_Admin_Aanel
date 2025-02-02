package com.app.admin.Service;


import com.app.admin.Model.Test;
import com.app.admin.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public String createTest(Test test) {
        testRepository.save(test);
        return "Susses";
    }
}
