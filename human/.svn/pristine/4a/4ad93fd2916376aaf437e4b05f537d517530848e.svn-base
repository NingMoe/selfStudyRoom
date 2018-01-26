package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherComplaintDao;
import com.human.ielts.entity.IeltsTeacherComplaint;
import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.ielts.service.IeltsTeacherComplaintService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherComplaintServiceImpl implements IeltsTeacherComplaintService {

    @Resource
    private IeltsTeacherComplaintDao ieltsTeacherComplaintDao;
    
    /**
     * 分页获取教学投诉
     * @param pageView
     * @param ieltsTeacherComplaint
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherComplaint ieltsTeacherComplaint) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherComplaint);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherOperate> list = ieltsTeacherComplaintDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教学投诉
     * @param ieltsTeacherComplaint
     * @return
     */
    public Map<String, Object> updateteachercomplaint(IeltsTeacherComplaint ieltsTeacherComplaint) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherComplaint == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherComplaint.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherComplaintDao.updateByPrimaryKeySelective(ieltsTeacherComplaint);
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
     * 批量删除教学投诉
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachercomplaint(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherComplaintDao.deleteByPrimaryKey(id);
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
