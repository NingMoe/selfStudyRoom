package com.human.basic.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSClient;
import com.human.front.entity.MenuDept;
import com.human.front.entity.MenuUser;
import com.human.front.entity.WxTeacherMenu;
import com.human.front.entity.WxTeacherModule;
import com.human.front.service.WxTeacherMenuService;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.Users;
import com.human.manager.service.HrOrganizationService;
import com.human.manager.service.UserService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/wxTeacher")
public class WxTeacherMenuController {
    private final  Logger logger = LogManager.getLogger(WxTeacherMenuController.class);
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.wxTeacherMenuPath}")
    private  String wxTeacherMenuPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Autowired
    private WxTeacherMenuService menuService;
    
    @Autowired
    private HrOrganizationService organizationService;
    
    @Autowired
    private UserService UserService;
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/basic/wxTeacher/list");
        List<WxTeacherModule> modules = menuService.getAllModules();
        mav.addObject("modules", modules);
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,WxTeacherMenu menu){
        return  menuService.query(pageView, menu);
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/basic/wxTeacher/add");
        List<WxTeacherModule> modules = menuService.getAllModules();
        mav.addObject("modules", modules);
        return mav;
    }
    
    /**
     * 新增菜单
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(WxTeacherMenu menu,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = wxTeacherMenuPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        menu.setIcon(newFileName);
                    }else{
                        map.put("flag", false);
                        map.put("message", "图片上传失败");
                        logger.error("微信教师菜单米快->新建菜单:图片上传失败");
                        return map;
                    }
                }
            }
            menuService.addMenu(menu);
            map.put("flag", true);
            map.put("message", "新增菜单成功");
        }catch(Exception e){
            logger.error("新增菜单出错");
            map.put("flag", false);
            map.put("mesage", "新增菜单失败");
        }
        
        return map;
    }
    
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Integer id){
        ModelAndView mav=new ModelAndView("/basic/wxTeacher/edit");
        List<WxTeacherModule> modules = menuService.getAllModules();
        mav.addObject("modules", modules);
        WxTeacherMenu menu = menuService.QueryById(id);
        mav.addObject("menu",menu);
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    
    
    /**
     * 新增菜单
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> edit(WxTeacherMenu menu,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            OSSClient ossClient = ossUtil.getClient();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = wxTeacherMenuPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    
                    if(StringUtils.isNotEmpty(menu.getIcon()) &&ossUtil.isObjectExist(ossClient, bucketName, menu.getIcon())){
                        ossUtil.deleteObject(ossClient, bucketName, menu.getIcon());
                    }
                    if((boolean) uploadResult.get("flag")){
                        menu.setIcon(newFileName);
                    }else{
                        map.put("flag", false);
                        map.put("message", "图片上传失败");
                        logger.error("微信教师菜单米快->编辑菜单:图片上传失败");
                        return map;
                    }
                }
            }
            menuService.editMenu(menu);
            map.put("flag", true);
            map.put("message", "编辑菜单成功");
        }catch(Exception e){
            logger.error("编辑菜单出错");
            map.put("flag", false);
            map.put("mesage", "编辑菜单失败");
        }
        
        return map;
    }
    
    
    /**
     * 进入部门分配页面
     * @return
     */
    @RequestMapping(value="toGrantDept")
    public ModelAndView toGrantDept(Integer menuId){
        ModelAndView mav=new ModelAndView("/basic/wxTeacher/grant_dept");
        mav.addObject("menuId", menuId);
        return mav;
    }
    
    /**
     * 保存菜单部门
     * @return
     */
    @ResponseBody
    @RequestMapping("saveMenuDept")
    public Map<String,Object> saveMenuDept(Integer menuId,String deptids){
        Map<String,Object> map = new HashMap<String,Object>();
        List<String> list = new ArrayList<String>();
        if(deptids.length()>0){
             list = Common.removeSameItem(Arrays.asList(deptids.split(",")));
        }
        try {
            List<MenuDept> depts = new ArrayList<MenuDept>();
            for(String s:list){
                if(StringUtils.isNotEmpty(s)){
                    MenuDept d = new MenuDept();
                    d.setMenuId(menuId);
                    d.setDeptId(s);
                    depts.add(d);
                }
            }
            menuService.addMenuDepts(depts);
            map.put("flag", true);
            map.put("message", "保存成功");
        } catch (Exception e) {
            logger.error("保存失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "保存失败");
        }
        return map;
    }
    
    /**
     * 进入部门分配页面
     * @return
     */
    @RequestMapping(value="toGrantUser")
    public ModelAndView toGrantUser(Integer menuId){
        ModelAndView mav=new ModelAndView("/basic/wxTeacher/grant_user");
        List<MenuUser> mus= menuService.getUsersByMenuId(menuId);
        mav.addObject("mus", mus);
        return mav;
    }
    
    /**
     * 保存菜单部门
     * @return
     */
    @ResponseBody
    @RequestMapping("saveMenuUser")
    public Map<String,Object> saveMenuUser(MenuUser mu){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Users u = new Users();
            u.setLoginName(mu.getUserId());
            List<Users> list = UserService.queryUser(u);
            if(list==null||list.size()==0){
                map.put("flag", false);
                map.put("message", "不存在该用户");
                return map;
            }else if(list.size()>1){
                map.put("flag", false);
                map.put("message", "不存在该用户");
                return map;
            }else{
                Integer cnt = menuService.getMenuUserCnt(mu);
                if(cnt!=null && cnt.intValue()>0){
                    map.put("flag", false);
                    map.put("mesage", "该用户已添加");
                }
                menuService.addMenuUser(mu);
                mu.setUserName(list.get(0).getName());
                map.put("flag", true);
                map.put("message", "添加成功");
                map.put("mu",mu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "系统问题，请联系系统管理员");
        }
        return map;
    }
    
    /**
     *删除用户
     * @return
     */
    @ResponseBody
    @RequestMapping("delMenuUser")
    public Map<String,Object> delMenuUser(MenuUser mu){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            menuService.delMenuUser(mu);
            map.put("flag", true);
            map.put("mesage", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("mesage", "系统问题，请联系系统管理员");
        }
        return map;
    }
    
    @RequestMapping(value = "showTree")
    @ResponseBody
    public List<HrOrganization> showTree(Integer menuId) {
        
        HrOrganization o = new HrOrganization();
        o.setCompany(Common.getMyUser().getCompanyId());
        List<HrOrganization> orgList =organizationService.findOrgByCondition(o);
        List<MenuDept> userDeptList = menuService.getDeptsByMenuId(menuId);
        for (HrOrganization mDept : orgList) {
            for (MenuDept me : userDeptList) {
                if (mDept.getId().equals(me.getDeptId())) {
                    mDept.setIsChecked(true);
                    break;
                }
            }
        }
        return orgList;
    }
}
