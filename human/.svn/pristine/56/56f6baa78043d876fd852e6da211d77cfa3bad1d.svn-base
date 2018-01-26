package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.teacherservice.entity.LibFeedBack;

@Repository
public interface LibFeedBackDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(LibFeedBack record);

    LibFeedBack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LibFeedBack record);
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<LibFeedBack> query(Map<Object, Object> map);
    
    /**
     * 导出意见反馈记录
     * @param map
     * @return
     */
    List<Map<String,Object>> exportAll(Map<String,Object> map);

}