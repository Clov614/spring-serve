package com.example.springserve.api;

import com.example.springserve.Response;
import com.example.springserve.data.AdminRepository;

import com.example.springserve.entity.Admin;
import com.example.springserve.entity.ReqAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/sign", produces="application/json")
@CrossOrigin(origins="*")
@Slf4j
public class AdminController {

    private AdminRepository adminRepo;

    private Response resp;

    @GetMapping
    public String getTest() {
        return "get_sign";
    }

    @Autowired
    public AdminController(AdminRepository repo) {
        this.adminRepo = repo;
    }

    @PostMapping(path = "/login")
    public Response signLogin(@RequestBody ReqAdmin reqAdmin) {
        String username = reqAdmin.getUsername();
        String password = reqAdmin.getPassword();
        // 账号密码为空的情况
        if (username.equals("") || password.equals("")) {
            return new Response(400,"账号密码为空",new Admin(username,password,null,null));
        }
        // 查询数据库
        Admin admin = adminRepo.findByUsername(username);
        if (admin == null) {
            return new Response(401,"账号不存在",null);
        }
        // 密码正确
        if (admin.getPassword().equals(password)) {
            return new Response(200,"登录成功",admin);
        } else if (!admin.getPassword().equals(password)){
            return new Response(403,"该账号: "+admin.getName()+" 密码错误",admin);
        }

        return new Response(500,"UnknownErr",null);
    }

    @GetMapping(path = "/login")
    public Admin getLogin() {
        return new Admin("username","password","nick_name","token");
    }
}
