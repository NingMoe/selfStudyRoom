package com.human.recruitment.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.basic.entity.Sms;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsTem;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.SmsTempService;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.HrActNode;
import com.human.bpm.service.HrWorkflowService;
import com.human.recruitment.entity.CommunicationDesc;
import com.human.recruitment.entity.CommunicationRecord;
import com.human.recruitment.entity.CommunicationSms;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.PositionProcessService;
import com.human.recruitment.service.RecruitAcceptanceService;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.entity.ResumeSnapshot;
import com.human.resume.service.ResumeAttachmentService;
import com.human.resume.service.ResumeService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/recruit/acceptance/")
public class RecruitAcceptanceController {
    
    private final  Logger logger = LogManager.getLogger(RecruitAcceptanceController.class);
    @Resource
    private RecruitAcceptanceService recruitAcceptanceService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private AreaInfoService areaInfoService;
    
    @Resource
    private ResumeService resumeService;
    
    @Resource
    private ResumeAttachmentService raService;
    
    @Resource
    private HrWorkflowService workFlowService;
    
    @Resource
    private SmsTempService smsService;
    
    @Resource
    private PositionProcessService processService;
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView index(String tel,String name){
        ModelAndView mav=new ModelAndView("/recruitment/acceptance/index");
        mav.addObject("tel",tel);
        mav.addObject("name",name);
        return mav;
    }
    
