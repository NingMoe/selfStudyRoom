package com.human.recruitment.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.human.basic.dao.SmsTempDao;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.EmployeeService;
import com.human.manager.entity.HrUser;
import com.human.recruitment.dao.CommunicationRecordDao;
import com.human.recruitment.dao.CommunicationSmsDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.CommunicationDesc;
import com.human.recruitment.entity.CommunicationRecord;
import com.human.recruitment.entity.CommunicationSms;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.RecruitAcceptanceService;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.entity.InterAppr;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class RecruitAcceptanceServiceImpl implements RecruitAcceptanceService {
    
    @Resource
    private RecruitAcceptanceDao recruitAcceptanceDao;
    
    @Resource
    private ResumeBaseDao resumeBaseDao;
    
    @Resource
    private CommunicationRecordDao communicationRecordDao;
    
    @Resource
    private CommunicationSmsDao communicationSmsDao; 
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.resumeseeker.header}")
    private String headerPath;

    @Resource 
    private OSSUtil ossUtil;
    
    @Resource
    private EmployeeService empService;
    
    @Resource
    private SmsTempDao smsTempDao;

    @Override
    public List<ResumeSeeker> queryResumeSeekerList(ResumeSeeker rs) {
        return recruitAcceptanceDao.queryResumeSeeker(rs);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveResumeSeeker(MultipartFile file1, ResumeSeeker rs) {
        Map<String, Object> m = new HashMap<String, Object>();
        if(null!=rs.getRecom()&&rs.getRecom().trim().length()>0){
            HrUser hu=empService.queryUserByEmail(rs.getRecom());
            if(hu==null){
                m.put("flag", false);
                m.put("msg", "无法找到对应推荐人!");
                return m;
            }
        }
        
        m.put("flag", true);
        m.put("msg", "新增成功!");
        ResumeSeeker rs1=new ResumeSeeker();
        rs1.setPhone(rs.getPhone());
        rs1.setName(rs.getName());
        List<ResumeSeeker> list=recruitAcceptanceDao.queryResumeSeeker(rs1);
        if(list.size()>0){
            m.put("flag", false);
            m.put("msg", "该人员已经存在!");
            return m;
        }
        String originalName = file1.getOriginalFilename();
        if (!"".equals(originalName) && originalName != null) {
            // 上传了头像，先上传头像
            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
            if ((boolean) uploadResult.get("flag")) {
                rs.setHeadUrl(newFileName);
            }
            else {
                m.put("flag", false);
                m.put("msg", "头像上传失败，请稍后重试!");
                return m;
            }
        }
        // 保存新建应聘人员
        recruitAcceptanceDao.saveResumeSeeker(rs);
        ResumeBase rb = new ResumeBase();
        rb.setName(rs.getName());
        rb.setSex(rs.getSex());
        rb.setTelephone(rs.getPhone());
        rb.setEmail(rs.getEmail());
        rb.setWorkingTime(rs.getWorkTime());
        rb.setSource("人工新增");
        rb.setType("社会招聘");
        rb.setLocationCity(rs.getLocationCity());
        rb.setCertificatesType("身份证");
        rb.setCertificatesNumber(rs.getIdCardNo());
        rb.setPhoneBack(rs.getStandbyPhone());
        rb.setDeliveryDate(rs.getDeliveryDate());
        rb.setGraSchool(rs.getGraSchool());
        rb.setMajor(rs.getMajor());
        rb.setGraduationDate(rs.getGraduationDate());
        rb.setHighEdu(rs.getHighEdu());
        rb.setResumeSeekerId(rs.getId());
        rb.setInsideRecommend(rs.getRecom());
        rb.setInsideRelation(rs.getRecomRelation());
        rb.setHeadUrl(rs.getHeadUrl());
        resumeBaseDao.insert(rb);
        return m;
    }

    @Override
    public Map<String, Object> updateResumeSeeker(MultipartFile file1, ResumeSeeker rs) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("flag", true);
        m.put("msg", "编辑成功!");
        if (null != file1) {
            String originalName = file1.getOriginalFilename();
            if (!"".equals(originalName) && originalName != null) {
                if (null == rs.getHeadUrl() || rs.getHeadUrl().length() == 0) {
                    // 上传了头像，先上传头像
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                    if ((boolean) uploadResult.get("flag")) {
                        rs.setHeadUrl(newFileName);
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
                    if (rs.getHeadUrl().startsWith("http")) {

                    }
                    else {
                        boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, rs.getHeadUrl());
                        if (isExist) {
                            ossUtil.deleteObject(ossUtil.getClient(), bucketName, rs.getHeadUrl());
                        }
                    }
                    String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    // 上传头像文件，
                    Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file1);
                    if ((boolean) uploadResult.get("flag")) {
                        rs.setHeadUrl(newFileName);
                    }
                    else {
                        m.put("flag", false);
                        m.put("msg", "头像上传失败，请稍后重试!");
                        return m;
                    }
                }
            }

        }
        // 更新应聘者信息
        recruitAcceptanceDao.updateResumeSeeker(rs);
        return m;
    }
    
    @Override
    public List<ResumeBase> jlQuery(ResumeSeeker rs) {
        return  resumeBaseDao.jlQuery(rs);
    }

    @Override
    public List<CommunicationRecord> linkQuery(CommunicationRecord cr) {
        return  communicationRecordDao.linkQuery(cr);
    }

    @Override
    public List<CommunicationSms> smsQuery(ResumeSeeker rs) {
        return  communicationSmsDao.smsQuery(rs);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> quickAdd(CommunicationRecord cr, CommunicationDesc cd) {
        Map<String, Object> map=new HashMap<String,Object>();
        //无主题：判断数据库是否已存在跟进中主题，若无则新增,若有则不能新增，重新刷新输入
        cd.setCommunication(Common.getMyUser().getUsername());
        if(null==cd.getCommunicationId()){
            List<CommunicationRecord> crList=communicationRecordDao.linkQuery(cr);
            if(crList.size()>0&&crList.get(0).getCd().size()>0&&1==crList.get(0).getCd().get(0).getStatus()){
                map.put("flag", false);
                map.put("errorType", 1);
                map.put("msg", "对不起，改人员已有跟进中的新主题， 无法新增主题!");
                return map;
            }else{
                communicationRecordDao.inserRecord(cr);
                cd.setCommunicationId(cr.getId());
                System.out.print(cd.getNextTime().length());
                if(cd.getNextTime()==null||cd.getNextTime().trim().length()==0){
                    cd.setStatus(2);
                }else{
                    cd.setStatus(1);
                }
                communicationRecordDao.inserDesc(cd);
                map.put("flag", true);
                map.put("resultType", cd.getStatus());
                map.put("msg", "新增主题成功");
                return map;
            }
        }
       List<CommunicationDesc> cdList=communicationRecordDao.queryCommunicationDesc(cd);
       cd.setStatus(1);
       System.out.print(cd.getNextTime().length());
       if(cd.getNextTime()==null||cd.getNextTime().trim().length()==0){
           cd.setStatus(2);
       }
       map.put("resultType", cd.getStatus());
       if(cdList.size()>0){
           Integer nowStatus=cdList.get(0).getStatus();
           if(nowStatus!=2){
               //有主题：数据库最新状态未结束，判断日期是否有，没有则直接结束，若有日期继续跟进
               communicationRecordDao.inserDesc(cd);
               map.put("flag", true);
               map.put("msg", "新增沟通记录成功");
               return map;
           }else{
               map.put("flag", false);
               map.put("errorType", 2);
               map.put("msg", "对不起，当前沟通主题已经结束!!");
               return map;
           }
       }else{
           communicationRecordDao.inserDesc(cd);
           map.put("flag", true);
           map.put("msg", "新增沟通记录成功");
           return map;
       }
    }

    @Override
    public PageView toLinkQuery(PageView pageView, ResumeSeeker rs) {
        Map<String, Object> map = new HashMap<String, Object>();
        rs.setLinkUser(Common.getMyUser().getUsername());
        map.put("paging", pageView);
        map.put("t", rs);
        List<InterAppr> list = recruitAcceptanceDao.toLinkQuery(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public ResumeSeeker selectByOpenId(String openId) {
        return recruitAcceptanceDao.selectByOpenId(openId);
    }

    @Override
    public ResumeSeeker selectByPrimaryKey(Long id) {       
        return recruitAcceptanceDao.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> updateMyResumeBase(HttpServletRequest request, ResumeBase rs) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                String fileName = "photo";
                // 取得上传文件
                MultipartFile multiFile = multiRequest.getFile(fileName);
                if (null != multiFile && multiFile.getSize() > 0) {
                    String originalName = multiFile.getOriginalFilename();
                    if (!"".equals(originalName) && originalName != null) {
                        if (!StringUtils.isNotEmpty(rs.getHeadUrl())) {
                            // 上传头像
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")) {
                                rs.setHeadUrl(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "简历头像上传失败,请稍后重试!");
                                return map;
                            }
                        }else {
                            // 重新上传了头像，先处理头像
                            // 如果原头像地址不是http开头， 且不为空，则删除云盘的头像文件
                            if (rs.getHeadUrl().startsWith("http")) {

                            }else {
                                boolean isExist = ossUtil.isObjectExist(ossUtil.getClient(), bucketName, rs.getHeadUrl());
                                if (isExist) {
                                    ossUtil.deleteObject(ossUtil.getClient(), bucketName, rs.getHeadUrl());
                                }
                            }
                            String newFileName = headerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                            // 上传头像文件，
                            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, multiFile);
                            if ((boolean) uploadResult.get("flag")) {
                                rs.setHeadUrl(newFileName);
                            }else {
                                map.put("flag", false);
                                map.put("msg", "简历头像上传失败,请稍后重试!");
                                return map;
                            }
                        }
                    }
                }
                //判断是否是面试中完善我的简历跳转过来的链接
                long resumeId=rs.getId();
                if(resumeId!=0){
                    resumeBaseDao.updateByPrimaryKeySelective(rs); 
                }else{
                    // 更新或者创建我的简历信息
                    ResumeBase rb = resumeBaseDao.selectOriginalResumeByRsId(rs.getResumeSeekerId());
                    ResumeSeeker rSeeker = recruitAcceptanceDao.selectByPrimaryKey(rs.getResumeSeekerId());                
                    if (rb == null) {
                        rs.setOriginalFlag("1");  
                        rs.setTelephone(rSeeker.getPhone());
                        resumeBaseDao.insertSelective(rs);
                    }else {
                       rs.setId(rb.getId());
                       if(StringUtils.isEmpty(rb.getTelephone())){
                           rs.setTelephone(rSeeker.getPhone());
                       }
                       resumeBaseDao.updateByPrimaryKeySelective(rs);
                    }
                }
                map.put("flag", true);
                map.put("msg", "编辑我的简历个人信息成功!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "编辑我的简历个人信息失败!");
        }
        return map;
    }
    
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Map<String, Object> updatePhone(String msg, ResumeBase rb) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "修改联系手机号成功!");
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(new Date());
            rightNow.add(Calendar.MINUTE, -30);
            Date dt1=rightNow.getTime();
            String time = sdf.format(dt1);
            System.out.println(time);
            String phone=rb.getTelephone();
            String sendComment = smsTempDao.getMsg(phone,time); 
            if(StringUtils.isNotEmpty(sendComment)){
                int start=sendComment.indexOf("(");
                int end=sendComment.indexOf(")");
                String code=sendComment.substring(start+1, end);
                if(msg.equals(code)){
                    //判断是否是面试中完善我的简历跳转过来的链接
                    long resumeId=rb.getId();
                    if(resumeId!=0){
                        resumeBaseDao.updateByPrimaryKeySelective(rb); 
                    }else{
                        // 更新或者创建我的简历信息
                        ResumeBase myRb = resumeBaseDao.selectOriginalResumeByRsId(rb.getResumeSeekerId());
                        if (myRb == null) {
                            rb.setOriginalFlag("1");
                            resumeBaseDao.insertSelective(rb);
                        }else {
                           rb.setId(myRb.getId());
                           resumeBaseDao.updateByPrimaryKeySelective(rb);
                        }            
                    }         
                }else{
                    map.put("flag", false);
                    map.put("msg", "验证码输入错误,请重新输入!");
                } 
            }else{
                map.put("flag", false);
                map.put("msg", "没有查询到验证码或验证码已经失效,请重新点击发送!");
            }   
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("msg", "修改求职者联系手机号失败!");
        }
        return map;
    }

    @Override
    public Map<String, Object> removeBinding(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "解除微信绑定成功!");
        try{
            if(id==null){
                map.put("flag", false);
                map.put("msg", "微信未绑定!解绑失败!");
            }else{
               recruitAcceptanceDao.removeBinding(id);
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "解除微信绑定失败!");
        }
        return map;
    }
}
