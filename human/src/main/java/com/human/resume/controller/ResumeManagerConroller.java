package com.human.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.ActCustomPhoto;
import com.human.bpm.entity.BpmTransitionConfig;
import com.human.bpm.service.BpmConfigService;
import com.human.bpm.service.HrWorkflowService;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.resume.entity.ActFlow;
import com.human.resume.entity.EditBase;
import com.human.resume.entity.ResumeBase;
import com.human.resume.service.ResumeManagerService;
import com.human.resume.service.ResumeService;
import com.human.utils.Common;

@Controller
@RequestMapping("/recruit/resumeManager/")
public class ResumeManagerConroller {
    
    private final  Logger logger = LogManager.getLogger(ResumeManagerConroller.class);
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private AreaInfoService areaInfoService;
    
    @Resource
    private ResumeService resumeService;
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    @Resource
    private HrWorkflowService workFlowService;
    
    @Resource
    private BpmConfigService configService;
    
    @Resource
    private ResumeManagerService managerService;
    
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
    
    /**
     * 下载考试物料
     * @param id
     * @return
     */
      @RequestMapping("dowEnclosure")
      public void dowEnclosure(Long id) {
           managerService.dowEnclosure(id);
      } 
      
      @RequestMapping("feedback")
      public ModelAndView feedback(String flowCode){
          ModelAndView mav=new ModelAndView();
          mav.setViewName("/recruitment/acceptance/feedback");
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
              String localUser=Common.getMyUser().getUsername();
              String nowNode="";
              for(ActCustomComment ac:list){
                  if(localUser.equals(ac.getApprover())){
                      ifQx=true;
                      nowNode=ac.getNodeId();
                      break;
                  }
              }
              if(ifQx){
                  //查询是否已经评论过
                  ActCustomComment acc=new ActCustomComment();
                  acc.setApprover(localUser);
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
                  mav.addObject("nowNode",nowNode);
                  mav.addObject("localUser",localUser);
                  acp.setApprover(localUser);
                  acp.setNodeId(nowNode);
              }
          }
       
          
          acp.setFlowCode(flowCode);
          List<ActCustomPhoto> acpList = resumeService.selectActCustomPhoto(acp);
          mav.addObject("acpList", acpList);
          mav.addObject("filePath", filePath);
          mav.addObject("flowCode",flowCode);
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
                String localUser = Common.getMyUser().getUsername();
                for (ActCustomComment ac : list) {
                    if (localUser.equals(ac.getApprover())) {
                        ifQx = true;
                        ac1.setNodeId(ac.getNodeId());
                        break;
                    }
                }
                if (ifQx) {
                    ac1.setApprover(localUser);
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
    
}
