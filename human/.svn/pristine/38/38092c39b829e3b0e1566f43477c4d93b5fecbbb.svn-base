package com.human.rank.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.rank.entity.RankClasses;
import com.human.rank.service.RankClassesService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/rankclasses")
public class RankClassesController {
    
    @Resource
    private RankClassesService rankClassesService;
    
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/rank/rankclasses");
    }
    
    /**
     * 新增班级页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/addview")
    public ModelAndView addview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/rank/addrankclasses");
    }
    
    /**
     * 新增班级页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/changeview")
    public ModelAndView changeview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/rank/changerankclasses");
    }
    
    /**
     * 上传班级页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/uploadview")
    public ModelAndView uploadview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/rank/uploadrankclasses");
    }
    
    /**
     * 分页获取排行规则
     * @param page
     * @param rankInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView page, RankClasses rankClasses){
        return rankClassesService.query(page, rankClasses);
    }
    
    /**
     * 新增班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert")
    public Map<String, Object> insert(RankClasses rankClasses){
        return rankClassesService.insert(rankClasses);
    }
    
    /**
     * 修改班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update")
    public Map<String, Object> update(RankClasses rankClasses){
        return rankClassesService.update(rankClasses);
    }
    
    /**
     * 查询班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(RankClasses rankClasses){
        return rankClassesService.select(rankClasses);
    }
    
    /**
     * 删除班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete")
    public Map<String, Object> delete(RankClasses rankClasses){
        return rankClassesService.delete(rankClasses);
    }
    
    /**
     * 批量删除班级
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteselect")
    public Map<String, Object> deleteselect(String ids){
        return rankClassesService.deleteselect(ids);
    }
    
    /**
     * 删除全部班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteall")
    public Map<String, Object> deleteall(RankClasses rankClasses){
        return rankClassesService.deleteall(rankClasses);
    }
    
    /**
     * 批量导入班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upload")
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response){
        return rankClassesService.upload(request, response);
    }
    
    /**
     * 导出班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/download")
    public Map<String, Object> download(RankClasses rankClasses, HttpServletRequest request, HttpServletResponse response){
        return rankClassesService.download(rankClasses, request, response);
    }
    
    /**
     * 导出班级
     * @param rankClasses
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getClassesNum")
    public Map<String, Object> getClassesNum(){
        rankClassesService.getClassesNum();
        return null;
    }
}
