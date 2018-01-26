package com.ls.spt.lstClassTest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstBasePaper.entity.LstBasePaper;
import com.ls.spt.lstBasePaper.service.LstBasePaperService;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.lstClass.service.LstClassService;
import com.ls.spt.lstClassTest.entity.LstClassTest;
import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.lstClassTest.service.LstClassTestService;
import com.ls.spt.lstClassTest.service.LstStudentTestService;
import com.ls.spt.lstClassTest.service.LstTestStudentAnswerService;
import com.ls.spt.utils.Common;

@Controller
@RequestMapping(value="/lstClassTest/")
public class LstClassTestController {
    
    private final  Logger logger = LogManager.getLogger(LstClassTestController.class);
    
    @Resource
    LstClassTestService lstClassTestService;
    
    @Resource
    LstClassService lstclassService;
    
    @Autowired
    DictionaryService dictionaryService;
    
    @Resource
    LstBasePaperService lstbasepaperService;
    
    @Resource 
    LstStudentTestService lststudentTestService;
    @Resource
    LstTestStudentAnswerService lstTestStudentAnswerService;
    
    @RequestMapping("index")
    @ResponseBody
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/lstclasstest/list");
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,LstClassTest lct){
        
        return lstClassTestService.query(pageView,lct);
    }
    
    @RequestMapping("toAdd")
    public ModelAndView toAdd(LstClassTest lct){
        ModelAndView mav=new ModelAndView("/lstclasstest/add");
        long userId=Common.getMyUser().getUserid();
        List<LstClass> list=lstclassService.selectPrimaryKeyInfo(userId);
        mav.addObject("classList", list);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(LstClassTest lct){
        long userId=Common.getMyUser().getUserid();
        lct.setCreateUser((int) userId);
        lct.setCreateTime(new Date());
        lct.setStatus(1);
        lct.setIntroductStatus(1);
        Map<String, Object> map=new HashMap<String, Object>();
        logger.info("---------------新增考试开始------------");
//        boolean flag=true;
        try {
            logger.info("-----新增测试------------");
            lstClassTestService.insert(lct);
            logger.info("-----------新增测试班级间的中间表------------------");
            lstClassTestService.insertToTestCl(lct);
            map.put("flag", true);
            map.put("message", "新增数据成功！");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "新增数据异常，请联系管理员！");
            // TODO: handle exception
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    @ResponseBody
    public ModelAndView toEdit(LstClassTest lct){
        ModelAndView mav=new ModelAndView("/lstclasstest/edit");
        LstClassTest l=lstClassTestService.selectPrimaryKey(lct);
        logger.info("-----------根据试卷id查询出考试 班级中间表中数据--------------");
        List<Map<String, Object>> lst=lstClassTestService.getTestInfo(lct.getId());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
        String EndTime=sdf.format(l.getEndTime()); 
        mav.addObject("endTime", EndTime);
        mav.addObject("lst", lst);
        mav.addObject("lct", l);
        return mav;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(LstClassTest lct){
        Map<String, Object> map=new HashMap<>();
        lct.setIntroductStatus(1);
        try {
            lstClassTestService.update(lct);
            //根据试卷id删除lst_class_test_cl中数据
            lstClassTestService.deleteToTestCl(lct);
            //向lst_class_test_cl中插入选中数据
            lstClassTestService.insertToTestCl(lct);
            map.put("flag",true);
            map.put("message", "更新信息成功");
        }
        catch (Exception e) {
            map.put("flag",false);
            map.put("message", "系统异常，请联系管理员");
            e.printStackTrace();
            e.getMessage();
        }
        return map;
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        Map<String, Object> map=new HashMap<>();
        logger.info("----------------删除数据开始-----------------");
        try {
            lstClassTestService.delete(ids);
            map.put("flag", true);
            map.put("message", "删除数据成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "系统异常，请联系管理员！");
        }
        return map;
    }
    @RequestMapping("toConfiguration")
    @ResponseBody
    public ModelAndView toConfiguration(LstClassTest lct){
        ModelAndView mav=new ModelAndView("/lstclasstest/config_list");
        LstClassTest lctest=lstClassTestService.selectPaperInfo(lct.getId());
        List<DicData>  sourceType= dictionaryService.getDataByKey("source_type");
        List<DicData>  year= dictionaryService.getDataByKey("year");
        mav.addObject("sourceType", sourceType);
        mav.addObject("year", year);
        mav.addObject("lct", lctest);
        return mav;
    }
    
    @RequestMapping("paperConfig")
    @ResponseBody
    public Map<String, Object> paperConfig(String paperId,String testId){
        Map<String, Object> map=new HashMap<>();
        LstClassTest lct=new  LstClassTest();
        lct.setId(Integer.valueOf(testId));
        lct.setPaperId(Integer.valueOf(paperId));
        try {
            LstClassTest l=lstClassTestService.selectPrimaryKey(lct);
            if(l.getPaperId()!=null&&l.getPaperId()!=0){
                map.put("flag",false);
                map.put("message","本次测试已经配置试卷，请移除试卷后进行配置！");
            }else{
                lct.setIntroductStatus(1);
                lstClassTestService.update(lct);
                map.put("paperId", paperId);
                map.put("flag",true);
                map.put("message","配置试卷成功！");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","系统异常，请联系管理员!");
        }
        return map;
    }
    
    @RequestMapping("deletePaper")
    @ResponseBody
    public Map<String, Object> delete1(String testId){
        Map<String, Object> map=new HashMap<>();
        LstClassTest lct=new LstClassTest();
        lct.setId(Integer.valueOf(testId));
        lct.setPaperId(0);
        try {
            lct.setIntroductStatus(1);
            lstClassTestService.deletePaper(lct);
            map.put("paperId", 0);
            map.put("flag", true);
            map.put("message", "删除试卷成功！！");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag",false);
            map.put("message","系统异常，请联系管理员!");
        }
        return map;
    }
    
    @RequestMapping("introduced")
    @ResponseBody
    public Map<String, Object> introduced(LstClassTest lst){
        Map<String, Object> map=new HashMap<>();
        int i=0;
        int answerNum=0;
        try {
            logger.info("----------发布时查询出lstStudenttest表中所需数据-----");
           List<LstStudentTest> studentlist= lststudentTestService.getStudentTestInfo(lst.getId());
           if(studentlist!=null&&studentlist.size()!=0){
           Date date=new Date();
           String createName=Common.getMyUser().getName();
           for (LstStudentTest sTest : studentlist) {
               //查询出大题数
              int totalNum= lstbasepaperService.getTotalNum(sTest.getPaperId());
              //小题数
              int questionNum=lstbasepaperService.getquestionNum(sTest.getPaperId());
              sTest.setDatiNum(totalNum);
              sTest.setTestNum(questionNum);
              sTest.setCreaateTime(date);
              sTest.setCreateUser(createName);
              sTest.setStatus(1);
//              sTest.setClassCode(classCode);
              sTest.setEndTime(lst.getEndTime());
              //插入数据到lstStudenttest表中
              logger.info("----------向lstStudenttest插入数据------");
              lststudentTestService.insert(sTest);
              i++;
           }
           logger.info("-----------lstStudenttest插入"+i+"条数据");
           List<LstTestStudentAnswer> answerList=lstTestStudentAnswerService.getStudentTestAnswerInfo(lst.getId());
           for (LstTestStudentAnswer aList : answerList) {
               lstTestStudentAnswerService.insert(aList);
               answerNum++;
           }
           logger.info("-----------------向studentAnswer表中插"+answerNum+"条数据");
           lst.setIntroductStatus(2);
           lstClassTestService.update(lst);
           map.put("flag", true);
           map.put("message","发布成功！" );
           }else{
               map.put("flag", false);
               map.put("message","您未配置试卷！" );
           }
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message","系统错误，请联系管理员！" );
        }
        return map;
    }
    @RequestMapping("paperView")
    @ResponseBody
    public ModelAndView paperView(LstClassTest lst){
        ModelAndView mav=new ModelAndView("/lstbasepaper/lstquestion_list");
        LstClassTest l=lstClassTestService.selectPrimaryKey(lst);
        String id=String.valueOf(l.getId());
        return mav;
    } 
}
