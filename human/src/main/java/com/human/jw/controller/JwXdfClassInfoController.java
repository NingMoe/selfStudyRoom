package com.human.jw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.ClassNo;
import com.human.basic.entity.ClassNotice;
import com.human.basic.entity.DicData;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.XdfClassInfoService;
import com.human.jw.service.JwService;
import com.human.jw.service.JwXdfClassInfoService;
import com.human.nologin.controller.FreeInterfaceController;
import com.human.stuadmin.controller.StuAdminController;
import com.human.stuadmin.entity.StuAdmin;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping("/jw/xdf/class/")
public class JwXdfClassInfoController {
    private final  Logger logger = LogManager.getLogger(StuAdminController.class);
    @Resource
    private JwXdfClassInfoService jwxdfClassInfoService;
    
    @Resource 
    private XdfClassInfoService xdfClassInfoService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private JwService jwService;
    
    
    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView mav=new ModelAndView("/xdfclassinfo/list");
        List<DicData> jyz = dictionaryService.getDataByKey("JW_JYZ");
        List<DicData> area = dictionaryService.getDataByKey("area");
        List<DicData> classLevel = dictionaryService.getDataByKey("class_level");
        List<DicData> xq = dictionaryService.getDataByKey("JW_SITE");
        List<DicData>  jibGrade = dictionaryService.getDataByKey("oneAndSixGrade");
        mav.addObject("jyz", jyz);
        mav.addObject("jibGrade", jibGrade);
        mav.addObject("xq", xq);
        mav.addObject("area", area);
        mav.addObject("classLevel", classLevel);
        return mav;
    }
    /**
     * 分页查询
     * @param pageView
     * @param xci
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView index(PageView pageView,XdfClassInfo xci){
        logger.info("一对六查询-----------------");
        List<DicData> xq = dictionaryService.getDataByKey("JW_SITE");
        return jwxdfClassInfoService.query(pageView,xci,xq);
 
    }
    /**
     * 进入学生信息页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("goStuInfo")
    public ModelAndView goStuInfo(HttpServletRequest request ,HttpServletResponse response){
        ModelAndView mav=new ModelAndView("/xdfclassinfo/list");
        return mav;
    }
    
    /**
     * 跳转
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toedit(String sClassCode){
        ModelAndView mav=new ModelAndView("/xdfclassinfo/edit");
        XdfClassInfo xdf= jwxdfClassInfoService.selectByprimary(sClassCode);
        Map<String, Object> xdfPri=new HashMap<>();
        //查询数据库中是否存在
        try {
        xdfPri=jwxdfClassInfoService.selectByprimaryForRm(sClassCode);
        if(xdfPri==null){
            jwxdfClassInfoService.insertForClass(sClassCode);
        }
        List<Map<String, Object>> studentlList;
            studentlList = jwService.getStudentInfoClass(sClassCode);
        int nCurrentNum=studentlList.size();
        String codeIndex="";
        for (Map<String, Object> studentlMap : studentlList) {
           String code= (String) studentlMap.get("Code");
           if(codeIndex.indexOf(code)>-1){
               nCurrentNum--;
           }
            codeIndex+=code;
       
        }
        xdf.setnCurrentCount(String.valueOf(nCurrentNum));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        List<DicData> classLevel = dictionaryService.getDataByKey("s_level");
        mav.addObject("classLevel", classLevel);
        mav.addObject("xp", xdfPri);
        mav.addObject("xdf", xdf);
        return mav;
    }
    
    /**
     * 编辑
     * @param sa
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(HttpServletRequest request){
        Map<String, Object> xdf=new HashMap<>();
        xdf.put("sClassCode", request.getParameter("sClassCode"));
        xdf.put("remark", request.getParameter("remark"));
        xdf.put("sLevel", request.getParameter("sLevel"));
        Map<String, Object> map=new HashMap();
        try {
            jwxdfClassInfoService.updateForMap(xdf);
            map.put("message", "编辑成功");
            map.put("flag", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑失败");
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
            
        return map;
    }
    
    @RequestMapping("toinit")
    public ModelAndView toinit(){
        ModelAndView mav=new ModelAndView("/xdfclassinfo/init");
        return mav;
    }
   @RequestMapping("init")  
   @ResponseBody
   public Map<String , Object> init(String sClassCode){
       logger.info("---------------初始化班级开始-----------------");
      Map<String, Object> map= jwxdfClassInfoService.initClassCode(sClassCode);
       return map;
   }
}
