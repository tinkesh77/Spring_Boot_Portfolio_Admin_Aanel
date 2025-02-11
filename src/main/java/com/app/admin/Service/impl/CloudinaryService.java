package com.app.admin.Service.impl;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryService {

    public Map upload(MultipartFile multipartFile);
}
