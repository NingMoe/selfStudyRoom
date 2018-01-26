package com.human.resume.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.ActCustomPhoto;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.resume.dao.ResumeAttachmentDao;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeEducationHistoryDao;
import com.human.resume.dao.ResumeIntentionDao;
import com.human.resume.dao.ResumeLanguageDao;
import com.human.resume.dao.ResumeMajorSkillDao;
import com.human.resume.dao.ResumeManagerDao;
import com.human.resume.dao.ResumePhotoDao;
import com.human.resume.dao.ResumePracticeExperienceDao;
import com.human.resume.dao.ResumeProjectExperienceDao;
import com.human.resume.dao.ResumeSchoolPostDao;
import com.human.resume.dao.ResumeTrainHistoryDao;
import com.human.resume.dao.ResumeWorkHistoryDao;
import com.human.resume.entity.ActFlow;
import com.human.resume.entity.EditBase;
import com.human.resume.entity.EditBase.EducationHistory;
import com.human.resume.entity.EditBase.PracticeExper;
import com.human.resume.entity.EditBase.ProjectExper;
import com.human.resume.entity.EditBase.SchoolPost;
import com.human.resume.entity.EditBase.TrainHistory;
import com.human.resume.entity.EditBase.WorkHistory;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.service.ResumeManagerService;
import com.human.utils.FileUtil;
import com.human.utils.OSSUtil;
import com.human.utils.RotateImage;
import com.human.utils.TimeUtil;

import sun.misc.BASE64Decoder;

