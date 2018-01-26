package com.human.continuation.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.continuation.dao.TeacherContinuationTeachernameDao;
import com.human.continuation.entity.TeacherContinuationTeachername;
import com.human.continuation.service.TeacherContinuationTeachernameService;
import com.human.utils.PageView;

@Service
public class TeacherContinuationTeachernameServiceImpl implements TeacherContinuationTeachernameService {
    
    @Resource
    private TeacherContinuationTeachernameDao teacherContinuationTeachernameDao;

    /**
     * 分页获取管理的班级
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    public PageView query(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        
        try {
            //MyUser myUser = Common.getMyUser();
            map.put("paging", pageView); 
            map.put("t", teacherContinuationTeachername);
            
            List<TeacherContinuationTeachername> list = teacherContinuationTeachernameDao.query(map);
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }
    
    /**
     * 分页获取班级教师
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    public PageView classesquery(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        
        try {
            map.put("paging", pageView); 
            map.put("t", teacherContinuationTeachername);
            
            List<TeacherContinuationTeachername> list = teacherContinuationTeachernameDao.classesquery(map);
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }

    /**
     * 新增班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getEmail_addr())){
            map.put("flag", false);
            map.put("message", "请填写邮箱前缀");
            return map;
        }
        
        try {
            teacherContinuationTeachernameDao.insertSelective(teacherContinuationTeachername);
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增班级教师异常");
        }
        
        return map;
    }

    /**
     * 修改班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> update(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请选择要改的班级");
            return map;
        }
        
        if(teacherContinuationTeachername.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要改的班级");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getEmail_addr())){
            map.put("flag", false);
            map.put("message", "请填写邮箱前缀");
            return map;
        }
        
        try {
            TeacherContinuationTeachername teacherContinuationTeachername1 = teacherContinuationTeachernameDao.getteachername(teacherContinuationTeachername);
            if(teacherContinuationTeachername1 == null || StringUtils.isEmpty(teacherContinuationTeachername1.getTeacher_name())){
                map.put("flag", false);
                map.put("message", "未找到该老师，请填写正确的邮箱前缀");
                return map;
            }
            
            teacherContinuationTeachernameDao.updateByPrimaryKeySelective(teacherContinuationTeachername);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改班级教师异常");
        }
        return map;
    }

    /**
     * 查询班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> select(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请选择班级");
            return map;
        }
        
        if(teacherContinuationTeachername.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择班级");
            return map;
        }
        
        try {
            TeacherContinuationTeachername teacherContinuationTeachername1 = teacherContinuationTeachernameDao.selectByPrimaryKey(teacherContinuationTeachername.getId());
            if(teacherContinuationTeachername1 == null){
                map.put("flag", false);
                map.put("message", "查询为空");
            }else{
                map.put("flag", true);
                map.put("message", "修改成功");
                map.put("teacherContinuationTeachername", teacherContinuationTeachername1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改班级教师异常");
        }
        return map;
    }

    /**
     * 删除班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请选择班级");
            return map;
        }
        
        if(teacherContinuationTeachername.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择班级");
            return map;
        }
        
        try {
            teacherContinuationTeachernameDao.deleteByPrimaryKey(teacherContinuationTeachername.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除班级教师异常");
        }
        return map;
    }

    /**
     * 获取教师名称
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> getclassname(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        
        try {
            TeacherContinuationTeachername teacherContinuationTeachername1 = teacherContinuationTeachernameDao.getclassname(teacherContinuationTeachername);
            if(teacherContinuationTeachername1 == null || StringUtils.isEmpty(teacherContinuationTeachername1.getClass_name())){
                map.put("flag", false);
                map.put("message", "未找到班级信息");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("teacherContinuationTeachername", teacherContinuationTeachername1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取班级名称异常");
        }
        
        
        return map;
    }

    /**
     * 获取教师名称
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> getteachername(TeacherContinuationTeachername teacherContinuationTeachername) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationTeachername == null){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationTeachername.getEmail_addr())){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀");
            return map;
        }
        
        try {
            TeacherContinuationTeachername teacherContinuationTeachername1 = teacherContinuationTeachernameDao.getteachername(teacherContinuationTeachername);
            if(teacherContinuationTeachername1 == null || StringUtils.isEmpty(teacherContinuationTeachername1.getTeacher_name())){
                map.put("flag", false);
                map.put("message", "未找到教师信息");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("teacherContinuationTeachername", teacherContinuationTeachername1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取班级名称异常");
        }
        
        return map;
    }
}
