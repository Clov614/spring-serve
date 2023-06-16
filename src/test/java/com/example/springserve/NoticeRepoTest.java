package com.example.springserve;

import com.example.springserve.data.NoticeRepository;
import com.example.springserve.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeRepoTest {

    @Autowired
    NoticeRepository noticeRepo;

    @Test
    public void findAllTest() {
        List<Notice> noticeList = (List<Notice>) noticeRepo.findAll();
        log.info(noticeList.toString());
    }

    @Test
    public void findByToken() {
        String token = "f56eb013f37207daeb3007e0109e2f465e1de2a278effdef292fe032b7d46123";
        List<Notice> noticeList = (List<Notice>) noticeRepo.findByToken(token);
        log.info(noticeList.toString());
    }

    @Test
    public void addNotice() {
        String title = "测试标题";
        String content = "测试内容";
        // username的token
        String token = "7084191d8ae792a110a55cc4c323e8e8982d4b26eea1fe1fabe7c9bfec2ac236";
        noticeRepo.save(new Notice(null,title,content,token));
    }
}
