package com.app.admin.Repository;


import com.app.admin.Model.ImageADS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageADSRepository extends JpaRepository<ImageADS , Long> {
}
