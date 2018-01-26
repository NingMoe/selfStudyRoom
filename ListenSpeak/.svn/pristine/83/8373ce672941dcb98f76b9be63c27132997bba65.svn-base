package com.ls.spt.zuoye.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;
import com.ls.spt.zuoye.service.LstZuoyeReportService;
import com.ls.spt.zuoye.service.LstZuoyeService;

@Controller
@RequestMapping(value = "/classZuoyeReport/")
public class LstZuoyeReportController {
    
    private final  Logger logger = LogManager.getLogger(LstZuoyeReportController.class);
  
    @Resource
    private LstZuoyeReportService zuoyereportService;
    
    @Resource
    private LstZuoyeService zuoyeService;

    @Value("${oss.fileurl}")
    private String fileurl;
    
    
    @RequestMapping("zuoyeReport")
    @ResponseBody
    public ModelAndView classReport(LstStudentZuoye lz ){
        ModelAndView mav=new ModelAndView("/zuoye/class_zuoye_report");
        zuoyeService.getClassList(1);
        int tjNum=zuoyereportService.getTjNum(lz,"zs");
        int nZhunsNum=zuoyereportService.getTjNum(lz,"nzs");
        double zsPercent=tjNum%(tjNum+nZhunsNum)+tjNum/(tjNum+nZhunsNum);
        double nzsPercent=1-zsPercent;
        //查询作业完成情况
        List<Map<String, Object>> zuoyeSitu=zuoyereportService.getZuoyeSitu(lz);
        //查询出这场测试有哪些题以题目code为编号
        List<Map<String, Object>> q =zuoyereportService.getQuestionCodes(lz);
        for (Map<String, Object> map : q) {
            String code=(String) map.get("question_code");
            List<Map<String, Object>> list=zuoyereportService.getQuestions(code,lz.getZuoye_id());
            if(list.size()>0){
                map.put("content", (String) list.get(0).get("content"));
                map.put("parse_text", (String) list.get(0).get("parse_text")) ;
                map.put("zdmessage", (String) list.get(0).get("zdmessage")) ;
                map.put("parse_audio", (String) list.get(0).get("parse_audio")) ;
                for (Map<String, Object> score : list) {
                    int question_id=(int) score.get("id");
                    lz.setId(question_id);
                    String type=(String) score.get("type");
                    Map<String, Object> sc=zuoyereportService.getScore(lz);
                    if("1".equals(type)){
                        map.put("overallPercent", sc.get("overallPercent"));
                        map.put("overall", sc.get("overall"));
                        map.put("accuracy", sc.get("accuracy"));
                        map.put("fluency", sc.get("fluency"));
                        map.put("integrity", sc.get("integrity"));
                        map.put("questionName", sc.get("questionName"));
                        map.put("difficulty", sc.get("difficulty"));
                        map.put("id", score.get("id"));
                    }else{
                        score.put("overallPercent", sc.get("overallPercent"));
                        score.put("overall", sc.get("overall"));
                        score.put("accuracy", sc.get("accuracy"));
                        score.put("fluency", sc.get("fluency"));
                        score.put("integrity", sc.get("integrity"));
                        score.put("questionName", sc.get("questionName"));
                        score.put("difficulty", sc.get("difficulty"));
                        score.put("id", score.get("id"));
                    }
                }
            }
            map.put("list", list);
        }
        mav.addObject("fileurl", fileurl);
        mav.addObject("questions", q);
        mav.addObject("zsPercent", zsPercent);
        mav.addObject("nzsPercent", nzsPercent);
        mav.addObject("zuoyeSitu",zuoyeSitu);
        mav.addObject("lz", lz);
        return mav;
    }
    
    @RequestMapping("zuoyeDfl")
    @ResponseBody
    public  List<Map<String, Object>> zuoyeDfl(LstStudentZuoye lz){
       List<Map<String, Object>> list= zuoyereportService.getZuoyeDfl(lz);
        return list;
    }
    
