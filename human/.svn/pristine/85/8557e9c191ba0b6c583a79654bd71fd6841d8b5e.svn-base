package com.human.resume.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumePhotoDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.service.ResumePhotoService;
import com.human.utils.OSSUtil;
@Service
public class ResumePhotoServiceImpl implements ResumePhotoService {

    @Resource
    private  ResumePhotoDao rpDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.resumeseeker.resumePhoto}")
    private String headerPath;

    @Resource 
    private OSSUtil ossUtil;
    
    
    
    @Override
    public List<ResumePhoto> selectByResumeId(Long resumeId) {
        return rpDao.selectByResumeId(resumeId);
    }

    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertResumePhoto(MultipartFile[] files, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存图片简历成功!");
        long resumeId=0L; 
        try{
            boolean originalFlag=ServletRequestUtils.getBooleanParameter(request, "originalFlag");
            Long resumeSeekerId=ServletRequestUtils.getLongParameter(request, "resumeSeekerId");
            boolean fastSign=ServletRequestUtils.getIntParameter(request, "fastFlag")==0?false:true;
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
                resumeId=rb.getId();                 
            }else{
                long paramResumeId=ServletRequestUtils.getLongParameter(request, "resumeId");
                if(paramResumeId!=0){
                    resumeId=paramResumeId;
                }else{
                    ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                    resumeId=orb.getId();
                }
                boolean flag=ServletRequestUtils.getBooleanParameter(request, "flag"); 
                if(flag){//是否有图片简历,如果存在将之前的图片简历逻辑删除
                    List<ResumePhoto> rpList=rpDao.selectByResumeId(resumeId);  
                    for(ResumePhoto resumePhoto:rpList){                       
                        rpDao.updateById(resumePhoto.getId());                        
                    }
                }
            }   
            for (MultipartFile file : files) {
                ResumePhoto rp=new ResumePhoto();
                String originalName = file.getOriginalFilename();
                if (!"".equals(originalName) && originalName != null) {
                    // 上传图片简历
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file);
                    if ((boolean) uploadResult.get("flag")){
                        rp.setPath(newFileName);
                        rp.setName(originalName);
                    }else {
                        map.put("flag", false);
                        map.put("msg", "上传图片简历失败,请稍后重试!");
                        return map;                                
                    }    
                    rp.setResumeId(resumeId);  
                    rp.setFastFlag(fastSign);
                    rpDao.insertSelective(rp);
                }  
            }  
        }catch(Exception e){
          e.printStackTrace();
          map.put("flag", false);
          map.put("msg", "保存图片简历失败!");
        }
        return map;
    }

    @Override
    public ResumePhoto selectByPrimaryKey(Long id) {
        return rpDao.selectByPrimaryKey(id);
    }


    @Override
    public int insertSelective(ResumePhoto record) {
        return rpDao.insertSelective(record);
    }

   
    @Override
    public List<ResumePhoto> selectFastRp(Long resumeId) {
        return rpDao.selectFastRp(resumeId);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> uploadReumsePhoto(MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "上传图片简历成功!请点击页面确认投递按钮完成最终的投递!");
        try{    
            String returnName="";
            for(MultipartFile file : files){
                String originalName = file.getOriginalFilename();
                if (!"".equals(originalName) && originalName != null) {
                    // 上传图片简历
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file);
                    if ((boolean) uploadResult.get("flag")){
                        returnName=newFileName+"-"+originalName+",";
                    }else {
                        map.put("flag", false);
                        map.put("msg", "上传图片简历失败,请稍后重试!");
                        return map;                                
                    }    
                }
            }
            if(StringUtils.isNoneEmpty(returnName)){
                returnName=returnName.substring(0,returnName.length()-1);
                map.put("returnName", returnName);
            }
        }catch(Exception e){
          e.printStackTrace();
          map.put("flag", false);
          map.put("msg", "上传图片简历失败!");
        }
        return map;
    }


}
