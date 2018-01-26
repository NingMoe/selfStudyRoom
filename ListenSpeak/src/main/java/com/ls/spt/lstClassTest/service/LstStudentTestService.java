package com.ls.spt.lstClassTest.service;

import java.util.List;

import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;

public interface LstStudentTestService {

    List<LstStudentTest> getStudentTestInfo(Integer id);

    int insert(LstStudentTest sTest);

    LstStudentTest selectPrimarykey(LstTestStudentAnswer lsta);

    LstStudentTest getAvgScoreInFlAc(LstTestStudentAnswer lsta);

    int getTotal(LstTestStudentAnswer lsta);

    int overNum(LstTestStudentAnswer lsta);


}
