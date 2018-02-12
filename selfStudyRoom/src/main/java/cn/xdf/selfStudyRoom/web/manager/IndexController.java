package cn.xdf.selfStudyRoom.web.manager;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/s/")
public class IndexController {
	
	private final  Logger logger = LogManager.getLogger(IndexController.class);
	
	/**
     * 忘记密码
     * @param request
     * @return
     */
    @GetMapping("changePassWrod")
    public ModelAndView changePassWrod(HttpServletRequest request) {
    	logger.info("进入忘记密码重新设置");
        return new ModelAndView("/framework/changePassWord");
    }   
    
}
