package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.teacherservice.entity.LibBookBuy;

@Repository
public interface LibBookBuyDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(LibBookBuy record);

    LibBookBuy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LibBookBuy record);

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<LibBookBuy> query(Map<Object, Object> map);
    
    /**
     * 导出反馈记录
     * @param map
     * @return
     */
    List<Map<String,Object>> exportAll(Map<String,Object> map);
}