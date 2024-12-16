package com.easybytes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "notice_details")
public class Notice {
    @Id
    @Column(name = "notice_id")
    private long noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notice_bg_dt")
    private Date noticeBgDt;

    @Column(name = "notice_end_dt")
    private Date noticeEndDt;

    @JsonIgnore
    @Column(name = "update_dt")
    private Date updateDt;
}
