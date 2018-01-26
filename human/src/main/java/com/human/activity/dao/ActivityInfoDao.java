package com.human.activity.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.human.activity.entity.ActivityInfo;
import com.human.activity.entity.ActivityInfoDto;

@Repository
public interface ActivityInfoDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<ActivityInfoDto>query(Map<Object, Object> map);
    
    /**
     * 导出活动列表
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
    /**
     * 活动封账
     * @param paraMap
     * @return
     */
    int updateAccountStatusByIds(Map<String, Object> paraMap);
    
    /**
     * 通过买家用户Id查询活动是否封账
     * @param buyInfoId
     * @return
     */
    String selectByBuyInfoId(Long buyInfoId);
}