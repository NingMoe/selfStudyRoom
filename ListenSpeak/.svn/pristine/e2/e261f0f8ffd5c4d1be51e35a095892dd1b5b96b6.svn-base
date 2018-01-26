package com.ls.spt.zuoye.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;

@Repository(value="lstStudentZuoyeDao1")
public interface LstStudentZuoyeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstStudentZuoye record);

    int insertSelective(LstStudentZuoye record);

    LstStudentZuoye selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstStudentZuoye record);

    int updateByPrimaryKey(LstStudentZuoye record);
    
    List<LstStudentZuoye> getStudentZuoyePage(Map<Object, Object> map);
    
    int updateStudentZuoyeTeacherScore(LstZuoyeStudentAnswer record);

    int getTjNum(Map<String, Object> map);
    
    LstStudentZuoye getStudentZuoyeByCondition(LstZuoyeStudentAnswer record);
}