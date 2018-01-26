package com.human.examineelist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.examineelist.entity.ClassComments;
import com.human.examineelist.service.ClassCommentsService;


@Controller
@RequestMapping(value="/comments/List/")
public class ClassCommentsController {
    
    private final  Logger logger = LogManager.getLogger(ClassCommentsController.class);
    
    @Resource
    private ClassCommentsService classCommentsService;
    
    @RequestMapping("edit")
    public ModelAndView index(String code,String name) {
        ModelAndView mav=new ModelAndView("/northamerica/examineelist/comments_edit");
        ClassComments classComments=new ClassComments();
        classComments.setCode(code);
        classComments.setName(name);
        ClassComments cc1=classCommentsService.queryforcode(code);
        if(cc1==null){
            classCommentsService.insert(classComments);
            cc1=classCommentsService.queryforcode(code);
        }
        mav.addObject("cc", cc1);
        return mav;
    }
    @RequestMapping("toedit")
    @ResponseBody
    public  Map<String, Object> toedit(ClassComments cc){
        Map<String, Object> map = new HashMap<String, Object>();
        //预备
//        cc.setPretkComments(cc.getPretkComments().replaceAll("\r\n", "</br>"));
//        cc.setPredComments(cc.getPredComments().replaceAll("\r\n", "</br>"));
//        cc.setPreyfComments(cc.getPreyfComments().replaceAll("\r\n", "</br>"));
//      //基础
//        cc.setBaslComments(cc.getBaslComments().replaceAll("\r\n", "</br>"));
//        cc.setBasrComments(cc.getBasrComments().replaceAll("\r\n", "</br>"));
//        cc.setBassComments(cc.getBassComments().replaceAll("\r\n", "</br>"));
//        cc.setBaswComments(cc.getBaswComments().replaceAll("\r\n", "</br>"));
//      //强化
//        cc.setStrlComments(cc.getStrlComments().replaceAll("\r\n", "</br>"));
//        cc.setStrrComments(cc.getStrrComments().replaceAll("\r\n", "</br>"));
//        cc.setStrsComments(cc.getDassComments().replaceAll("\r\n", "</br>"));
//        cc.setBaswComments(cc.getBaswComments().replaceAll("\r\n", "</br>"));
//      //强化
//        cc.setDaslComments(cc.getDaslComments().replaceAll("\r\n", "</br>"));
//        cc.setDasrComments(cc.getDasrComments().replaceAll("\r\n", "</br>"));
//        cc.setDassComments(cc.getDassComments().replaceAll("\r\n", "</br>"));
//        cc.setDaswComments(cc.getDaswComments().replaceAll("\r\n", "</br>"));
        try {
            classCommentsService.update(cc);
            map.put("message", "编辑成功!");
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    @RequestMapping("insertreportinfo")
    @ResponseBody
    public Map<String, Object> insertreportinfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        String code=(String) session.getAttribute("Code");
        Map<String, Object> map=classCommentsService.queryforcode2(code);
        return map;
        
    }
    
}
