package cn.xdf.selfStudyRoom.web.manager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.domain.entity.UserRole;
import cn.xdf.selfStudyRoom.service.UserService;
import cn.xdf.selfStudyRoom.utils.PageView;

/**
 * 用户管理类
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/manager/user/")
public class UserController {
	
    private final Logger logger = LogManager.getLogger(UserController.class);
    
    @Resource
    private UserService  userService;
    
    /**
     * 员工管理列表
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index() {
		return "/view/manager/user/index";
	}
    
    /**
	 * 分页查询
	 * @param managerUser
	 * @param pageView
	 * @return
	 */
	@RequestMapping("queryUser")
	@ResponseBody
	public PageView queryUser(User user,PageView pageView){
		logger.info("分页查询员工数据");			
		return userService.queryUser(user, pageView);		
	}
	
	/**
     * 跳转新增界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "/view/manager/user/add";
    }
    
    /**
     * 保存数据
     * @param model
     * @param videoType
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public Map<String, Object> add(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=userService.add(user);
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
    public String toEdit(Long id,Model model) {
        User user=userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "/view/manager/user/edit";
    }
    
	/**
     * 修改用户信息
     * @param user
     * @param userRoles
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public Map<String,Object> update(User user) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            map=userService.update(user);
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
    @PostMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds,Integer status) {
    	Map<String,Object> map=new HashMap<String,Object>();
        try{
            map=userService.updateStatus(deleteIds,status);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("message", "更新用户状态失败");
        }
        return map;  
    }
    
    /**
     * 给用户分配角色界面
     * @return
     */
    @RequestMapping("toCfgUserRole")
    public ModelAndView toCfgUserRole(Long userId){
        ModelAndView mav=new ModelAndView("/view/manager/user/cfgUserRole");
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
            map=userService.saveUserRole(userRole,roleIds);
        }catch (Exception e) {
            logger.error("save user role is error !",e);
            map.put("flag", true);
            map.put("message", "配置失败，请稍候重试!");
        }
        return map;
    }
}
