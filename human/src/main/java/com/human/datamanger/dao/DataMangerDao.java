package com.human.datamanger.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.human.datamanger.entity.DataManger;

@Repository
public interface DataMangerDao {
    List<DataManger> query(Map<Object, Object> map);

    int deleteByIds(Map<String, Object> paraMap);

    DataManger selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(DataManger dm);

    int insert(DataManger dm);
    

}