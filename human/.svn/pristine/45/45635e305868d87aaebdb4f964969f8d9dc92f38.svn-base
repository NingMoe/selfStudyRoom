package com.human.teacherservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.service.LibraryService;
import com.human.teacherservice.service.LibraryTypeService;
import com.human.utils.PageView;

/**
 * 
 * @author zx
 * 教师服务-微图书馆
 */
@Controller
@RequestMapping(value="/teacher/library/")
public class LibraryController {
    
    @Resource
    private LibraryService libraryService;
    
    @Resource
    private LibraryTypeService libraryTypeService;
    
    /**
     * 微图书馆主页
     * @return
     */
    @RequestMapping(value="view")
    public ModelAndView view(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/library");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 微图书馆上传excel
     * @return
     */
    @RequestMapping(value="updateexcel")
    public ModelAndView updateexcelview(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/updateexcel");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 微图书馆新增页
     * @return
     */
    @RequestMapping(value="addview")
    public ModelAndView addview(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/add");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 微图书馆修改页
     * @return
     */
    @RequestMapping(value="changeview")
    public ModelAndView changeview(){
        ModelAndView mov =new ModelAndView("/teacherservice/library/change");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 微图书馆分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView querylibrarybook(PageView pageView, LibBook libBook){
        return libraryService.query(pageView, libBook);
    }
    
    /**
     * 微图书馆通过id查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryById")
    public Map<String, Object> queryById(HttpServletRequest request,  HttpServletResponse response,  LibBook libBook){
        return libraryService.queryById(request, response, libBook);
    }
    
    /**
     * 微图书馆新建
     * @return
     */
    @ResponseBody
    @RequestMapping(value="insert")
    public Map<String, Object> insert(LibBook libBook, HttpServletRequest request){
        return  libraryService.insert(libBook, request);
    }
    
    /**
     * 微图书馆更新
     * @return
     */
    @ResponseBody
    @RequestMapping(value="update")
    public Map<String, Object> update(LibBook libBook, HttpServletRequest request){
        return  libraryService.update(libBook, request);
    }
    
    /**
     * 微图书馆删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delete")
    public Map<String, Object> delete(LibBook libBook){
        return  libraryService.delete(libBook);
    }
    
    /**
     * 微图书馆导入excel
     * @return
     */
    @ResponseBody
    @RequestMapping(value="upexcel")
    public Map<String, Object> upexcel(HttpServletRequest request){
        return  libraryService.upexcel(request);
    }
    
    /**
     * 微图书馆批量导出
     * @return
     */
    @ResponseBody
    @RequestMapping(value="downselect")
    public Map<String, Object> downselect(String ids, HttpServletRequest request, HttpServletResponse response){
        return  libraryService.downselect(ids, request, response);
    }
    
    /**
     * 微图书馆批量启用/禁用
     * @return
     */
    @ResponseBody
    @RequestMapping(value="openselect")
    public Map<String, Object> isopen(String ids, Integer valid){
        return  libraryService.isopen(ids,valid);
    }
    
    /**
     * spring接收date参数
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @org.springframework.web.bind.annotation.InitBinder  
    public void InitBinder(WebDataBinder dataBinder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
}
