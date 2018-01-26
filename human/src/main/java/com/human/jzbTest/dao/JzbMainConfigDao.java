package com.human.jzbTest.dao;

import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbMainConfig;

@Repository
public interface JzbMainConfigDao {
    
    int deleteByPrimaryKey(Long id);
    
    int insertSelective(JzbMainConfig record);

    JzbMainConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbMainConfig record);
    
    JzbMainConfig selectByCompanyId(String companyId);

}