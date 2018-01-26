package com.human.resume.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.human.basic.dao.CollegeDao;
import com.human.basic.entity.College;
import com.human.bpm.entity.ActCustomPhoto;
import com.human.bpm.service.HrWorkflowService;
import com.human.front.entity.FastDeliveryEditBase;
import com.human.front.entity.FastDeliveryEditBase.EducationHistory;
import com.human.front.entity.ResumeDeliveryEditBase;
import com.human.front.entity.ResumeDeliveryEditBase.ProjectExper;
import com.human.front.entity.ResumeDeliveryEditBase.WorkHistory;
import com.human.manager.dao.UserDeptDao;
import com.human.manager.entity.HrCompany;
import com.human.recruitment.dao.HrPositionDao;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.PositionProcessDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.dao.ResumeHrRemarkDao;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.NoAccDegree;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.ResumeHrRemark;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeAttachmentDao;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeCertificateDao;
import com.human.resume.dao.ResumeEducationHistoryDao;
import com.human.resume.dao.ResumeHighlightsDao;
import com.human.resume.dao.ResumeIntentionDao;
import com.human.resume.dao.ResumeLanguageDao;
import com.human.resume.dao.ResumeMajorSkillDao;
import com.human.resume.dao.ResumePhotoDao;
import com.human.resume.dao.ResumePracticeExperienceDao;
import com.human.resume.dao.ResumePrizeDao;
import com.human.resume.dao.ResumeProjectExperienceDao;
import com.human.resume.dao.ResumeSchoolPostDao;
import com.human.resume.dao.ResumeSnapshotDao;
import com.human.resume.dao.ResumeTrainHistoryDao;
import com.human.resume.dao.ResumeWorkHistoryDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.service.ResumeService;
import com.human.utils.Common;
import com.human.utils.Constants;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
@Service
public class ResumeServiceImpl implements ResumeService {
            
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Resource
    private ResumeWorkHistoryDao resumeWorkHistoryDao;
    
    @Resource
    private ResumeTrainHistoryDao resumeTrainHistoryDao;
    
    @Resource
    private ResumeSnapshotDao resumeSnapshotDao;
    
    @Resource
    private ResumeSchoolPostDao resumeSchoolPostDao;
    
    @Resource
    private ResumeProjectExperienceDao resumeProjectExperienceDao;
    
    @Resource
    private  ResumePrizeDao resumePrizeDao;
    
    @Resource    
    private  ResumePracticeExperienceDao resumePracticeExperienceDao;
    
    @Resource
    private ResumePhotoDao resumePhotoDao;
    
    @Resource
    private ResumeMajorSkillDao resumeMajorSkillDao;
    
    @Resource
    private ResumeLanguageDao resumeLanguageDao;
    
    @Resource
    private ResumeIntentionDao resumeIntentionDao;
    
    @Resource
    private ResumeHighlightsDao resumeHighlightsDao;
    
    @Resource
    private ResumeEducationHistoryDao resumeEducationHistoryDao;
    
    @Resource
    private  ResumeCertificateDao resumeCertificateDao;
    
    @Resource
    private ResumeAttachmentDao resumeAttachmentDao;
    
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private HrResumeFlowDao flowDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeHrRemarkDao hrRemarkDao;
    
