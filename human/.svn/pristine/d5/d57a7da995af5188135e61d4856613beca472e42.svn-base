package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsClassEnrollDao;
import com.human.ielts.entity.IeltsClassEnroll;
import com.human.ielts.service.IeltsClassEnrollService;
import com.human.security.MyUser;
import com.human.utils.Common;

@Service
public class IeltsClassEnrollServiceImpl implements IeltsClassEnrollService {
    
    @Resource
    private IeltsClassEnrollDao ieltsClassEnrollDao;

    /**
     * 班级高分学员分布
     * @param ieltsClassEnroll
     * @return
     */
    public Map<String, Object> selectclasstypecount(IeltsClassEnroll ieltsClassEnroll) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsClassEnroll == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsClassEnroll.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsClassEnroll.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsClassEnroll.getLeft_test_time());
            mapparam.put("right_test_time", ieltsClassEnroll.getRight_test_time());
            //获取各个班级学员分布
            List<IeltsClassEnroll> classtypelist = ieltsClassEnrollDao.selectclasstypecount(mapparam);
            map.put("flag", true);
            map.put("message", "高分学员统计成功");
            map.put("classtypelist", classtypelist);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取高分学员统计成功："+e);
        }
        
        return map;
    }

    /**
     * 教师个人班级高分学员分布
     * @param ieltsClassEnroll
     * @return
     */
    public Map<String, Object> selectteacherclasstypecount(IeltsClassEnroll ieltsClassEnroll) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        if(ieltsClassEnroll == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsClassEnroll.getLeft_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsClassEnroll.getRight_test_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsClassEnroll.getLeft_test_time());
            mapparam.put("right_test_time", ieltsClassEnroll.getRight_test_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取各个班级学员分布
            List<IeltsClassEnroll> classtypelist = ieltsClassEnrollDao.selectTeacherclasstypecount(mapparam);
            map.put("flag", true);
            map.put("message", "高分学员统计成功");
            map.put("classtypelist", classtypelist);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取高分学员统计成功："+e);
        }
        
        return map;
    }
}
