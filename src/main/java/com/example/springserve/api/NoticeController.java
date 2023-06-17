package com.example.springserve.api;

import com.example.springserve.Response;
import com.example.springserve.data.AdminRepository;
import com.example.springserve.data.NoticeRepository;
import com.example.springserve.entity.Admin;
import com.example.springserve.entity.Notice;
import com.example.springserve.entity.ReqNotice;
import com.example.springserve.entity.ReqNoticeID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/notice", produces="application/json")
@CrossOrigin(origins="*")
@Slf4j
public class NoticeController {

    private NoticeRepository noticeRepo;

    private AdminRepository adminRepo;

    private Response resp;

    @Autowired
    public NoticeController(NoticeRepository noticeRepo,AdminRepository adminRepo) {
        this.noticeRepo = noticeRepo;
        this.adminRepo = adminRepo;
    }

    @GetMapping("/getNoticeList")
    @Transactional
    public Response getList() {
        // 查询notice表全部
        List<Notice> noticeList = (List<Notice>) noticeRepo.findAll();
//        log.info(noticeList.toString());
        return new Response(200,"获取全部公告成功",noticeList);
    }

    @PostMapping("/add")
    @Transactional
    public Response addNotice(@RequestBody ReqNotice reqNotice) {
        // 获取title content token
        String title = reqNotice.getTitle();
        String content = reqNotice.getContent();
        String token = reqNotice.getToken();
        // 验证token是否存在admin表
        Admin admin = adminRepo.findAdminByToken(token);
        if (admin == null) {
            return new Response(400,"不存在该用户token",null);
        }
        // 添加notice
        noticeRepo.save(new Notice(null,title,content,token));
        return new Response(200,"添加公告成功",null);
    }

    @PostMapping("/modify")
    @Transactional
    public Response modifyNotice(@RequestBody ReqNoticeID reqNoticeID) {
        // 获取id title content token
        Long id = reqNoticeID.getId();
        String title = reqNoticeID.getTitle();
        String content = reqNoticeID.getContent();
        String token = reqNoticeID.getToken();
        // 获取需要修改的notice
        Optional<Notice> optional = noticeRepo.findById(id);
        Notice notice = optional.get();
        // notice为空，id不存在返回错误
        if (notice == null) {
            return new Response(401,"修改条目id错误",null);
        }
        // 保存修改
        notice.setTitle(title);
        notice.setContent(content);
        notice.setToken(token);
        noticeRepo.save(notice);
        return new Response(200,"修改成功",null);
    }
    @PostMapping("/delete")
    @Transactional
    public Response delNotice(@RequestBody ReqNoticeID reqNoticeID) { // 删除公告
        // 获取id title content token
        Long id = reqNoticeID.getId();
        String title = reqNoticeID.getTitle();
        String content = reqNoticeID.getContent();
        String token = reqNoticeID.getToken();
        // 获取需要删除的notice
        Optional<Notice> optional = noticeRepo.findById(id);
        Notice notice = optional.get();
        // notice为空，id不存在返回错误
        if (notice == null) {
            return new Response(402,"删除条目id错误",null);
        }
        noticeRepo.delete(new Notice(id,title,content,token));
        return new Response(200,"删除成功",null);
    }

}
