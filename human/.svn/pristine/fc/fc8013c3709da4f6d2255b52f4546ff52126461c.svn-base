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
import com.human.resume.dao.ResumeProjectExperienceDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.service.ResumeProjectExperienceService;
@Service
public class ResumeProjectExperienceServiceImpl implements ResumeProjectExperienceService {
    
    @Resource
    private  ResumeProjectExperienceDao rpeDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Override
    public List<ResumeProjectExperience> selectByResumeId(Long resumeId) {
        return rpeDao.selectByResumeId(resumeId);
    }

    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertProjectExperience(HttpServletRequest request, ResumeProjectExperience rpe) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存新增项目经验成功!");
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
               //创建项目经验
               rpe.setResumeId(resumeId);                                
           }else{
               if(rpe.getResumeId()==0){
                   ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                   //创建项目经验
                   rpe.setResumeId(orb.getId()); 
               }               
           } 
           rpeDao.insertSelective(rpe); 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存新增项目经验失败!");
        }
        return map;
    }

    @Override
    public ResumeProjectExperience selectByPrimaryKey(Long id) {
        return rpeDao.selectByPrimaryKey(id);
    }

    
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> editProjectExperience(ResumeProjectExperience rpe) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存修改后的项目经验成功!");
        try{
            rpeDao.updateByPrimaryKeySelective(rpe);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "保存修改后的项目经验失败!");
        }
        return map;
    }

    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> deleteProjectExperience(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "删除项目经验成功!");
        try{
            rpeDao.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "删除项目经验失败!");
        }
        return map;
    }

}
