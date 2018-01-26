package com.human.jzbTest.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.jzbTest.entity.JzbMainConfig;
import com.human.jzbTest.service.JzbMainConfigService;
import com.human.utils.Common;


@Controller
@RequestMapping(value="/basic/jzbMainConfig/")
public class JzbMainConfigController {
    
    private final  Logger logger = LogManager.getLogger(JzbMainConfigController.class);
                           
    @Resource
    private JzbMainConfigService configService;
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    /**
     * 首页配置
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbMainConfig/list");
        String companyId=Common.getMyUser().getCompanyId();
        JzbMainConfig  jzbMainConfig =configService.selectByCompanyId(companyId);
        mav.addObject("jzbMainConfig", jzbMainConfig);
        mav.addObject("filepath", filePath);
        return mav;
    }
        
    /**
     * 保存首页配置
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object>saveConfig(HttpServletRequest request,JzbMainConfig jmf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=configService.saveConfig(request,jmf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存首页配置失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "保存首页配置失败，请稍后重试!");
        }
        return map;
    }
}
