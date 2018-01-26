package com.human.stuexam.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.stuexam.entity.StuExam;
import com.human.utils.PageView;

public interface StuExamService {
    /**
     * 分页查询续班规则
     */
    PageView query(PageView pageView,StuExam se);

    Map<String, Object> delete(String deleteIds);

    int addData(StuExam se);

    Map<String, Object> update(StuExam se);

    StuExam selectByPrimaryKey(long id);

    List<StuExam> queryInfoByCode(String classCode);

     List<StuExam> lookforTearcher(StuExam se);

    Map<String, Object> addexcle(HttpServletRequest request);

    Map<String, Object> getBeginAndEndTime(String classCode);


    
}
