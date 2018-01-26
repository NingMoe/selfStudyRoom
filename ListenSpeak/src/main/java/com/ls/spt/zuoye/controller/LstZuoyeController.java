package com.ls.spt.zuoye.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.question.entity.LstQuestionType;
import com.ls.spt.question.service.LstQuestionTypeService;
import com.ls.spt.question.service.QuestionService;
import com.ls.spt.utils.Common;
import com.ls.spt.zuoye.entity.LstZuoye;
import com.ls.spt.zuoye.service.LstZuoyeService;

@Controller
@RequestMapping(value = "/zuoye/")
public class LstZuoyeController {
    
    private final  Logger logger = LogManager.getLogger(LstZuoyeController.class);
  
    @Resource
    private LstZuoyeService zuoyeService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private LstQuestionTypeService questionTypeService;
    
    @Autowired
    private QuestionService questionService;
    
    @Value("${oss.fileurl}")
    private String fileurl;
    
    /**
     * 进入作业草稿箱页面
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/zuoye/list");
        
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView, LstZuoye zuoye) {
        return zuoyeService.query(pageView, zuoye);
    }
    
    
    /**
     * 进入新增作业页面
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView("/zuoye/add");
        Integer teacher = Common.getMyUser().getUserid();
        List<LstClass> classList = zuoyeService.getClassList(teacher);
        mav.addObject("classList", classList);
        return mav;
    }
    
    /**
     * 进入新增作业页面
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Integer id) {
        ModelAndView mav = new ModelAndView("/zuoye/edit");
        Integer teacher = Common.getMyUser().getUserid();
        List<LstClass> classList = zuoyeService.getClassList(teacher);
        LstZuoye zuoye = zuoyeService.selectZuoyeById(id);
        mav.addObject("classList", classList);
        mav.addObject("zuoye", zuoye);
        return mav;
    }
    
    /**
     * 进入新增题库页面
     */
    @RequestMapping("toYulan")
    public ModelAndView toView(Integer zuoyeId,Integer currentSort,String zuoyeName) {
        List<LstQuestion> questions = zuoyeService.getZuoyeQuestion(zuoyeId);
        LstQuestion question = getQuestion(questions,currentSort);
        String view = "";
        String type = question.getType();
        String code = question.getCode();
        LstQuestion q = null;
        if("2".equals(type)){
            view = "/zuoye/question_multi_view";
            q = questionService.getMultiQuestion(code);
        }else{
            view = "/zuoye/question_simple_view";
            q = questionService.getSimpleQuestion(code);
        }
        if(StringUtils.isNotEmpty(question.getNextXh())){
            q.setNextXh(question.getNextXh());
        }
        q.setXh(question.getXh());
        q.setTmNum(question.getTmNum());
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", q);
        mav.addObject("zuoyeId", zuoyeId);
        mav.addObject("zuoyeName", zuoyeName);
        mav.addObject("fileurl", fileurl);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(LstZuoye zuoye) {
        logger.info("添加作业----");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer createUser = Common.getMyUser().getUserid().intValue();
            zuoye.setCreateUser(createUser);
            zuoyeService.insertLstZuoye(zuoye);
            map.put("flag", true);
            map.put("message", "新增作业成功");
        }
        catch (Exception e) {
            logger.error("添加作业失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增作业异常");
        }
        return map;
    }
    
    
    @RequestMapping("delZuoye")
    @ResponseBody
    public Map<String, Object> delZuoye(Integer id) {
        logger.info("删除作业----");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            zuoyeService.deleteZuoye(id);;
            map.put("flag", true);
            map.put("message", "删除作业成功");
        }
        catch (Exception e) {
            logger.error("删除作业失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除作业异常");
        }
        return map;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(LstZuoye zuoye) {
        logger.info("添加作业----");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            zuoyeService.editLstZuoye(zuoye);
            map.put("flag", true);
            map.put("message", "编辑作业成功");
        }
        catch (Exception e) {
            logger.error("编辑作业失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑作业异常");
        }
        return map;
    }
    
    
    /**
     * 进入新增作业页面
     */
    @RequestMapping("toAddQuestion")
    public ModelAndView toAddQuestion(Integer id) {
        ModelAndView mav = new ModelAndView("/zuoye/addQuestion");
        List<DicData> grades = dictionaryService.getDataByKey("grade");
        List<LstQuestionType> questionTypes = questionTypeService.getAll();
        mav.addObject("grades", grades);
        mav.addObject("questionTypes", questionTypes);
        
        return mav;
    }
    
    @RequestMapping("getZuoyeQuestionList")
    @ResponseBody
    public PageView getZuoyeQuestionList(PageView pageView, Integer id) {
        List<LstQuestion> existList = zuoyeService.getZuoyeQuestionList(id);
        pageView.setData(existList);
        pageView.setCount(1000);
        pageView.setCode("0");
        pageView.setMsg("");
        return pageView;
    }
    
    @RequestMapping("delQuestion")
    @ResponseBody
    public Map<String,Object> delQuestion(LstQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            zuoyeService.delQuestion(question);
            result.put("flag", true);
            result.put("message","删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message","删除出错");
        }
        return result;
    }
    
    @RequestMapping("updateQuestionXh")
    @ResponseBody
    public Map<String,Object> updateQuestionXh(LstQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            zuoyeService.updateQuestionXh(question);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message","编辑出错");
        }
        return result;
    }
    
    @RequestMapping("addZuoyeQuestion")
    @ResponseBody
    public Map<String,Object> addZuoyeQuestion(LstQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            zuoyeService.addZuoyeQuestion(question);
            result.put("flag", true);
            result.put("message","添加成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message","添加出错");
        }
        return result;
    }
    
    @RequestMapping("release")
    @ResponseBody
    public Map<String,Object> release(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            zuoyeService.releaseZuoye(id);
            result.put("flag", true);
            result.put("message","作业发布成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message","作业发布出错");
        }
        return result;
    }
    
    private LstQuestion getQuestion(List<LstQuestion> questions,Integer currentXh){
        LstQuestion q = null;
        if(currentXh==null){
            q = questions.get(0);
            q.setTmNum(1);
            if(questions.size()>1){
                q.setNextXh(questions.get(1).getXh());
            }
        }else{
            for(int i=0;i<questions.size();i++){
                Integer xh = Integer.valueOf(questions.get(i).getXh());
                if(xh.intValue() > currentXh.intValue()){
                    q = questions.get(i);
                    q.setTmNum(i+1);
                    if(i<questions.size()-1){
                        q.setNextXh(questions.get(i+1).getXh());
                    }
                    break;
                }
            }
        }
        return q;
    }
    
}
