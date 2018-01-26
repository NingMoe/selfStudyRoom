package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.recruitment.entity.InterviewEntity;
@Repository
public interface InterviewDao {
    /*
     * 分页查询面试安排
     */
    List<InterviewEntity> query(Map<String,Object> map);
    
    /*
     * 面试安排导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的面试安排
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
}