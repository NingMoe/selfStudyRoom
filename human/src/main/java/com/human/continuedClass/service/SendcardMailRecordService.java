package com.human.continuedClass.service;

import com.human.continuedClass.entity.SendcardMailRecord;
import com.human.utils.PageView;

public interface SendcardMailRecordService {
    /*
     * 分页查询续班卡邮件发送日志
     */
    PageView query(PageView pageView, SendcardMailRecord smr);
}
