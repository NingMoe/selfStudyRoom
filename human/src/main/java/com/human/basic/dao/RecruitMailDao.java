package com.human.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.human.basic.entity.RecruitMail;
@Repository
public interface RecruitMailDao {
    /*
     * 
     */
    int deleteByPrimaryKey(Long id);
   /*
    * 新增邮箱
    */
    int insertSelective(RecruitMail record);
    /*
     * 根据主键查询邮箱
     */
    RecruitMail selectByPrimaryKey(Long id);
    /*
     * 更新邮箱
     */
    int updateByPrimaryKeySelective(RecruitMail record);
    /*
     * 分页查询招聘邮箱
     */
    List<RecruitMail> query(Map<Object, Object> map);
    
    /**
     * 删除邮箱
     * @param paraMap
     * @return
     */
    int updateByIds(Map<String, Object> paraMap);
    
    /**
     * 查询所有的接收邮箱
     */
    List<RecruitMail> queryAllMail();
    
    /*
     * 根据机构ID查询发件邮箱
     */
    RecruitMail selectByCompanyId(String hrCompanyId);
    
    
    
    
    
}