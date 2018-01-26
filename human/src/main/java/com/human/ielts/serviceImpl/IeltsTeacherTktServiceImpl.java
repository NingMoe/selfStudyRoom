package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherInfoDao;
import com.human.ielts.dao.IeltsTeacherTktDao;
import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.ielts.service.IeltsTeacherTktService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherTktServiceImpl implements IeltsTeacherTktService {
    
    @Resource
    private IeltsTeacherTktDao ieltsTeacherTktDao;
    
    @Resource
    private IeltsTeacherInfoDao ieltsTeacherInfoDao;
    
    /**
     * 分页获取教师tkt详情
     * @param ieltsTeacherTkt
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherTkt ieltsTeacherTkt) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherTkt);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherTkt> list = ieltsTeacherTktDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 获取教师所有tkt成绩
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> selectteachertkt(IeltsTeacherTkt ieltsTeacherTkt) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherTkt == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherTkt.getTeacher_id() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            List<IeltsTeacherTkt> ieltsTeacherTktList = ieltsTeacherTktDao.selectByTeacherId(ieltsTeacherTkt.getTeacher_id());
            if(ieltsTeacherTktList != null && ieltsTeacherTktList.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("ieltsTeacherTktList", ieltsTeacherTktList);
            }else{
                map.put("flag", false);
                map.put("message", "获取失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
        }
        
        return map;
    }

    /**
     * 更新教师所有tkt成绩
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> updateteachertkt(IeltsTeacherTkt ieltsTeacherTkt) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherTkt == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherTkt.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherTktDao.updateByPrimaryKeySelective(ieltsTeacherTkt);
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
     * 批量删除tkt成绩
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachertkt(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherTktDao.deleteByPrimaryKey(id);
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
     * 查询TKT高分教师人数占比
     * @param ieltsTeacherTkt
     * @return
     */
    public Map<String, Object> selecttktteacher(IeltsTeacherTkt ieltsTeacherTkt) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherTkt == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        if(ieltsTeacherTkt.getLeft_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        if(ieltsTeacherTkt.getRight_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择时间");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_integral_time", ieltsTeacherTkt.getLeft_integral_time());
            mapparam.put("right_integral_time", ieltsTeacherTkt.getRight_integral_time());
            mapparam.put("source", 10);
            Integer x = ieltsTeacherTktDao.selectieltsteachersource(mapparam);
            mapparam.put("source", 11);
            Integer y = ieltsTeacherTktDao.selectieltsteachersource(mapparam);
            mapparam.put("source", 12);
            Integer z = ieltsTeacherTktDao.selectieltsteachersource(mapparam);
            Integer t_count = ieltsTeacherInfoDao.selectTeacherCount();
            
            map.put("flag", true);
            map.put("message", "获取TKT高分教师占比成功");
            map.put("x", x);
            map.put("y", y);
            map.put("z", z);
            map.put("t_count", t_count);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取TKT高分教师占比失败："+e);
        }
        
        return map;
    }
}
