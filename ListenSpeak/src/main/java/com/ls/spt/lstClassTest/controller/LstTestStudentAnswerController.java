package com.ls.spt.lstClassTest.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.lstClassTest.service.LstStudentTestService;
import com.ls.spt.lstClassTest.service.LstTestStudentAnswerService;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;

@Controller
@RequestMapping(value="/lstTestStudentAnswer/")
public class LstTestStudentAnswerController {
    
    private final  Logger logger = LogManager.getLogger(LstTestStudentAnswerController.class);
    
    @Resource
    LstTestStudentAnswerService lsttestStudentAnswerService;
    
    @Resource
    LstStudentTestService lststudenttestService;
    @Value("${oss.fileurl}")
    private String fileurl;
    
    @RequestMapping("index")
    @ResponseBody
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/list");
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView ,LstTestStudentAnswer ltsa){
        
        return lsttestStudentAnswerService.query(pageView,ltsa);
    }
    @RequestMapping("toStudentScore")
    @ResponseBody
    public ModelAndView toStudentScore(LstTestStudentAnswer ltsa,PageView pageView){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/stu_score_list");
        return mav;
    }
    
    @RequestMapping("correctTest")
    @ResponseBody
    public ModelAndView correctTest(LstTestStudentAnswer ltsa ,String flag){
        int paperId = ltsa.getPaperId();
        int questionXh = ltsa.getXh();
        List<LstQuestion> questions = lsttestStudentAnswerService.getTestQuestion(paperId);
        LstQuestion question = null;
        if(flag==null ||"1".equals(flag)){
            question = getNextQuestion(questions,questionXh);
        }else{
            question = getLastQuestion(questions,questionXh);
        }
                
        String view = "";
        String type = question.getType();
        String code = question.getCode();
        ltsa.setTotal_question_num(questions.size());
        LstQuestion q = null;
        if("2".equals(type)){
            view = "/lstclasscorrect/test_multi_pigai";
            q = lsttestStudentAnswerService.getMultiStudentQuestion(ltsa,code);
        }else{
            view = "/lstclasscorrect/test_simple_pigai";
            q = lsttestStudentAnswerService.getSimpleTestQuestion(ltsa,code);
        }
        if(StringUtils.isNotEmpty(question.getNextXh())){
            q.setNextXh(question.getNextXh());
        }
        q.setXh(question.getXh());
        q.setTmNum(question.getTmNum());
        ModelAndView mav = new ModelAndView(view);
        mav.addObject("question", q);
        mav.addObject("ltsa", ltsa);
        mav.addObject("fileurl", fileurl);
        return mav;
    }
    
    @RequestMapping("getGradeInfo")
    @ResponseBody
    public LstTestStudentAnswer getGradeInfo(LstTestStudentAnswer lsta){
        LstTestStudentAnswer l= lsttestStudentAnswerService.getGradeInfo(lsta);
        return l;
    }
    @RequestMapping("pgMultiTest")
    @ResponseBody
    public Map<String, Object> pgMultiTest(String jstr){
        Map<String, Object> result=new HashMap<String, Object>();
        try{
            List<LstTestStudentAnswer> list=JSON.parseArray(jstr, LstTestStudentAnswer.class);
            lsttestStudentAnswerService.pgMultiTest(list);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "批改作业失败");
        }
        return result;
    }
    
    @RequestMapping("pgSimpleTest")
    @ResponseBody
    public Map<String, Object> pgSimpleTest(LstTestStudentAnswer lsta){
        Map<String, Object> result=new HashMap<String, Object>();
        try{
            lsttestStudentAnswerService.pgSimpleTest(lsta);
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "批改作业失败,请联系管理员！");
        }
        return result;
    }
    
    @RequestMapping("insertGradeInfo")
    @ResponseBody
    public Map<String,Object> insertGradeInfo(String str){
        Map<String, Object> map=new HashMap<String, Object>();
        List<LstTestStudentAnswer> list=JSON.parseArray(str, LstTestStudentAnswer.class);
        int studentId;
        int testId;
        boolean flag=true;
        try {
            for (LstTestStudentAnswer Answer : list) {
                lsttestStudentAnswerService.insertGrade(Answer);
               studentId= Answer.getStudentId();
               testId= Answer.getTestId();
               if(flag){
                   lsttestStudentAnswerService.updateStudentTestStatus(studentId,testId);
                   flag=false;
               }
            }
            map.put("flag", true);
            map.put("message", "打分成功！");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "系统异常，请联系管理员！");
        }
        return map;
    }
    @RequestMapping("classReport")
    @ResponseBody
    public ModelAndView classReport(LstTestStudentAnswer lsta){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/class_report");
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        //获取正数前五人
        List<LstTestStudentAnswer> topList=lsttestStudentAnswerService.getDescTopFive(lsta);
        //获取班级倒数前五
        List<LstTestStudentAnswer> backwardList=lsttestStudentAnswerService.getDescBackwardFive(lsta);
        LinkedHashMap<String, Object> map= new LinkedHashMap<String, Object>();
        int i=1;
        for (LstTestStudentAnswer top : topList) {
            map.put("topName", top.getName());
            map.put("topStudentId", top.getStudentId());
            map.put("topOver", top.getOverallTeacher());
            int j=1;
            for (LstTestStudentAnswer back : backwardList) {
                if(j==i){
                    map.put("backName", back.getName());
                    map.put("backStudentId", back.getStudentId());
                    map.put("backOver", back.getOverallTeacher()); 
                   
                    list.add(map);
                }
                j++;
            }
              map = new LinkedHashMap<String, Object>(); 
//            map.clear();
            i++;
        }
        //查询出班级最高分等
        Map<String, Object> scoreList=lsttestStudentAnswerService.getScoreList(lsta);
        mav.addObject("score", scoreList);
        mav.addObject("list", list);
        mav.addObject("lsta", lsta);
        return mav;
    }
    /**
     * 班级报表
     * @param lsta
     * @return
     */
    @RequestMapping("report")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> report(LstTestStudentAnswer lsta){
        List<Map<String, Object>> classAvg=lsttestStudentAnswerService.getclassAvgScore(lsta);
        List<Map<String, Object>> classTotal=lsttestStudentAnswerService.getTotalAvgScore(lsta);
        Map<String, List<Map<String, Object>>> ma=new HashMap<>();
        ma.put("classAvg", classAvg);
        ma.put("classTotal", classTotal);
        return ma;
    }
    @RequestMapping("stuQuesTypeReport")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> stuQuesTypeReport(LstTestStudentAnswer lsta){
        List<Map<String, Object>> studentAvg=lsttestStudentAnswerService.getStuAvgScore(lsta);
        List<Map<String, Object>> total=lsttestStudentAnswerService.getTotalAvgScore(lsta);
        Map<String, List<Map<String, Object>>> ma=new HashMap<>();
        ma.put("studentAvg", studentAvg);
        ma.put("total", total);
        return ma;
    }
    /**
     * 学生成绩报表
     * @param lsta
     * @return
     */
    @RequestMapping("studentReport")
    @ResponseBody
    public ModelAndView studentReport(LstTestStudentAnswer lsta){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/stu_score_report");
        //根据id查询出学生
        LstStudentTest a= lststudenttestService.selectPrimarykey(lsta);
        //获取总成绩
        LstTestStudentAnswer la=lsttestStudentAnswerService.getOverallScore(lsta);
        //查询出本场考试一共多少人
        int totalNum=lststudenttestService.getTotal(lsta);
        //本场考试超过多少人
        int overNum=lststudenttestService.overNum(lsta);
        double over=(overNum+1)/totalNum+(overNum+1)%totalNum;
        String overPercent=decmailFormat2(over*100);
        if(a.getAccuracyTeacher()!=0&&a.getAccuracyTeacher()!=null){
            la.setAccuracyPercent(a.getAccuracyTeacher()*10);
        }else{
            la.setAccuracyPercent(0.00);
        }if(a.getIntegrityTeacher()!=null&&a.getIntegrityTeacher()!=0){
            la.setIntegrityPercent(a.getIntegrityTeacher()*10);
        }else{
            la.setIntegrityPercent(0.00);
        }
        if(a.getFluencyTeacher()!=null&&a.getFluencyTeacher()!=0){
            la.setFluencyPercent(a.getFluencyTeacher()*10);
        }else{
            la.setFluencyPercent(0.00);
        }
        //获取模考中流利度、完整、准确平均分
        LstStudentTest avgscore=lststudenttestService.getAvgScoreInFlAc(lsta);
        //查询出该学生的每道题
        List<Map<String, Object>> AllQues=lsttestStudentAnswerService.selectAllQuesForStu(lsta);
        String inegrityAvg=decmailFormat(avgscore.getIntegrityTeacher()*10);
        String fluencyAvg=decmailFormat(avgscore.getFluencyTeacher()*10);
        String accuracyAvg=decmailFormat(avgscore.getAccuracyTeacher()*10);
        a.setTestNum(a.getDatiNum()*10);
        double percent=(la.getOverallTeacher()/a.getTestNum())*100;
        a.setPercent(percent);
        mav.addObject("AllQues",AllQues);
        mav.addObject("fileurl", fileurl);
        mav.addObject("answer", a);
        mav.addObject("la", la);
        mav.addObject("accuracyAvg", accuracyAvg);
        mav.addObject("inegrityAvg", inegrityAvg);
        mav.addObject("fluencyAvg", fluencyAvg);
        mav.addObject("lsta", lsta);
        mav.addObject("overPercent", overPercent);
        mav.addObject("paiming", totalNum-overNum);
        return mav;
    }
    
    private LstQuestion getNextQuestion(List<LstQuestion> questions,Integer currentXh){
        LstQuestion q = new LstQuestion();
        if(currentXh==0){
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
    /**
     * 保留小数
     * @param num传入数字
     * @param 
     * @return
     */
    public static String decmailFormat(double num ){
        DecimalFormat df=new DecimalFormat(".##");
        double d=num;
        String st=df.format(d);
        return st;
    }
    public static String decmailFormat2(double num ){
        DecimalFormat df=new DecimalFormat("");
        double d=num;
        String st=df.format(d);
        return st;
    }
}
