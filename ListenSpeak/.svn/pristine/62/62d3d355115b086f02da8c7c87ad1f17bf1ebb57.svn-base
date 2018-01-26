package com.ls.spt.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ls.spt.basic.entity.SmsRecord;

@Repository
public interface SmsTempDao {

 

    /**
     * 插入短信发送记录
     * @param smsRecord
     * @return
     */
    int insertSmsRecord(SmsRecord smsRecord);
    
    /**
     * 分页查询所有短信历史记录
     * @param map
     * @return
     */
    List<SmsRecord> queryMsgRecord(Map<String, Object> map);

    /**
     * 查询所有短信历史记录
     * @param map
     * @return
     */
    List<SmsRecord> queryMsgRecordNoPage(SmsRecord sr);
    
    /**
     * 获取前台短信修改手机号验证码
     * @param phone
     * @param time
     * @return
     */
    SmsRecord getMsg(@Param("phone")String phone,@Param("time")String time);
    

}
