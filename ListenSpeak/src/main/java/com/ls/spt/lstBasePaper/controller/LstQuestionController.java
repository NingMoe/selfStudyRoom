package com.ls.spt.lstBasePaper.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstBasePaper.entity.LstBasePaper;
import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;
import com.ls.spt.lstBasePaper.entity.LstQuestion;
import com.ls.spt.lstBasePaper.service.LstBasePaperService;
import com.ls.spt.lstBasePaper.service.LstPaperQuestionService;
import com.ls.spt.lstBasePaper.service.LstQuestionService;



@Controller
@RequestMapping(value="/lstQuestion/")
public class LstQuestionController {
    
    private final  Logger logger = LogManager.getLogger(LstQuestionController.class);
    
    @Resource
    private LstQuestionService lstquestionService;
    
    @Resource
    private LstBasePaperService lstbasepaperService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private AreaInfoService areainfoService;
    
    @Resource
    private LstPaperQuestionService lstpaperQuestionService;
    
    @Value("${oss.fileurl}")
    private String fileurl;
    
    @RequestMapping("index")
    private ModelAndView index(String id){
        ModelAndView map=new ModelAndView("/lstbasepaper/lstquestion_list");
        List<DicData>  sourceType= dictionaryService.getDataByKey("source_type");
        List<DicData>  year= dictionaryService.getDataByKey("year");
        LstBasePaper lbp=lstbasepaperService.getLstBasePaperInfo(id);
        List<DicData>  questionType= dictionaryService.getDataByKey("question_type");
        List<DicData>  diffLevel= dictionaryService.getDataByKey("diff_level");
        List<DicData>  grade= dictionaryService.getDataByKey("grade");
        //查询出每份试卷总题量
        int total=lstquestionService.getTotalNum(id);
        String province= lstquestionService.getAreaName(lbp.getProvince());
        String city=lstquestionService.getAreaName(lbp.getCity());
        String areaName=province+city;
//        map.addObject("list", list);
        map.addObject("sourceTypes", sourceType);
        map.addObject("questionTypes", questionType);
        map.addObject("areaName", areaName);
        map.addObject("years", year);
        map.addObject("diffLevels", diffLevel);
        map.addObject("grades", grade);
        map.addObject("lbp", lbp);
        map.addObject("total", total);
        map.addObject("paperId", id);
        map.addObject("fileurl",fileurl);
        return map;
    }
    
