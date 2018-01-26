package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherMatchclassDao;
import com.human.ielts.entity.IeltsTeacherMatchclass;
import com.human.ielts.service.IeltsTeacherMatchclassService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherMatchclassServiceImpl implements IeltsTeacherMatchclassService {

    @Resource
    private IeltsTeacherMatchclassDao ieltsTeacherMatchclassDao;
    
    /**
     * 分页获取赛课信息
     * @param pageView
     * @param ieltsTeacherMatchclass
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherMatchclass ieltsTeacherMatchclass) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherMatchclass);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherMatchclass> list = ieltsTeacherMatchclassDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新赛课信息
     * @param ieltsTeacherMatchclass
     * @return
     */
    public Map<String, Object> updateteachermatchclass(IeltsTeacherMatchclass ieltsTeacherMatchclass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherMatchclass == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherMatchclass.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherMatchclassDao.updateByPrimaryKeySelective(ieltsTeacherMatchclass);
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
     * 批量删除赛课信息
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteachermatchclass(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherMatchclassDao.deleteByPrimaryKey(id);
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
