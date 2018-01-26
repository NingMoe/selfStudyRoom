package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherFeedbackDao;
import com.human.ielts.entity.IeltsTeacherFeedback;
import com.human.ielts.service.IeltsTeacherFeedbackService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherFeedbackServiceImpl implements IeltsTeacherFeedbackService{

    @Resource
    private IeltsTeacherFeedbackDao ieltsTeacherFeedbackDao;
    
    /**
     * 分页获取教学反馈
     * @param pageView
     * @param ieltsTeacherFeedback
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherFeedback ieltsTeacherFeedback) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherFeedback);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherFeedback> list = ieltsTeacherFeedbackDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教学反馈
     * @param ieltsTeacherFeedback
     * @return
     */
    public Map<String, Object> updateteacherfeedback(IeltsTeacherFeedback ieltsTeacherFeedback) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherFeedback == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherFeedback.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherFeedbackDao.updateByPrimaryKeySelective(ieltsTeacherFeedback);
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
     * 批量删除教学反馈
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacherfeedback(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherFeedbackDao.deleteByPrimaryKey(id);
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
