package com.example.springserve.data;

import com.example.springserve.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:8080")
public interface AdminRepository extends CrudRepository<Admin,String> {

    Admin findByUsername(String username);
}
