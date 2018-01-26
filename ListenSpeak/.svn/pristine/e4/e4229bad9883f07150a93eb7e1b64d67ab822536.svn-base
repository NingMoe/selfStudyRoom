package com.ls.spt.studentpc.student.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ls.spt.student.dao.StudentUserDao;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.service.StudentInfoService;
import com.ls.spt.utils.Md5Tool;
import com.ls.spt.utils.StudentPcConstants;
import com.wechat.studentunionid.dao.StudentUnionidDao;
import com.wechat.studentunionid.entity.StudentUnionid;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    
    @Resource
    private StudentUserDao studentUserDao;
    
    @Resource
    private StudentUnionidDao studentUnionidDao;

    /**
     * 修改密码
     * @param old_password
     * @param new_password
     * @return
     */
    public Map<String, Object> changePassword(String old_password, String new_password, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(StringUtils.isEmpty(old_password)){
            map.put("flag", false);
            map.put("message", "原密码不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(new_password)){
            map.put("flag", false);
            map.put("message", "新密码不能为空");
            return map;
        }
        
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "超时,请重新登录");
            return map;
        }
        
        try {
            StudentUser studentUser1 = studentUserDao.selectByPrimaryKey(studentUser.getId());
            if(studentUser1 == null|| !Md5Tool.getMd5(old_password).equals(studentUser1.getPassword())){
                map.put("flag", false);
                map.put("message", "密码错误");
                return map;
            }
            studentUser1.setPassword(Md5Tool.getMd5(new_password));
            studentUserDao.updateByPrimaryKeySelective(studentUser1);
            map.put("flag", true);
            map.put("message", "修改密码成功");
            httpSession.removeAttribute(StudentPcConstants.STUDENT_USER);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改密码异常："+e);
        }
        return map;
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> loginout(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        
        try {
            StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
            if(studentUser != null){
                httpSession.removeAttribute(StudentPcConstants.STUDENT_USER);
            }
            map.put("flag",true);
            map.put("message", "退出成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            map.put("message", "异常："+e);
        }
        return map;
    }

    /**
     * 解绑
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> bindingout(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        String unionid = (String) httpSession.getAttribute(StudentPcConstants.STUDENT_UNIONID);
        try {
            if(StringUtils.isNotEmpty(unionid)){
                StudentUnionid studentUnionid = new StudentUnionid();
                studentUnionid.setUnionid(unionid);
                studentUnionidDao.delectByUnionid(studentUnionid);
                httpSession.removeAttribute(StudentPcConstants.STUDENT_UNIONID);
            }
            
            if(studentUser != null){
                httpSession.removeAttribute(StudentPcConstants.STUDENT_USER);
            }
            
            map.put("flag", true);
            map.put("message", "解绑成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "解绑异常："+e);
        }
        
        return map;
    }

}
