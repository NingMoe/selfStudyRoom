package com.human.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.customer.entity.CenterModel;

@Repository
public interface CustomerCenterConfigModelDao {

    int add(CenterModel cm);

    int delByIds(String[] ids);
    
    int edit(CenterModel cm);

    List<CenterModel> queryByName(String name);

    List<CenterModel> query(Map<String, Object> map);

    CenterModel queryById(Long id);

    List<CenterModel> getModels();

}
