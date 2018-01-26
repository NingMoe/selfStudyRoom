package com.human.jzbTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jzbTest/weixin")
public class JzbWeixinController {
    
    @RequestMapping(value="/view0")
    public ModelAndView view0(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view0");
        return mav;
    }
    
    @RequestMapping(value="/view1")
    public ModelAndView view1(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view1");
        return mav;
    }
    
    @RequestMapping(value="/view2")
    public ModelAndView view2(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view2");
        return mav;
    }
    
    @RequestMapping(value="/view3")
    public ModelAndView view3(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view3");
        return mav;
    }
    
    @RequestMapping(value="/view4")
    public ModelAndView view4(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view4");
        return mav;
    }
    
    @RequestMapping(value="/view5")
    public ModelAndView view5(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view5");
        return mav;
    }
    
    @RequestMapping(value="/view6")
    public ModelAndView view6(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view6");
        return mav;
    }
    
    @RequestMapping(value="/view7")
    public ModelAndView view7(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view7");
        return mav;
    }
    
    @RequestMapping(value="/view8")
    public ModelAndView view8(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view8");
        return mav;
    }
    
    @RequestMapping(value="/view9")
    public ModelAndView view9(){
        ModelAndView mav=new ModelAndView("/jzbTest/weixin/view9");
        return mav;
    }
   
}
