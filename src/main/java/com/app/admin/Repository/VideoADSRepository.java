package com.app.admin.Repository;

import com.app.admin.Model.VideoADS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoADSRepository extends JpaRepository<VideoADS , Long> {
}
