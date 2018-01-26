package com.human.rank.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.rank.entity.RankClasses;
import com.human.utils.PageView;

public interface RankClassesService {

    /**
     * 分页获取班级信息
     * @param page
     * @param rankClasses
     * @return
     */
    public PageView query(PageView page, RankClasses rankClasses);

    /**
     * 新增班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> insert(RankClasses rankClasses);

    /**
     * 修改班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> update(RankClasses rankClasses);

    /**
     * 查询班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> select(RankClasses rankClasses);

    /**
     * 删除班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> delete(RankClasses rankClasses);

    /**
     * 批量删除班级
     * @param ids
     * @return
     */
    public Map<String, Object> deleteselect(String ids);

    /**
     * 删除全部班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> deleteall(RankClasses rankClasses);

    /**
     * 批量导入班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出班级
     * @param rankClasses
     * @return
     */
    public Map<String, Object> download(RankClasses rankClasses, HttpServletRequest request, HttpServletResponse response);

    
    /**
     * 前端获取排行榜信息
     * @param id
     * @param rank_num
     * @param rank_lastnum
     * @return
     */
    public Map<String, Object> selectclasses(Integer id, Integer rank_num, Integer rank_lastnum);

    public List<RankClasses> getClassesNum();
}
