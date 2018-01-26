package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherShareDao;
import com.human.ielts.entity.IeltsTeacherShare;
import com.human.ielts.service.IeltsTeacherShareService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherShareServiceImpl implements IeltsTeacherShareService {
    
    @Resource
    private IeltsTeacherShareDao ieltsTeacherShareDao;

    /**
     * 分页获取教学分享
     * @param pageView
     * @param ieltsTeacherShare
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherShare ieltsTeacherShare) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherShare);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherShare> list = ieltsTeacherShareDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教学分享
     * @param ieltsTeacherShare
     * @return
     */
    public Map<String, Object> updateteachershare(IeltsTeacherShare ieltsTeacherShare) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherShare == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherShare.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherShareDao.updateByPrimaryKeySelective(ieltsTeacherShare);
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
     * 批量删除教学分享
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachershare(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherShareDao.deleteByPrimaryKey(id);
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
