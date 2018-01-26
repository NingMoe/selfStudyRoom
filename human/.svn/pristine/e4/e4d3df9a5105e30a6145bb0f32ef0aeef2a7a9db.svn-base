package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.resume.entity.ResumeBase;
@Repository
public interface InterviewObserverDao {
    /*
     * 分页查询面试观察员
     */
    List<ResumeBase> query(Map<String,Object> map);
    
    /*
     * 面试观察员导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的面试观察员
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
}