package com.human.teacherservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.teacherservice.entity.LibBorrowList;
import com.human.teacherservice.service.LibraryBoorowService;
import com.human.teacherservice.service.LibraryTypeService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/libraryborrow/")
public class LibraryBorrowController {
    
    @Resource
    private LibraryBoorowService libraryBoorowService;
    
    @Resource
    private LibraryTypeService libraryTypeService;
    
    /**
     * 微图书馆借阅页面
     * @return
     */
    @RequestMapping(value="borrowview")
    public ModelAndView borrowview(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/borrow");
        mov.addAllObjects(libraryTypeService.queryAll());
        return mov;
    }
    
    /**
     * 分页获取借阅记录
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView query(LibBorrowList libBorrowList, PageView pageView){
        return libraryBoorowService.query(libBorrowList, pageView);
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
