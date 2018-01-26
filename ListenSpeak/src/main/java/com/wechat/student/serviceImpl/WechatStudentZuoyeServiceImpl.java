package com.wechat.student.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentzuoye.dao.LstStudentZuoyeDao;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.wechat.student.service.WechatStudentZuoyeService;
import com.wechat.utils.interceptor.BindingConstants;

@Service
public class WechatStudentZuoyeServiceImpl implements WechatStudentZuoyeService {
    
    @Resource
    private LstStudentZuoyeDao lstStudentZuoyeDao;
    
    /**
     * 获取学生所有作业
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllZuoye(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取学生已完成作业
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndZuoye(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取学生未完成作业
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndZuoye(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String ,Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<LstStudentZuoye> list = lstStudentZuoyeDao.selectStudentNotEndZuoye(mapparam);
            if(list != null && list.size() >0){
                map.put("flag", true);
                map.put("message", "获取未完成作业成功");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "没有未完成考试");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取未完成作业异常");
        }
        
        
        return map;
    }

}
