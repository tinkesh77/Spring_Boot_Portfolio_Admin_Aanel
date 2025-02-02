package com.app.admin.Repository;

import com.app.admin.Model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test , Long> {

}