    @RequestMapping("toNextPage")
    @ResponseBody
    private Map<String, Object> toNextPage(String id,int page){
        Map<String, Object> map=new HashMap<String, Object>();
        //获取每条数据的code 及类型
        logger.info("-----------------获取题目code及类型-----------------");
        LstQuestion list=lstquestionService.getQuestionType(id,page);
        LstQuestion question=new LstQuestion();
        List<LstQuestion> questionList =new ArrayList<LstQuestion>();
        if(list!=null){
            
            if("2".equals(list.getType())){
                logger.info("-----------------情景对话查询-----------------");
                questionList= lstquestionService.getAllQuestion(id,list.getCode()); 
            }else{
                logger.info("-----------------普通题型-----------------");
                question= lstquestionService.getQuestion(id,list.getCode());  
            }
        }
//        LstBasePaper lbp=lstbasepaperService.getLstBasePaperInfo(id);
        List<DicData>  questionType= dictionaryService.getDataByKey("question_type");
        List<DicData>  diffLevel= dictionaryService.getDataByKey("diff_level");
        List<DicData>  grade= dictionaryService.getDataByKey("grade");
        String showUp="";
        String showNext="";
        int total=lstquestionService.getTotalNum(id);
        if(page+1!=1){
            showUp="Y";
        }
        if(page+1!=total){
            showNext="Y";
        }
        //查询出每份试卷总题量
        map.put("list", list);
        map.put("questionTypes", questionType);
        map.put("type", list.getType());
        map.put("diffLevels", diffLevel);
        map.put("grades", grade);
        map.put("page", page+1);
        map.put("total", total);
        map.put("questionList", questionList);
        map.put("question", question);
        map.put("paperId", id);
        map.put("showUp", showUp);
        map.put("showNext", showNext);
        return map;
    }
    @RequestMapping("query")
    @ResponseBody
    public PageView query(LstQuestion lst,PageView pageView){
        
        return lstquestionService.query(lst,pageView);
    }
    
    
    @RequestMapping("addTest")
    @ResponseBody
    public Map<String,Object> addTest(String paperId,String questionId,String xh,String type,String questionType){
        if("".equals(xh)||xh==null){
            xh="0";
        }
        List<Map<String, Object>> lbp=lstbasepaperService.getTypeAndNum(Integer.valueOf(paperId));
        logger.info("-------------------向试题试卷配置表中添加普通试题数据----------------------"); 
        //"1" 为普通类型 "2"为情景对话类型
        Map<String, Object> map=new HashMap<>();
        try {
            boolean flag =true;
            for (Map<String, Object> map1 : lbp) {
                int num=(int) map1.get("num");
                int typeId=(int) map1.get("typeId");
                int questionNum=lstquestionService.selectQuestionCount(typeId,Integer.valueOf(paperId));
                if(flag){
                    if(num==questionNum){
                        flag=false;
                    }
                }
                if("1".equals(type)){
                    if(typeId==Integer.valueOf(questionType)){
                        if(num>questionNum){
                            Map<String, Object> pq= lstquestionService.getPaperQuestionList(paperId,questionId,xh);
                            if(pq==null){
                                lstquestionService.insertToQuestion(paperId,questionId,xh);
                                lstbasepaperService.update(paperId,"2");
                                map.put("message", "试题添加成功");
                                map.put("flag", true);
                                
                            }else{
                                map.put("message", "新增失败,试题已存在");
                                map.put("flag", false);
                            }
                    }else{
                        map.put("message", "新增失败,该题型已大最大题量");
                        map.put("flag", false);
                    }
                    }
                }else if("2".equals(type)){
                    String[] quesId=questionId.split(",");
                    if(typeId==Integer.valueOf(questionType)){
                        if(num>questionNum){
                            for (String id : quesId) {
                                Map<String, Object> pq= lstquestionService.getPaperQuestionList(paperId,id,xh);
                                if(pq==null){
                                    lstquestionService.insertToQuestion(paperId,id,xh);
                                    map.put("message", "试题添加成功");
                                    map.put("flag", true);
                                    
                                }else{
                                    map.put("message", "新增失败,试题已存在");
                                    map.put("flag", false);
                                }
                            }
                        }else{
                            map.put("message", "新增失败,该题型已大最大题量");
                            map.put("flag", false);
                        }
                    }
                }
                
            }
            if(flag){
                lstbasepaperService.update(paperId,"2");
            }
        }
        catch (Exception e) {
            map.put("message", "系统异常，请联系管理员！！");
            map.put("flag", false);
        }
        return map;
    }
    
