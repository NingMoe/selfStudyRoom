package com.human.teacherservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.teacherservice.entity.LibBookError;
import com.human.teacherservice.service.LibraryBookErrorService;
import com.human.teacherservice.service.LibraryTypeService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/libraryerror/")
public class LibraryErrorController {
    
    @Resource
    private LibraryTypeService libraryTypeService;
    
    @Resource
    private LibraryBookErrorService libraryBookErrorService;
    
    /**
     * 微图书馆异常处理页面
     * @return
     */
    @RequestMapping(value="errorview")
    public ModelAndView errorview(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/errorview");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 微图书馆书籍异常分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView queryerrorbook(PageView pageView, LibBookError libBookError){
        return libraryBookErrorService.query(pageView, libBookError);
    }
    
    /**
     * 微图书馆异常解决
     * @return
     */
    @ResponseBody
    @RequestMapping(value="selecttrue")
    public Map<String, Object> selecttrue(String ids){
        return libraryBookErrorService.selecttrue(ids);
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
