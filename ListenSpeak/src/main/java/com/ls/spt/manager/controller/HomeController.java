package com.ls.spt.manager.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.manager.service.HomeService;

@Controller
@RequestMapping(value="/home/")
public class HomeController {
    
    @Resource
    private HomeService homeService;
    
    @RequestMapping("home")
    public ModelAndView help() {
        return new ModelAndView("/framework/home");
    }
       
}
