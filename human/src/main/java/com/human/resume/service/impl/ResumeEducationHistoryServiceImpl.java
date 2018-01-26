package com.human.resume.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeEducationHistoryDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.service.ResumeEducationHistoryService;
@Service
public class ResumeEducationHistoryServiceImpl implements ResumeEducationHistoryService {

    @Resource
    private  ResumeEducationHistoryDao rehDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    
    @Override
    public List<ResumeEducationHistory> selectByResumeId(Long resumeId) {
       
        return rehDao.selectByResumeId(resumeId);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertEdu(HttpServletRequest request, ResumeEducationHistory reh) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存新增教育经历成功!");
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
               //创建教育经历
               reh.setResumeId(resumeId);                 
           }else{
               if(reh.getResumeId()==0){
                   ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                   //创建教育经历
                   reh.setResumeId(orb.getId()); 
               } 
           }   
           rehDao.insertSelective(reh); 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存新增教育经历失败!");
        }
        return map;
    }


    @Override
    public ResumeEducationHistory selectByPrimaryKey(Long id) {
        return rehDao.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> editEdu(ResumeEducationHistory reh) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存修改后的教育经历成功!");
        try{
            rehDao.updateByPrimaryKeySelective(reh);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "保存新增教育经历失败!");
        }
        return map;
    }
    
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> deleteEdu(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "删除教育经历成功!");
        try{
            rehDao.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "删除教育经历失败!");
        }
        return map;
        
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertFastEdu(ResumeEducationHistory reh) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存新增教育经历成功!");
        try{
           rehDao.insertSelective(reh); 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存新增教育经历失败!");
        }
        return map;
    }
       

    @Override
    public int insertSelective(ResumeEducationHistory record) {
        return rehDao.insertSelective(record);
    }

}
