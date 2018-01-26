package com.human.nologin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsTem;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.SmsTempService;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.ActCustomPhoto;
import com.human.bpm.entity.BpmTransitionConfig;
import com.human.bpm.entity.HrActNode;
import com.human.bpm.service.BpmConfigService;
import com.human.bpm.service.HrWorkflowService;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Users;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.service.PositionProcessService;
import com.human.resume.entity.ActFlow;
import com.human.resume.entity.EditBase;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.service.ResumeAttachmentService;
import com.human.resume.service.ResumeManagerService;
import com.human.resume.service.ResumePhotoService;
import com.human.resume.service.ResumeService;
import com.human.utils.SecurityHelper;


@Controller
@RequestMapping("/free/resume/")
public class FreeResumeController {
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    @Value("${urlPreKey}")
    private String urlPreKey;
    
    private final Logger logger = LogManager.getLogger(FreeResumeController.class);
    
    @Resource
    private ResumeService resumeService;
    
    @Resource
    private ResumeAttachmentService raService;
    
    @Resource
    private HrWorkflowService workFlowService;
    
    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private AreaInfoService areaInfoService;
    
    @Resource
    private ResumeManagerService managerService;
    
    @Resource
    private BpmConfigService configService;
    
    @Resource
    private SmsTempService smsService;
    
    @Resource
    private  UserDao userDao;
    
    @Resource
    private ResumePhotoService rpService;
    
