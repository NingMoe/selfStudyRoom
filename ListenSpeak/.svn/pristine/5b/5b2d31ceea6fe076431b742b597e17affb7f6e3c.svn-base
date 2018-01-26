package com.ls.spt.lstBasePaper.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.AreaInfo;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstBasePaper.entity.LstBasePaper;
import com.ls.spt.lstBasePaper.service.LstBasePaperService;
import com.ls.spt.lstBasePaper.service.LstQuestionService;
import com.ls.spt.utils.Common;



@Controller
@RequestMapping(value="/lstBasePaper/")
public class LstBasePaperController {
    
    private final  Logger logger = LogManager.getLogger(LstBasePaperController.class);
    
    @Autowired 
    LstBasePaperService lstbasepaperService;
    
    @Autowired 
    AreaInfoService areaInfoService;
    
    @Autowired
    DictionaryService dictionaryService;
    
    @Resource
    private LstQuestionService lstquestionService;
    @Value("${oss.fileurl}")
    private String fileurl;
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/lstbasepaper/list");
        //查询省份
        List<DicData>  sourceType= dictionaryService.getDataByKey("source_type");
        List<DicData>  year= dictionaryService.getDataByKey("year");
        mav.addObject("sourceType", sourceType);
        mav.addObject("year", year);
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,LstBasePaper lbp) {
        return  lstbasepaperService.query(pageView,lbp);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        Map<String, Object> map=new HashMap<String, Object>();
        logger.info("-----------------------删除开始-----------------");
        try {
          lstbasepaperService.delete(ids);  
          map.put("message", "操作成功！");
          map.put("flag", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("message", "操作失败，请联系管理员！");
            map.put("flag", false);
        }
        return map;
    }
    
    @RequestMapping("enterPaper")
    public ModelAndView enterPaper(){
      ModelAndView mav =new ModelAndView("/lstbasepaper/enterPaper");
    //查询省份
      AreaInfo area = new AreaInfo();
      area.setAreaLevel(1);
      List<AreaInfo> areaInfo = areaInfoService.getArea(area);
      List<DicData>  diffLevel= dictionaryService.getDataByKey("diff_level");
      List<DicData>  grade= dictionaryService.getDataByKey("grade");
      mav.addObject("areaInfo", areaInfo);
      mav.addObject("diffLevels", diffLevel);
      mav.addObject("grades", grade);
      return mav;  
    }
    
    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
      ModelAndView mav =new ModelAndView("lstbasepaper/add");
    //查询省份
      AreaInfo area = new AreaInfo();
      area.setAreaLevel(1);
      List<AreaInfo> areaInfo = areaInfoService.getArea(area);
      List<DicData>  years= dictionaryService.getDataByKey("year");
      List<DicData>  paperSources= dictionaryService.getDataByKey("paper_source");
      //查询试卷所用的题型
      List<Map<String, Object>> list=lstbasepaperService.getQuestionType();
      mav.addObject("areaInfo", areaInfo);
      mav.addObject("years", years);
      mav.addObject("paperSources", paperSources);
      mav.addObject("lists", list);
      return mav;  
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(LstBasePaper lst){
        logger.info("------------------------新增试卷开始--------------------------");
        lst.setCreateTime(new Date());
        lst.setCreateUser(Integer.valueOf(Common.getMyUser().getUserid().toString()));
        Map<String, Object> map=new HashMap<>();
        try {
            lst.setStatus(1);
            lstbasepaperService.insert(lst);
            map.put("id", lst.getId());
            map.put("flag", true);
            map.put("message", "新增试卷成功");
            logger.info("------------------------新增试卷结束--------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            // TODO: handle exception
            map.put("flag", false);
            map.put("message", "新增试卷失败");
        }
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(LstBasePaper lst){
        logger.info("------------------------新增试卷开始--------------------------");
        Map<String, Object> map=new HashMap();
        lst.setStatus(1);
        try {
            lstbasepaperService.updatePaperInfo(lst);
            map.put("flag", true);
        }
        catch (Exception e) {
            map.put("flag", false);
            e.getMessage();
            e.printStackTrace();
            
        }
        return map;
    }
    
    @RequestMapping("editToQtype")
    @ResponseBody
    public Map<String, Object> editToQtype(String num ,String typeId,String paperId){
        logger.info("--------------------------更新lst_base_paper_qtype表中插入数据---------------");
        Map<String, Object> map=new HashMap<>();
        try {
            lstbasepaperService.updateQuestionType(num,typeId,paperId);
            map.put("flag", true);
            map.put("message", "更新试卷信息成功");
            logger.info("------------------------更新lst_base_paper_qtype表中插入数据结束--------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "新增试卷失败，请联系管理员");
        }
        return map;
    }
    
    @RequestMapping("addToQtype")
    @ResponseBody
    public Map<String, Object> addToQtype(String num ,String typeId,String paperId){
        logger.info("--------------------------向lst_base_paper_qtype表中插入数据---------------");
        Map<String, Object> map=new HashMap<>();
        try {
            lstbasepaperService.insertQuestionType(num,typeId,paperId);
            map.put("flag", true);
            map.put("message", "新增试卷成功");
            logger.info("------------------------新增试卷结束--------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "新增试卷失败");
        }
        return map;
    }
    @RequestMapping("toInsertTest")
    @ResponseBody
    public ModelAndView toInsertTest(LstBasePaper lst){
        ModelAndView mav=new ModelAndView("/lstbasepaper/addQuestion");
        List<Map<String, Object>> lbpNum=lstbasepaperService.getTypeAndNum(lst.getId());
        List<DicData>  diffLevel= dictionaryService.getDataByKey("diff_level");
        List<DicData>  grade= dictionaryService.getDataByKey("grade");
        LstBasePaper lbp=lstbasepaperService.getPaperInfo(lst);
        List<Map<String, Object>> questionTypes=lstbasepaperService.getQuestionType();
        mav.addObject("diffLevels", diffLevel);
        mav.addObject("questionTypes", questionTypes);
        mav.addObject("grades", grade);
        mav.addObject("lst", lst);
        mav.addObject("lbpNums", lbpNum);
        mav.addObject("lbp",lbp);
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    @RequestMapping("toEdit")
    @ResponseBody
    public ModelAndView toEdit(LstBasePaper lst){
        ModelAndView mav =new ModelAndView("lstbasepaper/edit");
        //查询省份
          List<DicData>  years= dictionaryService.getDataByKey("year");
          List<DicData>  paperSources= dictionaryService.getDataByKey("paper_source");
          //查询试卷所用的题型的数量
          List<Map<String, Object>> list=lstbasepaperService.getQuestionTypeNum(lst);
          //查询试卷
          LstBasePaper paperInfo=lstbasepaperService.getPaperInfo(lst);
          mav.addObject("years", years);
          mav.addObject("paperInfo", paperInfo);
          mav.addObject("paperSources", paperSources);
          mav.addObject("lists", list);
          return mav;
    }
    @RequestMapping("introduced")
    @ResponseBody
    public Map<String, Object> introduced(LstBasePaper lst){
        Map<String, Object> result=new HashMap<>();
        boolean flag=true;
        try {
            List<Map<String, Object>> lbp=lstbasepaperService.getTypeAndNum(lst.getId());
            for (Map<String, Object> map : lbp) {
                int num=(int) map.get("num");
                int typeId=(int) map.get("typeId");
                int questionNum=lstquestionService.selectQuestionCount(typeId,lst.getId());
                if(questionNum!=num){
                    flag=false; 
                    result.put("flag", false);
                    result.put("message", "题型数量配置异常，请核对后发布");
                    break;
                }
            }
            if(flag){
                lst.setStatus(2);
                lstbasepaperService.updatePaperInfo(lst); 
                result.put("message", "发布成功！！！");
                result.put("flag", true);
            }
        }
        catch (Exception e) {
            result.put("message", "发布异常，请联系管理员！！！");
            result.put("flag", true);
        }
        return result;
    }
    @RequestMapping("used")
    @ResponseBody
    public ModelAndView used(String code){
        ModelAndView mav=new ModelAndView("lstbasepaper/used");
       List<Map<String,Object>> list= lstquestionService.used(code);
       mav.addObject("size", list.size());
       mav.addObject("list", list);
        return  mav;
    }
}
