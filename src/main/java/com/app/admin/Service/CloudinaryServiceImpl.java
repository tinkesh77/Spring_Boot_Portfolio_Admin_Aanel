package com.app.admin.Service;

import com.app.admin.Service.impl.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }


    @Override
    public Map upload(MultipartFile file) {
        try{
            Map data =  this.cloudinary.uploader().upload(file.getBytes() , Map.of());
            return data;
        }catch (IOException e){
            throw new RuntimeException("Image Upldaing Falid");
        }

    }
}
