package com.human.resume.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeIntentionDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.service.ResumeIntentionService;
@Service
public class ResumeIntentionServiceImpl implements ResumeIntentionService {

    @Resource
    private ResumeIntentionDao resumeIntentionDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    
    @Override
    public ResumeIntention selectByResumeId(Long resumeId) {
       
        return resumeIntentionDao.selectByResumeId(resumeId);
    }


    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> updateInterntion(HttpServletRequest request, ResumeIntention ri) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存求职意向成功!");
        try{
           boolean originalFlag=ServletRequestUtils.getBooleanParameter(request, "originalFlag");
           Long resumeSeekerId=ServletRequestUtils.getLongParameter(request, "resumeSeekerId");
           if(!originalFlag){//没有我的简历
               //创建我的简历               
               ResumeSeeker rs = recruitAcceptanceDao.selectByPrimaryKey(resumeSeekerId);
               ResumeBase rb=new ResumeBase();
               rb.setResumeSeekerId(resumeSeekerId);
               rb.setName(rs.getName());
               rb.setTelephone(rs.getPhone());
               rb.setOriginalFlag("1");
               rb.setSex(rs.getSex());
               rb.setEmail(rs.getEmail());
               rb.setLocationCity(rs.getLocationCity());
               rb.setHeadUrl(rs.getHeadUrl());
               resumeBaseDao.insertSelective(rb);
               long resumeId=rb.getId();
               //创建求职意向
               ri.setResumeId(resumeId);
               String expectWorkSalary=ri.getMinSalary()+"K-"+ri.getMaxSalary()+"K";
               ri.setExpectWorkSalary(expectWorkSalary);
               resumeIntentionDao.insertSelective(ri);                 
           }else{
               boolean flag=ServletRequestUtils.getBooleanParameter(request, "flag"); 
               if(flag){
                   String expectWorkSalary=ri.getMinSalary()+"K-"+ri.getMaxSalary()+"K";
                   ri.setExpectWorkSalary(expectWorkSalary);
                   resumeIntentionDao.updateByPrimaryKeySelective(ri);
               }else{
                   if(ri.getResumeId()==0){
                       ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                       //创建求职意向
                       ri.setResumeId(orb.getId()); 
                   }
                   String expectWorkSalary=ri.getMinSalary()+"K-"+ri.getMaxSalary()+"K";
                   ri.setExpectWorkSalary(expectWorkSalary);
                   resumeIntentionDao.insertSelective(ri);  
               }
           }   
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存求职意向失败!");
        }
        return map;
    }

}
