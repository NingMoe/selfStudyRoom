package com.human.examineelist.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.basic.entity.DicData;
import com.human.examineelist.entity.ExamineeList;
import com.human.stuexam.entity.StuExam;
import com.human.utils.PageView;

public interface ExamineeListService {
    PageView query(PageView pageView,ExamineeList ex);

    int insertIntoStu(Map<String, Object> map);

    int delete();

    List<ExamineeList> queryData(ExamineeList em);

    void editDicData(String classCode, String code, List<ExamineeList> datas);

    List<ExamineeList> queryByCode(Map<String, Object> code);

    List<ExamineeList> queryforClassCode(String code);

    List<ExamineeList> queryForTime(Map<String, Object> map2);

    List<ExamineeList> queryForGrade(Map<String, Object> map);

    void delete(String deleteIds);

    Map<String, Object> queryinfo(String code);

    List<ExamineeList> queryClassCode(String classCode);

    List<ExamineeList> queryByCodeTotal(Map<String, Object> map);

    String getStudentName(String code);


    
}
