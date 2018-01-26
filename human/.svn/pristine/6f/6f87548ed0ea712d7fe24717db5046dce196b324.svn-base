package com.human.jw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.jw.entity.JwJyzUser;

@Repository
public interface JwJyzUserDao {
    
    int deleteByPrimaryKey(Integer id);

    int insert(JwJyzUser record);

    JwJyzUser selectByPrimaryKey(Integer id);
    
    List<JwJyzUser> selectByEmail(String email);

    int updateByPrimaryKeySelective(JwJyzUser record);
    
    List<JwJyzUser> selectJyzPage(Map<Object,Object> map);
}