package com.human.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.entity.RecruitMail;
import com.human.basic.service.RecruitMailService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
/**
 * 邮箱配置
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/basic/recruitMail/")
public class RecruitMailController {
    
    private final  Logger logger = LogManager.getLogger(RecruitMailController.class);
    
    @Autowired 
    private HrCompanyService hrCompanyService;
            
    @Autowired 
    private RecruitMailService recruitMailService;
    
    
    /**
     * 招聘邮箱列表页
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("index")
    public ModelAndView query( ) {
        ModelAndView mav=new ModelAndView("/basic/recruitMail/list");
        List<HrCompany> companys = hrCompanyService.findAll();
        mav.addObject("companys", companys);
        return mav;
    }
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("queryList")
    @ResponseBody
    public PageView queryUser(PageView pageView,RecruitMail recruitMail) {
        return  recruitMailService.query(pageView,recruitMail);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/basic/recruitMail/add");
        List<HrCompany> companys = hrCompanyService.findAll();
        mav.addObject("companys", companys);
        return mav;
    }
    
    /**
     * 保存数据
     * 
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(RecruitMail recruitMail) {      
        logger.info("添加邮箱");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            recruitMail.setCreateUser(Common.getAuthenticatedUsername());
            recruitMailService.insertSelective(recruitMail);
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch(Exception e){
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    
  /**
   * 进入编辑界面
   * @param model
   * @param videoTypeIds
   * @return
   */
  @RequestMapping("toEdit")
  public ModelAndView toEdit(Long recruitMailId) {
      ModelAndView mav = new ModelAndView("/basic/recruitMail/edit");
      RecruitMail recruitMail=recruitMailService.selectByPrimaryKey(recruitMailId);
      mav.addObject("recruitMail", recruitMail);
      List<HrCompany> companys = hrCompanyService.findAll();
      mav.addObject("companys", companys);
      return mav;
  }
  
  
/**
 * 修改邮箱信息
 * @param user
 * @param userRoles
 * @return
 */
@RequestMapping("update")
@ResponseBody
public Map<String,Object> update(RecruitMail recruitMail) {
    logger.info("修改邮箱");
    Map<String,Object> map=new HashMap<String,Object>();
    try{
        recruitMail.setUpdateUser(Common.getAuthenticatedUsername());
        recruitMail.setUpdateTime(TimeUtil.getCurrentTimestamp());
        recruitMailService.updateByPrimaryKeySelective(recruitMail);
        map.put("flag", true);
        map.put("message", "修改成功");
    }catch(Exception e){
        logger.error(e);
        map.put("flag", false);
        map.put("message", "修改失败");
    }
    return map;
}

/**
* 删除邮箱（逻辑删除）
* @param videoTypeId
* @return
*/
@RequestMapping("updateStatus")
@ResponseBody
public Map<String, Object> updateStatus(String deleteIds) {
    logger.info("删除邮箱");
    return recruitMailService.updateStatus(deleteIds);
}

/**
 * 检测邮箱协议
 * @param id
 * @param type 1:pop3协议 、2：Exchange协议
 * @return
 */
@RequestMapping("checkRecruitMail")
@ResponseBody
public Map<String, Object> checkRecruitMail(Long id,int type) {
    logger.info("检测邮箱协议");   
    return  recruitMailService.checkRecruitMail(id, type);
}

   
    
    
    
    
    
    
   

   


    
    
    
    
    
    
}
