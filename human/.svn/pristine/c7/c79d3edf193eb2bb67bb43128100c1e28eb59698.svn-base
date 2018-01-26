package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.CommunicationSms;
import com.human.recruitment.entity.ResumeSeeker;

@Repository
public interface CommunicationSmsDao {

    /**
     * 获取应聘者短信记录
     * @param rs
     * @return
     */
    List<CommunicationSms> smsQuery(ResumeSeeker rs);

    /**
     * 插入应聘者沟通记录
     * @param cs
     * @return
     */
    int insertSmsRecord(CommunicationSms cs);

}
