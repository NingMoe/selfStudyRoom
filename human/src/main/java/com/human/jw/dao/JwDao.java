package com.human.jw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.jw.entity.JwTeacherGrade;
import com.human.jw.entity.JwTeacherInfo;
import com.human.jw.entity.JwTeacherSite;
import com.human.jw.entity.OrgEmployeeTree;

@Repository
public interface JwDao {
    List<OrgEmployeeTree> queryTreeById(String id);
    
    JwTeacherInfo getBaseMsgByEmail(String email);
    
    int insert(JwTeacherInfo teacherInfo);
    
    int updateByPrimaryKeySelective(JwTeacherInfo teacherInfo);
    
    JwTeacherInfo selectByEmail(String email);
    
    List<JwTeacherInfo> selectTeacherPage(Map<Object,Object> map);
    
    JwTeacherInfo getCnSj(Map<Object,Object> map);
    
    List<JwTeacherInfo> getAllTeacher();
    
    JwTeacherInfo getTeacherInfoById(Integer id);
    
    int delGradeByTeacherCode(String teacherCode);
    
    int insertGrades(List<JwTeacherGrade> grades);
    
    int delSitesByTeacherCode(String teacherCode);
    
    int insertSites(List<JwTeacherSite> sites);
    
    int delTeacherByCode(String teacherCode);
    
    String getTeacherIdsByCondition(JwTeacherInfo teacherInfo);
    
    String getAllTeacherIds();
    
    Integer isHasEditAu(String email);
    
    Integer isGaozhongManager(String email);
}
