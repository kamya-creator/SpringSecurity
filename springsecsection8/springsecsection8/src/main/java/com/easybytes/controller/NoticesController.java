package com.easybytes.controller;


import com.easybytes.model.Notice;
import com.easybytes.repository.NoticeRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")  configuration related to CROS-Origin is done in ProjectConfiguration file
public class NoticesController {

    private  final NoticeRepository noticeRepository;
    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices()
    {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if(notices != null)
        {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }

        return null;
    }
//
//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request)
//    {
//        return (CsrfToken) request.getAttribute("_csrf");
//
//
//    }
}