    @RequestMapping("zuotiSitu")
    public ModelAndView zuotiSitu(LstStudentZuoye lz){
        ModelAndView mav=new ModelAndView("/zuoye/zuotiSitu");
        List<Map<String, Object>> list=zuoyereportService.pgNum(lz);
        Map<String, Object> avgScore=zuoyereportService.getavgScore(lz);
        long pgN=0;
        long ntj=0;
        for (Map<String, Object> map : list) {
            String str=(String) map.get("pgNum");
            long count=(long)map.get("count");
            if("pg".equals(str)){
                pgN=count;
            }else{
                ntj=count;
            }
            
        }
        mav.addObject("avg", avgScore);
        mav.addObject("pg", pgN);
        mav.addObject("total", pgN+ntj);
        mav.addObject("ntj", ntj);
        mav.addObject("lz", lz);
        return mav;
    }
    @RequestMapping("reportClass")
    @ResponseBody
    public Map<String,Object>  reportClass(LstStudentZuoye lz){
        List<Map<String, Object>> list= zuoyereportService.getZuotiSitu(lz);
        Map<String, Object> score=new HashMap<>();
        for (Map<String, Object> map : list) {
            String stage=(String) map.get("stage");
            long num =(long) map.get("num");
            if("twoPoint".equals(stage)){
                score.put("twoPoint", num);
            }else if("five".equals(stage)){
                score.put("five", num);
            }else if("sevenpointfive".equals(stage)){
                score.put("sevenpointfive", num);
            }else if("seven".equals(stage)){
                score.put("ten", num);
            }
        }
        //获取班级学生在每个阶段的信息
        List<Map<String, Object>> sScore=zuoyereportService.getStudentLevel(lz);
        score.put("sScore", sScore);
        return score;
    }
    
