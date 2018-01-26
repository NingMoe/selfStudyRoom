package com.ls.spt.manager.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.AreaInfo;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.entity.School;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.basic.service.SchoolService;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.entity.UserRole;
import com.ls.spt.manager.service.TeacherUserService;

/** 
 * 后台用户管理
 * @author 刘威
 * 
 */
@Controller
@RequestMapping("/manager/user/")
public class UserController {
	
    private final Logger logger = LogManager.getLogger(UserController.class);
    
	@Resource
	private TeacherUserService tuService;
	
	@Resource
    private  DictionaryService dictionaryService;
    
	@Resource
	private AreaInfoService areaInfoService;
	
	@Resource
	private SchoolService  schoolService;
	
	/**
	 * @param managerUser
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView query() {
		ModelAndView mav=new ModelAndView("/manager/user/list");
		return mav;
	}
	
	/**
	 * 分页查询
	 * @param managerUser
	 * @param pageView
	 * @return
	 */
	@RequestMapping("queryUser")
	@ResponseBody
	public PageView queryUser(TeacherUser tu,PageView pageView){
		return  tuService.queryUser(tu, pageView);
	}
	
	
	
	/**
     * 跳转新增界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/manager/user/add");
        //查询学科数据字典
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        //查询年级数据字典
        List<DicData> gradeList=dictionaryService.selectByDicCode("grade");
        mav.addObject("gradeList", gradeList);
        //查询新东方学校数据字典
        List<DicData> xdfSchoolList=dictionaryService.selectByDicCode("xdfSchool");
        mav.addObject("xdfSchoolList", xdfSchoolList);   
        //查询省份
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
	
   
    /**
     * 保存数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(TeacherUser tu) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=tuService.add(tu);
        }catch (Exception e) {
            logger.error(e);
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
	
	

	/**
     * 进入编辑界面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav = new ModelAndView("/manager/user/edit");
        TeacherUser user=tuService.selectByPrimaryKey(id);
        mav.addObject("user", user);
        //查询学科数据字典
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        //查询年级数据字典
        List<DicData> gradeList=dictionaryService.selectByDicCode("grade");
        mav.addObject("gradeList", gradeList);
        //查询新东方学校数据字典
        List<DicData> xdfSchoolList=dictionaryService.selectByDicCode("xdfSchool");
        mav.addObject("xdfSchoolList", xdfSchoolList);   
        //查询省份
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        //查询出公立学校
        if("2".equals(user.getType())){
            School school=schoolService.selectById(Long.valueOf(user.getSchool()));
            mav.addObject("school", school);
        }
        return mav;
    }
    
    	
	/**
     * 修改用户信息
     * @param user
     * @param userRoles
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String,Object> update(TeacherUser tu) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            map=tuService.update(tu);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("message", "修改失败");
        }
        return map;
    }
    
    
    /**
     * 更新用户状态
     * @param videoTypeId
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds,Integer status) {
        return   tuService.updateStatus(deleteIds,status);
    }
    
    
    /**
     * 给用户分配角色界面
     * @return
     */
    @RequestMapping("toCfgUserRole")
    public ModelAndView toCfgUserRole(Long userId){
        ModelAndView mav=new ModelAndView("/manager/user/cfgUserRole");
        List<UserRole> roles = tuService.getUserRole(userId);
        mav.addObject("userId", userId);
        List<String> u=new ArrayList<String>();
        for(UserRole ur:roles){
            u.add(String.valueOf(ur.getRoleId()));
        }
        mav.addObject("roles", u);
        return mav;
    }
    
    
    /**
     * 保存用户分配角色
     * @return
     */
    @ResponseBody
    @RequestMapping("cfgUserRole")
    public Map<String,Object> cfgUserRole(UserRole userRole,String roleIds){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            map=tuService.saveUserRole(userRole,roleIds);
        }catch (Exception e) {
            logger.error("save user role is error !",e);
            map.put("flag", true);
            map.put("message", "配置失败，请稍候重试!");
        }
        return map;
    }
    
}