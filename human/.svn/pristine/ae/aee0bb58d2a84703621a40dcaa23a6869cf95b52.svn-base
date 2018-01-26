package com.human.basic.service;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.SmsParam;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsTem;
import com.human.utils.PageView;

public interface SmsTempService {

    /**
     * 分页查询短信模版
     * @param pageView
     * @param user
     * @param deptIds
     * @return
     */
    PageView queryTem(PageView pageView, SmsTem st,String deptIds);

    /**
     * 查询短信模版参数
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
     * 根据ID获取模版
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
     * 禁用模版
     * @param st
     * @return
     */
    Map<String,Object> delTemp(String deleteIds);

    /**
     * 批量发送短信
     * @param smss
     */
    void sendMessage(List<SmsRecord>  srList);

    /**
     * 发送单条短信
     * @param sms
     * @return
     */
    Integer sendMessage(SmsRecord sr);

    /**
     * 查询短信模版
     * @param pageView
     * @param user
     * @param deptIds
     * @return
     */
    List<SmsTem> queryTemAll(SmsTem st);
/**
 * 查询短信发送历史记录
 * @param pageView
 * @param sr
 * @param deptIds
 * @return
 */
    PageView queryMsgRecord(PageView pageView, SmsRecord sr, String deptIds);

    /**
     * 不分页查询短信发送记录
     * @param sr
     * @return
     */
    List<SmsRecord> queryMsgRecordNoPage(SmsRecord sr);
    
    /**
     * 前台发送修改手机号短信验证码
     * @param telNumber
     * @return
     */
    Map<String,Object>sendCode(String telNumber,Long resumeSeekerId);

}
