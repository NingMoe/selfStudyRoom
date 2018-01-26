package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.MailNoticeType;

@Repository
public interface MailNoticeTypeDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(MailNoticeType record);

    MailNoticeType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailNoticeType record);

    /**
     * 分页查询邮件通知类型
     * @param map
     * @return
     */
    List<MailNoticeType> query(Map<String, Object> map);
    
    /**
     * 禁用邮件通知类型
     * @param paraMap
     * @return
     */
    int delTemp(Map<String, Object> paraMap);
    
    /**
     * 查询邮件通知类型
     * @param mnt
     * @return
     */
    List<MailNoticeType> queryByParams(MailNoticeType mnt);
    
}