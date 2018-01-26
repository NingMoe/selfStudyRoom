package com.human.teacherservice.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.teacherservice.entity.LibBookType;
import com.human.teacherservice.service.LibraryTypeService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/librarytype/")
public class LibraryBookTypeController {
    
    @Resource
    private LibraryTypeService libraryTypeService;
    
    @RequestMapping(value="view")
    public ModelAndView view(){
        return new ModelAndView("/teacherservice/library/book_type");
    }
    
    @RequestMapping(value="addview")
    public ModelAndView addview(){
        return new ModelAndView("/teacherservice/library/addtype");
    }
    
    @RequestMapping(value="changeview")
    public ModelAndView changeview(){
        return new ModelAndView("/teacherservice/library/changetypeview");
    }
    
    /**
     * 获取所有分类
     * @param libBookType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryAll")
    public Map<String, Object> queryAll(){
        return libraryTypeService.queryAll();
    }
    
    /**
     * 分页获取分类
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView query(LibBookType libBookType, PageView pageView){
        return libraryTypeService.query(libBookType, pageView);
    }
    
    /**
     * 新增
     * @param libBookType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryById")
    public Map<String, Object> queryById(LibBookType libBookType, HttpServletRequest request){
        return libraryTypeService.queryById(libBookType);
    }
    
    /**
     * 新增
     * @param libBookType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="insert")
    public Map<String, Object> insert(LibBookType libBookType, HttpServletRequest request){
        return libraryTypeService.insert(libBookType,request);
    }
    
    /**
     * 删除
     * @param libBookType
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delete")
    public Map<String, Object> delete(LibBookType libBookType){
        return libraryTypeService.delete(libBookType);
    }
    
    /**
     * 更新
     * @param libBookType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="update")
    public Map<String, Object> update(LibBookType libBookType, HttpServletRequest request){
        return libraryTypeService.update(libBookType,request);
    }
    
    /**
     * 批量禁用
     * @param libBookType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updateselect")
    public Map<String, Object> updateselect(String ids, Integer is_valid){
        return libraryTypeService.updateselect(ids, is_valid);
    }
}
