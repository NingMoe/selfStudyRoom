package com.human.recruitment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.human.basic.entity.DicData;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.bpm.entity.BpmProcessDefinition;
import com.human.bpm.service.BpmConfigService;
import com.human.bpm.service.BpmProcessDefinitionService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.entity.Users;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.UserDeptService;
import com.human.manager.service.UserService;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.NoAccDegree;
import com.human.recruitment.entity.PositionAlias;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionMsUser;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.entity.PositionWatcher;
import com.human.recruitment.service.HrPositionService;
import com.human.recruitment.service.PositionProcessService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/position/")
public class PositionController {
    private final  Logger logger = LogManager.getLogger(PositionController.class);
    
    @Autowired
    private HrPositionService hrPositionService;
    
    @Autowired
    private PositionProcessService positionProcessService;
    
    @Autowired
    private HrCompanyService hrCompanyService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private BpmProcessDefinitionService bpmProcessDefinitionService;
    
    @Autowired
    private BpmConfigService bpmConfigService;
    
    @Autowired
    private UserService UserService;
    
    @Autowired
    private PositionProcessService processService;
    
    @Autowired
    private UserDeptService userDeptService;
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.postionPath}")
    private  String postionPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        
        HrCompany company = new HrCompany();
        company.setCompanyId(u.getCompanyId());
        company.setCompanyName(u.getCompanyName());
        ModelAndView mav=new ModelAndView("/recruitment/position/list");
        mav.addObject("orgs",orgs);
        mav.addObject("company",company);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,HrPosition hrPosition){
        MyUser u = Common.getMyUser();
        hrPosition.setLoginId(u.getUserid()+"");
        return  hrPositionService.getPositionPage(pageView, hrPosition);
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        
        HrCompany company = hrCompanyService.selectByPrimaryKey(u.getCompanyId());
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(2);
        area.setParentAreaCode(Integer.valueOf(company.getProvinceCode()));
        List<AreaInfo> citys = areaInfoService.getArea(area);
        List<DicData> attributes = dictionaryService.getDataByKey("position_attribute");
        List<DicData> natures = dictionaryService.getDataByKey("position_nature");
        List<DicData> classifications = dictionaryService.getDataByKey("postion_classification");
        List<DicData> degrees = dictionaryService.getDataByKey("edu_degree");
        List<DicData> years = dictionaryService.getDataByKey("position_workingyear");
        List<DicData> salaryTypes = dictionaryService.getDataByKey("position_salary_type");
        List<DicData> salaryRanges = dictionaryService.getDataByKey("position_salary_range");
        List<DicData> highlights = dictionaryService.getDataByKey("position_highlight");
        
        ModelAndView mav=new ModelAndView("/recruitment/position/add");
        mav.addObject("company",company);
        mav.addObject("orgs",orgs);
        mav.addObject("citys",citys);
        mav.addObject("attributes",attributes);
        mav.addObject("natures",natures);
        mav.addObject("classifications",classifications);
        mav.addObject("degrees",degrees);
        mav.addObject("years",years);
        mav.addObject("salaryRanges",salaryRanges);
        mav.addObject("salaryTypes",salaryTypes);
        mav.addObject("highlights",highlights);
        return mav;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(String jsonStr,String obContent,String qualifications){
        logger.info("添加职位");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrPosition hrPosition = JSON.parseObject(jsonStr, HrPosition.class);
            if(StringUtils.isNoneEmpty(obContent)){
                hrPosition.setObContent(obContent);
            }
            if(StringUtils.isNoneEmpty(qualifications)){
                hrPosition.setQualifications(qualifications);
            }
            hrPositionService.addHrPosition(hrPosition);
            result.put("flag", true);
            result.put("message", "添加职位成功");
        }catch(Exception e){
            logger.info(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加职位失败");
        }
        return result;
    }
    
    
    /**
     * 进入列表页面 
     * @return
     */
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Integer id){
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        
        HrCompany company = hrCompanyService.selectByPrimaryKey(u.getCompanyId());
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(2);
        area.setParentAreaCode(Integer.valueOf(company.getProvinceCode()));
        List<AreaInfo> citys = areaInfoService.getArea(area);
        
        List<DicData> attributes = dictionaryService.getDataByKey("position_attribute");
        List<DicData> natures = dictionaryService.getDataByKey("position_nature");
        List<DicData> classifications = dictionaryService.getDataByKey("postion_classification");
        List<DicData> degrees = dictionaryService.getDataByKey("edu_degree");
        List<DicData> years = dictionaryService.getDataByKey("position_workingyear");
        List<DicData> salaryTypes = dictionaryService.getDataByKey("position_salary_type");
        List<DicData> salaryRanges = dictionaryService.getDataByKey("position_salary_range");
        List<DicData> highlights = dictionaryService.getDataByKey("position_highlight");
        
        HrPosition position = hrPositionService.selectPostionDetailById(id);
        
        ModelAndView mav=new ModelAndView("/recruitment/position/edit");
        mav.addObject("citys",citys);
        mav.addObject("position",position);
        mav.addObject("company",company);
        mav.addObject("orgs",orgs);
        mav.addObject("attributes",attributes);
        mav.addObject("natures",natures);
        mav.addObject("classifications",classifications);
        mav.addObject("degrees",degrees);
        mav.addObject("years",years);
        mav.addObject("salaryRanges",salaryRanges);
        mav.addObject("salaryTypes",salaryTypes);
        mav.addObject("highlights",highlights);
        return mav;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> edit(String jsonStr,String obContent,String qualifications){
        logger.info("更换职位");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrPosition hrPosition = JSON.parseObject(jsonStr, HrPosition.class);
            if(StringUtils.isNoneEmpty(obContent)){
                hrPosition.setObContent(obContent);
            }
            if(StringUtils.isNoneEmpty(qualifications)){
                hrPosition.setQualifications(qualifications);
            }
            hrPositionService.editHrPosition(hrPosition);
            result.put("flag", true);
            result.put("message", "修改职位成功");
        }catch(Exception e){
            logger.info(e.getMessage());
            result.put("flag", false);
            result.put("message", "修改职位失败");
        }
        return result;
    }
    
    /**
     * 进入流程配置页面
     * @return
     */
    @RequestMapping(value="toProcessConfig")
    public ModelAndView toProcessConfig(Integer positionId,Integer type,String processDefId){
        PositionProcess p = new PositionProcess();
        String oldProcessDef = "";
        p.setPositionId(positionId);
        p.setType(type);
        PositionProcess process = positionProcessService.getPositionProcessByPrimaryCondition(p);
        if(process!=null){
            if(type==process.getType()){
                oldProcessDef = process.getProcessDef();
            }
            if(StringUtils.isEmpty(processDefId)||(StringUtils.isNotEmpty(processDefId) && processDefId.equals(process.getProcessDef()))){
                return toEditProcessConfig(process);
            }
        }
        List<BpmProcessDefinition> processDefs = bpmProcessDefinitionService.findAllProcessDef();
        BpmProcessDefinition currentProcessDef = null;
        for(BpmProcessDefinition bpd:processDefs){
            List<BpmNodeConfig> multiNodes = new ArrayList<BpmNodeConfig>();
            List<BpmNodeConfig> configs = bpmConfigService.getAllConfig(bpd.getId());
            for(BpmNodeConfig bnc:configs){
                if(bnc.getNodeType()!=null&&bnc.getNodeType().equals(BpmNodeConfig.NODETYPE_M)){
                    multiNodes.add(bnc);
                }
            }
            if(StringUtils.isNotEmpty(processDefId) && processDefId.equals(bpd.getId())){
                currentProcessDef = bpd;
            }
            multiNodes = setOperForListNodes(multiNodes,positionId,type);
            bpd.setNodes(multiNodes);
        }
        if(currentProcessDef==null){
            currentProcessDef = processDefs.get(0);
        }
        List<DicData> positionScoreItems = dictionaryService.getDataByKey("position_score_item");
        ModelAndView mav=new ModelAndView("/recruitment/position/bpmConfig");
        mav.addObject("processDefs", processDefs);
        mav.addObject("oldProcessDef", oldProcessDef);
        mav.addObject("positionId", positionId);
        mav.addObject("type", type);
        mav.addObject("positionScoreItems", positionScoreItems);
        mav.addObject("currentProcessDef", currentProcessDef);
        return mav;
    }
    
    
    
    @RequestMapping(value="toPositionRoleConfig")
    public ModelAndView toPositionRoleConfig(Integer positionId){
        ModelAndView mav=new ModelAndView("/recruitment/position/superConfig");
        HrPosition position = hrPositionService.getPositionSuperConfig(positionId);
        mav.addObject("position", position);
        return mav;
    }
    
    
    /**
     * 进入流程配置页面
     * @return
     */
    public ModelAndView toEditProcessConfig(PositionProcess positionProcess){
        Integer positionId = positionProcess.getPositionId();
        Integer type = positionProcess.getType();
        String processDefId = positionProcess.getProcessDef();
        List<BpmProcessDefinition> processDefs = bpmProcessDefinitionService.findAllProcessDef();
        BpmProcessDefinition currentProcessDef = null;
        String scoresJson = "";
        for(BpmProcessDefinition bpd:processDefs){
            List<BpmNodeConfig> multiNodes = new ArrayList<BpmNodeConfig>();
            List<BpmNodeConfig> configs = bpmConfigService.getAllConfig(bpd.getId());
            for(BpmNodeConfig bnc:configs){
                if(bnc.getNodeType()!=null&&bnc.getNodeType().equals(BpmNodeConfig.NODETYPE_M)){
                    multiNodes.add(bnc);
                }
            }
            multiNodes = setOperForListNodes(multiNodes,positionId,type);
            bpd.setNodes(multiNodes);
            if(currentProcessDef==null && StringUtils.isNoneEmpty(processDefId) && processDefId.equals(bpd.getId())){
                currentProcessDef = bpd;
                //setNodeConfig
                List<BpmNodeConfig> cuNodes = currentProcessDef.getNodes();
                cuNodes = setNodeConfig(cuNodes,positionProcess.getId());
                currentProcessDef.setNodes(cuNodes);
                List<PositionProcessScoreItem> scoreItems = positionProcessService.getScoreItemsByProcessId(positionProcess.getId());
                if(scoreItems!=null && scoreItems.size()>0){
                    scoresJson = JSON.toJSONString(scoreItems);
                }
            }
        }
        List<DicData> positionScoreItems = dictionaryService.getDataByKey("position_score_item");
        ModelAndView mav=new ModelAndView("/recruitment/position/editBpmConfig");
        mav.addObject("processDefs", processDefs);
        mav.addObject("positionId", positionId);
        mav.addObject("type", type);
        mav.addObject("positionScoreItems",positionScoreItems);
        mav.addObject("scoreConfig",positionScoreItems);
        mav.addObject("currentProcessDef", currentProcessDef);
        mav.addObject("positionProcess", positionProcess);
        mav.addObject("fileurl", fileurl);
        mav.addObject("scoresJson", scoresJson);
        return mav;
    }
    
    /**
     * addConfig
     * 添加流程配置
     */
    @RequestMapping(value="addProcessConfig")
    @ResponseBody
    public Map<String,Object> addProcessConfig(HttpServletRequest request,String jsonStr){
        logger.info("新增简历流程配置");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            jsonStr = jsonStr.replace('\'', '\"');
            PositionProcess process = JSON.parseObject(jsonStr, PositionProcess.class);
            if(StringUtils.isNotEmpty(process.getOldProcessDef())){
                PositionProcess oldProcess = new PositionProcess();
                oldProcess.setPositionId(process.getPositionId());
                oldProcess.setType(process.getType());
                oldProcess.setProcessDef(process.getOldProcessDef());
                oldProcess.setStatus(1);
                positionProcessService.disablePositionProcess(oldProcess);
            }
            
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = postionPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        int index = Integer.parseInt(fileName.substring(fileName.length()-1));
                        process.getConfigList().get(index).setPictureTask(newFileName);
                    }
                }
            }
            positionProcessService.addPositionProcess(process);
            result.put("flag", true);
            result.put("message", "职位流程配置成功");
        }catch(Exception e){
            logger.info(e.getMessage());
            result.put("flag", false);
            result.put("message", "职位流程配置失败");
        }
        return result;
    }
    
    
    /**
     * editProcessConfig
     * 编辑流程配置
     */
    @RequestMapping(value="editProcessConfig")
    @ResponseBody
    public Map<String,Object> editProcessConfig(HttpServletRequest request,String jsonStr){
        logger.info("编辑简历流程配置");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            jsonStr = jsonStr.replace('\'', '\"');
            PositionProcess process = JSON.parseObject(jsonStr, PositionProcess.class);
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = postionPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        int index = Integer.parseInt(fileName.substring(fileName.length()-1));
                        String pic = process.getConfigList().get(index).getPictureTask();
                        if(StringUtils.isNotEmpty(pic)){
                            ossUtil.deleteObject(ossClient, bucketName, pic);
                        }
                        process.getConfigList().get(index).setPictureTask(newFileName);
                    }
                }
            }
            positionProcessService.editPositionProcess(process);
            result.put("flag", true);
            result.put("message", "职位流程配置成功");
        }catch(Exception e){
            logger.info(e.getMessage());
            result.put("flag", false);
            result.put("message", "职位流程配置失败");
        }
        return result;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toAddBpmUsers")
    public ModelAndView toAddBpmUsers(PositionProcessUser processUser){
        ModelAndView mav=new ModelAndView("/recruitment/position/selectUsers");
        mav.addObject("processUser", processUser);
        return mav;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="addUser")
    @ResponseBody
    public Map<String,Object> addBpmUsers(PositionProcessUser positionProcessUser){
        Map<String,Object> result = new HashMap<String,Object>();
        String email = positionProcessUser.getEmail();
        if(StringUtils.isEmpty(email)){
            result.put("flag", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        try{
            Users u = new Users();
            u.setLoginName(email);
            List<Users> list = UserService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else{
                List<PositionProcessUser> users = processService.getProcessUserByCondition(positionProcessUser);
                if(users!=null && users.size()>0){
                    result.put("flag", false);
                    result.put("message", "该用户已添加");
                    return result;
                }
                positionProcessUser.setName(list.get(0).getName());
                processService.addProcessUser(positionProcessUser);
                result.put("flag", true);
                result.put("message", "添加成功");
                result.put("user",positionProcessUser);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="addHrUser")
    @ResponseBody
    public Map<String,Object> addHrUser(PositionHrUser hrUser){
        Map<String,Object> result = new HashMap<String,Object>();
        String userId = hrUser.getUserId();
        if(StringUtils.isEmpty(userId)){
            result.put("flag", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        try{
            Users u = new Users();
            u.setLoginName(userId);
            List<Users> list = UserService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else{
                boolean isExist = hrPositionService.isPositionUserExist(hrUser);
                if(isExist){
                    result.put("flag", false);
                    result.put("message", "该用户已添加");
                    return result;
                }
                hrUser.setUserName(list.get(0).getName());
                hrPositionService.insertPositionHrUser(hrUser);
                result.put("flag", true);
                result.put("message", "添加成功");
                result.put("hrUser",hrUser);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="addPositionWatcher")
    @ResponseBody
    public Map<String,Object> addPositionWatcher(PositionWatcher watcher){
        Map<String,Object> result = new HashMap<String,Object>();
        String watcherId = watcher.getWatcherId();
        if(StringUtils.isEmpty(watcherId)){
            result.put("flag", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        try{
            Users u = new Users();
            u.setLoginName(watcherId);
            List<Users> list = UserService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else{
                boolean isExist = hrPositionService.isPositionWatcherExist(watcher);
                if(isExist){
                    result.put("flag", false);
                    result.put("message", "该用户已添加");
                    return result;
                }
                watcher.setWatcherName(list.get(0).getName());
                hrPositionService.insertPositionWatcher(watcher);
                result.put("flag", true);
                result.put("message", "添加成功");
                result.put("watcher",watcher);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="delHrUser")
    @ResponseBody
    public Map<String,Object> delHrUser(PositionHrUser hrUser){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrPositionService.delPositionHrUser(hrUser);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="delWatcher")
    @ResponseBody
    public Map<String,Object> delWatcher(PositionWatcher watcher){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrPositionService.delPositionWatcher(watcher);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="addPositionMsUser")
    @ResponseBody
    public Map<String,Object> addPositionMsUser(PositionMsUser msUser){
        Map<String,Object> result = new HashMap<String,Object>();
        String msId = msUser.getMsId();
        if(StringUtils.isEmpty(msId)){
            result.put("flag", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        try{
            Users u = new Users();
            u.setLoginName(msId);
            List<Users> list = UserService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else{
                boolean isExist = hrPositionService.isPositionMsUserExist(msUser);
                if(isExist){
                    result.put("flag", false);
                    result.put("message", "该用户已添加");
                    return result;
                }
                msUser.setMsName(list.get(0).getName());
                hrPositionService.insertPositionMsUser(msUser);
                result.put("flag", true);
                result.put("message", "添加成功");
                result.put("msUser",msUser);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="delMsUser")
    @ResponseBody
    public Map<String,Object> delMsUser(PositionMsUser msUser){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrPositionService.delPositionMsUser(msUser);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="delUser")
    @ResponseBody
    public Map<String,Object> delUser(Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            processService.delProcessUser(id);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
    
    @RequestMapping(value="delImg")
    @ResponseBody
    public Map<String, Object> delImg(Integer configId,String pictureTask) {
        logger.info("删除节点图片 ");
        Map<String, Object> map = new HashMap<String, Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            ossUtil.deleteObject(ossClient, bucketName,pictureTask);
            PositionProcessNodeConfig nodeConfig = processService.selectNodeConfigById(configId);
            nodeConfig.setPictureTask("");
            processService.updatePositionNodeConfig(nodeConfig);
            map.put("flag", true);
            map.put("message", "删除图片成功!");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("删除图片失败");
            map.put("flag", false);
            map.put("message", "删除图片失败!");
        }
        return map;
    }
    
    private List<BpmNodeConfig> setOperForListNodes(List<BpmNodeConfig> nodes,Integer positionId,Integer type){
        for(BpmNodeConfig n:nodes){
            PositionProcessUser searchUser = new PositionProcessUser();
            searchUser.setNodeId(n.getNodeId());
            searchUser.setPositionId(positionId+"");
            searchUser.setRole(n.getAssigneeExp());
            searchUser.setType(type+"");
            List<PositionProcessUser> users = processService.getProcessUserByCondition(searchUser);
            if(users!=null && users.size()>0){
                n.setUsers(users);
            }
        }
        return nodes;
    }
    
    private List<BpmNodeConfig> setNodeConfig(List<BpmNodeConfig> nodes,Integer positionProcessId){
        for(BpmNodeConfig n:nodes){
            PositionProcessNodeConfig nodeConfig = positionProcessService.selectNodeConfigByProcessIdAndNodeId(new PositionProcessNodeConfig(positionProcessId,n.getNodeId()));
            if(n!=null){
                n.setPositionNodeConfig(nodeConfig);
            }
        }
        return nodes;
    }
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="editSuperConfig")
    @ResponseBody
    public Map<String,Object> editSuperConfig(String aliases,String nodegrees,HrPosition position){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            String[] aliasArr = aliases.split(",");
            List<PositionAlias> list = new ArrayList<PositionAlias>();
            if(aliasArr!=null && aliasArr.length>0){
                for(int i = 0;i<aliasArr.length;i++){
                    PositionAlias p = new PositionAlias();
                    if(StringUtils.isEmpty(aliasArr[i].trim())){
                        continue;
                    }
                    p.setAliasName(aliasArr[i].trim());
                    p.setPositionId(position.getId()+"");
                    if(!list.contains(p)){
                        list.add(p);
                    }
                }
            }
            
            
            String[] degreeArr = nodegrees.split(",");
            List<NoAccDegree> list1 = new ArrayList<NoAccDegree>();
            if(degreeArr!=null && degreeArr.length>0){
                for(int i = 0;i<degreeArr.length;i++){
                    NoAccDegree nad = new NoAccDegree();
                    if(StringUtils.isEmpty(degreeArr[i].trim())){
                        continue;
                    }
                    nad.setDegreeName(degreeArr[i].trim());
                    nad.setPositionId(position.getId()+"");
                    if(!list1.contains(nad)){
                        list1.add(nad);
                    }
                }
            }
            position.setPositionAlias(list);
            position.setNoDegrees(list1);
            hrPositionService.editHrPositionSuperConfig(position);
            result.put("flag", true);
            result.put("message", "职位配置成功");
        }catch(Exception e){
            logger.info(e.getMessage());
            result.put("flag", false);
            result.put("message", "职位配置失败");
        }
        return result;
    }
    
}
