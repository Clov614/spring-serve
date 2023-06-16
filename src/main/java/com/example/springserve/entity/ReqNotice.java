package com.example.springserve.entity;

import lombok.Data;

@Data
public class ReqNotice {
    private String title;
    private String content;
    private String token;
}
