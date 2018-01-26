package com.human.basic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.basic.entity.SmsParam;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsTem;

@Repository
public interface SmsTempDao {

    /**
     * 分页查询短信模版
     * @param map
     * @return
     */
    List<SmsTem> query(Map<String, Object> map);

    /**
     * 查询模版参数
     * @param sp
     * @return
     */
    List<SmsParam> queryParam(SmsParam sp);

    /**
     * 保存短信模版
     * @param st
     * @return
     */
    int add(SmsTem st);

    /**
     * 根据主键ID获取短信模版
     * @param id
     * @return
     */
    SmsTem queryById(Long id);

    /**
     * 编辑短信模版
     * @param st
     * @return
     */
    int edit(SmsTem st);

    /**
     * 禁用短信模版
     * @param paraMap
     * @return
     */
    int delTemp(Map<String, Object> paraMap);

    /**
     * 插入短信发送记录
     * @param smsRecord
     * @return
     */
    int insertSmsRecord(SmsRecord smsRecord);

    /**
     * 查询短信模版
     * @param map
     * @return
     */
    List<SmsTem> queryAll(SmsTem st);

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
    String getMsg(@Param("phone")String phone,@Param("time")String time);
    
    /**
     * 根据简历ID获取短信记录
     * @param resumeId
     * @return
     */
    List<SmsRecord> querySmsByResumeId(Integer resumeId);

}
