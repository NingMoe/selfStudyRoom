package com.ls.spt.studentpc.student.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ls.spt.StudentClass.dao.StudentClassDao;
import com.ls.spt.StudentClass.entity.StudentClass;
import com.ls.spt.lstClass.dao.LstClassDao;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.service.StudentClassService;
import com.ls.spt.utils.StudentPcConstants;


@Service(value="pcstudentClassServiceImpl")
public class StudentClassServiceImpl implements StudentClassService {
    
    @Resource
    private LstClassDao lstClassDao;
    
    @Resource
    private StudentClassDao studentClassDao;
    
    /**
     * 申请入班
     * @param invitation_code
     * @param phone
     * @return
     */
    public Map<String, Object> applyForJoinClass(String invitation_code, String phone, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "pleas login again");
            return map;
        }
        if(StringUtils.isEmpty(invitation_code)){
            map.put("flag", false);
            map.put("message", "invitation_code is empty");
            return map;
        }
        
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "phone is empty");
            return map;
        }
        
        try {
            
            //验证填写内容
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("invitation_code", invitation_code);
            mapparam.put("phone", phone);
            LstClass lstClass = lstClassDao.selectByInvitationcodeAndPhone(mapparam);
            if(lstClass == null){
                map.put("flag", false);
                map.put("message", "申请码或者手机号错误");
                return map;
            }
            
            //验证重复
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("student_id", studentUser.getId());
            mapparams.put("class_code", lstClass.getClassCode());
            StudentClass studentClass = studentClassDao.selectByClasscodeAndStudentid(mapparams);
            if(studentClass != null){
                if("1".equals(studentClass.getStatus())){
                    map.put("flag", false);
                    map.put("message", "已经申请过该班级，请等待老师审核");
                }else if("2".equals(studentClass.getStatus())){
                    map.put("flag", false);
                    map.put("message", "已经加入该班级");
                }else{
                    map.put("flag", false);
                    map.put("message", "申请错误，请联系管理员");
                }
                return map;
            }
            
            //申请班级
            StudentClass studentClass1 = new StudentClass();
            studentClass1.setStudentId(studentUser.getId());
            studentClass1.setClassCode(lstClass.getClassCode());
            studentClass1.setStatus("1");
            studentClassDao.insertSelective(studentClass1);
            map.put("flag", true);
            map.put("message", "ok");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "exception:"+e);
        }
        
        return map;
    }
    
    /**
     * 获取学生班级数量
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getCount(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            mapparam.put("status", 2);
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
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
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
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
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
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
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
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
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
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        
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
