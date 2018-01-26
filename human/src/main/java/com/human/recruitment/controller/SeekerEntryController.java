package com.human.recruitment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.AttachMent;
import com.human.basic.entity.DicData;
import com.human.basic.entity.HrEthnic;
import com.human.basic.entity.HrNationality;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.MailTem;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.HrNationalityService;
import com.human.basic.service.MailTempService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.HrOrganizationService;
import com.human.manager.service.MailSendRecordService;
import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeEntryhandler;
import com.human.recruitment.entity.PositionMsUser;
import com.human.recruitment.service.HrPositionService;
import com.human.recruitment.service.SeekerEntryService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;
import com.human.utils.PlaceholderUtils;
import com.human.utils.SecurityHelper;

@Controller
@RequestMapping("/recruit/seekerEntry/")
public class SeekerEntryController {
    private final  Logger logger = LogManager.getLogger(SeekerEntryController.class);
    
    @Autowired
    private SeekerEntryService entryService;
    
    @Autowired
    private HrCompanyService hrCompanyService;

    @Autowired
    private HrPositionService positionService;
    
    @Autowired
    private HrOrganizationService organizationService;
    
    @Autowired
    private HrNationalityService nationalityService;
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private MailSendRecordService mailSendRecordService;
    
    @Autowired
    private MailTempService mailTempService;
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.offerPath}")
    private  String offerPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Value("${resumeUrlPre}")
    private String resumeUrlPre;
    
    @Resource
    private SecurityHelper sh;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        List<HrCompany> companys = hrCompanyService.findAll();
        ModelAndView mav=new ModelAndView("/recruitment/seekerEntry/list");
        mav.addObject("companys",companys);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,HrResumeEntryhandler entryHandler){
        return entryService.getPositionPage(pageView,entryHandler,Common.getMyUser());  
    }
    
    /**
     * 进入入职邮件页面
     * @return
     */
    @RequestMapping(value="toEmailConfig")
    public ModelAndView toEmailConfig(Integer id){
        HrResumeEntryhandler entryhandler = entryService.selectByPrimaryKey(id);
        ModelAndView mav=new ModelAndView("/recruitment/seekerEntry/emailConfig");
        MyUser mu = Common.getMyUser();
        HrPosition p = new HrPosition();
        p.setComid(mu.getCompanyId());
        List<HrPosition> positions = positionService.getPositionList(p);
        
        HrOrganization org = new HrOrganization();
        org.setCompany(mu.getCompanyId());
        List<HrOrganization> depts = organizationService.findOrgByCondition(org);
        List<DicData> contracts = dictionaryService.getDataByKey("recruitment_contract_type");
        
        MailTem mt = new MailTem();
        mt.setTemCompany(mu.getCompanyId());
        mt.setTemDept(mu.getDeptId());
        mt.setTemName("入职");
        List<MailTem> mails = mailTempService.getTemLit(mt);
        
        mav.addObject("contracts",contracts);
        mav.addObject("entryhandler",entryhandler);
        mav.addObject("fileurl",fileurl);
        mav.addObject("positions",positions);
        mav.addObject("depts",depts);
        mav.addObject("mails",mails);
        mav.addObject("companyName",mu.getComName());
        return mav;
    }
    
    @RequestMapping(value="uploadOffer")
    @ResponseBody
    public Map<String,Object> uploadOffer(HttpServletRequest request,HrResumeEntryhandler entryhandler){
        logger.info("上传Offer文件");
        Map<String,Object> result = new HashMap<String,Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        result.put("flag", false);
                        result.put("message", "请选择上传文件");
                        return result;
                    }
                    if(StringUtils.isNoneEmpty(entryhandler.getOffer())
                            &&ossUtil.isObjectExist(ossClient, bucketName, entryhandler.getOffer())){
                        ossUtil.deleteObject(ossClient, bucketName, entryhandler.getOffer());
                    }
                    String newFileName = offerPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        entryhandler.setOffer(newFileName);
                        entryhandler.setOfferName(originalName);
                        entryService.updateByPrimaryKeySelective(entryhandler);
                    }
                }
            }
            result.put("entryhandler", entryhandler);
            result.put("flag", true);
            result.put("message", "OFFER上传成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "上传OFFER失败");
        }
        return result;
    }
    
    
    @RequestMapping(value="uploadHeadPic")
    @ResponseBody
    public Map<String,Object> uploadHeadPic(HttpServletRequest request,HrResumeEntryhandler entryhandler){
        logger.info("上传入职头像");
        Map<String,Object> result = new HashMap<String,Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        result.put("flag", false);
                        result.put("message", "请选择头像");
                        return result;
                    }
                    if(StringUtils.isNotEmpty(entryhandler.getHeadPic())
                            &&ossUtil.isObjectExist(ossClient, bucketName,entryhandler.getHeadPic())){
                        ossUtil.deleteObject(ossClient, bucketName, entryhandler.getHeadPic());
                    }
                    String newFileName = offerPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        entryhandler.setHeadPic(newFileName);
                        entryService.updateByPrimaryKeySelective(entryhandler);
                    }
                }
            }
            result.put("flag", true);
            result.put("message", "OFFER上传成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "上传OFFER失败");
        }
        return result;
    }
    
    @RequestMapping(value = "downLoadOffer")
    public void downLoadOffer(HttpServletResponse response,HrResumeEntryhandler entryhandler){
        logger.info("下载OFFER");
        InputStream is = null;
        OutputStream ros = null;
        try {
            OSSClient ossClient = ossUtil.getClient();
            response.setHeader("Content-Disposition","attachment;fileName="+URLEncoder.encode(entryhandler.getOfferName(),"UTF-8"));
            is = ossUtil.getObjectInputStream(ossClient, bucketName, entryhandler.getOffer()); 
            ros = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) > 0) {
                ros.write(bytes, 0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                ros.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    
    /**
     * 进入入职邮件页面
     * @return
     */
    @RequestMapping(value="toSeekerDetail")
    public ModelAndView toSeekerDetail(Integer id,String seekerName){
        ModelAndView mav = null;
        HrEntryBase entry = entryService.getBaseSeeker(id);
        if(entry==null){
            mav=new ModelAndView("/recruitment/seekerEntry/seekerAddDetail");
            mav.addObject("entryHandlerId",id);
            mav.addObject("seekerName",seekerName);
        }else{
            entry = entryService.getComplexSeeker(entry.getId());
            mav=new ModelAndView("/recruitment/seekerEntry/seekerEditDetail");
            mav.addObject("entryBase",entry);
            mav.addObject("fileurl",fileurl);
        }
        
        List<HrEthnic> ethnics = nationalityService.getAllEthnic();
        List<HrNationality> nationalitys = nationalityService.getAllNationality();
        AreaInfo a = new AreaInfo();
        a.setAreaLevel(1);
        List<AreaInfo> provinces = areaInfoService.getArea(a);
        mav.addObject("ethnics",ethnics);
        mav.addObject("nationalitys",nationalitys);
        mav.addObject("provinces",provinces);
        return mav;
    }
    
    
    /**
     * 进入查看入职资料页面
     * @return
     */
    @RequestMapping(value="toViewDetail")
    public ModelAndView toViewDetail(Integer id){
        ModelAndView mav = new ModelAndView("/recruitment/seekerEntry/seekerViewDetail");
        HrEntryBase entry = entryService.getBaseSeeker(id);
        if(entry!=null){
            entry = entryService.getComplexSeeker(entry.getId());
            mav.addObject("entryBase",entry);
            mav.addObject("fileurl",fileurl);
        }
        List<HrEthnic> ethnics = nationalityService.getAllEthnic();
        List<HrNationality> nationalitys = nationalityService.getAllNationality();
        AreaInfo a = new AreaInfo();
        a.setAreaLevel(1);
        List<AreaInfo> provinces = areaInfoService.getArea(a);
        mav.addObject("ethnics",ethnics);
        mav.addObject("nationalitys",nationalitys);
        mav.addObject("provinces",provinces);
        return mav;
    }
    
    
    /**
     * 入职信息填写
     * @return
     */
    @RequestMapping(value="addSeekerDetail")
    @ResponseBody
    public Map<String,Object> addSeekerDetail(String entryBaseStr,String isTj){
        logger.info("入职资料填写");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrEntryBase entryBase = JSON.parseObject(entryBaseStr, HrEntryBase.class);
            entryService.addSeekerDetail(entryBase);
            if("1".equals(isTj)){
                HrResumeEntryhandler entryhandler = new HrResumeEntryhandler();
                entryhandler.setId(entryBase.getEntryHandlerId());
                entryhandler.setStatus(3);
                entryService.updateByPrimaryKeySelective(entryhandler);
            }
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 入职信息编辑
     * @return
     */
    @RequestMapping(value="editSeekerDetail")
    @ResponseBody
    public Map<String,Object> editSeekerDetail(String entryBaseStr,String isTj){
        logger.info("入职资料填写");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrEntryBase entryBase = JSON.parseObject(entryBaseStr, HrEntryBase.class);
            entryService.editSeekerDetail(entryBase);
            if("1".equals(isTj)){
                HrResumeEntryhandler entryhandler = new HrResumeEntryhandler();
                entryhandler.setId(entryBase.getEntryHandlerId());
                entryhandler.setStatus(3);
                entryService.updateByPrimaryKeySelective(entryhandler);
            }
            result.put("flag", true);
            result.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "修改失败");
        }
        
        return result;
    }
    
    
    /**
     * 入职信息编辑
     * @return
     */
    @RequestMapping(value="editSeeker")
    @ResponseBody
    public Map<String,Object> editSeeker(HrResumeEntryhandler entryhandler){
        logger.info("入职资料填写");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            entryService.updateByPrimaryKeySelective(entryhandler);
            result.put("flag", true);
            result.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "修改失败");
        }
        return result;
    }
    
    
    /**
     * 更新入职办理信息
     * @return
     */
    @RequestMapping(value="editEmailConfig")
    @ResponseBody
    public Map<String,Object> editEmailConfig(HttpServletRequest request,String emailConfigStr,String isSendOffer){
        logger.info("更新入职办理信息");
        Map<String,Object> result = new HashMap<String,Object>();
        String me = "保存";
        try{
            HrResumeEntryhandler entryhandler = JSON.parseObject(emailConfigStr, HrResumeEntryhandler.class);
            if("1".equals(isSendOffer)){
                me = "发送OFFER";
                //发送OFFER邮件以及体检邮件
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{entryhandler.getEmail()});
                boolean isNeedMs = false;
                String[] bcc = null;
                List<PositionMsUser> msUsers = positionService.getPositionMsUsers(Integer.valueOf(entryhandler.getEntryPosition()));
                if(msUsers!=null && msUsers.size()>0){
                    isNeedMs = true;
                    bcc = new String[msUsers.size()];
                    for(int i=0;i<msUsers.size();i++){
                        bcc[i] = msUsers.get(i).getMsId()+"@xdf.cn";
                    }
                }
                
                mailMessage.setType("2");
                //mailMessage.setMessage();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(entryhandler.getEntryTime().getTime());
                c.add(Calendar.DATE, -1);
                Map<String,String> templateMap=new HashMap<String,String>();
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
                DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");  
                templateMap.put("emp_name",entryhandler.getSeekerName()); 
                templateMap.put("time",sdf.format(entryhandler.getEntryTime()));
                templateMap.put("material_time",sdf1.format(c.getTime()));
                templateMap.put("dept",entryhandler.getDeptName());
                templateMap.put("position", entryhandler.getPositionName());
                templateMap.put("handlerAddr", entryhandler.getEntryHandlerAddr());
                templateMap.put("addr", entryhandler.getWorkAddr());
                templateMap.put("company",Common.getMyUser().getComName());
                templateMap.put("contacts", entryhandler.getEntryHandler());
                
                String msSendMsg = "";
                if(isNeedMs){
                    templateMap.put("accept","");
                    templateMap.put("refuse","");
                    msSendMsg = PlaceholderUtils.resolvePlaceholders(mailTempService.queryById(entryhandler.getTempId()).getTemDesc(), templateMap);
                }
                
                String approveUrl = sh.CreateAcceptOfferUrl(entryhandler.getId(),entryhandler.getSeekerName());
                String refuseUrl = sh.CreateRefuseOfferUrl(entryhandler.getId());
                templateMap.put("accept","<a  href=\""+approveUrl+"\">接受OFFER</a>");
                templateMap.put("refuse","<a href=\""+refuseUrl+"\">拒绝OFFER</a>");
                String sendMsg = PlaceholderUtils.resolvePlaceholders(mailTempService.queryById(entryhandler.getTempId()).getTemDesc(), templateMap);
               
                String offName = entryhandler.getOffer(); 
                String offerSu = offName.substring(offName.indexOf("."));
                mailMessage.setMessage(sendMsg);
                mailMessage.setSubject("入职通知");
                List<AttachMent> attachments = new ArrayList<AttachMent>();
                //获取阿里云上附件的流
                OSSClient ossClient = ossUtil.getClient();       
                InputStream inputStream= ossUtil.getObjectInputStream(ossClient, bucketName, offName); 
                AttachMent a = new AttachMent();
                a.setContentStream(inputStream);;
                a.setAttachmentPathType("1");
                a.setName("薪酬确认单"+offerSu);
                attachments.add(a);
                
                String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp");
                AttachMent a1 = new AttachMent();
                a1.setAttachmentPath(newFilePath+"/新东方合肥学校FY18员工福利制度.pdf");
                a1.setAttachmentPathType("2");
                a1.setName("新东方合肥学校FY18员工福利制度.pdf");
                attachments.add(a1);
                
                
                AttachMent a2 = new AttachMent();
                a2.setAttachmentPath(newFilePath+"/入职登记表填写规范.docx");
                a2.setAttachmentPathType("2");
                a2.setName("入职登记表填写规范.docx");
                attachments.add(a2);
                
                
                AttachMent a3 = new AttachMent();
                a3.setAttachmentPath(newFilePath+"/新员工入职登记表.pdf");
                a3.setAttachmentPathType("2");
                a3.setName("新员工入职登记表.pdf");
                attachments.add(a3);
                
                mailMessage.setAttachments(attachments);
                mailSendRecordService.sendMail(Common.getMyUser().getCompanyId(), mailMessage,entryhandler.getTempId()+"");
                
                if(isNeedMs){
                    MailMessage msMessage = new MailMessage(mailMessage);
                    msMessage.setTo(null);
                    msMessage.setBcc(bcc);
                    msMessage.setMessage(msSendMsg);
                    mailSendRecordService.sendMail(Common.getMyUser().getCompanyId(), msMessage,entryhandler.getTempId()+"");
                }
                
                entryhandler.setStatus(1);
            }
            entryService.updateByPrimaryKeySelective(entryhandler);
            result.put("flag", true);
            result.put("message", me+"成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", me+"失败");
        }
        return result;
    }
    
    /**
     * 更新入职办理信息
     * @return
     */
    @RequestMapping(value="getEmailContent")
    @ResponseBody
    public Map<String,Object> getEmailContent(String id){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            String content = mailTempService.queryById(Long.valueOf(id)).getTemDesc();
            result.put("flag", true);
            result.put("content", content);
        }catch(Exception e){
            result.put("flag", false);
        }
        return result;
    }
}
