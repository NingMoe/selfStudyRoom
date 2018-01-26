package com.human.teacherservice.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.teacherservice.entity.LibBookBuy;
import com.human.teacherservice.service.LibBookBuyService;
import com.human.utils.PageView;


@Controller
@RequestMapping(value="/teacher/libraryFeedBack/")
public class LibraryBuyController {
 
    private final  Logger logger = LogManager.getLogger(LibraryBuyController.class);
       
    @Resource
    private LibBookBuyService libBookBuyService;
        
    /**
     * 反馈记录列表页
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView index(){
        ModelAndView mov = new ModelAndView("/teacherservice/library/feedBackList");       
        return mov;
    }
    
    /**
     * 反馈记录分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView query(PageView pageView, LibBookBuy info){
        logger.info("----反馈记录分页查询----");
        return libBookBuyService.query(pageView, info);
    }
    
    /**
     * 导出反馈记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportAll")
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
        logger.info("导出反馈记录-----");
        return  libBookBuyService.exportAll(request, response);
    }
    
}
