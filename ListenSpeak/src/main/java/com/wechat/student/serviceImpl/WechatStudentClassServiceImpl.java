package com.wechat.student.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ls.spt.lstClass.dao.LstClassDao;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.student.entity.StudentUser;
import com.wechat.student.service.WechatStudentClassService;
import com.wechat.utils.interceptor.BindingConstants;

@Service
public class WechatStudentClassServiceImpl implements WechatStudentClassService {
    
    @Resource
    private LstClassDao lstClassDao;
    
    
    /**
     * 获取学生班级数量
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getCount(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            Integer i = lstClassDao.selectClassCountByStudentUser(mapparam);
            if(i != null){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("listCount", i);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取所有班级异常：" + e);
        }
        return map;
    }

    /**
     * 获取学生所有班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<LstClass> lstClassList = lstClassDao.selectByStudentUser(mapparam);
            if(lstClassList != null && lstClassList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", lstClassList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取所有班级异常：" + e);
        }
        return map;
    }

    /**
     * 获取学生待审核班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotInClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("status", "1");
            List<LstClass> lstClassList = lstClassDao.selectByStudentUser(mapparam);
            if(lstClassList != null && lstClassList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", lstClassList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取待审核班级异常：" + e);
        }
        return map;
    }

    /**
     * 获取学生已经加入的所有班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllInClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("status", "2");
            List<LstClass> lstClassList = lstClassDao.selectByStudentUser(mapparam);
            if(lstClassList != null && lstClassList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", lstClassList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取已加入班级异常：" + e);
        }
        return map;
    }

    /**
     * 获取学生已经加入还未结课的班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndInClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("status", "2");
            mapparam.put("valid_time_left", "1");
            List<LstClass> lstClassList = lstClassDao.selectByStudentUser(mapparam);
            if(lstClassList != null && lstClassList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", lstClassList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取未结课班级异常：" + e);
        }
        return map;
    }

    /**
     * 获取学生已经加入已经结课的班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndInClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("status", "2");
            mapparam.put("valid_time_right", "1");
            List<LstClass> lstClassList = lstClassDao.selectByStudentUser(mapparam);
            if(lstClassList != null && lstClassList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", lstClassList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取已结课班级异常：" + e);
        }
        return map;
    }
}
