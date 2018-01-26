package com.human.resume.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oss.OSSClient;
import com.human.basic.dao.CollegeDao;
import com.human.basic.dao.DicDataDao;
import com.human.basic.dao.RecruitMailDao;
import com.human.basic.dao.ResumeKeywordDao;
import com.human.basic.dao.ResumeModularDao;
import com.human.basic.entity.College;
import com.human.basic.entity.DicData;
import com.human.bpm.service.HrWorkflowService;
import com.human.mail.dao.AcceptMailDao;
import com.human.mail.dao.AnalysisMailDao;
import com.human.mail.entity.AcceptMail;
import com.human.mail.entity.AnalysisMail;
import com.human.recruitment.dao.HrPositionDao;
import com.human.recruitment.dao.PositionProcessDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.NoAccDegree;
import com.human.recruitment.entity.PositionProcess;
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
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeCertificate;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeSnapshot;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.service.AnalysisResumeService;
import com.human.utils.Common;
import com.human.utils.FileUtil;
import com.human.utils.OSSUtil;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.CommonReadResumeUtil;
import com.human.utils.mailUtils.NewRead51JobResumeUtil;
import com.human.utils.mailUtils.NewReadLieTouResumeUtil;
import com.human.utils.mailUtils.NewReadXinAnResumeUtil;
import com.human.utils.mailUtils.NewReadZhiLianResumeUtil;

@Service
public class AnalysisResumeServiceImpl implements AnalysisResumeService {
    
    private final  Logger logger = LogManager.getLogger(AnalysisResumeServiceImpl.class);
    
    @Resource
    private AcceptMailDao acceptMailDao;
      
    @Resource
    private RecruitMailDao recruitMailDao;
    
    @Resource
    private DicDataDao dicDataDao;
    
    @Resource
    private PositionProcessDao processDao;
    
    @Resource
    private AnalysisMailDao analysisMailDao;
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
        
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
    private HrPositionDao hrPositionDao;
    
    @Autowired
    private HrWorkflowService flowService;
    
    @Resource
    private CollegeDao collegeDao;
    
    @Resource
    private  ResumeModularDao  rmDao;
    
    @Resource
    private ResumeKeywordDao rkDao;
        
    @Resource
    private  OSSUtil oSSUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;    
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Value("${oss.snapshotEmailPath}")
    private  String snapshotPath;
    
    @Value("${xinAnMail}")
    private  String xinAnMail;    
    
    @Value("${zhiLianMail}")
    private  String zhiLianMail;
    
    @Value("${qianChenMail}")
    private  String qianChenMail;
    
    @Value("${lietouMail}")
    private  String lietouMail;
    
