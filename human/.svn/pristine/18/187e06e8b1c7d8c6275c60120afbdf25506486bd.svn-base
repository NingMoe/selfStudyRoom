package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsClassEnrollDao;
import com.human.ielts.dao.IeltsClassTypeDao;
import com.human.ielts.entity.IeltsClassType;
import com.human.ielts.service.IeltsClassTypeService;
import com.human.utils.PageView;

@Service
public class IeltsClassTypeServiceImpl implements IeltsClassTypeService {
    
    @Resource
    private IeltsClassTypeDao ieltsClassTypeDao;
    
    @Resource
    private IeltsClassEnrollDao ieltsClassEnrollDao;

    /**
     * 初始化班级类型
     * @param ieltsStudentInfo
     * @param classids
     * @param bookids
     * @return
     */
    public Map<String, Object> getclasstype() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<IeltsClassType> list = ieltsClassTypeDao.selectAllClassType();
            if(list != null && list.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "请管理人员配置班级类型");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "失败:"+e);
        }
        return map;
    }

    /**
     * 分页获取班级类型
     * @param pageView
     * @param ieltsClassType
     * @return
     */
    public PageView queryclasstype(PageView pageView, IeltsClassType ieltsClassType) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsClassType);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsClassType> list = ieltsClassTypeDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 新增班级类型
     * @param ieltsClassType
     * @return
     */
    public Map<String, Object> insertclasstype(IeltsClassType ieltsClassType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsClassType == null){
            map.put("flag", false);
            map.put("message", "请填写班级类型。");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsClassType.getClass_type_name())){
            map.put("flag", false);
            map.put("message", "请填写班级类型");
            return map;
        }
        
        try {
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("class_type_name", ieltsClassType.getClass_type_name());
            IeltsClassType ieltsClassType1 = ieltsClassTypeDao.selectByClassName(mapparams);
            if(ieltsClassType1 == null){
                ieltsClassTypeDao.insertSelective(ieltsClassType);
                map.put("flag", true);
                map.put("message", "新增成功");
            }else{
                map.put("flag", false);
                map.put("message", "班级类型名称不能重复");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增错误");
        }
        return map;
    }

    /**
     * 删除班级类型
     * @param ieltsClassType
     * @return
     */
    public Map<String, Object> deleteclasstype(IeltsClassType ieltsClassType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsClassType == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsClassType.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        try {
            ieltsClassEnrollDao.deleteByClasstypeId(ieltsClassType.getId());
            ieltsClassTypeDao.deleteByPrimaryKey(ieltsClassType.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        return map;
    }

    /**
     * 修改班级类型
     * @param ieltsClassType
     * @return
     */
    public Map<String, Object> updateclasstype(IeltsClassType ieltsClassType) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsClassType == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsClassType.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsClassType.getClass_type_name())){
            map.put("flag", false);
            map.put("message", "请填写班级类型");
            return map;
        }
        
        try {
            
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("class_type_name", ieltsClassType.getClass_type_name());
            IeltsClassType ieltsClassType1 = ieltsClassTypeDao.selectByClassName(mapparams);
            if(ieltsClassType1 == null){
                ieltsClassTypeDao.updateByPrimaryKeySelective(ieltsClassType);
                map.put("flag", true);
                map.put("message", "修改成功");
            }else{
                map.put("flag", false);
                map.put("message", "班级类型名称不能重复");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
        }
        return map;
    }

    /**
     * 查询书籍信息班级类型
     * @param ieltsClassType
     * @return
     */
    public Map<String, Object> selectclasstype(IeltsClassType ieltsClassType) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsClassType == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsClassType.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        try {
            IeltsClassType ieltsClassType1 = ieltsClassTypeDao.selectByPrimaryKey(ieltsClassType.getId());
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ieltsClassType", ieltsClassType1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询失败："+e);
        }
        return map;
    }
}
