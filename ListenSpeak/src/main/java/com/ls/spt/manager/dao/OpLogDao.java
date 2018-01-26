package com.ls.spt.manager.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.ls.spt.manager.entity.OplogEntity;



@Repository
public interface OpLogDao {
    
    List<OplogEntity> query(Map<String, Object> map);
    
    Integer insert(OplogEntity opLog);

}