    @RequestMapping("getGradeAndDiff")
    @ResponseBody
    public Map<String, Object> getGradeAndDiff(){
        Map<String, Object> map=new HashMap<>();
        List<DicData>  diffLevel= dictionaryService.getDataByKey("diff_level");
        List<DicData>  grade= dictionaryService.getDataByKey("grade");
        map.put("difficulty", diffLevel);
        map.put("grade", grade);
        return map;
    }
    @RequestMapping("testView")
    @ResponseBody
    public ModelAndView testView(LstQuestion lst){
        ModelAndView mav=new ModelAndView("/lstbasepaper/ques_edit");
        String questionTypeName=lstquestionService.selectQuestionTypeName(lst);
        mav.addObject("questionTypeName", questionTypeName);
        mav.addObject("difficultyName", lst.getDifficultyName());
        mav.addObject("GradeName", lst.getGradeName());
        mav.addObject("code", lst.getCode());
        mav.addObject("paperId", lst.getPaperId());
        mav.addObject("questionType", lst.getQuestionType());
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    
    @RequestMapping("getQuestion")
    @ResponseBody
    public List<Map<String, Object>> getQuestion(LstQuestion lst){
        List<Map<String, Object>> quesList=new ArrayList<>();
        Map<String, Object> ques=new HashMap<>();
        quesList=lstquestionService.getQuestionByCode(lst.getCode());
        return quesList;
    }
    @RequestMapping("getQuestionInfoByCode")
    @ResponseBody
    public List<LstQuestion> getQuestionInfo(LstQuestion lst){
        List<LstQuestion> list= lstquestionService.getQuestionInfoByCode(lst);
        
        return list;
    }
    
    @RequestMapping("getCodes")
    @ResponseBody
    public List<String> getCodes(LstQuestion lst){
        List<String> list=lstquestionService.getCodes(lst);
        return list;
    } 
    @RequestMapping("getSenceQuestion")
    @ResponseBody
    public List<LstQuestion> getSenceQuestion(LstQuestion lst){
        List<LstQuestion> list=lstquestionService.getSenceQuestion(lst);
        return list;
    }
    
    @RequestMapping("addQues")
    @ResponseBody
    public Map<String, Object> addQues(LstQuestion lst){
        List<Map<String, Object>> lbp=lstbasepaperService.getTypeAndNum(lst.getPaperId());
        logger.info("-------------------向试题试卷配置表中添加普通试题数据----------------------"); 
        Map<String, Object> map=new HashMap<>();
            try {
                boolean flag =true;
                for (Map<String, Object> map1 : lbp) {
                    int num=(int) map1.get("num");
                    int typeId=(int) map1.get("typeId");
                    int questionNum=lstquestionService.selectQuestionCount(typeId,lst.getPaperId());
                    if(flag){
                        if(num!=questionNum){
                            flag=false;
                        }
                    }
                        if(typeId==lst.getQuestionType()){
                            if(num>questionNum){
                                List<LstQuestion> questionList=  lstquestionService.selectInfoByCode(lst);
                                List<Integer> maxList= lstquestionService.getMaxXh(lst.getPaperId());
                                int max=1;
                                if (maxList.size()!=0) {
                                    for (Integer xh : maxList) {
                                        max=xh+1;
                                 }
                                }
                                for (LstQuestion question : questionList) {
                                    question.setPaperId(lst.getPaperId());
                                    question.setXh(max);
                                    lstquestionService.insert(question);
                                }
                                map.put("flag", true);
                                map.put("message", "新增成功");
                            }else{
                                map.put("flag", true);
                                map.put("message", "新增失败,该题型已大最大题量,请选择其他题型！！");
                            }
                    }
                }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "新增异常，请联系管理员！");
            // TODO: handle exception
        }
        return map;
    }
    //修改题目序号
    @RequestMapping("updateXh")
    @ResponseBody
    public Map<String, Object> updateXh(String paperId,String code,String xh){
        Map<String, Object> map=new HashMap<>();
        try {
            LstPaperQuestion lpq=lstpaperQuestionService.getPaperQuestionInfo(paperId,code,xh);
            if(lpq!=null){
                map.put("flag", false);
                map.put("message", "排序已存在，请核对后再操作！");
            }else{
                lstbasepaperService.updateXh(paperId,code,xh);
                map.put("flag", true);
                map.put("message", "更新成功");
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新信息失败，请联系管理员");
        }
        return map;
    }
//    批量新增
    @RequestMapping("addQuesBath")
    @ResponseBody
    public Map<String, Object> addQuesBath(String jstr,String paperId){
        List<LstQuestion> list=JSON.parseArray(JSON.parseObject(jstr).getString("Data"), LstQuestion.class);
        Map<String, Object> map=new HashMap<>();
        List<Map<String, Object>> lbp=lstbasepaperService.getTypeAndNum(Integer.valueOf(paperId));
        for (LstQuestion lst : list) {
            boolean flag =true;
            for (Map<String, Object> map2 : lbp) {
                int num=(int) map2.get("num");
                int typeId=(int) map2.get("typeId");
                int questionNum=lstquestionService.selectQuestionCount(typeId,lst.getPaperId());
                if(flag){
                    if(num!=questionNum){
                        flag=false;
                    }
                }
                if(typeId==lst.getQuestionType()){
                    if(num>questionNum){
                        List<LstQuestion> questionList=  lstquestionService.selectInfoByCode(lst);
                        for (LstQuestion question : questionList) {
                            question.setPaperId(lst.getPaperId());
                            lstquestionService.insert(question);
                        }
                        map.put("flag", true);
                        map.put("message", "新增成功");
                    }else{
                        map.put("flag", true);
                        map.put("message", "新增失败,该题型已大最大题量,请选择其他题型！！");
                    }
                }
            }
        }
        return map;
        }
}
