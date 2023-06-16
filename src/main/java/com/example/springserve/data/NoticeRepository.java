package com.example.springserve.data;

import com.example.springserve.entity.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
public interface NoticeRepository extends CrudRepository<Notice, Long> {
    // 按照token查询
    List<Notice> findByToken(String token);

    Optional<Notice> findById(Long id);
}
