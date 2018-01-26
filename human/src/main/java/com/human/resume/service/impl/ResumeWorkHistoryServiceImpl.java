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
import com.human.resume.dao.ResumeWorkHistoryDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.service.ResumeWorkHistoryService;
@Service
public class ResumeWorkHistoryServiceImpl implements ResumeWorkHistoryService {
    
    @Resource
    private  ResumeWorkHistoryDao rwhDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Override
    public List<ResumeWorkHistory> selectByResumeId(Long resumeId) {
        
        return rwhDao.selectByResumeId(resumeId);
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertWorkHistory(HttpServletRequest request, ResumeWorkHistory rwh) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存新增工作经历成功!");
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
               //创建工作经历
               rwh.setResumeId(resumeId);                   
           }else{
               if(rwh.getResumeId()==0){
                   ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                   //创建工作经历
                   rwh.setResumeId(orb.getId());
               }                             
           } 
           if(rwh.getMinSalary()!=null && rwh.getMaxSalary()!=null){
               String salary=rwh.getMinSalary()+"K-"+rwh.getMaxSalary()+"K";
               rwh.setSalary(salary);;
           }
           rwhDao.insertSelective(rwh); 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存新增工作经历失败!");
        }
        return map;
    }

    @Override
    public ResumeWorkHistory selectByPrimaryKey(Long id) {
        return rwhDao.selectByPrimaryKey(id);
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> editWorkHistory(ResumeWorkHistory rwh) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存修改后的工作经历成功!");
        try{
            rwhDao.updateByPrimaryKeySelective(rwh);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "保存修改后的工作经历失败!");
        }
        return map;
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> deleteWorkHistory(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "删除工作经历成功!");
        try{
            rwhDao.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "删除工作经历失败!");
        }
        return map;
    }

}
