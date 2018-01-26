package com.human.jzbTest.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.jzbTest.entity.JzbStudent;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.JzbStudentDto;

@Repository
public interface JzbStudentDao {
    int deleteByPrimaryKey(Integer id);    

    int insertSelective(JzbStudent record);

    JzbStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbStudent record);

    /*
     * 分页查询
     * @param map
     * @return
     */
    List<JzbStudentDto> query(Map<Object, Object> map);
    
    int insertBmRecord(JzbStudentBmRecord bmRecord);
    
    List<JzbStudentBmRecord> getBmRecord(Integer paperId); 
    
    List<JzbStudentDto> getRecentPaper(Date startDate);
}