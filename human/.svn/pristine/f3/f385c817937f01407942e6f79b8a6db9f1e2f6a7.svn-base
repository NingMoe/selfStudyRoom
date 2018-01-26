package com.human.examineelist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.examineelist.entity.ExamineeList;
import com.human.examineelist.entity.ExamineeList2;
import com.human.stuexam.entity.StuExam;
import com.human.utils.PageView;

@Repository
public interface ExamineeListDao {
    List<ExamineeList> query(Map<Object, Object> map);

    int insertIntoStu(Map<String, Object> map);

    int deleteAll();

    List<ExamineeList> queryData(ExamineeList em);

    int editDicData();


    void insert(ExamineeList data);

    void deleteByCode(String classCode, String code);

    void deleteByCode(Map<String, Object> map);

    List<ExamineeList> queryByCode(Map<String, Object> code);

    List<ExamineeList> queryforclassCode(String code);

    List<ExamineeList> queryForTime(Map<String, Object> map2);

    List<ExamineeList> queryForGrade(Map<String, Object> map);

    Map<String, Object> queryinfo(String code);
//    查询该班号下所有学生信息
    List<ExamineeList> queryClassCode(String classCode);

    int deleteById(Map<String, Object> map);

    List<ExamineeList> queryByCodeTotal(Map<String, Object> map);

    String getStudentName(String code);

    int getKsNum(String code);



}