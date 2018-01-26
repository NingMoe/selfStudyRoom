package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsBookInfoDao;
import com.human.ielts.dao.IeltsStudentBookDao;
import com.human.ielts.entity.IeltsBookInfo;
import com.human.ielts.service.IeltsStudentBookService;
import com.human.utils.PageView;

@Service
public class IeltsStudentBookServiceImpl implements IeltsStudentBookService {
    
    @Resource
    private IeltsBookInfoDao ieltsBookInfoDao;
    
    @Resource
    private IeltsStudentBookDao ieltsStudentBookDao;

    /**
     * 初始化书籍
     * @param ieltsStudentInfo
     * @param classids
     * @param bookids
     * @return
     */
    public Map<String, Object> getbook() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //初始化书籍
            List<IeltsBookInfo> list = ieltsBookInfoDao.selectAllBook();
            if(list != null && list.size() > 0){
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "请联系管理员配置教材信息。");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "失败:"+e);
        }
        return map;
    }

    /**
     * 分页获取书籍信息
     */
    public PageView querybook(PageView pageView, IeltsBookInfo ieltsBookInfo) {
      //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsBookInfo);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsBookInfo> list = ieltsBookInfoDao.query(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return pageView;
    }

    /**
     * 新增书籍信息
     */
    public Map<String, Object> insertbook(IeltsBookInfo ieltsBookInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsBookInfo == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsBookInfo.getBook_name())){
            map.put("flag", false);
            map.put("message", "请填写书名");
            return map;
        }
        
        try {
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("book_name", ieltsBookInfo.getBook_name());
            IeltsBookInfo ieltsBookInfo1 = ieltsBookInfoDao.selectByBookName(mapparams);
            if(ieltsBookInfo1 == null){
                ieltsBookInfoDao.insertSelective(ieltsBookInfo);
                map.put("flag", true);
                map.put("message", "新增成功");
            }else{
                map.put("flag", false);
                map.put("message", "书籍名称不能重复");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增错误");
        }
        return map;
    }

    /**
     * 删除书籍信息
     */
    public Map<String, Object> deletebook(IeltsBookInfo ieltsBookInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsBookInfo == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsBookInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        try {
            ieltsStudentBookDao.deleteByBookId(ieltsBookInfo.getId());
            ieltsBookInfoDao.deleteByPrimaryKey(ieltsBookInfo.getId());
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
     * 修改书籍信息
     */
    public Map<String, Object> updatebook(IeltsBookInfo ieltsBookInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsBookInfo == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsBookInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        try {
            
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("book_name", ieltsBookInfo.getBook_name());
            IeltsBookInfo ieltsBookInfo1 = ieltsBookInfoDao.selectByBookName(mapparams);
            if(ieltsBookInfo1 == null){
                ieltsBookInfoDao.updateByPrimaryKeySelective(ieltsBookInfo);
                map.put("flag", true);
                map.put("message", "修改成功");
            }else{
                map.put("flag", false);
                map.put("message", "书籍名称不能重复");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
        }
        return map;
    }

    /**
     * 查询书籍信息
     */
    public Map<String, Object> selectbook(IeltsBookInfo ieltsBookInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsBookInfo == null){
            map.put("flag", false);
            map.put("message", "请填写书籍信息。");
            return map;
        }
        
        if(ieltsBookInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择书籍");
            return map;
        }
        
        try {
            IeltsBookInfo ieltsBookInfo1 = ieltsBookInfoDao.selectByPrimaryKey(ieltsBookInfo.getId());
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ieltsStudentBook", ieltsBookInfo1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询失败："+e);
        }
        return map;
    }

}
