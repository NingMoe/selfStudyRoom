package com.human.ielts.service;

import java.util.Map;

import com.human.ielts.entity.IeltsClassEnroll;

public interface IeltsClassEnrollService {

    /**
     * 班级高分学员分布
     * @param ieltsClassEnroll
     * @return
     */
    public Map<String, Object> selectclasstypecount(IeltsClassEnroll ieltsClassEnroll);

    public Map<String, Object> selectteacherclasstypecount(IeltsClassEnroll ieltsClassEnroll);

}
