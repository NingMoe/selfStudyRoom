package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherOperateDao;
import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.ielts.service.IeltsTeacherOperateService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherOperateServiceImpl implements IeltsTeacherOperateService{
    
    @Resource
    private IeltsTeacherOperateDao ieltsTeacherOperateDao;

    /**
     * 分页获取教学运营支持
     * @param pageView
     * @param ieltsTeacherOperate
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherOperate ieltsTeacherOperate) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherOperate);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherOperate> list = ieltsTeacherOperateDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教学运营支持
     * @param ieltsTeacherOperate
     * @return
     */
    public Map<String, Object> updateteacheroperate(IeltsTeacherOperate ieltsTeacherOperate) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherOperate == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherOperate.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherOperateDao.updateByPrimaryKeySelective(ieltsTeacherOperate);
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
     * 批量删除教学运营支持
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacheroperate(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherOperateDao.deleteByPrimaryKey(id);
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
