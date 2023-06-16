package com.example.springserve.entity;

import lombok.Data;

@Data
public class ReqNoticeID { // 多了个id的requestBody
    private Long id;
    private String title;
    private String content;
    private String token;
}