    @Resource
    private CollegeDao collegeDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.resumeseeker.header}")
    private String headerPath;

    @Resource 
    private OSSUtil ossUtil;
       
    @Resource
    private HrPositionDao  hpDao;
    
    @Resource
    private PositionProcessDao processDao;
    
    @Resource
    private HrWorkflowService flowService;

    @Override
    public PageView query(PageView pageView, ResumeBase resumeBase) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptDao.getUserCompany(Common.getMyUser().getUserid());
        List<String>companyIds=new ArrayList<String>();
        if(null!=companyList&&companyList.size()>0){
            for(HrCompany hc:companyList){
                companyIds.add(hc.getCompanyId()) ;
            }
        }
        map.put("paging", pageView);
        map.put("t", resumeBase);
        if(companyIds!=null && companyIds.size()>0){
            map.put("s", companyIds);  
        }
        List<ResumeBase> list=resumeBaseDao.query(map);
        pageView.setRecords(list);
        return pageView;
        
    }


    @Override
    public ResumeBase selectByPrimaryKey(Long id) {
       
        return resumeBaseDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(ResumeBase record) {
        return resumeBaseDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public HrResumeFlow selectHrFlowByResumeId(Integer resumeId) {
        return flowDao.selectHrfByResumeId(resumeId);
    }


    @Override
    public ResumeBase queryResumeDetail(Long id) {
        return resumeBaseDao.queryResumeDetail(id);
    }


    @Override
    public ResumeBase selectByResumeSeekerId(Long resumeSeekerId) {
        return resumeBaseDao.selectByResumeSeekerId(resumeSeekerId);
    }


    @Override
    public ResumeBase selectOriginalResumeByRsId(Long resumeSeekerId) {
        return resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public synchronized void copyResume(Long resumeSeekerId) {
        try{
            //第一步 :通过求职者ID去查询我的简历
            ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb==null){
              //第二步 :通过求职者ID去查询最新的简历
                ResumeBase rb=resumeBaseDao.selectByResumeSeekerId(resumeSeekerId);
                if(rb!=null){
                    Long resumeId=rb.getId();
                    //第三步:复制一份简历为我的简历
                    rb.setId(null);
                    rb.setOriginalFlag("1");
                    rb.setSource(null);
                    rb.setAnalysisMailId(null);
                    rb.setResumeLink(null);
                    rb.setFlowStatus(null);
                    rb.setMatchingPosition(null);
                    rb.setApplyPosition(null);
                    rb.setInsideRecommend(null);
                    rb.setInsideRelation(null);
                    resumeBaseDao.insertSelective(rb);
                    long originalResumeId=rb.getId();
                    /*
                     * 复制最新额简历的其他部分为我的简历其他部分
                     */
                    copy(resumeId,originalResumeId);
                }
            } 
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> updateInsideRecommend(HttpServletRequest request, ResumeBase rb) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "保存内部推荐成功!");
        try{
           boolean originalFlag=ServletRequestUtils.getBooleanParameter(request, "originalFlag");
           Long resumeSeekerId=ServletRequestUtils.getLongParameter(request, "resumeSeekerId");
           if(!originalFlag){//没有我的简历
               //创建我的简历               
               ResumeSeeker rs = recruitAcceptanceDao.selectByPrimaryKey(resumeSeekerId);
               rb.setResumeSeekerId(resumeSeekerId);
               rb.setName(rs.getName());
               rb.setTelephone(rs.getPhone());
               rb.setOriginalFlag("1");
               rb.setSex(rs.getSex());
               rb.setEmail(rs.getEmail());
               rb.setLocationCity(rs.getLocationCity());
               rb.setHeadUrl(rs.getHeadUrl());
               resumeBaseDao.insertSelective(rb);              
           }else{
               if(rb.getId()!=0){
                   resumeBaseDao.updateByPrimaryKeySelective(rb); 
               }else{
                   ResumeBase orb=resumeBaseDao.selectOriginalResumeByRsId(resumeSeekerId);
                   orb.setInsideRecommend(rb.getInsideRecommend());
                   orb.setInsideRelation(rb.getInsideRelation());
                   resumeBaseDao.updateByPrimaryKeySelective(orb);
               }            
           }   
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "保存内部推荐失败!");
        }
        return map;
    }

    /**
     * 快速投递简历
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> finishDelivery(String jstr ,String resumePhotoPathName,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "快速投递简历已成功,请静候佳音!");
        try{
            FastDeliveryEditBase eb=JSONObject.parseObject(jstr, FastDeliveryEditBase.class);
            /*
             * 保存简历个人基本信息 
             */
            ResumeBase rb=new ResumeBase();        
            rb.setSource("微信投递");
            rb.setOriginalFlag("2");
            rb.setSaveFlag("1"); 
            rb.setName(eb.getName());
            rb.setTelephone(eb.getTelephone());
            rb.setSex(eb.getSex());
            rb.setBirthDate(eb.getBirthDate());
            rb.setMarriage(eb.getMarriage());
            rb.setLocationCity(eb.getLocationCity());
            rb.setEmail(eb.getEmail());
            rb.setResumeSeekerId(eb.getResumeSeekerId());
            rb.setMatchingPosition(eb.getMatchingPosition());
            //判断用户是否上传了图像                        
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    String fileName ="photo";
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    if(null != multiFile && multiFile.getSize()>0){
                        String originalName = multiFile.getOriginalFilename();
                        if (!"".equals(originalName) && originalName != null) {
                            // 上传简历个人图像
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")){
                                rb.setHeadUrl(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "上传简历个人图像失败!");
                                return map;                                
                            }    
                               
                        }
                    }else{
                        //没有上传图像再去看看我的简历中是否有图像
                        boolean hasHeadPhotoFlag=eb.isHasHeadPhotoFlag();
                        if(hasHeadPhotoFlag){
                            rb.setHeadUrl(eb.getHeadUrl()); 
                        }
                    } 
           } 
           //通过职位ID查询职位信息
           HrPosition hp= hpDao.selectByPrimaryKey(Integer.valueOf(eb.getMatchingPosition()));
           rb.setApplyPosition(hp.getName());
           rb.setHrCompanyId(hp.getComid());
           //查看是否是校招活动
           String schoolRecruitmentId=(String) request.getSession().getAttribute(Constants.XIAOZHAOID);
           if(StringUtils.isNotEmpty(schoolRecruitmentId)){
               rb.setSchoolRecruitmentId(Long.valueOf(schoolRecruitmentId));
           }
           //查看是否是内部推荐
           String insideRecommend=(String) request.getSession().getAttribute(Constants.NEITUIREN);
           if(StringUtils.isNotEmpty(insideRecommend)){
               rb.setInsideRecommend(insideRecommend);
           }           
           resumeBaseDao.insertSelective(rb);           
           /*
            * 保存教育经历信息 
            */
           String patten="yyyy-MM-dd";
           if(null!=eb.getResumeEduList()){
               for(EducationHistory re:eb.getResumeEduList()){
                   Date starTime=null;
                   if(null!=re.getStartTime()&&re.getStartTime().trim().length()>0){
                       if(!re.getStartTime().equals("至今")){
                           starTime=TimeUtil.getDateByTime(re.getStartTime(),patten);
                       }
                   }
                   Date endTime=null;
                   if(null!=re.getEndTime()&&re.getEndTime().trim().length()>0){
                       if(!re.getEndTime().equals("至今")){
                           endTime=TimeUtil.getDateByTime(re.getEndTime(),patten);
                       }
                   }
                   ResumeEducationHistory reh=new ResumeEducationHistory(null, rb.getId(), starTime, endTime, re.getSchoolName(), null, null, re.getEducation(), re.getMajor(), null, null);
                   resumeEducationHistoryDao.insertSelective(reh);
               }
           }
           
           /*
            * 保存图片简历
            */
           if(StringUtils.isNotEmpty(resumePhotoPathName)){
               String[]photos=resumePhotoPathName.split(",");
               for(String photo:photos){
                   String[]nameAndPath=photo.split("-");
                   ResumePhoto rp=new ResumePhoto();
                   rp.setPath(nameAndPath[0].trim());
                   rp.setName(nameAndPath[1].trim());
                   rp.setResumeId(rb.getId());
                   rp.setFastFlag(true);
                   resumePhotoDao.insertSelective(rp);
               }
           }else{
               ResumeBase originRb = selectOriginalResumeByRsId(eb.getResumeSeekerId()); 
               if(originRb!=null){
                   List<ResumePhoto> list=resumePhotoDao.selectByResumeId(originRb.getId());
                   for(ResumePhoto rp:list){
                       rp.setResumeId(rb.getId());
                       rp.setFastFlag(true);
                       rp.setId(null);
                       resumePhotoDao.insertSelective(rp);
                   }
               }
              
               
           }
           /*
            * 进行最终的投递
            */
           rb.setSaveFlag("0");
           /*
            * 设置简历的最高学历以及投递时间
            */
           setHighEduAndTime(rb);
           /*
            * 更新求职者信息
            */
           ResumeSeeker rs = recruitAcceptanceDao.selectByPrimaryKey(rb.getResumeSeekerId());
           if(rs!=null){
               rs.setBirthDate(rb.getBirthDate());
               rs.setEmail(rb.getEmail());
               rs.setDeliveryDate(rb.getDeliveryDate());
               rs.setSex(rb.getSex());
               rs.setMajor(rb.getMajor());
               rs.setHighEdu(rb.getHighEdu());
               rs.setGraduationDate(rb.getGraduationDate());
               rs.setGraSchool(rb.getGraSchool());
               rs.setLocationCity(rb.getLocationCity());
               rs.setMarriage(rb.getMarriage());
               recruitAcceptanceDao.updateByPrimaryKeySelective(rs);
           }
           /*
            * 通过职位的学历要求设置简历是否淘汰
            */
           boolean isEliminateFlag=setReusmeFlowStatus(rb);
           if(isEliminateFlag){
               rb.setFlowStatus("2");//直接淘汰
               resumeBaseDao.updateByPrimaryKeySelective(rb);
           }else{
               //判断当前求职者是否有正在进行的面试流程
               ResumeBase hasFlowRb=resumeBaseDao.selectRbByReSeekId(rb);
               boolean hasFlowFlag=false;
               if(hasFlowRb!=null){//有
                   rb.setFlowFlag("2");
                   hasFlowFlag=true;
               }
               resumeBaseDao.updateByPrimaryKeySelective(rb);
               /*
                * 根据匹配的职位流程 判断是否自动提交 进行相应操作
                */
               String matchingPosition=rb.getMatchingPosition();
               if(StringUtils.isNotEmpty(matchingPosition) && !"0".equals(matchingPosition)&& !hasFlowFlag){
                   HrPosition p = hpDao.selectByPrimaryKey(Integer.valueOf(matchingPosition));
                   //如果所匹配的岗位   配置了自动提交
                   if(p.getIsAotusub()==0){
                       PositionProcess process = new PositionProcess();
                       process.setPositionId(Integer.valueOf(matchingPosition));
                       process.setType(0);
                       process = processDao.getPositionProcessByPrimaryCondition(process);
                       //如果该岗位已配置标准流程，则自动提交
                       if(process!=null){
                           InterviewSchedule interviewSchedule = new InterviewSchedule();
                           interviewSchedule.setPositionProcessId(process.getId());
                           interviewSchedule.setResumeId(rb.getId()+"");
                           flowService.startProcess(interviewSchedule);
                       }
                   }
               }                       
           } 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "快速投递简历失败！"); 
        }
        return map;
    }

    /**
     * 简历投递
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> finishDelivery(String jstr,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "简历投递已成功,请静候佳音!");
        try{
            ResumeDeliveryEditBase eb=JSONObject.parseObject(jstr, ResumeDeliveryEditBase.class);
            /*
             * 保存简历个人基本信息及内部推荐
             */
            ResumeBase rb=new ResumeBase();        
            rb.setSource("微信投递");
            rb.setOriginalFlag("2");
            rb.setSaveFlag("1"); 
            rb.setName(eb.getName());
            rb.setTelephone(eb.getTelephone());
            rb.setSex(eb.getSex());
            rb.setBirthDate(eb.getBirthDate());
            rb.setMarriage(eb.getMarriage());
            rb.setLocationCity(eb.getLocationCity());
            rb.setEmail(eb.getEmail());
            rb.setResumeSeekerId(eb.getResumeSeekerId());
            rb.setMatchingPosition(eb.getMatchingPosition());
            rb.setInsideRecommend(eb.getInsideRecommend());
            rb.setInsideRelation(eb.getInsideRelation());
            //判断用户是否上传了图像                        
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)){
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    String fileName ="photo";
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    if(null != multiFile && multiFile.getSize()>0){
                        String originalName = multiFile.getOriginalFilename();
                        if (!"".equals(originalName) && originalName != null) {
                            // 上传简历个人图像
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")){
                                rb.setHeadUrl(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "上传简历个人图像失败!");
                                return map;                                
                            }    
                               
                        }
                    }else{
                        //没有上传图像再去看看我的简历中是否有图像
                        boolean hasHeadPhotoFlag=eb.isHasHeadPhotoFlag();
                        if(hasHeadPhotoFlag){
                            rb.setHeadUrl(eb.getHeadUrl()); 
                        }
                    } 
           } 
            //通过职位ID查询职位信息
            HrPosition hp= hpDao.selectByPrimaryKey(Integer.valueOf(eb.getMatchingPosition()));
            rb.setApplyPosition(hp.getName());
            rb.setHrCompanyId(hp.getComid());
            //查看是否是校招活动
            String schoolRecruitmentId=(String) request.getSession().getAttribute(Constants.XIAOZHAOID);
            if(StringUtils.isNotEmpty(schoolRecruitmentId)){
                rb.setSchoolRecruitmentId(Long.valueOf(schoolRecruitmentId));
            }     
            resumeBaseDao.insertSelective(rb); 
            /*
             * 保存求职意向信息 
             */
            if(null!=eb.getResumeInterntion()){
                ResumeIntention ri=eb.getResumeInterntion();
                String expectWorkSalary="";
                if(ri.getMinSalary()!=null && ri.getMaxSalary()!=null){
                    expectWorkSalary=ri.getMinSalary()+"K-"+ri.getMaxSalary()+"K";
                }
                ri.setExpectWorkSalary(expectWorkSalary);
                ri.setResumeId(rb.getId());
                resumeIntentionDao.insertSelective(ri);
                
            }
            /*
             * 保存教育经历信息 
             */
            String patten="yyyy-MM-dd";
            if(null!=eb.getResumeEduList()){
                for(com.human.front.entity.ResumeDeliveryEditBase.EducationHistory re:eb.getResumeEduList()){
                    Date starTime=null;
                    if(null!=re.getStartTime()&&re.getStartTime().trim().length()>0){
                        if(!re.getStartTime().equals("至今")){
                            starTime=TimeUtil.getDateByTime(re.getStartTime(),patten);
                        }
                    }
                    Date endTime=null;
                    if(null!=re.getEndTime()&&re.getEndTime().trim().length()>0){
                        if(!re.getEndTime().equals("至今")){
                            endTime=TimeUtil.getDateByTime(re.getEndTime(),patten);
                        }
                    }
                    ResumeEducationHistory reh=new ResumeEducationHistory(null, rb.getId(), starTime, endTime, re.getSchoolName(), null, null, re.getEducation(), re.getMajor(), null, null);
                    resumeEducationHistoryDao.insertSelective(reh);
                }
            }
            /*
             * 保存工作经历信息 
             */
            if(null!=eb.getResumeWorkList()){
                for(WorkHistory wh:eb.getResumeWorkList()){
                    Date starTime=null;
                    if(null!=wh.getStartTime()&&wh.getStartTime().trim().length()>0){
                        if(!wh.getStartTime().equals("至今")){
                            starTime=TimeUtil.getDateByTime(wh.getStartTime(),patten);
                        }
                    }
                    Date endTime=null;
                    if(null!=wh.getEndTime()&&wh.getEndTime().trim().length()>0){
                        if(!wh.getEndTime().equals("至今")){
                            endTime=TimeUtil.getDateByTime(wh.getEndTime(),patten);
                        }
                    }
                    String salary="";
                    if(wh.getMinSalary()!=null && wh.getMaxSalary()!=null){
                        salary=wh.getMinSalary()+"K-"+wh.getMaxSalary()+"K";
                    }
                    ResumeWorkHistory rwh=new ResumeWorkHistory(null, rb.getId(),starTime, endTime, wh.getCompanyName(), null, null, null, null, wh.getPosition(),salary, wh.getDescribes(), null);
                    rwh.setMinSalary(wh.getMinSalary());
                    rwh.setMaxSalary(wh.getMaxSalary());
                    rwh.setWorkType(wh.getWorkType());
                    resumeWorkHistoryDao.insertSelective(rwh);                    
                }
            }
            /*
             * 保存项目经验信息 
             */
            if(null!=eb.getProjectList()){
                for(ProjectExper rex:eb.getProjectList()){
                    Date starTime=null;
                    if(null!=rex.getStartTime()&& rex.getStartTime().trim().length()>0){
                        if(!rex.getStartTime().equals("至今")){
                            starTime=TimeUtil.getDateByTime(rex.getStartTime(),patten);
                        }
                    }
                    Date endTime=null;
                    if(null!=rex.getEndTime()&& rex.getEndTime().trim().length()>0){
                        if(!rex.getEndTime().equals("至今")){
                            endTime=TimeUtil.getDateByTime(rex.getEndTime(),patten);
                        }
                    }
                    ResumeProjectExperience rpe=new ResumeProjectExperience(null, rb.getId(), starTime, endTime,rex.getProjectName() , rex.getResponsibilityDescribe(), rex.getProjectDescribe(), rex.getCompanyName());
                    resumeProjectExperienceDao.insertSelective(rpe);
                }
            }            
            /*
             * 保存语言能力信息 
             */
            if(null!=eb.getLanguageList()){
                for(ResumeLanguage rl:eb.getLanguageList()){
                    rl.setResumeId(rb.getId());
                    rl.setId(null);
                    resumeLanguageDao.insertSelective(rl);
                }
            }
            /*
             * 进行最终的投递
             */
            rb.setSaveFlag("0");
            /*
             * 设置简历的最高学历以及投递时间
             */
            setHighEduAndTime(rb);
            /*
             * 更新求职者信息
             */
            ResumeSeeker rs = recruitAcceptanceDao.selectByPrimaryKey(rb.getResumeSeekerId());
            if(rs!=null){
                rs.setBirthDate(rb.getBirthDate());
                rs.setEmail(rb.getEmail());
                rs.setDeliveryDate(rb.getDeliveryDate());
                rs.setSex(rb.getSex());
                rs.setMajor(rb.getMajor());
                rs.setHighEdu(rb.getHighEdu());
                rs.setGraduationDate(rb.getGraduationDate());
                rs.setGraSchool(rb.getGraSchool());
                rs.setLocationCity(rb.getLocationCity());
                rs.setMarriage(rb.getMarriage());
                recruitAcceptanceDao.updateByPrimaryKeySelective(rs);
            }
            /*
             * 通过职位的学历要求设置简历是否淘汰
             */
            boolean isEliminateFlag=setReusmeFlowStatus(rb);
            if(isEliminateFlag){
                rb.setFlowStatus("2");//直接淘汰
                resumeBaseDao.updateByPrimaryKeySelective(rb);
            }else{
                //判断当前求职者是否有正在进行的面试流程
                ResumeBase hasFlowRb=resumeBaseDao.selectRbByReSeekId(rb);
                boolean hasFlowFlag=false;
                if(hasFlowRb!=null){//有
                    rb.setFlowFlag("2");
                    hasFlowFlag=true;
                }
                resumeBaseDao.updateByPrimaryKeySelective(rb);                
                /*
                 * 根据匹配的职位流程 判断是否自动提交 进行相应操作
                 */
                String matchingPosition=rb.getMatchingPosition();
                if(StringUtils.isNotEmpty(matchingPosition) && !"0".equals(matchingPosition)&& !hasFlowFlag){
                    HrPosition p = hpDao.selectByPrimaryKey(Integer.valueOf(matchingPosition));
                    //如果所匹配的岗位   配置了自动提交
                    if(p.getIsAotusub()==0){
                        PositionProcess process = new PositionProcess();
                        process.setPositionId(Integer.valueOf(matchingPosition));
                        process.setType(0);
                        process = processDao.getPositionProcessByPrimaryCondition(process);
                        //如果该岗位已配置标准流程，则自动提交
                        if(process!=null){
                            InterviewSchedule interviewSchedule = new InterviewSchedule();
                            interviewSchedule.setPositionProcessId(process.getId());
                            interviewSchedule.setResumeId(rb.getId()+"");
                            flowService.startProcess(interviewSchedule);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace(); 
            map.put("flag", false);
            map.put("msg", "简历投递失败！"); 
        }
        return map;
    }
    
    /**
     * 复制简历其他部分公用方法
     * @param myResumeId
     * @param newResumeId
     */
    public void copy(long myResumeId,long newResumeId ){
        //复制求职意向
        ResumeIntention ri=resumeIntentionDao.selectByResumeId(myResumeId);
        if(ri!=null){
            ri.setResumeId(newResumeId);
            ri.setId(null);
            resumeIntentionDao.insertSelective(ri);
        }                   
        //复制教育经历      
        List<ResumeEducationHistory>resumeEducationHistoryList=resumeEducationHistoryDao.selectByResumeId(myResumeId);
        if(resumeEducationHistoryList!=null && resumeEducationHistoryList.size()>0 ){
            for(ResumeEducationHistory res:resumeEducationHistoryList){
                res.setResumeId(newResumeId);
                res.setId(null);
                resumeEducationHistoryDao.insertSelective(res);
            }
        } 
        //复制语言能力
        List<ResumeLanguage> resumeLanguageList=resumeLanguageDao.selectByResumeId(myResumeId);
        if(resumeLanguageList!=null && resumeLanguageList.size()>0 ){
            for(ResumeLanguage rl:resumeLanguageList){
                rl.setResumeId(newResumeId);
                rl.setId(null);
                resumeLanguageDao.insertSelective(rl);
            }
        }  
        //复制工作经历
        List<ResumeWorkHistory>resumeWorkHistoryList=resumeWorkHistoryDao.selectByResumeId(myResumeId);
        if(resumeWorkHistoryList!=null && resumeWorkHistoryList.size()>0 ){
            for(ResumeWorkHistory rwh:resumeWorkHistoryList){
                rwh.setResumeId(newResumeId);
                rwh.setId(null);
                resumeWorkHistoryDao.insertSelective(rwh);
            }
        }
        //复制项目经验
        List<ResumeProjectExperience> resumeProjectExperienceList=resumeProjectExperienceDao.selectByResumeId(myResumeId);
        if(resumeProjectExperienceList!=null && resumeProjectExperienceList.size()>0 ){
            for(ResumeProjectExperience rpe:resumeProjectExperienceList){
                rpe.setResumeId(newResumeId);
                rpe.setId(null);
                resumeProjectExperienceDao.insertSelective(rpe);
            }
        }
        //复制图片简历
        List<ResumePhoto> resumePhotoList=resumePhotoDao.selectByResumeId(myResumeId);
        if(resumePhotoList!=null && resumePhotoList.size()>0 ){
            for(ResumePhoto rpe:resumePhotoList){
                rpe.setResumeId(newResumeId);
                rpe.setId(null);
                resumePhotoDao.insertSelective(rpe);
            }
        } 
    }
    
   /**
    * 设置简历的最高学历以及投递时间公用方法
    * @param rb
    */
    public void setHighEduAndTime(ResumeBase rb){
        List<ResumeEducationHistory>resumeEducationHistoryList=resumeEducationHistoryDao.selectByResumeId(rb.getId());
        if(resumeEducationHistoryList!=null && resumeEducationHistoryList.size()>1){
            resumeEducationHistoryList.sort(new Comparator<ResumeEducationHistory>() {
                @Override
                public int compare(ResumeEducationHistory o1, ResumeEducationHistory o2) {
                    Date endTime1=o1.getEndTime();
                    if(endTime1==null){
                        endTime1=new Date();
                    }
                    Date endTime2=o2.getEndTime();
                    if(endTime2==null){
                        endTime2=new Date();
                    }
                    return endTime1.before(endTime2)?1:-1;
                } 
            });
        }
        if(resumeEducationHistoryList!=null && resumeEducationHistoryList.size()>0 ){
            //查询学校是否为985或211
            for(ResumeEducationHistory reh:resumeEducationHistoryList){
                College college=collegeDao.selectByName(reh.getSchoolName());
                if(college!=null){
                    int Is985=college.getIs985();
                    int Is211=college.getIs211();
                    if(Is985==1 && Is211==1){
                        reh.setSchoolName(reh.getSchoolName()+"(985,211)");
                    }else if(Is985==1 && reh.getSchoolName().indexOf("985")==-1){
                        reh.setSchoolName(reh.getSchoolName()+"(985)");
                    }else if(Is211==1 && reh.getSchoolName().indexOf("211")==-1){
                        reh.setSchoolName(reh.getSchoolName()+"(211)");
                    }                                
                }
            }
            ResumeEducationHistory res=resumeEducationHistoryList.get(0);           
            rb.setHighEdu(res.getEducation().trim().replaceAll("[　*| *| *|\\s*]*", ""));           
            rb.setMajor(res.getMajor().trim().replaceAll("[　*| *| *|\\s*]*", ""));            
            rb.setGraSchool(res.getSchoolName());
            if(res.getEndTime()!=null){
                rb.setGraduationDate(TimeUtil.date2String(res.getEndTime(),"yyyy-MM"));  
            }                    
        }
        //设置投递时间
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String deliveryDate = outFormat.format(now);
        rb.setDeliveryDate(deliveryDate);
    }
    
    @Override
    @Transactional
    public void addResume(ResumeSeeker rs) {
        List<ResumeSeeker> rsList = recruitAcceptanceDao.queryResumeSeeker(rs);
        ResumeBase rb = new ResumeBase();
        if(rsList.size()==0){
            recruitAcceptanceDao.insert(rs);
            rb.setResumeSeekerId(rs.getId());
            rb.setName(rs.getName());
            rb.setTelephone(rs.getPhone());
            rb.setHrCompanyId(Common.getMyUser().getCompanyId());
            rb.setSource("系统人工新增");
            rb.setDeliveryDate(TimeUtil.getStringByTimestamp(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"));
            resumeBaseDao.insertSelective(rb);
        }else{
            rb.setHrCompanyId(Common.getMyUser().getCompanyId());
            rb.setResumeSeekerId(rsList.get(0).getId());
            ResumeBase rb1 = resumeBaseDao.selectRbByReSeekId(rb);
            if(rb1!=null){
                rb.setFlowFlag("2");
            }
            rb.setName(rs.getName());
            rb.setTelephone(rs.getPhone());
            rb.setSource("系统人工新增");
            rb.setDeliveryDate(TimeUtil.getStringByTimestamp(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"));
            resumeBaseDao.insertSelective(rb);
        }
    }
    
    @Override
    public void addHrContent(ResumeHrRemark rhr) {
        rhr.setCreateTime(new Date());
        rhr.setCreateUser(Common.getMyUser().getEmailAddr());
        hrRemarkDao.insert(rhr);
        ResumeBase rb = new ResumeBase();
        rb.setId(Long.valueOf(rhr.getResumeId()));
        rb.setLastComment(rhr.getContent());
        resumeBaseDao.updateByPrimaryKeySelective(rb);
    }
    
    @Override
    public List<ResumeHrRemark> getCommentByResumeId(Integer resumeId) {
        return hrRemarkDao.selectByResumeId(resumeId);
    }
    
    
    public boolean setReusmeFlowStatus(ResumeBase rb){
        boolean flag=false;
        List<NoAccDegree> list= hpDao.getPositionNogrees(Integer.valueOf(rb.getMatchingPosition()));
        if(CollectionUtils.isNotEmpty(list) && StringUtils.isNotEmpty(rb.getHighEdu())){
             String education=rb.getHighEdu();
             for(NoAccDegree nd:list){
                 if(education.indexOf(nd.getDegreeName())!=-1){
                     flag=true;
                     break;
                 }
             }
        }
        return flag;
        
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delayInterview(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            resumeBaseDao.delayInterviewByIds(paraMap);
            map.put("flag", true);
            map.put("message", "简历设置延迟面试成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "简历设置延迟面试失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> recoverInterview(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            resumeBaseDao.recoverInterviewByIds(paraMap);
            map.put("flag", true);
            map.put("message", "简历恢复面试成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "简历恢复面试失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public List<ActCustomPhoto> selectActCustomPhoto(ActCustomPhoto acp) {
        return resumeBaseDao.selectActCustomPhoto(acp);
    }
    
    
}
