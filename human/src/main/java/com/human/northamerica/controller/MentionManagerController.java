package com.human.northamerica.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.northamerica.entity.MentionInfo;
import com.human.northamerica.service.MentionService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value = "/northamerica/mention/")
public class MentionManagerController {

    @Resource
    private MentionService mentionService;
    
    @RequestMapping(value="inputIndex")
    public ModelAndView inputIndex(){
        return new ModelAndView("/northamerica/mention/inputIndex");
    }
    
    @RequestMapping("queryInfoPage")
    @ResponseBody
    public PageView queryInfoPage(MentionInfo info,PageView pageView) {
        return  mentionService.queryInfoPage(pageView,info);
    }
    
    @RequestMapping("exportMentionInfo")
    public void exportMentionInfo(HttpServletRequest request,HttpServletResponse response,MentionInfo info) {
        mentionService.exportMentionInfo(request,response,info);
    }
    
    
    @RequestMapping("importScoreView")
    public ModelAndView importScoreView() {
        ModelAndView mav=new ModelAndView("/northamerica/mention/import_score");
        return mav;
    }
    
    /**
     * 批量导入考试成绩
     * @return
     */
    @RequestMapping(value = "importScore")
    @ResponseBody
    public Map<String,Object> importScore(HttpServletRequest request) {
        Map<String,Object> result=mentionService.importScore(request);
        return result;
    }
    
    @RequestMapping("addScoreView")
    public ModelAndView addScoreView() {
        ModelAndView mav=new ModelAndView("/northamerica/mention/add_score");
        return mav;
    }
    
    
    @RequestMapping(value = "saveMention")
    @ResponseBody
    public Map<String,Object> saveMention(MentionInfo info) {
        Map<String,Object> result=new HashMap<String,Object>();
        try{
            mentionService.saveMention(info);
            result.put("flag", true);
            result.put("msg", "新增成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("msg", "新增异常，请稍后再试");
        }
        return result;
    }
    
    @RequestMapping("editScoreView")
    public ModelAndView editScoreView(Long id) {
        ModelAndView mav=new ModelAndView("/northamerica/mention/edit_score");
        MentionInfo info=mentionService.queryById(id);
        mav.addObject("info",info);
        return mav;
    }
    
    
    @RequestMapping(value = "editMention")
    @ResponseBody
    public Map<String,Object> editMention(MentionInfo info) {
        Map<String,Object> result=new HashMap<String,Object>();
        try{
            info.setUploadUser(Common.getMyUser().getUserid());
            mentionService.editMention(info);
            result.put("flag", true);
            result.put("msg", "更新成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("msg", "更新异常，请稍后再试");
        }
        return result;
    }
    
    @RequestMapping(value = "delScore")
    @ResponseBody
    public Map<String,Object> delScore(String deleteIds) {
        Map<String,Object> result=new HashMap<String,Object>();
        try{
            mentionService.delMentionByIds(deleteIds);
            result.put("flag", true);
            result.put("msg", "删除成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("msg", "删除异常，请稍后再试");
        }
        return result;
    }
    
    @RequestMapping(value = "refreshClassInfo")
    @ResponseBody
    public List<String> refreshClassInfo() {
        List<String> errorList = mentionService.refreshClassInfo();
        return errorList;
    }
    
    @RequestMapping(value="mentionReport")
    public ModelAndView mentionReport(){
        return new ModelAndView("/northamerica/mention/report_index");
    }
    
    @RequestMapping(value="mentionTeachReport")
    public ModelAndView mentionTeachReport(){
        return new ModelAndView("/northamerica/mention/teach_report_index");
    }
    
    @RequestMapping("queryMentionReport")
    @ResponseBody
    public PageView queryMentionReport(MentionInfo info,PageView pageView) {
        return  mentionService.queryMentionReport(pageView,info);
    }
    
    @RequestMapping("queryMentionTeachReport")
    @ResponseBody
    public PageView queryMentionTeachReport(MentionInfo info,PageView pageView) {
        return  mentionService.queryMentionTeachReport(pageView,info);
    }
    
    @RequestMapping(value="lookDetail")
    public ModelAndView lookDetail(MentionInfo info){
        ModelAndView mav=new ModelAndView("/northamerica/mention/look_detail");
        mav.addObject("info",info);
        return mav;
    }
    
    @RequestMapping("queryDetailReport")
    @ResponseBody
    public List<MentionInfo> queryDetailReport(MentionInfo info) {
        return  mentionService.queryMentionInfo(info);
    }
    
    @RequestMapping("exportMentionReport")
    public void exportMentionReport(HttpServletRequest request,HttpServletResponse response,MentionInfo info) {
        mentionService.exportMentionReport(request,response,info);
    }
    
    @RequestMapping("exportTeachMentionReport")
    public void exportTeachMentionReport(HttpServletRequest request,HttpServletResponse response,MentionInfo info) {
        mentionService.exportTeachMentionReport(request,response,info);
    }
}
