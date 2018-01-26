package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.recruitment.entity.InsideRecommendManagerEntity;
@Repository
public interface InsideRecommendManagerDao {
    /*
     * 分页查询内推管理
     */
    List<InsideRecommendManagerEntity> query(Map<String,Object> map);
    
    /*
     * 内推管理导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的内推管理
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
    
    /*
     * 统计简历不同状态的数量
     */
    List<Map<String, Object>> getNumberHashMap(String insideRecommend);
    
    /*
     * 统计入职简历的数量
     */
    long getEntryNumberMap(String insideRecommend);
}