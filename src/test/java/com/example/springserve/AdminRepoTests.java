package com.example.springserve;

import com.example.springserve.data.AdminRepository;
import com.example.springserve.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminRepoTests {

    @Autowired
    AdminRepository adminRepo;

    @Test
    public void findTest() {
        // 测试admin数据库查询
        Admin admin = adminRepo.findByUsername("username");
        log.info(admin.toString());
    }

    @Test
    public void digestTest() {
        String encoding = DigestUtils.sha256Hex("adminadmin管理员");
        log.info(encoding);
        if (encoding.equals("f56eb013f37207daeb3007e0109e2f465e1de2a278effdef292fe032b7d46123")) {
            log.info("True");
        }

    }

}
