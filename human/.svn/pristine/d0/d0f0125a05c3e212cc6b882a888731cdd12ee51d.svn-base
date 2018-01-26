package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherInfoDao;
import com.human.ielts.dao.IeltsTeacherSourceDao;
import com.human.ielts.entity.IeltsTeacherSource;
import com.human.ielts.service.IeltsTeacherSourceService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherSourceServiceImpl implements IeltsTeacherSourceService {
    
    @Resource
    private IeltsTeacherSourceDao ieltsTeacherSourceDao;
    
    @Resource
    private IeltsTeacherInfoDao ieltsTeacherInfoDao;

    /**
     * 分页获取雅思成绩
     * @param pageView
     * @param ieltsTeacherSource
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherSource ieltsTeacherSource) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherSource);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherSource> list = ieltsTeacherSourceDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教师雅思成绩
     * @param ieltsTeacherSource
     * @return
     */
    public Map<String, Object> updateteachersource(IeltsTeacherSource ieltsTeacherSource) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherSource == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherSource.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherSourceDao.updateByPrimaryKeySelective(ieltsTeacherSource);
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
     * 批量删除教师雅思成绩
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachersource(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherSourceDao.deleteByPrimaryKey(id);
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

    /**
     * 查询雅思高分教师人数占比
     * @param ieltsTeacherSource
     * @return
     */
    public Map<String, Object> selectieltsteacher(IeltsTeacherSource ieltsTeacherSource) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherSource == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        if(ieltsTeacherSource.getLeft_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        if(ieltsTeacherSource.getRight_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_integral_time", ieltsTeacherSource.getLeft_integral_time());
            mapparam.put("right_integral_time", ieltsTeacherSource.getRight_integral_time());
            mapparam.put("source", 7);
            Integer x = ieltsTeacherSourceDao.selectieltsteachersource(mapparam);
            mapparam.put("source", 7.5);
            Integer y = ieltsTeacherSourceDao.selectieltsteachersource(mapparam);
            mapparam.put("source", 8);
            Integer z = ieltsTeacherSourceDao.selectieltsteachersource(mapparam);
            Integer t_count = ieltsTeacherInfoDao.selectTeacherCount();
            
            map.put("flag", true);
            map.put("message", "获取雅思高分教师占比成功");
            map.put("x", x);
            map.put("y", y);
            map.put("z", z);
            map.put("t_count", t_count);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取雅思高分教师占比失败："+e);
        }
        
        return map;
    }

}
