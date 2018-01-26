package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.ResumeModular;
@Repository
public interface ResumeModularDao {
    int deleteByPrimaryKey(Long id);
   
    int insertSelective(ResumeModular record);

    ResumeModular selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeModular record);
    
    List<ResumeModular> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);
    
    List<ResumeModular> findResumeModularByCondition(String website);

}