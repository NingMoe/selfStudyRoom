package com.human.continuedClass.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.continuedClass.entity.SendCardMail;
import com.human.continuedClass.service.SendCardMailService;
import com.human.utils.PageView;
@Controller
@RequestMapping(value="/continued_class/sendCard_mail/")
public class SendCardMailController {
    
    private final  Logger logger = LogManager.getLogger(SendCardMailController.class);
    
    @Resource
    private SendCardMailService scmService;
    
    /**
     * 续班卡发送邮箱首页
     */
    @RequestMapping("index")
    public ModelAndView index(Long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/sendCardMail/list");
        mav.addObject("ruleId", ruleId);
        return mav;
    }
    
    /**
     * 分页查询续班卡邮箱
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,SendCardMail scm) {
        return  scmService.query(pageView, scm);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(Long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/sendCardMail/add");
        mav.addObject("ruleId", ruleId);
        return mav;  
    }
    
    /**
     * 保存续班卡邮箱
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(SendCardMail scm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=scmService.add(scm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存续班卡发送邮箱失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/sendCardMail/edit");
        SendCardMail scm=scmService.selectByPrimaryKey(id);
        mav.addObject("scm", scm);
        return mav; 
    }
    
    /**
     * 编辑续班卡邮箱
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(SendCardMail scm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=scmService.edit(scm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑续班卡发送邮箱失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除续班卡发送邮箱（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除续班卡发送邮箱");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=scmService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除续班卡发送邮箱失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除续班卡发送邮箱失败,请稍后重试!");
        }
        return map;
    }
    
   /**
    * 检测邮箱发件服务器连接是否正常
    * @param id
    * @return
    */
   @RequestMapping("checkMail")
   @ResponseBody
   public Map<String, Object> checkMail(Long id) {
       logger.info("检测邮箱发件服务器连接是否正常");   
       Map<String, Object> map = new HashMap<String, Object>();
       try {
           map=scmService.checkMail(id);
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("检测邮箱发件服务器连接失败!", e.getMessage());
           map.put("flag", false);
           map.put("message", "检测邮箱发件服务器连接失败,请稍后重试!");
       }
       return map;
   }
    
    
    
}
