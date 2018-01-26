package com.human.mail.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.mail.entity.AcceptMail;
import com.human.mail.service.AcceptMailService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/mail/accept_mail/")
public class AcceptMailController {
    
    private final  Logger logger = LogManager.getLogger(AcceptMailController.class);
    
    @Resource
    private AcceptMailService acceptMailService;
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private  DictionaryService dictionaryService;
    
    
    @RequestMapping(value="queryList")
    @ResponseBody
    public Map<String, Object> queryList(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        String path=request.getServletContext().getRealPath("/static/temp/");
        acceptMailService.downLoadAcceptMail(path);
        return result;
         
    }
    
    /**
     * 解析简历管理列表页面
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/recruitment/acceptMail/list");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        //获取简历来源
        List<DicData> resumeSourceList=dictionaryService.selectByDicCode("resume_source");       
        mav.addObject("companyList", companyList);
        mav.addObject("resumeSourceList", resumeSourceList);
        return mav;
    }
    
    /**
     * 分页查询
     * @param model
     * @param resources
     * @param pageNow
     * @return
     * @throws SchedulerException 
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,AcceptMail acceptMail){
        return  acceptMailService.query(pageView, acceptMail);
    }
    
    

}