    @Resource
    private PositionProcessService processService;
    
    
    @RequestMapping(value = "jdDesc")
    public ModelAndView jdDesc(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        try {
            String idStr = request.getParameter("id");
            Long id = Long.valueOf(SecurityHelper.decrypt(urlPreKey, idStr));
            String flowCode = request.getParameter("flowCode");
            flowCode = SecurityHelper.decrypt(urlPreKey, flowCode);
            String userName = request.getParameter("userName");
            userName = SecurityHelper.decrypt(urlPreKey, userName);
            if(id==null||flowCode==null||flowCode.trim().length()==0||userName==null||userName.trim().length()==0){
                return mav;
            }
            ResumeBase rb1 = resumeService.queryResumeDetail(id);
            ResumeAttachment resumenAtt = new ResumeAttachment();
            resumenAtt.setIsValid(false);
            resumenAtt.setResumeId(String.valueOf(id));
            List<ResumeAttachment> ra = raService.queryRa(resumenAtt);
            boolean enclosure = false;
            if (ra.size() > 0) {
                enclosure = true;
            }
            boolean ifQx = false;
            List<ActCustomComment> list = workFlowService.getAssignee(flowCode);
            
            HrActNode node = workFlowService.getCurrNode(flowCode);
            String currApprovers = "";
            if(node.getCurrNode().equals(HrPosition.ENDNODE)){
                currApprovers = "";
                node.setIsMsgDesc("0");
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
            
            for (ActCustomComment ac : list) {
                if (userName.equals(ac.getApprover())) {
                    ifQx = true;
                    break;
                }
            }
            mav.addObject("enclosure", enclosure);
            List<DicData> degrees = dictionaryService.getDataByKey("edu_degree");
            List<DicData> eduTypeList = dictionaryService.getDataByKey("edu_type");
            mav.addObject("degrees", degrees);
            mav.addObject("flowCode", flowCode);
            mav.addObject("ifQx", ifQx);
            mav.addObject("eduTypeList", eduTypeList);
            AreaInfo area = new AreaInfo();
            area.setAreaLevel(2);
            List<AreaInfo> areaInfo = areaInfoService.getArea(area);
            mav.addObject("areaInfo", areaInfo);
            mav.addObject("node", node);
            mav.addObject("rb", rb1);
            mav.addObject("filepath", filePath);
            mav.addObject("userName",userName);
            mav.setViewName("/nologin/jl_desc");
            return mav;
        }
        catch (Exception e) {
            logger.error(e);
            return mav;
        }
    }
    
    @RequestMapping("gtRecord")
    @ResponseBody
    public Map<String, Object> gtRecord(String resumeId) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            List<ActFlow> ac=managerService.getGtRecord(resumeId);
            m.put("flag", true);
            m.put("ac", ac);
        }catch (Exception e) {
            logger.error(e);
            m.put("flag", false);
            m.put("msg", "操作异常，请稍后重试!");
        }
        return m;
    }
    
    /**
     * 更新头像
     * @param file1
     * @param rb
     * @return
     */
    @RequestMapping(value="editResumeHead")
    @ResponseBody
    public Map<String, Object> editResumeHead(MultipartFile file1,ResumeBase rb) {
        Map<String, Object> m = new HashMap<String, Object>();
        m = managerService.editResumeHead(file1, rb);
        return m;
    }
    
    @RequestMapping(value="uploadResumePhoto")
    @ResponseBody
    public Map<String, Object> uploadResumePhoto(MultipartFile file2,Long resumeId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m = managerService.uploadResumePhoto(file2, resumeId);
        return m;
    }
    
    @RequestMapping(value="updateResumeBasic")
    @ResponseBody
    public Map<String, Object> updateResumeBasic(String  jstr) {
        Map<String, Object> m = new HashMap<String, Object>();
        EditBase eb=JSONObject.parseObject(jstr, EditBase.class);
        try {
            m = managerService.updateResumeBasicDetail(eb);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("更新简历异常!",e);
            m.put("flag", false);
            m.put("msg", "更新异常，请稍后重试!");
        }
        return m;
    }
    
    @RequestMapping("delResumePhoto")
    @ResponseBody
    public Map<String, Object> delResumePhoto(Long photoId,String path) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            managerService.delResumePhoto(photoId,path);
            m.put("flag", true);
            m.put("msg", "删除成功");
        }catch (Exception e) {
            logger.error(e);
            m.put("flag", false);
            m.put("msg", "操作异常，请稍后重试!");
        }
        return m;
    }
    
      @RequestMapping("dowEnclosure")
      public void dowEnclosure(Long id) {
           managerService.dowEnclosure(id);
      } 
      
      @RequestMapping("feedback")
      public ModelAndView feedback(String flowCode,String userName){
          ModelAndView mav=new ModelAndView();
          if(flowCode==null||flowCode.trim().length()==0||userName==null||userName.trim().length()==0){
             mav.setViewName("/nologin/denied");
              return mav;
          }
          mav.setViewName("/nologin/feedback");
          BpmTransitionConfig config = new BpmTransitionConfig();
          List<BpmTransitionConfig> trans = null;
          Task t = workFlowService.getCurrentTask(flowCode);
          if(t!=null){
              config.setProcessdefId(t.getProcessDefinitionId());
              config.setSourceNode(t.getTaskDefinitionKey());
              trans = configService.selectListByCondition(config);
              for(BpmTransitionConfig c : trans){
                  String conditionText = c.getConditionText();
                  c.setConditionValue(conditionText.substring(conditionText.indexOf("\"")+1, conditionText.lastIndexOf("\"")));
              }
          }
          boolean ifQx=false;
          ActCustomPhoto acp=new ActCustomPhoto();
          if(null!=flowCode&&flowCode.trim().length()>0){
              List<ActCustomComment> list=workFlowService.getAssignee(flowCode);
              String nowNode="";
              for(ActCustomComment ac:list){
                  if(userName.equals(ac.getApprover())){
                      ifQx=true;
                      nowNode=ac.getNodeId();
                      break;
                  }
              }
              if(ifQx){
                  //查询是否已经评论过
                  ActCustomComment acc=new ActCustomComment();
                  acc.setApprover(userName);
                  acc.setFlowCode(flowCode);
                  acc.setNodeId(nowNode);
                  List<ActCustomComment> ac= managerService.selectComment(acc);
                  //未评论过获取评分项，,进入新增页面
                  List<PositionProcessScoreItem> itemList= managerService.getItemScoreByFlowCode(flowCode);
                  mav.addObject("itemList",itemList);
                  if(ac.size()>0){
                      //如果评论过，只显示最新反馈
                      //mav.addObject("acPre", ac.get(0));
                  }
                  acp.setApprover(userName);
                  acp.setNodeId(nowNode);
                  mav.addObject("nowNode",nowNode);
              }
          }
          acp.setFlowCode(flowCode);
          List<ActCustomPhoto> acpList = resumeService.selectActCustomPhoto(acp);
          mav.addObject("acpList", acpList);
          mav.addObject("filePath", filePath);
          mav.addObject("flowCode",flowCode);
          mav.addObject("userName",userName);
          mav.addObject("trans", trans);
          mav.addObject("ifQx",ifQx);
          return mav;
      }  
      
      @RequestMapping("feedbackAdd")
      @ResponseBody
      public Map<String, Object> feedbackAdd(String jstr) {
          Map<String, Object> m = new HashMap<String, Object>();
          try {
              ActCustomComment ac1 = JSONObject.parseObject(jstr, ActCustomComment.class);
              boolean ifQx = false;
              String flowCode = ac1.getFlowCode();
              if (null != flowCode && flowCode.trim().length() > 0) {
                  List<ActCustomComment> list = workFlowService.getAssignee(flowCode);
                  for (ActCustomComment ac : list) {
                      if (ac1.getApprover()!=null&&ac1.getApprover().equals(ac.getApprover())) {
                          ifQx = true;
                          ac1.setNodeId(ac.getNodeId());
                          break;
                      }
                  }
                  if (ifQx) {
                      workFlowService.tjResumeMultiFlow(ac1);
                      m.put("flag", true);
                      m.put("msg", "评价成功提交!");
                  }
                  else {
                      m.put("flag", false);
                      m.put("msg", "对不起，您没有权限处理此操作!");
                  }
              }
              else {
                  m.put("flag", false);
                  m.put("msg", "对不起，您无法找到对应流程表示!");
                  return m;
              }
          }
          catch (Exception e) {
              e.printStackTrace();
              logger.error(e);
              m.put("flag", false);
              m.put("msg", "更新异常，请稍后重试!");
          }
          return m;
      }
      
    @RequestMapping("sendView")
    public ModelAndView sendView(SmsRecord sr, String userName) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        if (userName == null || userName.trim().length() == 0) {
            return mav;
        }
        Users user = new Users();
        user.setLoginName(userName);
        List<Users> list = userDao.queryUser(user);
        if (list.size() > 0) {
            SmsTem st = new SmsTem();
            st.setUserId(list.get(0).getId());
            List<SmsTem> stList = smsService.queryTemAll(st);
            List<SmsRecord> srList = smsService.queryMsgRecordNoPage(sr);
            mav.addObject("stList", stList);
            mav.addObject("srList", srList);
            mav.addObject("sr", sr);
            mav.addObject("userName", userName);
            mav.setViewName("/nologin/sendView");
            return mav;
        }
        else {
            return mav;
        }
    }
      
      @RequestMapping("sendMsg")
      @ResponseBody
      public Map<String,Object> sendMsg(SmsRecord sr,String userName) {
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("flag", false);
          try {
              if(userName==null||userName.trim().length()==0){
                  return map;
              }
              Users user=new Users();
              user.setLoginName(userName);
              List<Users> list=userDao.queryUser(user);
              if(list.size()>0){
                  Users u=list.get(0);
                  sr.setCompany(u.getCompanyId());
                  sr.setDeptId(u.getDeptId());
                  sr.setSendUser(u.getId());
                  sr.setSendName(u.getName());
                  smsService.sendMessage(sr);
                  map.put("flag", true);
              }else{
                  return map;
              }
          }
          catch (Exception e) {
              logger.error("发送短信错误", e);
          }
          return map; 
      }
      
    @RequestMapping("quickUploadResumPhoto")
    public ModelAndView quickUploadResumPhoto(Long resumeId) {
        ModelAndView mav = new ModelAndView("/nologin/uploadResumeIdPhoto");
        mav.addObject("resumeId", resumeId);
        ResumeBase rb = resumeService.selectByPrimaryKey(resumeId);
        mav.addObject("rb",rb);
        mav.addObject("filepath", filePath);
        return mav;
    }
    
    @RequestMapping("saveQuickPhoto")
    @ResponseBody
    public Map<String,Object> saveQuickPhoto(Long resumeId,String fileType,String base64String) {
        return managerService.saveQuickPhoto(resumeId,fileType,base64String);
    }
      
    @RequestMapping("uploadRephoto")
    public ModelAndView uploadRephoto(Long resumeId) {
        ModelAndView mav = new ModelAndView("/nologin/uploadRephoto");
        List<ResumePhoto> rp=rpService.selectByResumeId(resumeId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("rp", rp);
        mav.addObject("filepath", filePath);
        return mav;
    }
    
    @RequestMapping("saveBasePhoto")
    @ResponseBody
    public Map<String,Object> saveBasePhoto(HttpServletRequest req,Long resumeId) { 
        return managerService.saveBasePhoto(req,resumeId);
    }
    
    @RequestMapping("tjPhotoFeedBack")
    public ModelAndView tjPhotoFeedBack(Long resumeId) {
        ModelAndView mav = new ModelAndView("/nologin/tjPhotoFeedBack");
        mav.addObject("resumeId", resumeId);
        ResumeBase rb = resumeService.selectByPrimaryKey(resumeId);
        mav.addObject("rb",rb);
        mav.addObject("filepath", filePath);
        return mav;
    }
    
    
    @RequestMapping("quickUploadCustomPhoto")
    public ModelAndView quickUploadCustomPhoto(ActCustomPhoto acp) {
        ModelAndView mav = new ModelAndView("/nologin/uploadCustomPhoto");
        List<ActCustomPhoto> acpList = resumeService.selectActCustomPhoto(acp);
        mav.addObject("acp", acp);
        mav.addObject("acpList", acpList);
        mav.addObject("filepath", filePath);
        return mav;
    }
    
    @RequestMapping("saveActPhoto")
    @ResponseBody
    public Map<String,Object> saveActPhoto(HttpServletRequest req,ActCustomPhoto acp) { 
        return managerService.saveActPhoto(req,acp);
    }
    
    
    @RequestMapping("delActPhoto")
    @ResponseBody
    public Map<String, Object> delActPhoto(Long id,String photoUrl) {
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            managerService.delActPhoto(id,photoUrl);
            m.put("flag", true);
            m.put("msg", "删除成功");
        }catch (Exception e) {
            logger.error(e);
            m.put("flag", false);
            m.put("msg", "操作异常，请稍后重试!");
        }
        return m;
    }
    
}
