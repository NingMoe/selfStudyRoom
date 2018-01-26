package com.human.basic.service;

import java.util.List;
import java.util.Map;
import com.human.basic.entity.MailNoticeType;
import com.human.utils.PageView;

public interface MailNoticeTypeService {
    /**
     * 分页查询邮件通知类型
     * @param pageView
     * @param user
     * @param deptIds
     * @return
     */
    PageView queryTem(PageView pageView,MailNoticeType mnt,String deptIds);
    
    /**
     * 保存邮件通知类型
     * @param st
     * @return
     */
    int add(MailNoticeType mnt);
    
    /**
     * 根据ID获取邮件通知类型
     * @param id
     * @return
     */
    MailNoticeType queryById(Long id);

    /**
     * 编辑邮件通知类型
     * @param st
     * @return
     */
    int edit(MailNoticeType mnt);

    /**
     * 禁用邮件通知类型
     * @param st
     * @return
     */
    Map<String,Object> delTemp(String deleteIds);
    
    List<MailNoticeType> queryByParams(MailNoticeType mnt);
}