    @Override
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void analysisResume(AcceptMail acceptMail) throws Exception {      
        /*
         * 判断邮件简历是否是纯图片格式，如果不是 ，则根据html格式的邮件进行解析
         */
        File file=new File("");
        long startTime=System.currentTimeMillis();
        Long resumeId = null;
        String matchingPosition = "";
        boolean hasFlowFlag=false;
        boolean isEliminateFlag=false;
        try {
            Integer isPhoto=acceptMail.getIsPhoto();
            String subject=acceptMail.getSubject();
            //第一步：根据邮件的发件人调用不同的招聘网站解析方法
            String messageSource=acceptMail.getMessageSource();
            DicData dicData= dicDataDao.selectByName(messageSource);
            String dataValue=dicData.getDataValue();
            String applyPosition="";//应聘职位
            String mailHtml=acceptMail.getMailHtml();
            String path=acceptMail.getPath();
            if(isPhoto==0){//不是纯图片简历
                List<Object>list=new ArrayList<Object>(16); 
                file=getHtmlFileByPath(mailHtml,path);                
                Date statTime=new Date();//解析开始时间 
                acceptMail.setIsAnalysis(1);                             
                if(xinAnMail.equalsIgnoreCase(dataValue)){//新安人才网的简历
                    CommonReadResumeUtil commonReadResumeUtil=new NewReadXinAnResumeUtil(rmDao,rkDao);
                    list=commonReadResumeUtil.dealHtmlResumeByResource(file);
                    applyPosition=getApplyPosition(subject,1);
                }else if(zhiLianMail.equalsIgnoreCase(dataValue)){//智联招聘的简历
                    CommonReadResumeUtil commonReadResumeUtil=new NewReadZhiLianResumeUtil(rmDao,rkDao);
                    list=commonReadResumeUtil.dealHtmlResumeByResource(file);
                    applyPosition=getApplyPosition(subject,2);
                }else if(qianChenMail.equalsIgnoreCase(dataValue)){//前程无忧的简历
                    CommonReadResumeUtil commonReadResumeUtil=new NewRead51JobResumeUtil(rmDao,rkDao);
                    list=commonReadResumeUtil.dealHtmlResumeByResource(file);
                    applyPosition=getApplyPosition(subject,3);
                }else if(lietouMail.equalsIgnoreCase(dataValue)){//猎聘网的简历
                    CommonReadResumeUtil commonReadResumeUtil=new NewReadLieTouResumeUtil(rmDao,rkDao);
                    list=commonReadResumeUtil.dealHtmlResumeByResource(file);
                    applyPosition=getApplyPosition(subject,4);
                }
                Date endTime=new Date();//解析结束时间
                //第二步：往解析中间表插入或者更新一条记录
                AnalysisMail am=new AnalysisMail();
                am.setStatTime(statTime);
                am.setAcceptMailId(acceptMail.getId());
                boolean analysisFlag=(boolean) list.get(14);
                if(analysisFlag){//解析成功               
                    am.setEndTime(endTime);                                            
                    acceptMail.setIsAnalysisSuccess(1);              
                }else{
                    am.setFailTime(endTime);
                    acceptMail.setIsAnalysisSuccess(0);
                }
                AnalysisMail analysisMail=analysisMailDao.selectByAcceptMailId(acceptMail.getId());
                if(analysisMail==null){
                    am.setCount(1);
                    analysisMailDao.insertSelective(am); 
                }else{
                    am.setId(analysisMail.getId());
                    am.setCount(analysisMail.getCount()+1);
                    analysisMailDao.updateByPrimaryKeySelective(am);
                } 
                long analysisMailId=am.getId();
                acceptMailDao.updateByPrimaryKeySelective(acceptMail);
                //第三步：如果解析成功，往简历相关表中插入记录
                if(analysisFlag){
                    //对教育经历时间排序，取出最高学历               
                    List<ResumeEducationHistory>resumeEducationHistoryList=(List<ResumeEducationHistory>) list.get(3);
                    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
                    ResumeBase resumeBase=(ResumeBase) list.get(1);
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
                    if(resumeEducationHistoryList!=null && resumeEducationHistoryList.size()>0){
                        //查询学校是否为985或211
                        for(ResumeEducationHistory reh:resumeEducationHistoryList){
                            reh.setSchoolName(checkSchoolIs211Or985(reh.getSchoolName()));         
                        }
                        ResumeEducationHistory res=resumeEducationHistoryList.get(0);
                        if(StringUtils.isEmpty(resumeBase.getHighEdu())){
                            resumeSeeker.setHighEdu(res.getEducation()==null?"":res.getEducation().trim().replaceAll("[　*| *| *|\\s*]*", ""));
                            resumeBase.setHighEdu(res.getEducation()==null?"":res.getEducation().trim().replaceAll("[　*| *| *|\\s*]*", ""));
                        }                        
                        if(StringUtils.isEmpty(resumeBase.getMajor())){
                            resumeSeeker.setMajor(res.getMajor()==null?"":res.getMajor().trim().replaceAll("[　*| *| *|\\s*]*", ""));
                            resumeBase.setMajor(res.getMajor()==null?"":res.getMajor().trim().replaceAll("[　*| *| *|\\s*]*", ""));
                        }
                        if(StringUtils.isEmpty(resumeBase.getGraSchool())){
                            resumeSeeker.setGraSchool(res.getSchoolName());
                            resumeBase.setGraSchool(res.getSchoolName());
                        }else{
                            String schoolName=checkSchoolIs211Or985(resumeBase.getGraSchool());
                            resumeSeeker.setGraSchool(schoolName);
                            resumeBase.setGraSchool(schoolName);
                        }
                        if(res.getEndTime()!=null){
                            resumeSeeker.setGraduationDate(TimeUtil.date2String(res.getEndTime(),"yyyy-MM"));
                            resumeBase.setGraduationDate(TimeUtil.date2String(res.getEndTime(),"yyyy-MM"));  
                        }                    
                        //判断本科类型
                        String entranceType=getEntranceType(res);
                        resumeSeeker.setEduType(entranceType);
                    }                   
                    //查找应聘职位
                    resumeBase.setApplyPosition(applyPosition);                    
                    //去职位表匹配发布的职位
                    matchingPosition = getMatchingPosition(acceptMail.getMessageAccept(),applyPosition);
                    if(matchingPosition!="0"){
                        resumeBase.setMatchingPosition(matchingPosition); 
                    }
                    //简历来源
                    resumeBase.setSource(messageSource); 
                    //简历所属机构
                    String hrCompanyId=acceptMail.getHrCompanyId();
                    resumeBase.setHrCompanyId(hrCompanyId);
                    //简历链接
                    resumeBase.setResumeLink(acceptMail.getOriginalMail());
                    //设置投递时间
                    resumeSeeker.setDeliveryDate(TimeUtil.getStringByTimestamp(acceptMail.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
                    resumeBase.setDeliveryDate(TimeUtil.getStringByTimestamp(acceptMail.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
                    //通过手机号和姓名检查应聘者是否存在
                    String name=resumeSeeker.getName();
                    String phone=resumeSeeker.getPhone();
                    Long resumeSeekerId=0L;
                    //当姓名和手机都解析出来时要去检查应聘者是否存在
                    if((!Common.isEmpty(name)) &&(!Common.isEmpty(phone))){
                        ResumeSeeker resumeSeekerExist =recruitAcceptanceDao.checkResumeByNameAndPhone(name.trim(), phone.trim());                  
                        if(resumeSeekerExist!=null){//存在,更新应聘者
                            resumeSeekerId=resumeSeekerExist.getId();
                            resumeSeeker.setId(resumeSeekerId);
                            recruitAcceptanceDao.updateByPrimaryKeySelective(resumeSeeker);
                        }else{//不存在，创建应聘者
                            recruitAcceptanceDao.insert(resumeSeeker);
                            resumeSeekerId=resumeSeeker.getId() ;   
                        }
                    }else{
                        recruitAcceptanceDao.insert(resumeSeeker);
                        resumeSeekerId=resumeSeeker.getId(); 
                    }                  
                    /*
                     * 插入简历相关实体
                     */
                    resumeBase.setAnalysisMailId(analysisMailId);
                    resumeBase.setResumeSeekerId(resumeSeekerId);
                    //判断当前求职者是否有正在进行的面试流程
                    ResumeBase  rb=resumeBaseDao.selectRbByReSeekId(resumeBase);
                    if(rb!=null){//有
                        resumeBase.setFlowFlag("2");
                        hasFlowFlag=true;
                    }
                    //通过职位的学历要求设置简历是否淘汰
                    isEliminateFlag=setReusmeFlowStatus(resumeBase);
                    if(isEliminateFlag){
                        resumeBase.setFlowStatus("2");//直接淘汰
                    }
                    resumeBaseDao.insertSelective(resumeBase);
                    resumeId = resumeBase.getId();
                    saveResumeWorkHistory(list,resumeId);
                    saveResumeTrainHistory(list,resumeId);               
                    saveResumeSchoolPost(list,resumeId);
                    saveResumeProjectExperience(list,resumeId);
                    saveResumePrize(list,resumeId);
                    saveResumePracticeExperience(list,resumeId);
                    saveResumeMajorSkill(list,resumeId);
                    saveResumeLanguage(list,resumeId);
                    saveResumeIntention(list,resumeId);
                    saveResumeEducationHistory(list,resumeId);
                    saveResumeCertificate(list,resumeId);
                    saveResumeAttachment(list,resumeId);
                }    
            }else{//纯图片简历
                 //图片简历只能通过邮件的主题获取求职者的姓名
                String name="";
                int index=0;
                if(xinAnMail.equalsIgnoreCase(dataValue)){//新安人才网的简历
                    index=subject.lastIndexOf("-");
                    name=subject.substring(index+1).trim();
                    applyPosition=getApplyPosition(subject,1);
                }else if(zhiLianMail.equalsIgnoreCase(dataValue)){//智联招聘的简历
                    index=subject.lastIndexOf("-");
                    name=subject.substring(index+1).trim();
                    applyPosition=getApplyPosition(subject,2);
                }else if(qianChenMail.equalsIgnoreCase(dataValue)){//前程无忧的简历
                    index=subject.lastIndexOf("－");
                    name=subject.substring(index+1).trim();
                    applyPosition=getApplyPosition(subject,3);
                }  
                ResumeSeeker resumeSeeker=new ResumeSeeker();
                ResumeBase resumeBase=new ResumeBase();
                resumeSeeker.setName(name);
                resumeBase.setName(name);
                //查找应聘职位
                resumeBase.setApplyPosition(applyPosition);                    
                //去职位表匹配发布的职位
                matchingPosition=getMatchingPosition(acceptMail.getMessageAccept(),applyPosition);
                if(matchingPosition!="0"){
                    resumeBase.setMatchingPosition(matchingPosition); 
                }
                //简历来源
                resumeBase.setSource(messageSource); 
                //简历所属机构
                resumeBase.setHrCompanyId(acceptMail.getHrCompanyId());
                //简历链接
                resumeBase.setResumeLink(acceptMail.getOriginalMail());
                //设置投递时间
                resumeSeeker.setDeliveryDate(TimeUtil.getStringByTimestamp(acceptMail.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
                resumeBase.setDeliveryDate(TimeUtil.getStringByTimestamp(acceptMail.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
                recruitAcceptanceDao.insert(resumeSeeker);
                resumeBase.setResumeSeekerId(resumeSeeker.getId());
                resumeBaseDao.insertSelective(resumeBase); 
                //保存简历图片
                ResumePhoto resumePhoto=new ResumePhoto();
                resumePhoto.setResumeId(resumeBase.getId());
                resumePhoto.setPath(mailHtml);
                resumePhotoDao.insertSelective(resumePhoto);
                resumeId = resumeBase.getId();
            }
            long endTime=System.currentTimeMillis();
            System.out.println("解析简历并入库耗时："+(endTime-startTime)/1000+"秒");
            //没有淘汰的简历再进行是否自动提交的判断
            if(!isEliminateFlag){
                /*
                 * 根据匹配的职位流程 判断是否自动提交 进行相应操作
                 */
                if(StringUtils.isNotEmpty(matchingPosition) && !"0".equals(matchingPosition) && !hasFlowFlag){
                    HrPosition p = hrPositionDao.selectByPrimaryKey(Integer.valueOf(matchingPosition));
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
                            interviewSchedule.setResumeId(resumeId+"");
                            flowService.startProcess(interviewSchedule);
                        }
                    }
                }
            }
        }catch (Exception e) {
          e.printStackTrace();
          logger.error("简析简历出错！", e.getMessage());
          throw new Exception(e);
        }finally{
            file.delete();         
        }       
    }

    /**
     * 从阿里云文件服务器下载文件
     * @param mailHtmlPath 路径
     * @return
     */
    public File getHtmlFileByPath(String mailHtmlPath,String path){
        File file=new File("");
        OSSClient ossClient = oSSUtil.getClient();       
        InputStream inputStream= oSSUtil.getObjectInputStream(ossClient, bucketName, mailHtmlPath);         
        String fileName =path+System.currentTimeMillis()+".html";
        System.out.println("阿里云文件服务器下载文件到应用服务器路径:"+fileName);
        try {
            file=FileUtil.saveFile(inputStream, fileName);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("从阿里云文件服务器下载文件失败！",e.getMessage());
        }
        return file;
    }
    
    /**
     * 判断本科类型
     * @param res
     * @return
     */
    public String getEntranceType(ResumeEducationHistory res){
        String entranceType="";
        Date startTime=res.getStartTime();        
        Date endTime=res.getEndTime();
        Calendar calendar=Calendar.getInstance();
        if(startTime!=null && endTime!=null){           
            calendar.setTime(startTime);
            int startYear=calendar.get(calendar.YEAR);
            calendar.setTime(endTime);
            int endYear=calendar.get(calendar.YEAR);
            int years=endYear-startYear;
            if(years>=3){
                entranceType="统招";
            }else{
                entranceType="其它";
            }
        }
        return entranceType;
    }
    
    /**
     * 查找应聘职位
     * @param subject
     * @param type
     * @return
     */
    public String getApplyPosition(String subject,int type){
        String applyPosition="";
        int index1=0,index2=0;
        if(type==1){//新安人才网的简历
            index1=subject.indexOf("(");
            index2=subject.lastIndexOf(")");
            applyPosition=subject.substring(index1+6,index2).trim();  
        }else if(type==2){//智联招聘的简历
            subject=subject.replaceAll(" ","");
            index1=subject.indexOf("应聘");
            index2=subject.lastIndexOf("-");
            applyPosition=subject.substring(index1+2,index2).trim();           
        }else if(type==3){//前程无忧的简历
            index1=subject.indexOf("贵公司");
            index2=subject.lastIndexOf("－");
            if(index2==-1){
                applyPosition=subject.substring(index1+3).trim(); 
            }else{
                applyPosition=subject.substring(index1+3,index2).trim(); 
            }
        }else if(type==4){//猎聘网的简历
            index1=subject.indexOf("【");
            index2=subject.indexOf("】");
            if(index1!=-1 && index2!=-1){
               applyPosition=subject.substring(index1+1,index2).trim();  
            }            
        }
        return applyPosition;
    }

    
    /**
     * 匹配流程职位
     * @param subject
     * @param type
     * @return
     */
    public String getMatchingPosition(String recruitMailId,String applyPosition){
        String  positionId="0";
        Map<String,String>map=hrPositionDao.getPositionId(recruitMailId, applyPosition);
        if(map!=null){
            positionId=String.valueOf(map.get("positionId"));
        }
        return positionId;
    }
    
    /**
     * 保存工作经历
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeWorkHistory(List<Object>list,long resumeId){
        List<ResumeWorkHistory>resumeWorkHistoryList=(List<ResumeWorkHistory>) list.get(12);
        if(resumeWorkHistoryList!=null && resumeWorkHistoryList.size()>0 ){
            for(ResumeWorkHistory rwh:resumeWorkHistoryList){
                rwh.setResumeId(resumeId);
                resumeWorkHistoryDao.insertSelective(rwh);
            }
        }        
    }
    
    /**
     * 保存培训经历
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeTrainHistory(List<Object>list,long resumeId){
        List<ResumeTrainHistory> resumeTrainHistoryList=(List<ResumeTrainHistory>) list.get(11);
        if(resumeTrainHistoryList!=null && resumeTrainHistoryList.size()>0 ){
            for(ResumeTrainHistory rth:resumeTrainHistoryList){
                rth.setResumeId(resumeId);
                resumeTrainHistoryDao.insertSelective(rth);
            }
        }        
    }
    
    /**
     * 保存校内职位
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeSchoolPost(List<Object>list,long resumeId){
        List<ResumeSchoolPost> resumeSchoolPostList=(List<ResumeSchoolPost>) list.get(10);
        if(resumeSchoolPostList!=null && resumeSchoolPostList.size()>0 ){
            for(ResumeSchoolPost rsp:resumeSchoolPostList){
                rsp.setResumeId(resumeId);
                resumeSchoolPostDao.insertSelective(rsp);
            }
        }        
    }
    
    /**
     * 保存项目经验
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeProjectExperience(List<Object>list,long resumeId){
        List<ResumeProjectExperience> resumeProjectExperienceList=(List<ResumeProjectExperience>) list.get(9);
        if(resumeProjectExperienceList!=null && resumeProjectExperienceList.size()>0 ){
            for(ResumeProjectExperience rpe:resumeProjectExperienceList){
                rpe.setResumeId(resumeId);
                resumeProjectExperienceDao.insertSelective(rpe);
            }
        }        
    }
    
    /**
     * 保存获奖荣誉
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumePrize(List<Object>list,long resumeId){
        List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
        if(resumePrizeList!=null && resumePrizeList.size()>0 ){
            for(ResumePrize rp:resumePrizeList){
                rp.setResumeId(resumeId);
                resumePrizeDao.insertSelective(rp);
            }
        }        
    }
    
    /**
     * 保存实践经验
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumePracticeExperience(List<Object>list,long resumeId){
        List<ResumePracticeExperience> resumePracticeExperienceList=(List<ResumePracticeExperience>) list.get(7);
        if(resumePracticeExperienceList!=null && resumePracticeExperienceList.size()>0 ){
            for(ResumePracticeExperience rpe:resumePracticeExperienceList){
                rpe.setResumeId(resumeId);
                resumePracticeExperienceDao.insertSelective(rpe);
            }
        }        
    }

    /**
     * 保存专业技能
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeMajorSkill(List<Object>list,long resumeId){
        List<ResumeMajorSkill> resumeMajorSkillList=(List<ResumeMajorSkill>) list.get(6);
        if(resumeMajorSkillList!=null && resumeMajorSkillList.size()>0 ){
            for(ResumeMajorSkill rms:resumeMajorSkillList){
                rms.setResumeId(resumeId);
                resumeMajorSkillDao.insertSelective(rms);
            }
        }        
    }
    
    /**
     * 保存语言能力
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeLanguage(List<Object>list,long resumeId){
        List<ResumeLanguage> resumeLanguageList=(List<ResumeLanguage>) list.get(5);
        if(resumeLanguageList!=null && resumeLanguageList.size()>0 ){
            for(ResumeLanguage rl:resumeLanguageList){
                rl.setResumeId(resumeId);
                resumeLanguageDao.insertSelective(rl);
            }
        }        
    }
    
    /**
     * 保存求职意向
     * @param list
     * @param resumeId
     */
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeIntention(List<Object>list,long resumeId){
        ResumeIntention resumeIntention=(ResumeIntention) list.get(4);
        if(resumeIntention!=null ){
            resumeIntention.setResumeId(resumeId);
            resumeIntentionDao.insertSelective(resumeIntention);
        }        
    }
    
    
    /**
     * 保存教育经历
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeEducationHistory(List<Object>list,long resumeId){
        List<ResumeEducationHistory>resumeEducationHistoryList=(List<ResumeEducationHistory>) list.get(3);
        if(resumeEducationHistoryList!=null && resumeEducationHistoryList.size()>0 ){
            for(ResumeEducationHistory res:resumeEducationHistoryList){
                res.setResumeId(resumeId);
                resumeEducationHistoryDao.insertSelective(res);
            }
        }        
    }
    
    /**
     * 保存证书
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeCertificate(List<Object>list,long resumeId){
        List<ResumeCertificate>resumeCertificateList=(List<ResumeCertificate>) list.get(13);
        if(resumeCertificateList!=null && resumeCertificateList.size()>0 ){
            for(ResumeCertificate rcf:resumeCertificateList){
                rcf.setResumeId(resumeId);
                resumeCertificateDao.insertSelective(rcf);
            }
        }        
    }
    
    /**
     * 保存附件
     * @param list
     * @param resumeId
     */
    @SuppressWarnings("unchecked")
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeAttachment(List<Object>list,long resumeId){
        List<ResumeAttachment> resumeAttachmentList=(List<ResumeAttachment>) list.get(2);
        if(resumeAttachmentList!=null && resumeAttachmentList.size()>0 ){
            for(ResumeAttachment ral:resumeAttachmentList){
                ral.setResumeId(String.valueOf(resumeId));
                resumeAttachmentDao.insertSelective(ral);
            }
        }        
    }
    
    /**
     * 保存邮件快照
     * @param imageFile1
     * @param imageFile2
     * @param resumeId
     * @param hrCompanyId
     */
    @Transactional(rollbackFor=Exception.class)
    public void saveResumeSnapshot(File imageFile,long resumeId,String hrCompanyId){
        ResumeSnapshot rsh=new ResumeSnapshot();
        String snapshotDir=snapshotPath+hrCompanyId+"/";//邮件快照保存路径
        OSSClient ossClient = oSSUtil.getClient();           
        String newFileName = snapshotDir+System.currentTimeMillis()+".jpg"; 
        System.out.println("邮件快照保存路径: " + newFileName);
        Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,imageFile);
        boolean uploadflag = (boolean)uploadResult.get("flag");
        String savePath="";
        if(uploadflag){
            savePath=(String)uploadResult.get("objectkey");
            rsh.setPathName(savePath);
            rsh.setResumeId(resumeId);
            resumeSnapshotDao.insertSelective(rsh);
        } 
    }
    
    /**
     * 判断学校是否为211或985
     * @param schoolName
     * @return
     */
    public String checkSchoolIs211Or985(String schoolName){
        String returnString=schoolName;
        if(StringUtils.isNotEmpty(schoolName)){
            College college=collegeDao.selectByName(schoolName.trim());
            if(college!=null){
                int Is985=college.getIs985();
                int Is211=college.getIs211();
                if(Is985==1 && Is211==1){
                    returnString=schoolName+"(985,211)";
                }else if(Is985==1 && schoolName.indexOf("985")==-1){
                    returnString=schoolName+"(985)";
                }else if(Is211==1 && schoolName.indexOf("211")==-1){
                    returnString=schoolName+"(211)";
                }                                
            }
        }
       return returnString;
    }
    
    /**
     * 通过职位的学历要求设置简历是否淘汰
     * @param rb
     * @return
     */
    public boolean setReusmeFlowStatus(ResumeBase rb){
        boolean flag=false;
        if( StringUtils.isNotEmpty(rb.getMatchingPosition())){
            List<NoAccDegree> list= hrPositionDao.getPositionNogrees(Integer.valueOf(rb.getMatchingPosition()));
            if(CollectionUtils.isNotEmpty(list) && StringUtils.isNotEmpty(rb.getHighEdu())){
                 String education=rb.getHighEdu();
                 for(NoAccDegree nd:list){
                     if(education.indexOf(nd.getDegreeName())!=-1){
                         flag=true;
                         break;
                     }
                 }
            }
        }
        return flag;        
    }
    
}
