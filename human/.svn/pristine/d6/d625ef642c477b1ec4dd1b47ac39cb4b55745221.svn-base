package com.human.teacherservice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.teacherservice.entity.LibBookType;
import com.human.utils.PageView;

public interface LibraryTypeService {

    /**
     * 分页获取分类名称
     * @param libBookType
     * @param pageView
     * @return
     */
    public PageView query(LibBookType libBookType, PageView pageView);

    /**
     * 新增
     * @param libBookType
     * @param request
     * @return
     */
    public Map<String, Object> insert(LibBookType libBookType, HttpServletRequest request);

    /**
     * 删除
     * @param libBookType
     * @return
     */
    public Map<String, Object> delete(LibBookType libBookType);

    /**
     * 修改
     * @param libBookType
     * @param request
     * @return
     */
    public Map<String, Object> update(LibBookType libBookType, HttpServletRequest request);

    /**
     * 通过id获取分类
     * @param libBookType
     * @param request
     * @return
     */
    public Map<String, Object> queryById(LibBookType libBookType);

    /**
     * 获取所有分类
     * @return
     */
    public Map<String, Object> queryAll();

    
    /**
     * 批量禁用
     * @param ids
     * @return
     */
    public Map<String, Object> updateselect(String ids, Integer is_valid);
}
