package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherArticleDao;
import com.human.ielts.entity.IeltsTeacherArticle;
import com.human.ielts.service.IeltsTeacherArticleService;
import com.human.utils.PageView;

@Service
public class IeltsTeacherArticleServiceImpl implements IeltsTeacherArticleService {
    
    @Resource
    private IeltsTeacherArticleDao ieltsTeacherArticleDao;

    /**
     * 分页获取教师教研文章
     * @param pageView
     * @param ieltsTeacherArticle
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherArticle ieltsTeacherArticle) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherArticle);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsTeacherArticle> list = ieltsTeacherArticleDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 更新教师教研文章
     * @param ieltsTeacherArticle
     * @return
     */
    public Map<String, Object> updateteacherarticle(IeltsTeacherArticle ieltsTeacherArticle) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherArticle == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherArticle.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherArticleDao.updateByPrimaryKeySelective(ieltsTeacherArticle);
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
     * 批量删除教师教研文章
     * @param ids
     * @return
     */
    public Map<String, Object> deletesteacherarticle(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择要删除的项");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                ieltsTeacherArticleDao.deleteByPrimaryKey(id);
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
