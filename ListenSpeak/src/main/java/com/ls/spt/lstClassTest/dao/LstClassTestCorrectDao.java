package com.ls.spt.lstClassTest.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstClassTest.entity.LstClassTestCorrect;

public interface LstClassTestCorrectDao {

    List<LstClassTestCorrect> query(Map<String, Object> map);

    LstClassTestCorrect   getScore(Map<String, Object> map);

    int getTatalNum(Map<String, Object> map);

    int getTiNum(Map<String, Object> map);

    List<String> getClassCode(Map<String, Object> map);

    List<LstClassTestCorrect> queryCorrect(Map<String, Object> map);
    
}