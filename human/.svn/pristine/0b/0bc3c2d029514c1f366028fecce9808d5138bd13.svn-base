package com.human.rank.dao;

import java.util.List;
import java.util.Map;

import com.human.rank.entity.RankClasses;

public interface RankClassesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RankClasses record);

    int insertSelective(RankClasses record);

    RankClasses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankClasses record);

    int updateByPrimaryKey(RankClasses record);

    /**
     * 分页获取班级信息
     * @param map
     * @return
     */
    public List<RankClasses> query(Map<Object, Object> map);

    /**
     * 通过id批量删除
     * @param list
     * @return
     */
    public int deleteSelectById(List<Integer> list);

    /**
     * 通过规则id批量删除
     * @param rankinfo_id
     * @return
     */
    public int deleteSelectByRankinfoId(Integer rankinfo_id);

    /**
     * 通过规则id获取班级信息
     * @param mapparam
     * @return
     */
    public List<Map<String, Object>> selectByRankinfoId(Map<String, Object> mapparam);

    /**
     * 前端刷新获取班级信息
     * @param mapparam
     * @return
     */
    public List<RankClasses> selectclasses(Map<String, Object> mapparam);

    /**
     * 获取要刷新的班级
     * @return
     */
    public List<RankClasses> selectClassesInfo();

    public int updateByClasscode(RankClasses c);
}