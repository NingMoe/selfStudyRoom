package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.resume.entity.ResumeBase;
@Repository
public interface MyInsideRecommendDao {
    /*
     * 分页查询我的内推
     */
    List<ResumeBase> query(Map<String,Object> map);
    
    /*
     * 我的内推导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的我的内推
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
}