package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.SendcardMailRecord;
@Repository
public interface SendcardMailRecordDao {
    
    int insertSelective(SendcardMailRecord record);
    
    /*
     * 更新发送失败的续班卡日志
     * @param record
     * @return
     */
    int updateFailClassCode(SendcardMailRecord record);

    /*
     * 分页查询续班卡邮件发送日志
     */
    List<SendcardMailRecord> queryClassCardMail(Map<Object, Object> map);
    
    /*
     * 查询某规则下发送失败的续班卡班号
     */
    List<SendcardMailRecord> queryFailClassCode(Long ruleId);
    
}