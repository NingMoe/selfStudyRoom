package com.human.jzbTest.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbDeptManager;
import com.human.jzbTest.service.JzbDeptService;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/jzbTest/dept/")
public class JzbDeptController {
    
    private final  Logger logger = LogManager.getLogger(JzbDeptController.class);
    
    @Autowired
    private JzbDeptService deptService;
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/jzbTest/dept/list");
            
        return mav;
    }
    
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView){
        JzbDept dept = new JzbDept();
        dept.setCompany(Common.getMyUser().getCompanyId());
        return  deptService.getDeptPage(pageView, dept);
    }
    
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/jzbTest/dept/add");
        MyUser user = Common.getMyUser();
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> addDept(JzbDept dept){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            deptService.addDept(dept);
            result.put("flag", true);
            result.put("message", "新增成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "新增失败");
        }
        return result;
    }
    
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Integer id){
        ModelAndView mav=new ModelAndView("/jzbTest/dept/edit");
        JzbDept dept = deptService.getDeptById(id);
        mav.addObject("dept", dept);
        return mav;
    }
    
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> editDept(JzbDept dept){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            deptService.editDept(dept);
            result.put("flag", true);
            result.put("message", "修改成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "修改失败");
        }
        return result;
    }
    
    @RequestMapping(value="toFpGly")
    public ModelAndView toFpGly(Integer id){
        ModelAndView mav=new ModelAndView("/jzbTest/dept/pzGly");
        List<JzbDeptManager> managers = deptService.getDeptManagers(id);
        JzbDept dept = deptService.getDeptById(id);
        mav.addObject("dept", dept);
        mav.addObject("managers", managers);
        return mav;
    }
    
    @RequestMapping(value="addGly")
    @ResponseBody
    public Map<String,Object> addGly(JzbDeptManager manager){
        Map<String,Object> result = new HashMap<String,Object>();
        String email = manager.getEmail();
        if(StringUtils.isEmpty(email)){
            result.put("flag", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        try{
            Users u = new Users();
            u.setLoginName(email);
            u.setCompanyId(Common.getMyUser().getCompanyId());
            List<Users> list = userService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else{
                boolean isExist = deptService.isManagerExist(manager);
                if(isExist){
                    result.put("flag", false);
                    result.put("message", "该用户已添加");
                    return result;
                }
                manager.setRealName(list.get(0).getName());
                deptService.addDeptManager(manager);
                result.put("flag", true);
                result.put("message", "添加成功");
                result.put("manager",manager);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    
    /**
     * 删除管理员
     * @return
     */
    @RequestMapping(value="delManager")
    @ResponseBody
    public Map<String,Object> delManager(JzbDeptManager manager){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            deptService.delDeptManager(manager);
            result.put("flag", true);
            result.put("message", "删除成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
}
