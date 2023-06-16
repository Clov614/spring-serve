package com.example.springserve.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "notice")
public class Notice {
    @Id
    Long id;

    @Column("title")
    private String title;
    @Column("content")
    private String content;
    @Column("token")
    private String token;

    public Notice(Long id, String title, String content, String token) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.token = token;
    }
}
