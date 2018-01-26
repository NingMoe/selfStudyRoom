package com.ls.spt.zuoye.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.entity.LstClassZuoyeDto;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;
import com.ls.spt.zuoye.service.LstZuoyeService;

@Controller
@RequestMapping(value = "/zuoyeScore/")
public class LstZuoyeScoreController {
    
    private final  Logger logger = LogManager.getLogger(LstZuoyeScoreController.class);
  
    @Resource
    private LstZuoyeService zuoyeService;

    @Value("${oss.fileurl}")
    private String fileurl;
    
    /**
     * 进入作业草稿箱页面
     */
    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/zuoye/score_list");
        List<LstClassZuoyeDto> noCompleteList = zuoyeService.getNoCompleteZuoye();
        mav.addObject("noCompleteList",noCompleteList);
        return mav;
    }
    
    /**
     * 获取已完成作业的分页
     * @param pageView
     * @return
     */
    @RequestMapping("getCompleteZuoye")
    @ResponseBody
    public PageView getCompleteZuoye(PageView pageView) {
        return zuoyeService.getCompleteZuoyePage(pageView);
    }

    /**
     * 进入作业草稿箱页面
     */
    @RequestMapping("toStudentList")
    public ModelAndView toStudentList() {
        return new ModelAndView("/zuoye/student_list");
    }
    
    /**
     * 根据班号及作业ID获取学生列表
     * @param pageView
     * @param studentZuoye
     * @return
     */
    @RequestMapping("getStudentList")
    @ResponseBody
    public PageView getStudentList(PageView pageView,LstStudentZuoye studentZuoye) {
        return zuoyeService.getStudentZuoyePage(pageView, studentZuoye);
    }
    
    /**
     * 进入批改作业页面
     */
    @RequestMapping("topgzy")
    public ModelAndView topgzy(LstZuoyeStudentAnswer answer,String flag) {
        Integer zuoyeId = answer.getZuoye_id();
        Integer questionXh = answer.getXh();
        List<LstQuestion> questions = zuoyeService.getZuoyeQuestion(zuoyeId);
        LstQuestion question = null;
        if(flag==null ||"1".equals(flag)){
            question = getNextQuestion(questions,questionXh);
        }else{
            question = getLastQuestion(questions,questionXh);
        }
                
        String view = "";
        String type = question.getType();
        String code = question.getCode();
        answer.setTotal_question_num(questions.size());
        LstQuestion q = null;
        if("2".equals(type)){
            view = "/zuoye/question_multi_pigai";
            q = zuoyeService.getMultiStudentQuestion(answer,code);
        }else{
            view = "/zuoye/question_simple_pigai";
            q = zuoyeService.getSimpleStudentQuestion(answer,code);
        }
        if(StringUtils.isNotEmpty(question.getNextXh())){
            q.setNextXh(question.getNextXh());
        }
        q.setXh(question.getXh());
        q.setTmNum(question.getTmNum());
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", q);
        mav.addObject("answer", answer);
        mav.addObject("fileurl", fileurl);
        return mav;
    }
    
    /**
     * 进入批改作业页面
     */
    @RequestMapping("pgSimpleZuoye")
    @ResponseBody
    public Map<String,Object> pgSimpleZuoye(LstZuoyeStudentAnswer answer) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            zuoyeService.pgSimpleZuoye(answer);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "批改作业失败");
        }
        
        return result;
    }
    
    @RequestMapping("zuoyeReport")
    @ResponseBody
    public ModelAndView classReport(LstStudentZuoye lz ){
        ModelAndView mav=new ModelAndView("/zuoye/class_zuoye_report");
        int tjNum=zuoyeService.getTjNum(lz,"zs");
        int nZhunsNum=zuoyeService.getTjNum(lz,"nzs");;
        return mav;
    }
    /**
     * 进入批改作业页面
     */
    @RequestMapping("pgMultiZuoye")
    @ResponseBody
    public Map<String,Object> pgMultiZuoye(String details,String isEnd) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<LstZuoyeStudentAnswer> answers = JSON.parseArray(details, LstZuoyeStudentAnswer.class);
            zuoyeService.pgMultiZuoye(answers,isEnd);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "批改作业失败");
        }
        return result;
    }
    
    
    private LstQuestion getNextQuestion(List<LstQuestion> questions,Integer currentXh){
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
    
    private LstQuestion getLastQuestion(List<LstQuestion> questions,Integer currentXh){
        LstQuestion q = null;
        if(currentXh==null){
            q = questions.get(0);
            q.setTmNum(1);
            if(questions.size()>1){
                q.setNextXh(questions.get(1).getXh());
            }
        }else{
            for(int i=questions.size()-2;i>=0;i--){
                Integer xh = Integer.valueOf(questions.get(i).getXh());
                if(xh.intValue() < currentXh.intValue()){
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
