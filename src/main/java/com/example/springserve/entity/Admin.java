package com.example.springserve.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Admin {

    private String username;

    private String password;

    private String name;

    private String token;

    public Admin() {
    }

    public Admin(String username, String password, String name, String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.token = token;
    }
}
