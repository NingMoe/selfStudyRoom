package com.human.continuedClass.service;

import java.util.Map;
import com.human.continuedClass.entity.SendCardMail;
import com.human.utils.PageView;

public interface SendCardMailService {
    /**
     * 分页查询发送续班卡邮箱
     */
    PageView query(PageView pageView,SendCardMail scm);
    
    /**
     * 保存发送续班卡邮箱
     * @param ccr
     * @return
     */
    Map<String, Object> add(SendCardMail scm);
    
    /**
     * 根据主键查询发送续班卡邮箱
     * @param id
     * @return
     */
    SendCardMail selectByPrimaryKey(Long id);
    
    /**
     * 编辑发送续班卡邮箱
     * @param ccr
     * @return
     */
    Map<String, Object> edit(SendCardMail scm);
    
    /**
     * 删除发送续班卡邮箱
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 检测邮箱发件服务器连接是否正常
     * @param id
     * @return
     */
    Map<String, Object> checkMail(Long id);
}
