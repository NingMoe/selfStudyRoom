package cn.xdf.selfStudyRoom.web.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.domain.entity.Role;
import cn.xdf.selfStudyRoom.service.ResourcesService;
import cn.xdf.selfStudyRoom.service.RoleService;
import cn.xdf.selfStudyRoom.utils.CommonUtil;
import cn.xdf.selfStudyRoom.utils.PageView;


/**
 * 角色管理
 * @author liuwei63 
 */
@Controller
@RequestMapping("/manager/role/")
public class RoleController {

	private final Logger logger = LogManager.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	
	@Resource
    private ResourcesService resourceService;

	/**
	 * 角色列表
	 * 
	 */
	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/view/manager/role/index");
		return mav;
	}
	
	/**
	 * 分页查询
	 * @param role
	 * @param pageView
	 * @return
	 */
	@RequestMapping(value="queryRole")
	@ResponseBody
	public PageView query(Role role,PageView pageView){
	    role.setCreateUser(CommonUtil.getMyUser().getUserId());
		return  roleService.query(pageView, role);
	}
	
	/**
     * 分页查询
     * @param role
     * @param pageView
     * @return
     */
    @RequestMapping(value="getRoles")
    @ResponseBody
    public PageView getRoles(Role role,PageView pageView){
        return  roleService.query(pageView, role);
    }

	/**
     * 更新角色状态
     * @param videoTypeId
     * @return
     */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds,Integer status) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("flag", true);
            map.put("message", "操作成功！");
            roleService.updateStatus(deleteIds,status);
        } catch (Exception e) {
            map.put("flag", false);
            map.put("message", "操作失败！");
            logger.error(" role updateStatus id error ", e);
        }
        return map;
    }
	
	/**
	 * 跳到新增页面
	 * @return
	 */
	@RequestMapping(value = "toAdd")
	public ModelAndView toAdd() {
		ModelAndView mav = new ModelAndView("/view/manager/role/add");
		return mav;
	}

	/**
	 * 保存新增
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> add(Role role) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Role> oldRole = roleService.queryRole(role);
			if (oldRole.size()>0) {
				map.put("flag", false);
				map.put("message", "对不起，角色名重复，请重新输入!");
			}else{
				role.setCreateUser(CommonUtil.getMyUser().getUserId());
				roleService.add(role);
				map.put("flag", true);
				map.put("message", "角色添加成功!");
			}
		}catch (Exception e) {
			logger.error("Role is add error !", e);
			map.put("flag", false);
			map.put("message", "添加失败，请稍后重试!");
		}
		return map;
	}

	/**
	 * 进入编辑页面
	 * @param role
	 * @return
	 */
    @RequestMapping(value = "toEdit")
    public ModelAndView getById(Role role) {
        List<Role> roles = roleService.queryRole(role);
        ModelAndView mav = new ModelAndView();
        if(roles.size()>0){
            mav.addObject("role", roles.get(0));
        }
        mav.setViewName("/view/manager/role/edit");
        return mav;
    }

    /**
     * 更新修改的信息
     * @param model
     * @param role
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(Model model, Role role) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            roleService.modify(role);
            map.put("flag", true);
            map.put("message", "角色编辑成功!");
        } catch (Exception e) {
            logger.error("Role is update error !", e);
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }

    /**
     * 设置角色权限页
     * @return
     */
    @RequestMapping(value = "toCfgRoleRes")
    public ModelAndView toCfgRoleRes(Role role) {
        ModelAndView mav = new ModelAndView("/view/manager/role/cfgRoleRes");
        mav.addObject("roleId", role.getId());
        return mav;
    }
    
    
    @RequestMapping(value="permissioRole")
    @ResponseBody
    public List<Resources> permissioRole(Role role){
        //获取当前角色拥有的权限
        List<Resources> resources = roleService.getRoleRes(role);
        //获取资源树
        List<Resources> allRes = resourceService.findAll();        
        for(Resources mrall:allRes){
            for(Resources mr:resources){
                if(mrall.getId().equals(mr.getId())){
                    mrall.setIsChecked(true);
                    break;
                }
            }
        }
        return allRes;
    }

    /**
     * 获取权限树
     * @return
     */
    @ResponseBody
    @RequestMapping(value="saveRoleRes")
    public String saveRoleRes(Long roleId,String rescId){
        String result= "true";
        List<String> list = new ArrayList<String>();
        if(rescId.length()>0){
             list = CommonUtil.removeSameItem(Arrays.asList(rescId.split(",")));
        }
        try {
            roleService.saveRoleRes(roleId, list);
        } catch (Exception e) {
            logger.error("saveRoleRes is error !",e);
            result="false";
        }
        return result;
    }
    
    
}