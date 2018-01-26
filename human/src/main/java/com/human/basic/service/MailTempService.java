package com.human.basic.service;

import java.util.List;
import java.util.Map;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.MailParam;
import com.human.basic.entity.MailTem;
import com.human.utils.PageView;

public interface MailTempService {

    /**
     * 分页查询邮件模版
     * @param pageView
     * @param user
     * @param deptIds
     * @return
     */
    PageView queryTem(PageView pageView, MailTem mt,String deptIds);
    
    
    /**
     * 分页查询邮件模版
     * @param pageView
     * @param user
     * @param deptIds
     * @return
     */
    List<MailTem> getTemLit(MailTem mt);

    /**
     * 查询模版参数
     * @param sp
     * @return
     */
    List<MailParam> queryParam(MailParam mp);

    /**
     * 保存邮件模版
     * @param st
     * @return
     */
    int add(MailTem mt);

    /**
     * 根据ID获取模版
     * @param id
     * @return
     */
    MailTem queryById(Long id);

    /**
     * 编辑邮件模版
     * @param st
     * @return
     */
    int edit(MailTem mt);

    /**
     * 禁用模版
     * @param st
     * @return
     */
    Map<String,Object> delTemp(String deleteIds);

    /**
     * 批量发送邮件
     * @param mms
     */
    void sendMailMessage(List<MailMessage> mms);

    /**
     * 发送单条邮件
     * @param mm
     * @return
     */
    Integer sendMessage(MailMessage mm);

}
