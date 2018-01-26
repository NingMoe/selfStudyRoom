package com.human.front.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.RecruitAcceptanceService;
import com.human.utils.Constants;

@Controller
@RequestMapping(value = "/front/mine/")
public class MineController {
    
    private final  Logger logger = LogManager.getLogger(MineController.class);
    
    @Resource
    private RecruitAcceptanceService recruitAcceptanceService;
    
    /**
     * 进入我的主页
     * @return
     */
    @RequestMapping(value="tomain")
    public ModelAndView toMyCenter(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav= new ModelAndView("/front/mine/main");
        try{
            String openId = (String) request.getSession().getAttribute(Constants.OPENID); 
            if(StringUtils.isNotEmpty(openId)){
                ResumeSeeker rs = recruitAcceptanceService.selectByOpenId(openId);
                if(rs!=null){
                    mav.addObject("rsId",rs.getId()); 
                }  
            }  
        }catch(Exception e){
            e.printStackTrace();
            logger.error("进入我的主页失败!", e.getMessage());
        }        
        return mav;
    }
    
    
    /**
     * 进入微信关联页面
     * @return
     */
    @RequestMapping(value="toWeixinRelation")
    public ModelAndView toTddetail(Long rsId){
        ModelAndView mav=new ModelAndView("/front/mine/toWeixinRelation");
        mav.addObject("rsId",rsId); 
        return mav;
    }
    
    
    /**
     * 解除微信绑定
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="removeBinding")
    @ResponseBody
    public Map<String, Object>removeBinding(HttpServletRequest request,Long resumeSeekerId) {
        logger.info("解除微信绑定");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = recruitAcceptanceService.removeBinding(resumeSeekerId);
            if((boolean) map.get("flag")){//解绑成功清除解绑状态
                request.getSession().removeAttribute(Constants.BINDSTATUS); 
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("解除微信绑定失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "解除微信绑定失败！");
        }
        return map;
    }
    
    /**
     * 进入联系我们页面
     * @return
     */
    @RequestMapping(value="toCallMe")
    public ModelAndView toCallMe(){
        return new ModelAndView("/front/mine/toCallMe");
    }
    
    
}
