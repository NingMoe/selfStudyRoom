package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.recruitment.entity.SchoolRecruitment;
@Repository
public interface SchoolRecruitmentDao {
    int deleteByPrimaryKey(Long id);
   
    int insertSelective(SchoolRecruitment record);

    SchoolRecruitment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SchoolRecruitment record);
    
    /*
     * 分页查询校招管理
     */
    List<SchoolRecruitment> query(Map<String,Object> map);
    
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 统计校招收到的总共简历数
     */
    long getTotalCount(long id);
        
    /*
     * 统计简历不同状态的数量
     */
    List<Map<String, Object>> getNumberHashMap(long id);
    
    /*
     * 统计入职简历的数量
     */
    long getEntryNumberMap(long id);
    
    /*
     * 校招管理导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的校招管理
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
}