    @RequestMapping("toStudentReport")
    public ModelAndView toStudentReport(LstStudentZuoye lz) {
        ModelAndView mav=new ModelAndView("/zuoye/student_zuoye_report");
        //获取学生作业信息
       Map<String, Object> zuoyeInfo=zuoyereportService.getzuoyeInfo(lz);
       Map<String, Object> dfl= zuoyereportService.getStudentZuoyeByCode(lz);
       //查询完成情况
       List<Map<String, Object>> quesList=zuoyereportService.getStudentQuestion(lz);
       //获取试卷总分
       Map<String, Object> totalScoreList=zuoyereportService.totalScoreList(lz);
       //获取成绩曲线数据
       List<Map<String, Object>> growth=zuoyereportService.getGrowthData(lz);
       List<Map<String, Object>> q =zuoyereportService.getQuestionCodes(lz);
       for (Map<String, Object> map : q) {
           String code=(String) map.get("question_code");
           List<Map<String, Object>> list=zuoyereportService.getQuestions(code,lz.getZuoye_id());
           if(list.size()>0){
               map.put("content", (String) list.get(0).get("content"));
               map.put("parse_text", (String) list.get(0).get("parse_text")) ;
               map.put("zdmessage", (String) list.get(0).get("zdmessage")) ;
               map.put("parse_audio", (String) list.get(0).get("parse_audio")) ;
               for (Map<String, Object> score : list) {
                   int question_id=(int) score.get("id");
                   lz.setId(question_id);
                   //查询出
//                   Map<String, Object> audio=zuoyereportService.
                   String type=(String) score.get("type");
                   Map<String, Object> sc=zuoyereportService.getScoreForStudent(lz);
                   if(sc!=null){
                       if("1".equals(type)){
                           map.put("overallPercent", sc.get("overallPercent"));
                           map.put("overall", sc.get("overall"));
                           map.put("accuracy", sc.get("accuracy"));
                           map.put("fluency", sc.get("fluency"));
                           map.put("integrity", sc.get("integrity"));
                           map.put("questionName", sc.get("questionName"));
                           map.put("difficulty", sc.get("difficulty"));
                           map.put("student_answer", sc.get("student_answer"));
                           map.put("answer_comment", sc.get("answer_comment"));
                           map.put("id", score.get("id"));
                       }else{
                           score.put("overallPercent", sc.get("overallPercent"));
                           score.put("overall", sc.get("overall"));
                           score.put("accuracy", sc.get("accuracy"));
                           score.put("fluency", sc.get("fluency"));
                           score.put("integrity", sc.get("integrity"));
                           score.put("questionName", sc.get("questionName"));
                           score.put("difficulty", sc.get("difficulty"));
                           score.put("student_answer", sc.get("student_answer"));
                           score.put("answer_comment", sc.get("answer_comment"));
                           score.put("id", score.get("id"));
                       }
                   }
               }
           }
           map.put("list", list);
       }
       logger.info("------------查看击败多少人比例----");
       Map<String, Object> map=new HashMap<String, Object>();
       int beatNum=zuoyereportService.beat(lz);
       int totalNum=zuoyereportService.totalStudent(lz);
       double beatPercent;
       if(totalNum!=1){
           beatPercent=beatNum/(totalNum-1)+beatNum%(totalNum-1)*100;
           
       }else{
           beatPercent=100.00;
                   }
//       未击败人数
       double beatPercentTest;
       int wBeatNum=totalNum-beatNum;
       LstStudentZuoye lsz =new LstStudentZuoye();
       lsz.setZuoye_id(lz.getZuoye_id());
       lsz.setStudent_id(lz.getStudent_id());
       int beatNumtest=zuoyereportService.beat(lsz);
       int totalNumtest=zuoyereportService.totalStudent(lsz);
       if(totalNum!=1){
           beatPercentTest=beatNumtest/(totalNumtest-1)+beatNumtest%(totalNumtest-1)*100;
           
       }else{
           beatPercentTest=100.00;
                   }
       int wBeatNumTest=totalNumtest-beatNumtest;
       mav.addObject("beatNumclass", beatNum);
       mav.addObject("wBeatNumClass", wBeatNum);
       mav.addObject("totalNumClass", totalNum);
       mav.addObject("beatPercent", beatPercent);
       
       mav.addObject("beatNumTest", beatNumtest);
       mav.addObject("totalNumTest", totalNumtest);
       mav.addObject("beatPercentTest", beatPercentTest);
       mav.addObject("wBeatNumTest", wBeatNumTest);
       
       mav.addObject("zuoyeInfo", zuoyeInfo);
       mav.addObject("zuoyeSitu", quesList);
       mav.addObject("Scores", totalScoreList);
       mav.addObject("growth", growth);
       mav.addObject("dflMap", dfl);
       mav.addObject("lz", lz);
       mav.addObject("questions", q);
       mav.addObject("fileurl", fileurl);
       return mav;
    }
    @RequestMapping("getGrowth")
    @ResponseBody
    public  List<Map<String, Object>> getGrowth(LstStudentZuoye lz){
        logger.info("--------查询成长轨迹曲线数据----------");
        List<Map<String, Object>> growth=zuoyereportService.getGrowthData(lz);
        return growth;
    }
    @RequestMapping("beatClass")
    @ResponseBody
    public Map<String, Object> beatClass(LstStudentZuoye lz){
        logger.info("------------查看击败多少人比例----");
        Map<String, Object> map=new HashMap<String, Object>();
        int beatNum=zuoyereportService.beat(lz);
        int totalNum=zuoyereportService.totalStudent(lz);
        double beatPercent;
        if(totalNum!=1){
            beatPercent=beatNum/(totalNum-1)+beatNum%(totalNum-1)*100;
            
        }else{
            beatPercent=100.00;
                    }
        map.put("beatPercent", beatPercent);
        return map;
    }
    
}