    /**
     * 首页搜索查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public Map<String, Object> query(ResumeSeeker rs){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rsList", recruitAcceptanceService.queryResumeSeekerList(rs));
        return m;
    }
    
    
    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView("/recruitment/acceptance/new_applicant");
        List<DicData> degrees = dictionaryService.getDataByKey("edu_degree"); 
        List<DicData> eduTypeList = dictionaryService.getDataByKey("edu_type");
        mav.addObject("degrees", degrees);
        mav.addObject("eduTypeList", eduTypeList);
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(2);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
    
    @RequestMapping(value="saveResumeSeeker")
    @ResponseBody
    public Map<String, Object> saveResumeSeeker(MultipartFile file1, ResumeSeeker rs) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            m = recruitAcceptanceService.saveResumeSeeker(file1, rs);
        }
        catch (Exception e) {
            logger.error(e);
            m.put("flag", false);
            m.put("msg", "新增异常，请稍后重试!");
        }
        return m;
    }
    @RequestMapping(value="updateResumeSeeker")
    @ResponseBody
    public Map<String, Object> updateResumeSeeker(MultipartFile file1, ResumeSeeker rs) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            m = recruitAcceptanceService.updateResumeSeeker(file1, rs);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            m.put("flag", false);
            m.put("msg", "更新异常，请稍后重试!");
        }
        return m;
    }
    @RequestMapping(value = "subResumel")
    public ModelAndView subResumel(Long id) {
        ModelAndView mav = new ModelAndView("/recruitment/acceptance/applicant_desc");
        ResumeSeeker rs=new ResumeSeeker();
        rs.setId(id);
       List<ResumeSeeker> rsList= recruitAcceptanceService.queryResumeSeekerList(rs);
       if(rsList.size()>0){
           ResumeSeeker rs1=rsList.get(0);
           List<DicData> degrees = dictionaryService.getDataByKey("edu_degree"); 
           List<DicData> eduTypeList = dictionaryService.getDataByKey("edu_type");
           mav.addObject("degrees", degrees);
           mav.addObject("eduTypeList", eduTypeList);
           AreaInfo area = new AreaInfo();
           area.setAreaLevel(2);
           List<AreaInfo> areaInfo = areaInfoService.getArea(area);
           mav.addObject("areaInfo", areaInfo);
           mav.addObject("rs", rs1);
           mav.addObject("filepath",filePath);
       }
        return mav;
    }
    
    @RequestMapping(value="jlQuery")
    @ResponseBody
    public Map<String, Object> jlQuery(ResumeSeeker rs){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("jlList", recruitAcceptanceService.jlQuery(rs));
        return m;
    }
    
    @RequestMapping(value="linkQuery")
    @ResponseBody
    public Map<String, Object> linkQuery(CommunicationRecord cr){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("linkList", recruitAcceptanceService.linkQuery(cr));
        return m;
    }
    
    @RequestMapping(value="smsQuery")
    @ResponseBody
    public Map<String, Object> smsQuery(SmsRecord rs){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("smsList",smsService.queryMsgRecordNoPage(rs));
        return m;
    }
    
    @RequestMapping(value="quickAdd")
    @ResponseBody
    public Map<String, Object> quickAdd(CommunicationRecord cr, CommunicationDesc cd) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            m = recruitAcceptanceService.quickAdd(cr, cd);
        }
        catch (Exception e) {
            logger.error(e);
            m.put("flag", false);
            m.put("errorType", 3);
            m.put("msg", "处理异常，请稍后重试");
        }
        return m;
    }
    
    @RequestMapping(value = "jdDesc")
    public ModelAndView jdDesc(ResumeBase rb) {
        ModelAndView mav = new ModelAndView("/recruitment/acceptance/jl_desc");
        ResumeBase rb1 = resumeService.queryResumeDetail(rb.getId());
        ResumeAttachment resumenAtt=new ResumeAttachment();
        resumenAtt.setIsValid(false);
        resumenAtt.setResumeId(String.valueOf(rb.getId()));
        List<ResumeAttachment> ra=raService.queryRa(resumenAtt);
        boolean enclosure=false;
        if(ra.size()>0){
            enclosure=true;
        }
        boolean ifQx=false;
        if(null!=rb.getFlowCode()&&rb.getFlowCode().trim().length()>0){
            List<ActCustomComment> list=workFlowService.getAssignee(rb.getFlowCode());
            String localUser=Common.getMyUser().getUsername();
            for(ActCustomComment ac:list){
                if(localUser.equals(ac.getApprover())){
                    ifQx=true;
                    break;
                }
            }
            HrActNode node = workFlowService.getCurrNode(rb.getFlowCode());
            String currApprovers = "";
            if(node.getCurrNode().equals(HrPosition.ENDNODE)){
                node.setIsMsgDesc("0");
                currApprovers = "";
            }else if(node.getCurrNode().equals(HrPosition.RENLINODE)){
                node.setIsMsgDesc("0");
                List<PositionHrUser> hrUsers = workFlowService.getPositionHrUsersByPositionId(Integer.valueOf(node.getPositionId()));
                if(hrUsers!=null && hrUsers.size()>0){
                    for(PositionHrUser u:hrUsers){
                        currApprovers += StringUtils.isEmpty(currApprovers)?u.getUserName():","+u.getUserName();
                    }
                }
            }else{
                node.setIsMsgDesc("1");
                PositionProcessUser u = new PositionProcessUser();
                u.setPositionId(node.getPositionId());
                u.setNodeId(node.getCurrNode());
                u.setType(node.getType());
                List<PositionProcessUser> users = processService.getProcessUserByCondition(u);
                if(users!=null && users.size()>0){
                    for(PositionProcessUser u1:users){
                        currApprovers += StringUtils.isEmpty(currApprovers)?u1.getName():","+u1.getName();
                    }
                }
            }
            node.setAssigneeStr(currApprovers);
            mav.addObject("node", node);
        }
        mav.addObject("enclosure", enclosure);
        List<DicData> degrees = dictionaryService.getDataByKey("edu_degree");
        List<DicData> eduTypeList = dictionaryService.getDataByKey("edu_type");
        mav.addObject("degrees", degrees);
        mav.addObject("flowCode",rb.getFlowCode());
        mav.addObject("ifQx",ifQx);
        mav.addObject("eduTypeList", eduTypeList);
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(2);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        mav.addObject("rb", rb1);
        mav.addObject("filepath", filePath);
        return mav;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toLinkView")
    public ModelAndView toLinkView(){
        ModelAndView mav=new ModelAndView("/recruitment/toLink/index");
        return mav;
    }
    /**
     * 分页查询待沟通的人
     * @return
     */
    @RequestMapping(value="toLinkQuery")
    @ResponseBody
    public PageView query(PageView pageView, ResumeSeeker rs){
        return  recruitAcceptanceService.toLinkQuery(pageView, rs);
    }
    
    @RequestMapping(value = "readSendView")
    public ModelAndView readSendView(CommunicationSms cs) {
        ModelAndView mav=new ModelAndView("/recruitment/acceptance/sendMsgView");
        SmsTem st=new SmsTem();
        st.setUserId(Common.getMyUser().getUserid());
        List<SmsTem> stList=smsService.queryTemAll(st);
        mav.addObject("stList", stList);
        mav.addObject("cs", cs);
        return mav;
    }
    
}
