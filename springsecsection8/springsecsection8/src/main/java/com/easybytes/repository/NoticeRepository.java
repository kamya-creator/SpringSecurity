package com.easybytes.repository;

import com.easybytes.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = "from Notice n where CURD,  ATE() BETWEEN noticeBgDt AND noticeEndDt")
    List<Notice> findAllActiveNotices();
}
