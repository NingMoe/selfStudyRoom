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
import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.College;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.CollegeService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
/**
 * 大学基础数据配置
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/basic/college/")
public class CollegeController {
    
    private final  Logger logger = LogManager.getLogger(CollegeController.class);
            
    @Autowired 
    private CollegeService collegeService;
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    /**
     * 招聘大学列表页
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("index")
    public ModelAndView query( ) {
        ModelAndView mav=new ModelAndView("/basic/college/list");
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
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
    public PageView queryUser(PageView pageView,College college) {
        return  collegeService.query(pageView,college);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/basic/college/add");
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
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
    public Map<String, Object> add(College college) {      
        logger.info("添加大学");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            college.setCreateUser(Common.getAuthenticatedUsername());
            collegeService.insertSelective(college);
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
  public ModelAndView toEdit(Long collegeId) {
      ModelAndView mav = new ModelAndView("/basic/college/edit");
      College college=collegeService.selectByPrimaryKey(collegeId);
      mav.addObject("college", college);
      AreaInfo area = new AreaInfo();
      area.setAreaLevel(1);
      List<AreaInfo> areaInfo = areaInfoService.getArea(area);
      mav.addObject("areaInfo", areaInfo);
      return mav;
  }
  
  
/**
 * 修改大学信息
 * @param user
 * @param userRoles
 * @return
 */
@RequestMapping("update")
@ResponseBody
public Map<String,Object> update(College college) {
    logger.info("修改大学");
    Map<String,Object> map=new HashMap<String,Object>();
    try{
        college.setUpdateUser(Common.getAuthenticatedUsername());
        college.setUpdateTime(TimeUtil.getCurrentTimestamp());
        collegeService.updateByPrimaryKeySelective(college);
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
* 删除大学（逻辑删除）
* @param videoTypeId
* @return
*/
@RequestMapping("updateStatus")
@ResponseBody
public Map<String, Object> updateStatus(String deleteIds) {
    logger.info("删除大学");
    return collegeService.updateStatus(deleteIds);
}

/**
 * 同步大学数据
 * @return
 */
@RequestMapping("refreshCollege")
@ResponseBody
public Map<String, Object> checkRecruitMail() {
    logger.info("同步大学数据"); 
    Map<String, Object> map= new HashMap<String, Object>();
    try{
        collegeService.refreshCollege();
        map.put("flag", true);
        map.put("message", "同步成功"); 
    }catch(Exception e){
        logger.error("同步失败！", e.getMessage());
        map.put("flag", false);
        map.put("message", "同步失败");
    }
    return map;
}

   
    
    
    
    
    
    
   

   


    
    
    
    
    
    
}
