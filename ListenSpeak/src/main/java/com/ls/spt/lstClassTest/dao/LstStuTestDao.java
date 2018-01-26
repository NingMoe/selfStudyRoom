package com.ls.spt.lstClassTest.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;

public interface LstStuTestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstStudentTest record);

    int insertSelective(LstStudentTest record);

    LstStudentTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstStudentTest record);

    int updateByPrimaryKey(LstStudentTest record);

    List<LstStudentTest> getStudentTestInfo(Integer id);

    LstStudentTest getAvgScoreInFlAc(LstTestStudentAnswer lsta);

    int getTotal(LstTestStudentAnswer lsta);

    int overNum(Map<String, Object> map);

    List<LstStudentTest> query(Map<String, Object> map);

}