@Service
public class ResumeManagerServiceImpl implements ResumeManagerService {
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.resumeseeker.header}")
    private String headerPath;
    
    @Value("${oss.resumeseeker.resumePhoto}")
    private String resumePhoto;

    @Resource 
    private OSSUtil ossUtil;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Resource
    private ResumeBaseDao baseDao;
    
    @Resource
    private ResumeAttachmentDao raDao;
    
    @Resource
    private HttpServletRequest request;
    
    @Resource
    private HttpServletResponse response;
    
    @Resource
    private ResumeIntentionDao intentionDao;
    
    @Resource
    private ResumeManagerDao managerDao;
    
    @Resource 
    private ResumeMajorSkillDao  majorSkillDao;
    
    @Resource
    private ResumeLanguageDao resumeLanagerDao;
    
    @Resource
    private ResumeWorkHistoryDao workDao;
    
    @Resource
    private ResumeEducationHistoryDao  educationDao;
    
    @Resource
    private ResumeSchoolPostDao schoolPostDao;
    
    @Resource
    private ResumeTrainHistoryDao trainDao;
    
    @Resource
    private ResumeProjectExperienceDao projectDao;
    
    @Resource
    private ResumePracticeExperienceDao practiceDao;
    
    @Resource
    private ResumePhotoDao rpdDao;
    
    private final Logger logger = LogManager.getLogger(ResumeManagerServiceImpl.class);

    @Override
    public Map<String, Object> updateResumeBasic(MultipartFile file1, ResumeBase rb) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "编辑成功!");
     if(null!=file1){
         String originalName = file1.getOriginalFilename();
         if (!"".equals(originalName) && originalName != null) {
             if(null==rb.getHeadUrl()|rb.getHeadUrl().length()==0){
                 // 上传了头像，先上传头像
                 String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                 Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                 if ((boolean) uploadResult.get("flag")) {
                     rb.setHeadUrl(newFileName);
                 }
                 else {
                     m.put("flag", false);
                     m.put("msg", "头像上传失败，请稍后重试!");
                     return m;
             }
         }else{
                 // 重新上传了头像，先处理头像
                 //如果原头像地址不是http开头，  且不为空，则删除云盘的头像文件
                 if(rb.getHeadUrl().startsWith("http")){
                 
                 }else{
                     boolean isExist= ossUtil.isObjectExist(ossUtil.getClient(), bucketName,rb.getHeadUrl());
                     if(isExist){
                         ossUtil.deleteObject(ossUtil.getClient(), bucketName, rb.getHeadUrl());
                     }
                 }
                 String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                 //上传头像文件，
                 Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                 if ((boolean) uploadResult.get("flag")) {
                     rb.setHeadUrl(newFileName);
                 }else {
                     m.put("flag", false);
                     m.put("msg", "头像上传失败，请稍后重试!");
                     return m;
                 }
         }
         }
     };
        //更新应聘者信息
        baseDao.editByPrimaryKey(rb);
        return m;
    }

    @Override
    public void dowEnclosure(Long id) {
        String zipFile = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + String.valueOf(System.currentTimeMillis()) + ".zip";
        String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + String.valueOf(System.currentTimeMillis());
        try {
            ResumeAttachment ra = new ResumeAttachment();
            ra.setIsValid(false);
            if(id==null){
                return ;
            }
            ra.setResumeId(String.valueOf(id));
            List<ResumeAttachment> resumeList = raDao.queryRa(ra);
            if (resumeList.size() > 0) {
                File directroy = new File(newFilePath);
                if (!directroy.exists() && !directroy.isDirectory()) {
                    directroy.mkdir();
                }
                
                for (ResumeAttachment resumeAtt : resumeList) {
                    ossUtil.downloadFile(ossUtil.getClient(), bucketName, resumeAtt.getPathName(), newFilePath+"/"+ resumeAtt.getPathName().substring(resumeAtt.getPathName().lastIndexOf("/")+1));
                }
                FileUtil.fileToZip(newFilePath, zipFile);
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(("简历附件_" + String.valueOf(System.currentTimeMillis()) + ".zip").getBytes("gbk"), "iso-8859-1"));
                InputStream inputStream = new FileInputStream(zipFile);
                OutputStream os = response.getOutputStream();
                int size = (int) zipFile.length();
                byte[] b = new byte[size];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    os.write(b, 0, length);
                }
                os.close();
                inputStream.close();
            }
            else {
                   logger.error("==========未找到对应附件=============");
            }

        }
        catch (Exception e) {
            logger.error("==========附件下载错误=============",e);
        }
        finally {
            FileUtil.deletefile(newFilePath);
            FileUtil.deletefile(zipFile);
        }
    }

    @Override
    public Map<String, Object> editResumeHead(MultipartFile file1, ResumeBase rb) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "头像上传成功!");
        if (null != file1) {
            String originalName = file1.getOriginalFilename();
            if (!"".equals(originalName) && originalName != null) {
                if (null == rb.getHeadUrl() || rb.getHeadUrl().length() == 0) {
                    // 上传了头像，先上传头像
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                    if ((boolean) uploadResult.get("flag")) {
                        rb.setHeadUrl(newFileName);
                    }
                    else {
                        m.put("flag", false);
                        m.put("msg", "头像上传失败，请稍后重试!");
                        return m;
                    }
                }
                else {
                    // 重新上传了头像，先处理头像
                    // 如果原头像地址不是http开头， 且不为空，则删除云盘的头像文件
                    if (rb.getHeadUrl().startsWith("http")) {

                    }
                    else {
                        boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, rb.getHeadUrl());
                        if (isExist) {
                            ossUtil.deleteObject(ossUtil.getClient(), bucketName, rb.getHeadUrl());
                        }
                    }
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    // 上传头像文件，
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                    if ((boolean) uploadResult.get("flag")) {
                        rb.setHeadUrl(newFileName);
                        m.put("imgUrl", fileurl+newFileName);
                    }
                    else {
                        m.put("flag", false);
                        m.put("msg", "头像上传失败，请稍后重试!");
                        return m;
                    }
                }
            }
        }
        baseDao.updateByPrimaryKeySelective(rb);
        return m;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> updateResumeBasicDetail(EditBase eb) {
        Map<String, Object> map=new HashMap<String, Object>();
       //更新基础信息
        managerDao.updateBaseByKey(eb);
        Long resumeId=eb.getId();
        //处理求职意向
        ResumeIntention ri=eb.getResumeInterntion();
        if(null!=ri){
            if(null!=ri.getId()){
                managerDao.editIntentionByKey(ri);
            }else{
                ri.setResumeId(resumeId);
                intentionDao.insertSelective(ri);
            }
        }
        
        //处理专业技能
        majorSkillDao.deleteByResumeId(resumeId);
        if(null!=eb.getResumeMajorList()){
            for(ResumeMajorSkill ms:eb.getResumeMajorList()){
                    ms.setResumeId(resumeId);
                    majorSkillDao.insertSelective(ms);
            }
        }
     
        
        //处理语言能力
        resumeLanagerDao.deleteByResumeId(resumeId);
        if (null != eb.getLanguageList()) {
            for (ResumeLanguage rl : eb.getLanguageList()) {
                rl.setResumeId(resumeId);
                resumeLanagerDao.insertSelective(rl);
            }
        }
     
        //处理工作经历
        String patten="yyyy年MM月";
        workDao.deleteByResumeId(resumeId);
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
                ResumeWorkHistory rwh=new ResumeWorkHistory(wh.getId(), wh.getResumeId(), starTime, endTime, wh.getCompanyName(), wh.getWorkTime(), wh.getWorkProperty(), wh.getCompanyScale(), wh.getDepartment(), wh.getPosition(), wh.getSalary(), wh.getDescribes(), wh.getLeaveReason());
                    rwh.setResumeId(resumeId);
                    workDao.insertSelective(rwh);
            }
        }
    
        //处理教育经历
        educationDao.deleteByResumeId(resumeId);
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
                ResumeEducationHistory reh=new ResumeEducationHistory(re.getId(), resumeId, starTime, endTime, re.getSchoolName(), null, null, re.getEducation(), re.getMajor(), re.getDescribes(), null);
                    educationDao.insertSelective(reh);
            }
        }
        
        //处理校内职务
        schoolPostDao.deleteByResumeId(resumeId);
        if(null!=eb.getSchoolPostList()){
            for(SchoolPost sp:eb.getSchoolPostList()){
                Date startTime=null;
                if(null!=sp.getStartTime()&&sp.getStartTime().trim().length()>0){
                    if(!sp.getStartTime().equals("至今")){
                        startTime=TimeUtil.getDateByTime(sp.getStartTime(),patten);
                    }
                }
                Date endTime=null;
                if(null!=sp.getEndTime()&&sp.getEndTime().trim().length()>0){
                    if(!sp.getEndTime().equals("至今")){
                        endTime=TimeUtil.getDateByTime(sp.getEndTime(),patten);
                    }
                }
                ResumeSchoolPost rsp=new ResumeSchoolPost(sp.getId(), resumeId, startTime, endTime, sp.getPostName(), sp.getDescribes());
                    schoolPostDao.insertSelective(rsp);
            }
        }
       
        //处理培训经历
        trainDao.deleteByResumeId(resumeId);
        if(null!=eb.getTrainList()){
            for(TrainHistory th:eb.getTrainList()){
                Date startTime=null;
                if(null!=th.getStartTime()&&th.getStartTime().trim().length()>0){
                    if(!th.getStartTime().equals("至今")){
                        startTime=TimeUtil.getDateByTime(th.getStartTime(),patten);
                    }
                }
                Date endTime=null;
                if(null!=th.getEndTime()&&th.getEndTime().trim().length()>0){
                    if(!th.getEndTime().equals("至今")){
                        endTime=TimeUtil.getDateByTime(th.getEndTime(),patten);
                    }
                }
                ResumeTrainHistory rth=new ResumeTrainHistory(th.getId(), resumeId, startTime, endTime, th.getTrainCompany(), th.getPlace(), th.getTrainName(), th.getCertificate(), th.getDescribes());
                trainDao.insertSelective(rth);
            }
        }
   
        //处理项目经验
        projectDao.deleteByResumeId(resumeId);
        if(null!=eb.getProjectList()){
            for(ProjectExper pe:eb.getProjectList()){
                Date startTime=null;
                if(null!=pe.getStartTime()&&pe.getStartTime().trim().length()>0){
                    if(!pe.getStartTime().equals("至今")){
                        startTime=TimeUtil.getDateByTime(pe.getStartTime(),patten);
                    }
                }
                Date endTime=null;
                if(null!=pe.getEndTime()&&pe.getEndTime().trim().length()>0){
                    if(!pe.getEndTime().equals("至今")){
                        endTime=TimeUtil.getDateByTime(pe.getEndTime(),patten);
                    }
                }
                ResumeProjectExperience projectExp=new ResumeProjectExperience(pe.getId(), resumeId, startTime, endTime, pe.getProjectName(), pe.getResponsibilityDescribe(), pe.getProjectDescribe(), pe.getCompanyName());
                projectDao.insertSelective(projectExp);
            }
        }
        
        //处理实践经验
        practiceDao.deleteByResumeId(resumeId);
        if(null!=eb.getPracticeList()){
            for(PracticeExper pe:eb.getPracticeList()){
                Date startTime=null;
                if(null!=pe.getStartTime()&&pe.getStartTime().trim().length()>0){
                    if(!pe.getStartTime().equals("至今")){
                        startTime=TimeUtil.getDateByTime(pe.getStartTime(),patten);
                    }
                }
                Date endTime=null;
                if(null!=pe.getEndTime()&&pe.getEndTime().trim().length()>0){
                    if(!pe.getEndTime().equals("至今")){
                        endTime=TimeUtil.getDateByTime(pe.getEndTime(),patten);
                    }
                }
                ResumePracticeExperience practiceExp=new ResumePracticeExperience(pe.getId(), resumeId, startTime, endTime, pe.getPracticeName(), pe.getPracticeDescribe(), pe.getPracticePosition(), pe.getPracticeCompany());
                 practiceDao.insertSelective(practiceExp);
            }
        }
        map.put("flag", true);
        map.put("msg", "更新成功!");
        return map;
    }

    @Override
    public List<PositionProcessScoreItem> getItemScoreByFlowCode(String flowCode) {
        List<PositionProcessScoreItem> list=new ArrayList<PositionProcessScoreItem>();
        if(null!=flowCode&&flowCode.trim().length()>0){
            list=managerDao.getItemScoreByFlowCode(flowCode);
        }
        return list;
    }

    @Override
    public List<ActCustomComment> selectComment(ActCustomComment acc) {
        return managerDao.selectComment(acc);
    }

    @Override
    public List<ActFlow> getGtRecord(String resumeId) {
        return managerDao.getGtRecord(resumeId);
    }

    @Override
    public Map<String, Object> uploadResumePhoto(MultipartFile file2, Long resumeId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "图片上传成功!");
        ResumePhoto rp=new ResumePhoto();
        if (null != file2) {
            String originalName = file2.getOriginalFilename();
            if (!"".equals(originalName) && originalName != null) {
                    // 上传了头像，先上传头像
                    String newFileName = resumePhoto + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file2);
                    if ((boolean) uploadResult.get("flag")) {
                        rp.setResumeId(resumeId);
                        rp.setPath(newFileName);
                        rpdDao.insertSelective(rp);
                    }
                    else {
                        m.put("flag", false);
                        m.put("msg", "上传失败，请稍后重试!");
                        return m;
                    }
            }
        }
        return m;
    }

    @Override
    public int delResumePhoto(Long id,String path) {
        boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, path);
        if (isExist) {
            ossUtil.deleteObject(ossUtil.getClient(), bucketName, path);
        }
        return rpdDao.deleteByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> saveQuickPhoto(Long id, String fileType, String base64String) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "头像上传成功!");
        try {
            if (null == base64String || base64String.length() == 0) {
                m.put("flag", false);
                m.put("msg", "无法获取照片数据!");
                return m;
            }
            ResumeBase rb = baseDao.selectByPrimaryKey(id);
            String oldHeadUrl = rb.getHeadUrl();
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64String);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String fileName = String.valueOf(id) + String.valueOf(System.currentTimeMillis())+ "." + fileType;
            String temPath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + fileName ;
            OutputStream out = new FileOutputStream(temPath);
            out.write(b);
            out.flush();
            out.close();
            File file = new File(temPath);
            fileName=headerPath+fileName;
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, file);
            FileUtil.deletefile(temPath);
            if ((boolean) uploadResult.get("flag")) {
                rb.setHeadUrl(fileName);
                m.put("imgUrl", fileurl + fileName);
            }
            else {
                m.put("flag", false);
                m.put("msg", "头像上传失败，请稍后重试!");
                return m;
            }
            baseDao.updateByPrimaryKeySelective(rb);
            if (null != oldHeadUrl && oldHeadUrl.length() != 0 && !oldHeadUrl.startsWith("http")) {
                // 已经存在头像数据，并且是存在阿里云的，得先把阿里云头像删除
                boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, oldHeadUrl);
                if (isExist) {
                    ossUtil.deleteObject(ossUtil.getClient(), bucketName, oldHeadUrl);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            m.put("flag", false);
            m.put("msg", "上传异常，请稍后再试!");
        }
        return m;
    }
    
    @Override
    public Map<String, Object> addPhotoFeedBack(Long id, String fileType, String base64String) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "头像上传成功!");
        try {
            if (null == base64String || base64String.length() == 0) {
                m.put("flag", false);
                m.put("msg", "无法获取照片数据!");
                return m;
            }
            ResumeBase rb = baseDao.selectByPrimaryKey(id);
            String oldHeadUrl = rb.getHeadUrl();
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64String);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String fileName = String.valueOf(id) + String.valueOf(System.currentTimeMillis())+ "." + fileType;
            String temPath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + fileName ;
            OutputStream out = new FileOutputStream(temPath);
            out.write(b);
            out.flush();
            out.close();
            File file = new File(temPath);
            fileName=headerPath+fileName;
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, file);
            FileUtil.deletefile(temPath);
            if ((boolean) uploadResult.get("flag")) {
                rb.setHeadUrl(fileName);
                m.put("imgUrl", fileurl + fileName);
            }
            else {
                m.put("flag", false);
                m.put("msg", "头像上传失败，请稍后重试!");
                return m;
            }
            baseDao.updateByPrimaryKeySelective(rb);
            if (null != oldHeadUrl && oldHeadUrl.length() != 0 && !oldHeadUrl.startsWith("http")) {
                // 已经存在头像数据，并且是存在阿里云的，得先把阿里云头像删除
                boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, oldHeadUrl);
                if (isExist) {
                    ossUtil.deleteObject(ossUtil.getClient(), bucketName, oldHeadUrl);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            m.put("flag", false);
            m.put("msg", "上传异常，请稍后再试!");
        }
        return m;
    }

    @Override
    public Map<String, Object> saveBasePhoto(HttpServletRequest req,Long resumeId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "图片上传成功!");
        try {
            if(req instanceof MultipartHttpServletRequest){  
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;  
                //取出一个list的multipartfile  
                List<MultipartFile> files = multipartRequest.getFiles("files");  
                for (MultipartFile multipartFile : files) {  
                    Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());  
                    ExifIFD0Directory directory=metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                    int orientation = 0;  
                        try{
                            orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION); 
                        }catch (Exception me) {  
                            logger.warn("Could not get orientation");  
                        }  
                        BufferedImage des;
                        if(orientation == 3){
                            System.out.println("rotate 180");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 180);  
                        }else if(orientation == 6){
                            System.out.println("rotate 90");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 90);  
                        }else if(orientation == 8){
                            System.out.println("rotate 90");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 270);  
                        }else{
                            des = ImageIO.read(multipartFile.getInputStream());
                        }
                        
                        System.out.println(des.getHeight());
                        System.out.println(des.getWidth());
                        Image img = (Image)des;
                        int newWidth=1024;  
                        int newHeight=768; 
                        int width=img.getWidth(null);
                        int height=img.getHeight(null);
                       if(width >newWidth ){
                            double rate = (double)width/(double)newWidth;  
                            height = (int) ((double)height/rate);  
                       }
                       if(height >newHeight ){
                           double rate = (double)height/(double)newHeight;  
                           newWidth = (int) ((double)newWidth/rate);  
                      }
                        BufferedImage tag = new BufferedImage((int)newWidth,(int)newHeight,BufferedImage.TYPE_INT_RGB);               
                        tag.getGraphics().drawImage(img,0,0,newWidth,newHeight,null);  
                         ByteArrayOutputStream os = new ByteArrayOutputStream(); 
                        ImageIO.write(tag, "png", os);  
                       // ImageIO.write(tag,"jpg", new File("C:\\Users\\Administrator\\Desktop\\IMG_0362.JPG"));  
                        InputStream is = new ByteArrayInputStream(os.toByteArray()); 
                        String fileName =resumePhoto+ String.valueOf(resumeId) + String.valueOf(System.currentTimeMillis()) + ".png";
                        Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName,is);
                        if ((boolean) uploadResult.get("flag")) {
                            ResumePhoto rp=new ResumePhoto();
                            rp.setResumeId(resumeId);
                            rp.setPath(fileName);
                            rpdDao.insertSelective(rp);
                        } else {
                            // m.put("flag", false);
                            // m.put("msg", "上传失败，请稍后重试!");
                            //return m;
                        }
                }  
            }else{
                m.put("flag", false);
                m.put("msg", "无法获取照片数据!");
                return m;
            }  
        }
        catch (Exception e) {
            e.printStackTrace();
            m.put("flag", false);
            m.put("msg", "上传异常，请稍后再试!");
        }
        return m;
    }

    @Override
    public Map<String, Object> saveActPhoto(HttpServletRequest req, ActCustomPhoto acp) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "图片上传成功!");
        try {
            if(req instanceof MultipartHttpServletRequest){  
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;  
                //取出一个list的multipartfile  
                List<MultipartFile> files = multipartRequest.getFiles("files");  
                for (MultipartFile multipartFile : files) {  
                    Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());  
                    ExifIFD0Directory directory=metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                    int orientation = 0;  
                        try{
                            orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION); 
                        }catch (Exception me) {  
                            logger.warn("Could not get orientation");  
                        }  
                        BufferedImage des;
                        if(orientation == 3){
                            System.out.println("rotate 180");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 180);  
                        }else if(orientation == 6){
                            System.out.println("rotate 90");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 90);  
                        }else if(orientation == 8){
                            System.out.println("rotate 90");
                            BufferedImage src = ImageIO.read(multipartFile.getInputStream());  
                            des = RotateImage.Rotate(src, 270);  
                        }else{
                            des = ImageIO.read(multipartFile.getInputStream());
                        }
                       System.out.println(des.getHeight());
                       System.out.println(des.getWidth());
                       Image img = (Image)des;
                       int newWidth=1024;  
                       int newHeight=768; 
                       int width=img.getWidth(null);
                       int height=img.getHeight(null);
                      if(width >newWidth ){
                           double rate = (double)width/(double)newWidth;  
                           height = (int) ((double)height/rate);  
                      }
                      if(height >newHeight ){
                          double rate = (double)height/(double)newHeight;  
                          newWidth = (int) ((double)newWidth/rate);  
                     }
                       BufferedImage tag = new BufferedImage((int)newWidth,(int)newHeight,BufferedImage.TYPE_INT_RGB);               
                       tag.getGraphics().drawImage(img,0,0,newWidth,newHeight,null);  
                        ByteArrayOutputStream os = new ByteArrayOutputStream();  
                        ImageIO.write(tag, "png", os);  
                       //ImageIO.write(des,"jpg", new File("C:\\Users\\Administrator\\Desktop\\IMG_0362.JPG"));  
                        InputStream is = new ByteArrayInputStream(os.toByteArray()); 
                        String fileName =resumePhoto+ String.valueOf(acp.getFlowCode()) + String.valueOf(System.currentTimeMillis()) + ".jpg";
                        Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName,is);
                        if ((boolean) uploadResult.get("flag")) {
                            acp.setPhotoUrl(fileName);
                            rpdDao.insertActPhoto(acp);
                        } else {
                            // m.put("flag", false);
                            // m.put("msg", "上传失败，请稍后重试!");
                            //return m;
                        }
                }  
            }else{
                m.put("flag", false);
                m.put("msg", "无法获取照片数据!");
                return m;
            }  
        }
        catch (Exception e) {
            e.printStackTrace();
            m.put("flag", false);
            m.put("msg", "上传异常，请稍后再试!");
        }
        return m;
    }

    
    @Override
    public int delActPhoto(Long id, String photoUrl) {
            boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, photoUrl);
            if (isExist) {
                ossUtil.deleteObject(ossUtil.getClient(), bucketName, photoUrl);
            }
            return rpdDao.delActPhoto(id);
    }

}
