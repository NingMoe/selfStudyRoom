package com.ls.spt.lstBasePaper.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;
import com.ls.spt.lstBasePaper.service.LstPaperQuestionService;

@Controller
@RequestMapping(value="/lstPaperQuestion/")
public class LstPaperQuestionController {
    
    private final  Logger logger = LogManager.getLogger(LstPaperQuestionController.class);
    
    @Resource LstPaperQuestionService lstPaperQuestionService;
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(LstPaperQuestion lpq ,PageView pageView){
        
        return lstPaperQuestionService.query(lpq,pageView);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(LstPaperQuestion lpq){
        Map<String, Object> map =new HashMap<String, Object>();
        try {
            lstPaperQuestionService.delete(lpq);
            map.put("flag", true);
            map.put("message", "删除成功！");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("flag", false);
            map.put("message", "系统异常请联系管理员");
        }
        return map;
    }
}
