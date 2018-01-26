package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.CombinationClass;
@Repository
public interface CombinationClassDao {
    int deleteByPrimaryKey(Long id);

    int insertByBatch(List<CombinationClass> list);

    int insertSelective(CombinationClass record);

    CombinationClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CombinationClass record);
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<CombinationClass> query(Map<Object, Object> map);
    
    
    int deleteByIds(Map<String, Object> map);
    
    int deleteAll(CombinationClass record);
    
    List<Map<String,Object>> exportSelect(Map<String,Object> map);

    /**
     * 根据校区及年级、班号查询套餐 
     * @param cm
     * @return
     */
    CombinationClass queryCombinationClass(ClassMatch cm);
}