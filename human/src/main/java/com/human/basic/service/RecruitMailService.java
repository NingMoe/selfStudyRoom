package com.human.basic.service;

import java.util.List;
import java.util.Map;
import com.human.basic.entity.RecruitMail;
import com.human.utils.PageView;

public interface RecruitMailService {
    /*
     * 分页查询配置邮箱
     */
    PageView query(PageView pageView, RecruitMail recruitMail);
    
    /*
     * 新增邮箱
     */
    int insertSelective(RecruitMail recruitMail);
    
    /*
     * 根据主键查询邮箱
     */
    RecruitMail selectByPrimaryKey(Long id);
    
    /*
     * 编辑邮箱
     */
    int updateByPrimaryKeySelective(RecruitMail record);
    
    /**
     * 删除邮箱
     * @param deleteIds
     * @return
     */
    Map<String, Object> updateStatus(String deleteIds);
    
    /**
     * 检测邮箱协议
     * @param id
     * @param type
     * @return
     */
    Map<String, Object> checkRecruitMail(Long id,int type);
    
    /**
     * 查询所有的接收邮箱
     */
    List<RecruitMail> queryAllMail();
}
