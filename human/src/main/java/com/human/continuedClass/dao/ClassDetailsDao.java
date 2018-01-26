package com.human.continuedClass.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.ClassDetails;

@Repository
public interface ClassDetailsDao {

    int insertSelective(ClassDetails record);

    ClassDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassDetails record);
    
    ClassDetails selectByClassCode(String classCode);
    
    /**
     * 批量插入
     * @param list
     */
    void insertByBatch(List<ClassDetails> list);
        
}