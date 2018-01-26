package com.human.manager.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.human.manager.entity.MailSendRecord;

@Repository
public interface MailSendRecordDao {
    
    int deleteByPrimaryKey(Long id);

    int insert(MailSendRecord record);

    int insertSelective(MailSendRecord record);

    MailSendRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailSendRecord record);

    int updateByPrimaryKey(MailSendRecord record);
    
    /*
     * 分页查询邮件发送日志
     */
    List<MailSendRecord> query(Map<Object, Object> map);    
}