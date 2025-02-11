package com.app.admin.Configuration;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public Cloudinary getColudinary(){

        Map config = new HashMap();
        config.put("cloud_name" , "dkcaypehy");
        config.put("api_key" , "777875757548254");
        config.put("api_secret" , "WtbNA7qW6coRjnp5wEUSJSCH-MY");
        return  new Cloudinary(config);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
    .allowedOrigins("http://127.0.0.1:5500", "http://127.0.0.1:5501");
    }
}
