package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherAttendanceDao;
import com.human.ielts.entity.IeltsTeacherAttendance;
import com.human.ielts.service.IeltsTeacherAttendanceService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherAttendanceServiceImpl implements IeltsTeacherAttendanceService {
    
    @Resource
    private IeltsTeacherAttendanceDao ieltsTeacherAttendanceDao;
    
    /**
     * 分页获取教师考勤详情
     * @param pageView
     * @param ieltsTeacherAttendance
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherAttendance ieltsTeacherAttendance) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherAttendance);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherAttendance> list = ieltsTeacherAttendanceDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }
    
    /**
     * 更新教师attendance详情
     * @param ieltsTeacherAttendance
     * @return
     */
    public Map<String, Object> updateteacherattendance(IeltsTeacherAttendance ieltsTeacherAttendance) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherAttendance == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherAttendance.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherAttendanceDao.updateByPrimaryKeySelective(ieltsTeacherAttendance);
            map.put("flag", true);
            map.put("message", "更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
        }
        
        return map;
    }

    /**
     * 批量删除教师考勤详情
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacherattendance(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherAttendanceDao.deleteByPrimaryKey(id);
            }
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        
        return map;
    }

}
