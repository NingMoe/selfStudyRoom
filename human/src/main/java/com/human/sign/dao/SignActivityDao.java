package com.human.sign.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.sign.entity.SignActivity;

@Repository
public interface SignActivityDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(SignActivity record);

    SignActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignActivity record);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<SignActivity>query(Map<Object, Object> map);
    
    /**
     * 根据时间戳查询
     * @param activityTime
     * @return
     */
    SignActivity selectByActivityTime(Long activityTime);
    
    /**
     * 根据主键查询总签到率
     * @param id
     * @return
     */
    SignActivity selectById(Long id);
    
}