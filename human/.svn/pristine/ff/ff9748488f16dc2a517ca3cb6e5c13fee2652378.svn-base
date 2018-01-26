package com.human.jw.controller;
import java.util.ArrayList;
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

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jw.entity.FullCalendarCourse;
import com.human.jw.entity.JwCourse;
import com.human.jw.entity.JwJyzUser;
import com.human.jw.entity.JwTeacherInfo;
import com.human.jw.entity.OrgEmployeeTree;
import com.human.jw.service.JwService;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/jw/")
public class JwManageController {
    
    private final  Logger logger = LogManager.getLogger(JwManageController.class);
    
    @Autowired
    private JwService jwService;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private DictionaryService dictionaryService;
    
    @RequestMapping(value="index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/jw/index");
        return mav;
    }
    
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/jw/list");
        List<DicData> dksxs = dictionaryService.getDataByKey("JW_DKSX");
        List<DicData> jyzs = dictionaryService.getDataByKey("JW_JYZ");
        List<DicData> jwdepts = dictionaryService.getDataByKey("JW_DEPT");
        List<DicData> jwgrades = dictionaryService.getDataByKey("JW_GRADE");
        List<DicData> jwsites = dictionaryService.getDataByKey("JW_SITE");
        List<DicData> jwSubjects = dictionaryService.getDataByKey("JW_SUBJECT");
        boolean editFlag = jwService.isHasEditAu(Common.getAuthenticatedUsername());
        if(Common.getMyUser().getAuthorities().toString().indexOf("ROLE_jw_refresh")>-1){
            mav.addObject("refreshFlag", "1"); 
        }
        mav.addObject("editFlag", editFlag);
        mav.addObject("dksxs", dksxs);
        mav.addObject("jyzs", jyzs);
        mav.addObject("jwdepts", jwdepts);
        mav.addObject("jwgrades", jwgrades);
        mav.addObject("jwsites", jwsites);
        mav.addObject("jwSubjects", jwSubjects);
        mav.addObject("currUser", Common.getMyUser().getEmailAddr());
        return mav;
    }
    
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Integer id){
        ModelAndView mav=new ModelAndView("/jw/edit");
        JwTeacherInfo teacher = jwService.getTeacherInfoById(id);
        List<DicData> dksxs = dictionaryService.getDataByKey("JW_DKSX");
        List<DicData> jyzs = dictionaryService.getDataByKey("JW_JYZ");
        List<DicData> jwdepts = dictionaryService.getDataByKey("JW_DEPT");
        List<DicData> jwgrades = dictionaryService.getDataByKey("JW_GRADE");
        List<DicData> jwsites = dictionaryService.getDataByKey("JW_SITE");
        List<DicData> jwSubjects = dictionaryService.getDataByKey("JW_SUBJECT");
        mav.addObject("dksxs", dksxs);
        mav.addObject("jyzs", jyzs);
        mav.addObject("jwdepts", jwdepts);
        mav.addObject("jwgrades", jwgrades);
        mav.addObject("jwsites", jwsites);
        mav.addObject("jwSubjects", jwSubjects);
        mav.addObject("teacher", teacher);
        mav.addObject("currUser", Common.getMyUser().getEmailAddr());
        return mav;
    }
    
    
    @RequestMapping(value="toFpjyz")
    public ModelAndView toFpjyz(Integer id){
        ModelAndView mav=new ModelAndView("/jw/fpjyz");
        JwTeacherInfo teacher = jwService.getTeacherInfoById(id);
        List<DicData> jyzs = dictionaryService.getDataByKey("JW_JYZ");
        mav.addObject("jyzs", jyzs);
        mav.addObject("teacher", teacher);
        return mav;
    }
    
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> edit(JwTeacherInfo jwTeacherInfo){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.edit(jwTeacherInfo);
            if(jwTeacherInfo.getOneSx()!=null){
                logger.info(jwTeacherInfo.getTeacherName()+"修改1对1上限为"+jwTeacherInfo.getOneSx()+",修改人"+Common.getMyUser().getEmailAddr());
            }
            result.put("flag", true);
            result.put("message", "修改数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("修改数据失败");
            result.put("flag", true);
            result.put("message", "修改数据失败");
        }
        return result;
    }
    
    @RequestMapping(value="fpjyz")
    @ResponseBody
    public Map<String,Object> fpjyz(JwTeacherInfo jwTeacherInfo){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.editSelective(jwTeacherInfo);
            result.put("flag", true);
            result.put("message", "教研组分配成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("教研组分配失败");
            result.put("flag", true);
            result.put("message", "教研组分配失败");
        }
        return result;
    }
    
    @RequestMapping(value="refreshData")
    @ResponseBody
    public Map<String,Object> refreshData(String ids){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.refreshTechData(ids);
            result.put("flag", true);
            result.put("message", "刷新数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "刷新数据失败");
        }
        return result;
    }
    
    @RequestMapping(value="refreshAllData")
    @ResponseBody
    public Map<String,Object> refreshAllData(){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.refreshAllTechData();
            result.put("flag", true);
            result.put("message", "刷新数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "刷新数据失败");
        }
        return result;
    }
    
    @RequestMapping(value="del")
    @ResponseBody
    public Map<String,Object> del(String teacherCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.del(teacherCode);
            result.put("flag", true);
            result.put("message", "删除数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "删除数据失败");
        }
        return result;
    }
    
    
    @RequestMapping(value="lock")
    @ResponseBody
    public Map<String,Object> lock(JwTeacherInfo jwTeacherInfo){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwTeacherInfo.setLockUser(Common.getMyUser().getEmailAddr());
            jwService.editSelective(jwTeacherInfo);
            result.put("flag", true);
            result.put("message", "数据锁定成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("数据锁定失败");
            result.put("flag", true);
            result.put("message", "数据锁定失败");
        }
        return result;
    }
    
    @RequestMapping(value="delJyzYser")
    @ResponseBody
    public Map<String,Object> delJyzYser(JwJyzUser jwJyzUser){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            jwService.delJyzUser(jwJyzUser);
            result.put("flag", true);
            result.put("message", "删除数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据失败");
            result.put("flag", true);
            result.put("message", "删除数据失败");
        }
        return result;
    }
    
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,JwTeacherInfo jwTeacherInfo){
        try {
            return  jwService.queryPageView(pageView, jwTeacherInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value="getTotal")
    @ResponseBody
    public JwTeacherInfo getTotalCnSj(JwTeacherInfo jwTeacherInfo){
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("t", jwTeacherInfo);
        JwTeacherInfo jw = jwService.getTotalCnSj(jwTeacherInfo);
        return jw;
    }
    
    
    @RequestMapping(value="toJyzList")
    public ModelAndView toJyzList(){
        ModelAndView mav=new ModelAndView("/jw/jyzList");
        List<DicData> jyzs = dictionaryService.getDataByKey("JW_JYZ");
        mav.addObject("jyzs", jyzs);
        return mav;
    }
    
    @RequestMapping(value="toJyzAdd")
    public ModelAndView toJyzAdd(){
        ModelAndView mav=new ModelAndView("/jw/jyzAdd");
        List<DicData> jyzs = dictionaryService.getDataByKey("JW_JYZ");
        mav.addObject("jyzs", jyzs);
        return mav;
    }
    
    @RequestMapping(value="addJyzUser")
    @ResponseBody
    public Map<String,Object> addJyzUser(JwJyzUser jwJyzUser){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            /**
             * jyz是以逗号隔开的教研组
             */
            jwService.addJyzUser(jwJyzUser);
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    @RequestMapping(value="queryJyzUser")
    @ResponseBody
    public PageView queryJyzUser(PageView pageView,JwJyzUser jwJyzUser){
        return  jwService.queryJyzPageView(pageView, jwJyzUser);
    }
    
    
    @RequestMapping(value="queryTreeById")
    @ResponseBody
    public List<OrgEmployeeTree> queryTreeById(String id){
        List<OrgEmployeeTree> list = new ArrayList<OrgEmployeeTree>();
        MyUser user = Common.getMyUser();
        if(StringUtils.isEmpty(id)){
            OrgEmployeeTree tree = new OrgEmployeeTree();
            tree.setId("0");
            tree.setName(user.getComName());
            tree.setIsParent(true);
            tree.setParentId("");
            list.add(tree);
        }else if("0".equals(id)){
            id= user.getCompanyId();
            list = jwService.queryTreeById(id);
        }else{
            list = jwService.queryTreeById(id);
        }
        return list;
    }
    
    @RequestMapping(value="initTeachData")
    @ResponseBody
    public Map<String,Object> initTeachData(String ids){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            return jwService.initTeachData(ids);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "初始化数据失败");
        }
        return result;
    }
    
    @RequestMapping(value="viewKb")
    public ModelAndView viewKb(String teacherCode){
        return new ModelAndView("/jw/kb");
    }
    
    
    @RequestMapping(value="viewKsTj")
    public ModelAndView viewKsTj(String teacherCode,String cnHours){
        ModelAndView mav = new ModelAndView("/jw/ksTj");
        try {
            JwTeacherInfo teacher = jwService.getTeacherKsTj(teacherCode);
            mav.addObject("teacher", teacher);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
    
    @RequestMapping(value="getEvents")
    @ResponseBody
    public Map<String,Object> getEvents(String teacherCode,String start,String end){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            List<JwCourse> courses = jwService.getTeacherJwCourses(teacherCode, start, end);
            List<FullCalendarCourse> fcCourses = convertToFullCalendarCourse(courses);
            result.put("flag", true);
            result.put("data", fcCourses);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("初始化数据失败");
            result.put("flag", true);
            result.put("message", "初始化数据失败");
        }
        return result;
    }
    
    private List<FullCalendarCourse> convertToFullCalendarCourse(List<JwCourse> courses){
        List<FullCalendarCourse> result = new ArrayList<FullCalendarCourse>();
        if(courses!=null && courses.size()>0){
            for(JwCourse c:courses){
                FullCalendarCourse f = new FullCalendarCourse(c);
                result.add(f);
            }
        }
        return result;
    }
    
    /**
     * 获取员工信息
     * @return
     */
    @RequestMapping(value="checkUser")
    @ResponseBody
    public Map<String,Object> checkUser(String email){
        Map<String,Object> result = new HashMap<String,Object>();
        List<JwJyzUser> users = jwService.getJwJyzUserByEmail(email);
        if(users!=null && users.size()>0){
            result.put("flag", false);
            result.put("message", "该用户已参加");
            return result;
        }else{
            Users u = new Users();
            u.setLoginName(email);
            List<Users> list = userService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "输入有误");
                return result;
            }else{
                result.put("flag", true);
                result.put("user",list.get(0));
                return result;
            }
        }
    }
}
