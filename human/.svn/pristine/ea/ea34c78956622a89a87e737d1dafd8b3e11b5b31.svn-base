package com.human.recruitment.controller;


import java.util.List;
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
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.entity.InsideRecommendManagerEntity;
import com.human.recruitment.service.InsideRecommendManagerService;
import com.human.utils.Common;
import com.human.utils.PageView;

/**
 * 内推管理
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/recruit/insideRecommendManager/")
public class InsideRecommendManagerController {
    
    private final  Logger logger = LogManager.getLogger(InsideRecommendManagerController.class);
    
    @Resource
    private UserDeptService userDeptService;
       
    @Resource
    private InsideRecommendManagerService ioService;
    
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/recruitment/insideRecommendManager/list");
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,InsideRecommendManagerEntity  irm){        
        return ioService.query(pageView, irm);  
    }
    
     
    /**
     * 导出选择的内推管理信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelect")
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
        logger.info("内推管理信息导出(选择项)");
        return  ioService.exportSelect(ids, request, response);
    }
    
    /**
     * 内推管理信息本页导出数据处理
     * @param pageView
     * @param interviewEntity
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value ="exportThisPage")
    @ResponseBody
    public Map<String, Object> exportThisPage(PageView pageView,InsideRecommendManagerEntity  irm,HttpServletRequest request, HttpServletResponse response){
        logger.info("内推管理信息导出(本页)");
        return ioService.exportThisPage(pageView, irm, request, response);
    }
    
    /**
     * 导出所有的
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportAll")
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
        logger.info("内推管理信息导出(全部)");
        return  ioService.exportAll(request, response);
    }
    

}
