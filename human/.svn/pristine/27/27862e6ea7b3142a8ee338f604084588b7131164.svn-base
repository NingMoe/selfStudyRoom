package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.ResumeKeyword;

@Repository
public interface ResumeKeywordDao {
    int deleteByPrimaryKey(Long id);
   
    int insertSelective(ResumeKeyword record);

    ResumeKeyword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeKeyword record);

    List<ResumeKeyword> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);
    
    List<ResumeKeyword> serachByCondition(ResumeKeyword record);
}