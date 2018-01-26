package com.human.rank.dao;

import java.util.List;
import java.util.Map;

import com.human.rank.entity.RankInfo;

public interface RankInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RankInfo record);

    int insertSelective(RankInfo record);

    RankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankInfo record);

    int updateByPrimaryKey(RankInfo record);

    /**
     * 分页获取排行榜规则
     * @param map
     * @return
     */
    public List<RankInfo> query(Map<Object, Object> map);
}