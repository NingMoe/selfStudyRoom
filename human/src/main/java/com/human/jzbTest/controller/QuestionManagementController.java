package com.human.jzbTest.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.aliyun.oss.OSSClient;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;


@Controller
@RequestMapping("/jzbTest/question/")
public class QuestionManagementController {
    
    private final  Logger logger = LogManager.getLogger(QuestionManagementController.class);
    
    @Autowired
    private JzbQuestionService questionService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private JzbGradeService gradeService;
    
    @Autowired
    private JzbDeptService deptService;
    
    @Autowired 
    jzbKnowledgePointService pointService;
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.tiganPath}")
    private  String tiganPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/jzbTest/question/list");
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        return mav;
    }
    
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,JzbQuestion question){
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        question.setqDept(deptId+"");
        return  questionService.selectQuestionPage(pageView, question);
    }
    
    
    @RequestMapping(value="toSelect")
    public ModelAndView toSelect(){
        ModelAndView mav=new ModelAndView("/jzbTest/question/toSelect");
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        deptService.getDeptManagers(deptId);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("diffs", diffs);
        mav.addObject("deptId", deptId);
        return mav;
    }
    
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(String isTk){
        ModelAndView mav = null;
        if("1".equals(isTk)){
            mav = new ModelAndView("/jzbTest/question/add");
        }
        if("2".equals(isTk)){
            mav = new ModelAndView("/jzbTest/question/tk_add");
        }
        
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        deptService.getDeptManagers(deptId);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("diffs", diffs);
        mav.addObject("deptId", deptId);
        return mav;
    }
    
    
    /**
     * 进入填空题新增页面
     * @return
     */
    @RequestMapping(value="toAddTk")
    public ModelAndView toAddTk(){
        ModelAndView mav=new ModelAndView("/jzbTest/question/tk_add");
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        deptService.getDeptManagers(deptId);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("diffs", diffs);
        mav.addObject("deptId", deptId);
        return mav;
    }
    
    @RequestMapping(value="getKnowledgeByCondition")
    @ResponseBody
    public Map<String,Object> getKnowledgeByCondition(jzbKnowledgePoint point){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<jzbKnowledgePoint> points = pointService.queryByCondition(point);
            result.put("flag", true);
            result.put("Data", points);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", true);
            result.put("message", "查询知识点失败");
        }
        return result;
    }
    
    @RequestMapping(value="addQuestion")
    @ResponseBody
    public Map<String,Object> add(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("添加题目");
        try{
            Map<String,String> imgMap = new HashMap<String,String>();
            if(question.getqType().equals("1")){
                OSSClient ossClient = ossUtil.getClient();
                CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
                if (resolver.isMultipart(request)) {
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    // 取得request中的所有文件名
                    Iterator<String> iter = multiRequest.getFileNames();
                    while (iter.hasNext()) {
                        String fileName = iter.next();
                        // 取得上传文件
                        MultipartFile multiFile = multiRequest.getFile(fileName);
                        String originalName = multiFile.getOriginalFilename();
                        if("".equals(originalName)||originalName==null){
                            continue;
                        }
                        String newFileName = tiganPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                        Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                        if((boolean) uploadResult.get("flag")){
                            imgMap.put(fileName.substring(fileName.length()-1),newFileName);
                        }
                    }
                }
            }
            questionService.addQuestion(question,imgMap);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    
    @RequestMapping(value="addTkQuestion")
    @ResponseBody
    public Map<String,Object> addTk(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("添加题目");
        try{
            questionService.addTkQuestion(question);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    @RequestMapping(value="toSimpleEdit")
    public ModelAndView toSimleEdit(String id){
        ModelAndView mav=new ModelAndView("/jzbTest/question/simple_edit");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        JzbQuestion question = questionService.getSimpleQusetion(id);
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(question.getqDept()));
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        mav.addObject("question", question);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("diffs", diffs);
        mav.addObject("fileurl", fileurl);
        
        return mav;
    }
    
    
    @RequestMapping(value="toTkSimpleEdit")
    public ModelAndView toTkSimpleEdit(String id){
        ModelAndView mav=new ModelAndView("/jzbTest/question/tk_simple_edit");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        JzbQuestion question = questionService.getSimpleQusetion(id);
        String tkAnswer = question.getTkAnswer();
        List<String> tmp = new ArrayList<String>();
        if(tkAnswer.indexOf("@@@")>-1){
           String[] answerArr =  tkAnswer.split("@@@");
           for(String t:answerArr){
               if(StringUtils.isNotEmpty(t)){
                   tmp.add(t);
               }
           }
        }else{
            tmp.add(tkAnswer);
        }
        question.setTkAnswerList(tmp);
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(question.getqDept()));
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        mav.addObject("question", question);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("diffs", diffs);
        mav.addObject("fileurl", fileurl);
        
        return mav;
    }
    
    
    @RequestMapping(value="editSimpleQuestion")
    @ResponseBody
    public Map<String,Object> editSimpleQuestion(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("编辑普通选择题");
        try{
            Map<String,String> imgMap = new HashMap<String,String>();
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = tiganPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        imgMap.put(fileName.substring(fileName.length()-1),newFileName);
                    }
                }
            }
            questionService.editSimpleQuestion(question, imgMap);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    
    @RequestMapping(value="editTkSimpleQuestion")
    @ResponseBody
    public Map<String,Object> editTkSimpleQuestion(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("编辑普通填空题");
        try{
            questionService.editTkSimpleQuestion(question);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    @RequestMapping(value="toMultiEdit")
    public ModelAndView toMultiEdit(String id){
        ModelAndView mav=new ModelAndView("/jzbTest/question/multi_edit");
        JzbQuestion question = questionService.getMultipleQusetion(id);
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        mav.addObject("question", question);
        mav.addObject("diffs", diffs);
        mav.addObject("fileurl", fileurl);
        
        return mav;
    }
    
    @RequestMapping(value="toTkMultiEdit")
    public ModelAndView toTkMultiEdit(String id){
        ModelAndView mav=new ModelAndView("/jzbTest/question/tk_multi_edit");
        JzbQuestion question = questionService.getMultipleQusetion(id);
        List<DicData> diffs = dictionaryService.getDataByKey("JZB_QUES_DIFF");
        mav.addObject("question", question);
        mav.addObject("diffs", diffs);
        mav.addObject("fileurl", fileurl);
        
        return mav;
    }
    
    @RequestMapping(value="editMultiQuestion")
    @ResponseBody
    public Map<String,Object> editMultiQuestion(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("编辑普通选择题");
        try{
            questionService.editMultiQuestion(question);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    @RequestMapping(value="editTkMultiQuestion")
    @ResponseBody
    public Map<String,Object> editTkMultiQuestion(JzbQuestion question,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("编辑复杂填空题");
        try{
            questionService.editTkMultiQuestion(question);
            result.put("flag", true);
            result.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "保存失败");
        }
        return result;
    }
    
    @RequestMapping(value="editStatus")
    @ResponseBody
    public Map<String,Object> editStatus(JzbQuestion question){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("修改状态");
        try{
            questionService.editStatus(question);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "系统错误，请联系管理员");
        }
        return result;
    }
    
    
    @RequestMapping(value="delQuestion")
    @ResponseBody
    public Map<String,Object> delQuestion(JzbQuestion question){
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("删除题目");
        try{
            questionService.delQuestion(question);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "系统错误，请联系管理员");
        }
        return result;
    }
    
    
    @RequestMapping(value="delImg")
    @ResponseBody
    public Map<String, Object> delImg(Integer answerId) {
        logger.info("删除答案图片");
        Map<String, Object> map = new HashMap<String, Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            JzbQuestionAnswer answer = questionService.getAnswerById(answerId);
                ossUtil.deleteObject(ossClient, bucketName, answer.getaImg());
                questionService.delImg(answerId);
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
    
}
