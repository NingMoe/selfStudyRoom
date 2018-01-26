package com.human.front.service;

import java.util.Map;

import com.human.recruitment.entity.ResumeSeeker;

public interface FrontService {
    Map<String,Object> sendBindMsg(String telephone);
    
    ResumeSeeker getSeekerBaseByNameAndPhone(String name,String phone);
    
    void bindOpenId(ResumeSeeker seeker);
    
    boolean isPositionHasTd(String openId,Integer positionId);
}
