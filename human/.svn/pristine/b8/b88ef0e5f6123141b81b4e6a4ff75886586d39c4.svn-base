package com.human.bpm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.human.bpm.entity.BpmProcessDefinition;
import com.human.bpm.entity.BpmTaskActivity;
import com.human.bpm.service.BpmConfigService;
import com.human.bpm.service.BpmProcessDefinitionService;
import com.human.bpm.service.BpmTaskService;
import com.human.utils.PageView;

/**
 * 工作流程流程部署
 * @author cys
 *
 */
@Controller
@RequestMapping("/bpm/processDef")
public class BpmProcessDefController {
    
    private final  Logger logger = LogManager.getLogger(BpmProcessDefController.class);
    
    @Autowired
    BpmProcessDefinitionService bpmProcessDefinitionService;
    
    @Autowired
    BpmTaskService bpmTaskService;
    
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
        
    @Autowired
    ProcessEngineFactoryBean processEngine;
    
    @Autowired
    HistoryService historyService;
    
    @Autowired
    RepositoryService repositoryService;
    
    @Autowired
    RuntimeService runtimeService;
    
    @Autowired
    BpmConfigService configService;
    /**
     * 进入流程定义列表页面
     * @return
     */
    @RequestMapping("toProcessDefList")
    public ModelAndView province() {
        return new ModelAndView("/processDef/list");
    }
    
    
    /**
     * 分页查询流程定义结果
     * @param pageView
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    @ResponseBody
    public PageView queryPdPage(PageView pageView,BpmProcessDefinition bpmProcessDefinition) {
        return bpmProcessDefinitionService.findAllProcessDefinition(pageView,bpmProcessDefinition);
    }
    
    /**
     * 流程挂起
     * @param pageView
     * @return
     */
    @RequestMapping(value = "suspend", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> suspend(BpmProcessDefinition bpmProcessDefinition) {
        Map<String,Object> map = new HashMap<String,Object>();
        String message = "";
        try{
            
            if("1".equals(bpmProcessDefinition.getStatus())){
                repositoryService.suspendProcessDefinitionById(bpmProcessDefinition.getId());
                message= "禁用";
            }
            if("2".equals(bpmProcessDefinition.getStatus())){
                repositoryService.activateProcessDefinitionById(bpmProcessDefinition.getId());
                message= "启用";
            }
            map.put("flag", true);
            map.put("message", message+"成功");
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", message+"失败");
        }
        
        return map;
    }
    
    
    /**
     * 进入流程定义新增页面
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView("/processDef/add");
    }
    
    
    
    /**
     * 新增部署
     * @param request
     * @param pdName
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request,String delpoyName) {
        logger.info("部署流程定义");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    if (!multiFile.isEmpty()) {
                        String deployId = bpmProcessDefinitionService.deployFromZip(delpoyName, multiFile.getInputStream());
                        configService.doCustomConfig(deployId);
                    }
                }
            }
            map.put("flag", true);
            map.put("message", "部署成功!");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("部署失败");
            map.put("flag", false);
            map.put("message", "上传失败!");
        }
        return map;
    }
    
    /**
     * 进入流程图查看页面
     * @param processDefinitionId
     * @return
     */
    @RequestMapping("toViewFlows")
    public ModelAndView viewFlows(String processDefinitionId) {
        ModelAndView mav = new ModelAndView("/processDef/viewPd");
        mav.addObject("processDefinitionId", processDefinitionId);
        return mav;
    }
    
    
    /**
     * 进入流程图查看页面
     * @param pid
     * @return
     */
    @RequestMapping("toViewProcessInstance")
    public ModelAndView toViewProcessInstance(String pid) {
        ModelAndView mav = new ModelAndView("/processDef/viewProcessInstance");
        mav.addObject("pid", pid);
        return mav;
    }
    
    /**
     * 进入流程图查看页面
     * @param pid
     * @return
     */
    @RequestMapping("/viewProcessInstance")
    public void viewFlows(HttpServletResponse response,@RequestParam("pid") String processInstanceId) {
        try {
            HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            BpmnModel bpmnModel = bpmProcessDefinitionService.getBpmnModelByPdid(processInstance
                    .getProcessDefinitionId());
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", 
                    processEngine.getProcessEngineConfiguration().getActivityFontName(), 
                    processEngine.getProcessEngineConfiguration().getLabelFontName(),
                    processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                    processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0);
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
      /**
       * 读取带跟踪的图片
       */
      @RequestMapping(value = "/viewProcessInstance/trace/auto")
      public void readResource(String executionId, HttpServletResponse response)
              throws Exception {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
        // 不使用spring请使用下面的两行代码
//      ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
//      Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());

        // 使用spring注入引擎请使用下面的这行代码
        Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
          response.getOutputStream().write(b, 0, len);
        }
      }
    
    
    @RequestMapping("traceFlows")
    public void traceFlows(HttpServletResponse response,String processInstanceId) {
        try {
            HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            
            BpmnModel bpmnModel = bpmProcessDefinitionService.getBpmnModelByPdid(processInstance.getProcessDefinitionId());
            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
            List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
            //高亮环节id集合
            List<String> highLightedActivitis = new ArrayList<String>();
            //高亮线路id集合
            List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);

            for(HistoricActivityInstance tempActivity : highLightedActivitList){
                String activityId = tempActivity.getActivityId();
                highLightedActivitis.add(activityId);
            }
            
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            
            
            
            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, 
                    "png", highLightedActivitis, highLightedFlows, processEngine.getProcessEngineConfiguration().getActivityFontName(), 
                    processEngine.getProcessEngineConfiguration().getLabelFontName(),
                    processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                    processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0);
            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
       * 输出跟踪流程信息
       *
       * @param processInstanceId
       * @return
       * @throws Exception
       */
      @RequestMapping(value = "/traceProcess")
      @ResponseBody
      public List<BpmTaskActivity> traceProcess(@RequestParam("pid") String processInstanceId) throws Exception {
          List<BpmTaskActivity> activityInfos = bpmTaskService.getTaskActivities(processInstanceId);
        return activityInfos;
      }
    
    
    
    /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
    
}
