package com.human.mail.service;

import java.util.List;
import com.human.mail.entity.AcceptMail;
import com.human.utils.PageView;

public interface AcceptMailService {

    /**
     * 下载各学校招聘接收邮箱中每日邮件
     */
    void downLoadAcceptMail(String path);
    
    /**
     * 根据邮件自带的唯一标示messageId查询邮件
     */
    int queryByMessageId(String messageId);
    
    /*
     * 查询所有解析失败的邮件
     */
    List<AcceptMail> queryFailAnalysis();
    
    /**
     * 分页查询解析邮件
     */
    PageView query(PageView pageView, AcceptMail acceptMail);
}
