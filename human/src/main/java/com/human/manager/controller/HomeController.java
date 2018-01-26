package com.human.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.manager.entity.HomeSearchBean;
import com.human.manager.service.HomeService;

@Controller
@RequestMapping(value="/home/")
public class HomeController {
    
    @Resource
    private HomeService homeService;
    
    @RequestMapping("home")
    public ModelAndView help() {
        return new ModelAndView("/framework/home");
    }
    
    
    @RequestMapping(value="getLinkDist")
    @ResponseBody
    public Map<String,Object> getLinkDist(HomeSearchBean  bean){
      return  homeService.getLinkDistReport(bean);
    }

}
