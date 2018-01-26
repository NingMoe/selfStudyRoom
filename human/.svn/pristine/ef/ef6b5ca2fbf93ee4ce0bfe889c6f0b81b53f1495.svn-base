package com.human.resume.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeLanguageDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.service.ResumeLanguageService;
import com.human.utils.OSSUtil;
@Service
public class ResumeLanguageServiceImpl implements ResumeLanguageService {
    
    @Resource
    private  ResumeLanguageDao rlDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.resumeseeker.languagePhoto}")
    private String headerPath;

    @Resource 
    private OSSUtil ossUtil;
    
    
    @Override
    public List<ResumeLanguage> selectByResumeId(Long resumeId) {
        return rlDao.selectByResumeId(resumeId);
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> insertLanguage(HttpServletRequest request, ResumeLanguage rl) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存新增语言能力成功!");
        try{
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    String fileName ="languagePhoto";
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    if (null != multiFile && multiFile.getSize()>0) {
                        String originalName = multiFile.getOriginalFilename();
                        if (!"".equals(originalName) && originalName != null) {
                            // 上传成绩证书
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")){
                                rl.setPhoto(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "上传成绩证书失败,请稍后重试!");
                                return map;                                
                            }    
                               
                        }
                    } 
           }           
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
               //创建语言能力
               rl.setResumeId(resumeId);                  
           }else{
               if(rl.getResumeId()==0){
                   ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                   //创建语言能力
                   rl.setResumeId(orb.getId());
               }               
           } 
           rlDao.insertSelective(rl); 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存新增语言能力失败!");
        }
        return map;
    }

    @Override
    public ResumeLanguage selectByPrimaryKey(Long id) {
        return rlDao.selectByPrimaryKey(id);
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> editLanguage(HttpServletRequest request,ResumeLanguage rl) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存编辑后的语言能力成功!");
        try{
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    String fileName ="languagePhoto";
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    if (null != multiFile && multiFile.getSize()>0) {
                        String originalName = multiFile.getOriginalFilename();
                        if (!"".equals(originalName) && originalName != null) {
                            // 上传成绩证书
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")){
                                rl.setPhoto(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "头像上传失败，请稍后重试!");
                                return map;                                
                            }    
                               
                        }
                    } 
           }  
          rlDao.updateByPrimaryKeySelective(rl);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "保存编辑后的语言能力失败!");
        }
        return map;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> deleteLanguage(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "删除语言能力成功!");
        try{
            rlDao.deleteByPrimaryKey(id);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "删除语言能力失败!");
        }
        return map;
    }

}
