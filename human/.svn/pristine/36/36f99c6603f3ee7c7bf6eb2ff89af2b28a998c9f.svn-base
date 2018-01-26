package com.human.sign.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.service.MailTempService;
import com.human.sign.entity.SignActivity;
import com.human.sign.entity.SignInfo;
import com.human.sign.service.SignActivityService;
import com.human.sign.service.SignInfoService;
/**
 * 签到系统前台控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/activity/")
public class SignWxController {
    
    private final Logger logger = LogManager.getLogger(SignWxController.class);
    
    @Resource
    private SignActivityService  saService;
    
    @Resource
    private MailTempService mailTempService;
    
    @Resource
    private SignInfoService signInfoService;
    
    
    /**
     * 进入签到首页页面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping(value="/sign/{activityTime}",method = RequestMethod.GET)
    public ModelAndView toIndex(@PathVariable("activityTime") Long activityTime) {
        logger.info("---进入签到首页-----");
        ModelAndView mav = new ModelAndView("/sign/frontWeiXin/index");
        SignActivity signActivity=saService.selectByActivityTime(activityTime);
        mav.addObject("signActivity", signActivity);
        return mav;
    }
    
    /**
     * 校验签到时间
     * @param fops
     * @param request
     * @return
     */
    @RequestMapping(value="/sign/checkSignTime")
    @ResponseBody
    public Map<String, Object> checkSignTime(Long activityId,String telOrCardNo){ 
        logger.info("校验签到时间----");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=signInfoService.checkSignTime(activityId, telOrCardNo);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("校验签到时间异常", e.getMessage());
            map.put("flag", false);
            map.put("message", "校验签到时间失败,请稍后重试!");
        }  
        return map;
    }
    
    /**
     * 签到成功页
     * @param id
     * @return
     */
    @RequestMapping(value="/sign/toSignSuccess")
    public ModelAndView toSignSuccess(String id,String activityTime) {
        logger.info("---进入签到成功页-----");
        ModelAndView mav = new ModelAndView("/sign/frontWeiXin/signSuccess");
        SignInfo signInfo=signInfoService.selectByPrimaryKey(Long.valueOf(id));
        mav.addObject("signInfo", signInfo);
        mav.addObject("activityTime", activityTime);
        return mav;
    }
    
    /**
     * 前台撤销签到
     * @param videoTypeId
     * @return
     */
     @RequestMapping(value="/sign/revoke",method = RequestMethod.POST)
     @ResponseBody
     public Map<String, Object> revoke(Long id) {
         logger.info("----前台撤销签到-----");
         return signInfoService.revoke(id);
     }
     
     /**
      * 确认签到页
      * @param id
      * @return
      */
     @RequestMapping(value="/sign/toMySign")
     public ModelAndView toMySign(String ids,String activityTime) {
         logger.info("---进入确认签到页-----");
         ModelAndView mav = new ModelAndView("/sign/frontWeiXin/mySign");         
         List<SignInfo>signInfoList=signInfoService.selectByIds(ids);
         List<SignInfo>hasSignList=new ArrayList<SignInfo>(5);//已签到的
         List<SignInfo>noSignList=new ArrayList<SignInfo>(5);//未签到的
         for(SignInfo signInfo:signInfoList){
             if("1".equals(signInfo.getIsSign())){
                 hasSignList.add(signInfo);
             }else{
                 noSignList.add(signInfo);
             }
         }
         mav.addObject("hasSignList", hasSignList);
         mav.addObject("noSignList", noSignList);
         mav.addObject("activityTime", activityTime);
         return mav;
     }
     
     /**
      * 确认签到(后4位重复)
      * @param fops
      * @param request
      * @return
      */
     @RequestMapping(value="/sign/mySign")
     @ResponseBody
     public Map<String, Object> confirmMySign(Long id){ 
         logger.info("----确认签到----");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=signInfoService.confirmMySign(id);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("确认签到异常", e.getMessage());
             map.put("flag", false);
             map.put("message", "确认签到失败,请稍后重试!");
         }  
         return map;
     }
     
     
     /**
      * 进入签到详情页
      * @param model
      * @param videoTypeIds
      * @return
      */
     @RequestMapping(value="/sign/signDetails",method = RequestMethod.GET)
     public ModelAndView toSignDetails(Long activityId) {
         logger.info("---进入签到详情页-----");
         //获取各个部门/学校的签到率
         Map<String, Object> map=saService.selectById(activityId);
         return new ModelAndView("/sign/frontWeiXin/signDetails",map);
     }
     
     
     /**
      * 进入部门签到详情页
      * @param model
      * @param videoTypeIds
      * @return
      */
     @RequestMapping(value="/sign/toDeptSignDetails",method = RequestMethod.GET)
     public ModelAndView toSignDetails(SignInfo info) {
         logger.info("---进入部门签到详情页-----");
         Map<String, Object> map=signInfoService.selectDeptSignDetails(info);
         return new ModelAndView("/sign/frontWeiXin/deptSignDetails",map);
     }
    
}
