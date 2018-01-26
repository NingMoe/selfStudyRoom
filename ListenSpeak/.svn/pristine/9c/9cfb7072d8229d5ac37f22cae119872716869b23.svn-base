package com.ls.spt.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.basic.entity.DicData;

@Repository
public interface DicDataDao {
    int deleteByPrimaryKey(Integer id);

    int deleteByDicCode(String dicCode);
    
    List<DicData> getDataPage(Map<Object,Object> map);
    
    int insert(DicData record);

    DicData selectByPrimaryKey(Integer id);
    
    List<DicData> selectByDicCode(String dicCode);

    int updateByPrimaryKeySelective(DicData record);

    int updateByPrimaryKey(DicData record);
    
    /**
     * 根据字典内容模糊查询
     * @param dicCode
     * @return
     */
    int queryByDataValue(String dataValue);
    
    DicData selectByName(String name);
    
    /**
     * 根据字典编码及公司ID查询
     * @param record
     * @return
     */
    List<DicData> selectByDicCodeAndCompany(DicData record);
}