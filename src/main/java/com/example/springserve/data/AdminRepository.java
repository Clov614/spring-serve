package com.example.springserve.data;

import com.example.springserve.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
public interface AdminRepository extends CrudRepository<Admin,String> {

    Admin findByUsername(String username);

    Admin findAdminByToken(String token);
}
