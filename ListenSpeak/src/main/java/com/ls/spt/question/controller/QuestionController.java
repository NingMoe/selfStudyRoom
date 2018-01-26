package com.ls.spt.question.controller;

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
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.question.entity.LstQuestionType;
import com.ls.spt.question.service.LstQuestionTypeService;
import com.ls.spt.question.service.QuestionService;
import com.ls.spt.utils.OSSUtil;

@Controller
@RequestMapping(value = "/question/")
public class QuestionController {
    
    private final  Logger logger = LogManager.getLogger(QuestionController.class);
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private LstQuestionTypeService questionTypeService;
    
    @Autowired
    private OSSUtil ossUtil;

    @Value("${oss.bucket}")
    private String bucketName;

    @Value("${oss.lsAudioPath}")
    private String lsAudioPath;

    @Value("${oss.fileurl}")
    private String fileurl;
    
    /**
     * 进入题库类型列表页面
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/question/list");
        List<DicData> grades = dictionaryService.getDataByKey("grade");
        List<LstQuestionType> questionTypes = questionTypeService.getAll();
        mav.addObject("grades", grades);
        mav.addObject("questionTypes", questionTypes);
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView, LstQuestion question) {
        return questionService.query(pageView, question);
    }
    
    
    /**
     * 进入新增题库页面
     */
    @RequestMapping("toSelect")
    public ModelAndView toSelect() {
        ModelAndView mav = new ModelAndView("/question/toSelect");
        List<DicData> grades = dictionaryService.getDataByKey("grade");
        List<LstQuestionType> questionTypes = questionTypeService.getAll();
        mav.addObject("grades", grades);
        mav.addObject("questionTypes", questionTypes);
        return mav;
    }
    
    
    /**
     * 进入新增题库页面
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(LstQuestion question) {
        String view = "/question/simple_add";
        LstQuestionType questionType = questionTypeService.selectById(question.getQuestionType());
        if(questionType.getQuestionNum()>1){
            view =  "/question/multi_add";
        }
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", question);
        mav.addObject("questionType", questionType);
        return mav;
    }
    
    /**
     * 添加单项题目
     */
    @RequestMapping("addSimple")
    @ResponseBody
    public Map<String,Object> addSimpleQuestion(HttpServletRequest request,LstQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        logger.info("添加题目");
        try{
            /**
             * 将文本转换为WAV音频文件，上传阿里云，同时保存在题目中
             */
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                OSSClient ossClient = ossUtil.getClient();
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    String newFileName = lsAudioPath + System.currentTimeMillis() + ".wav";
                    System.out.println(fileurl + newFileName);
                    ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                    question.setParseAudio(newFileName);
                }
            }
            questionService.insertSimpleQuestion(question);
            result.put("flag", true);
            result.put("message", "新增成功");
        }catch(Exception e){
            logger.error("新增题目失败");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "新增题目失败");
        }
        logger.info("添加题目结束");
        return result;
    }
    
    /**
     * 添加多项题目
     */
    @RequestMapping("addMulti")
    @ResponseBody
    public Map<String,Object> addMultiQuestion(HttpServletRequest request,LstQuestion question,String details) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            /**
             * 将文本转换为WAV音频文件，上传阿里云，同时保存在题目中
             */
            List<LstQuestion> questions =  JSON.parseArray(details, LstQuestion.class);
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                OSSClient ossClient = ossUtil.getClient();
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    String newFileName = lsAudioPath + System.currentTimeMillis() + ".wav";
                    System.out.println(fileurl + newFileName);
                    ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                    
                    System.out.println(fileName);
                    if(fileName.indexOf("audioBlob")>-1){
                        Integer index = Integer.valueOf(fileName.substring(fileName.length()-1)); 
                        if(index>=questions.size()){
                            questions.get(index-questions.size()).setContentAudio(newFileName);
                        }else{
                            questions.get(index).setParseAudio(newFileName);
                        }
                    }
                }
            }
            question.setQuestionList(questions);
            questionService.insertMultiQuestion(question);
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch(Exception e){
            logger.error("添加题目失败！");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "添加题目失败");
        }
        return result;
    }
    
    /**
     * 进入新增题库页面
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(LstQuestion question) {
        String view = "";
        String type = question.getType();
        String code = question.getCode();
        LstQuestion q = null;
        if("2".equals(type)){
            view = "/question/multi_edit";
            q = questionService.getMultiQuestion(code);
        }else{
            view = "/question/simple_edit";
            q = questionService.getSimpleQuestion(code);
        }
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", q);
        mav.addObject("fileurl", fileurl);
        return mav;
    }
    
    /**
     * 进入新增题库页面
     */
    @RequestMapping("toView")
    public ModelAndView toView(LstQuestion question) {
        String view = "";
        String type = question.getType();
        String code = question.getCode();
        LstQuestion q = null;
        if("2".equals(type)){
            view = "/question/multi_view";
            q = questionService.getMultiQuestion(code);
        }else{
            view = "/question/simple_view";
            q = questionService.getSimpleQuestion(code);
        }
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", q);
        mav.addObject("fileurl", fileurl);
        return mav;
    }
    
    /**
     * 添加单项题目
     */
    @RequestMapping("editSimple")
    @ResponseBody
    public Map<String,Object> editSimpleQuestion(HttpServletRequest request,LstQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        /**
         * 将文本转换为WAV音频文件，上传阿里云，同时保存在题目中
         */
        logger.info("编辑题目");
        try{
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                OSSClient ossClient = ossUtil.getClient();
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    String newFileName = lsAudioPath + System.currentTimeMillis() + ".wav";
                    System.out.println(fileurl + newFileName);
                    
                    
                    ossUtil.deleteObject(ossClient, bucketName, question.getParseAudio());;
                    ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                    question.setParseAudio(newFileName);
                }
            }
            questionService.updateByPrimaryKeySelective(question);
            result.put("flag", true);
            result.put("message", "保存成功");
        }catch(Exception e){
            logger.error("编辑题目失败");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "编辑题目失败");
        }
        logger.info("编辑题目结束");
        return result;
    }
    
    /**
     * 更新多项题目
     */
    @RequestMapping("editMulti")
    @ResponseBody
    public Map<String,Object> editMultiQuestion(HttpServletRequest request,LstQuestion question,String details) {
        Map<String,Object> result = new HashMap<String,Object>();
        /**
         * 将文本转换为WAV音频文件，上传阿里云，同时保存在题目中
         */
        try{
            /**
             * 将文本转换为WAV音频文件，上传阿里云，同时保存在题目中
             */
            List<LstQuestion> questions =  JSON.parseArray(details, LstQuestion.class);
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                OSSClient ossClient = ossUtil.getClient();
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if ("".equals(originalName) || originalName == null) {
                        continue;
                    }
                    Integer index = Integer.valueOf(fileName.substring(fileName.length()-1)); 
                    String newFileName = lsAudioPath + System.currentTimeMillis() + ".wav";
                    System.out.println(fileurl + newFileName);
                    if(index>=questions.size()){
                        if(StringUtils.isNotEmpty(questions.get(index-questions.size()).getContentAudio()) && ossUtil.isObjectExist(ossClient, bucketName, questions.get(index-questions.size()).getContentAudio())){
                            ossUtil.deleteObject(ossClient, bucketName, questions.get(index-questions.size()).getContentAudio());
                        }
                        ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                        System.out.println(fileName);
                        questions.get(index-questions.size()).setContentAudio(newFileName);
                    }else{
                        if(StringUtils.isNotEmpty(questions.get(index).getParseAudio()) && ossUtil.isObjectExist(ossClient, bucketName, questions.get(index).getParseAudio())){
                            ossUtil.deleteObject(ossClient, bucketName, questions.get(index).getParseAudio());
                        }
                        ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);
                        System.out.println(fileName);
                        questions.get(index).setParseAudio(newFileName);
                    }
                    
                    
                }
            }
            question.setQuestionList(questions);
            questionService.editMultiQuestion(question);
            result.put("flag", true);
            result.put("message", "保存成功");
        }catch(Exception e){
            logger.error("添加题目失败！");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "添加题目失败");
        }
        return result;
    }
    
    
    /**
     * 更新多项题目
     */
    @RequestMapping("delQuestion")
    @ResponseBody
    public Map<String,Object> delQuestion(HttpServletRequest request,String questionCode) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            questionService.delQuestionByCode(questionCode);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error("删除题目失败！");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "删除题目失败");
        }
        return result;
    }
    
    
}
