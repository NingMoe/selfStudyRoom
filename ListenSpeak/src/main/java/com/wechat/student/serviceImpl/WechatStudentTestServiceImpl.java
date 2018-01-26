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
import com.ls.spt.studenttest.dao.LstStudentTestDao;
import com.ls.spt.studenttest.entity.LstStudentTest;
import com.wechat.student.service.WechatStudentTestService;
import com.wechat.utils.interceptor.BindingConstants;

@Service
public class WechatStudentTestServiceImpl implements WechatStudentTestService {
    
    @Resource
    private LstStudentTestDao lstStudentTestDao;
    
    /**
     * 获取学生所有考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllTest(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取学生已完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndTest(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndTest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        try {
            Map<String ,Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<LstStudentTest> list = lstStudentTestDao.selectStudentNotEndTest(mapparam);
            if(list != null && list.size() >0){
                map.put("flag", true);
                map.put("message", "获取未完成考试成功");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "没有未完成考试");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取未完成考试异常");
        }
        
        
        return map;
    }

}
