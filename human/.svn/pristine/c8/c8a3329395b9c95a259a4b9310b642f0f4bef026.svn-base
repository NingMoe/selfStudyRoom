package com.human.manager.controller;


import java.util.ArrayList;
import java.util.Arrays;
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

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.entity.UserRole;
import com.human.manager.entity.Users;
import com.human.manager.service.RoleService;
import com.human.manager.service.UserDeptService;
import com.human.manager.service.UserService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.Md5Tool;
import com.human.utils.PageView;

/**
 * 
 * @author HF-121093-01
 * 后台用户管理
 */
@Controller
@RequestMapping("/manager/user/")
public class UserController {
	
    private final Logger logger = LogManager.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private RoleService managerRoleService;
    @Resource
    private UserDeptService udService;
	/**
	 * 
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
	public PageView queryUser(Users managerUser,PageView pageView) {
	    managerUser.setCompanyId(Common.getMyUser().getCompanyId());
		return  userService.query(pageView, managerUser);
	}
	
	/**
     * 更新用户状态
     * 
     * @param videoTypeId
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds,Integer status) {
        return   userService.updateStatus(deleteIds,status);
    }

    
    
	
	/**
	 * 跳转新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public ModelAndView toAdd() {
	    ModelAndView mav=new ModelAndView("/manager/user/add");
	    List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
	    mav.addObject("companyList", companyList);
		return mav;
	}

	/**
     * 进入编辑界面
     * 
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Users user) {
        user.setEmpSource(2);
        if(user.getId()==null){
            return null;
        }
        List<Users> users = userService.queryUser(user);
        ModelAndView mav = new ModelAndView();
        if(users.size()>0){
            mav.addObject("user", users.get(0));
            Long userId=Common.getMyUser().getUserid();
            List<HrCompany> companyList=udService.getUserCompany(userId);
            mav.addObject("companyList", companyList);
            UserDept ud =new UserDept();
            ud.setCompanyId(users.get(0).getCompanyId());
            ud.setUserId(userId);
            List<HrOrganization> hrOrg=udService.getUserOrg(ud);
            mav.addObject("hrOrgList", hrOrg);
        }
        mav.setViewName("/manager/user/edit");
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
	public Map<String, Object> add(Users user) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		    Users nUser=new Users();
		    nUser.setLoginName(user.getLoginName());
			List<Users> users = userService.queryUser(nUser);
			if (users.size()>0) {
				map.put("flag", false);
				map.put("message", "对不起，用户名已存在，请重新输入!");
			} else {
			    MyUser myUser=Common.getMyUser();
			    user.setCreateUser(myUser.getUserid());
			    user.setCreateName(myUser.getName());;
			    user.setUserPassword(Md5Tool.getMd5(user.getUserPassword()));
			    userService.add(user);
				map.put("flag", true);
				map.put("message", "用户添加成功!");
			}
		} catch (Exception e) {
		    logger.error(e);
			map.put("flag", false);
			map.put("message", "添加失败，请稍后重试!");
		}
		return map;
	}

	/**
     * 修改用户信息
     * @param user
     * @param userRoles
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String,Object> update(Users user) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            userService.updateMkUser(user);
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
     * 给用户分配角色界面
     * @return
     */
    @RequestMapping("toCfgUserRole")
    public ModelAndView toCfgUserRole(Long userId){
        ModelAndView mav=new ModelAndView("/manager/user/cfgUserRole");
        List<UserRole> roles = userService.getUserRole(userId);
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
            userService.saveUserRole(userRole,roleIds);
            map.put("flag", true);
            map.put("message", "配置成功");
        } catch (Exception e) {
            logger.error("save user role is error !",e);
            map.put("flag", true);
            map.put("message", "配置失败，请稍候重试!");
        }
        return map;
    }
    
	
	
	/**
	 * 给用户分配学校界面
	 * @return
	 */
	@RequestMapping("toCfgDept")
	public ModelAndView toCfgDept(long userId){
		ModelAndView mav=new ModelAndView("/manager/user/cfgDept");
		mav.addObject("userId", userId);
		return mav;
	}
	
    @RequestMapping(value = "showTree")
    @ResponseBody
    public List<HrOrganization> showTree(long userId) {
        // 获取当前选择用户已配置的管理部门
        List<UserDept> userDeptList = udService.getUserAllOrg(userId);
        // 获取当前登陆用户已配置的管理部门
        List<HrOrganization> myDeptList =udService.getUserDeptTree(Common.getMyUser().getUserid());
        for (HrOrganization mDept : myDeptList) {
            for (UserDept uDept : userDeptList) {
                if (mDept.getId().equals(uDept.getDeptId())) {
                    mDept.setIsChecked(true);
                    break;
                }
            }
        }
        return myDeptList;
    }
	
	/**
	 * 保存用户学校
	 * @return
	 */
	@ResponseBody
	@RequestMapping("saveUserDept")
	public String saveUserDept(String rescId,Long userId){
	    String result= "true";
        List<String> list = new ArrayList<String>();
        if(rescId.length()>0){
             list = Common.removeSameItem(Arrays.asList(rescId.split(",")));
        }
        try {
            udService.saveUserDept(userId, list);
        } catch (Exception e) {
            logger.error("saveRoleRes is error !",e);
            result="false";
        }
        return result;
	}
}