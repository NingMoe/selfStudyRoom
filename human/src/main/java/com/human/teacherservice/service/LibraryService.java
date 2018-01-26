package com.human.teacherservice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.teacherservice.entity.LibBook;
import com.human.utils.PageView;

public interface LibraryService {

    /**
     * 分页查询图书列表
     * @param pageView
     * @param libBook
     * @return
     */
    public PageView query(PageView pageView, LibBook libBook);
    
    /**
     * 通过id查询图书信息
     * @param libBook
     * @return
     */
    public Map<String, Object> queryById(HttpServletRequest request,HttpServletResponse response ,LibBook libBook);
    
    /**
     * 新增图书
     * @param libBook
     * @return
     */
    public Map<String, Object> insert(LibBook libBook, HttpServletRequest request);
    
    /**
     * 更新图书
     * @param libBook
     * @return
     */
    public Map<String, Object> update(LibBook libBook, HttpServletRequest request);
    
    /**
     * 删除图书
     * @param libBook
     * @return
     */
    public Map<String, Object> delete(LibBook libBook);
    
    /**
     * excel导入
     * @param request
     * @return
     */
    public Map<String, Object> upexcel(HttpServletRequest request);

    /**
     * 批量导出
     * @param ids
     * @return
     */
    public Map<String, Object> downselect(String ids, HttpServletRequest request, HttpServletResponse response);

    /**
     * 批量启用/禁用
     * @param ids
     * @return
     */
    public Map<String, Object> isopen(String ids, Integer valid);

}
