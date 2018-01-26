package com.human.xdfStudent.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.xdfStudent.entity.XdfStudentInfo;

@Repository
public interface XdfStudentInfoDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(XdfStudentInfo record);

    XdfStudentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XdfStudentInfo record);
    
    /*
     * 批量插入
     * @param list
     */
    void insertByBatch(List<XdfStudentInfo> list);
        
    /*
     * 批量更新
     * @param list
     */
    void updateBatch(List<XdfStudentInfo> list);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 分页查询
     * @param map
     * @return
     */
    List<XdfStudentInfo> query(Map<Object, Object> map);
    
    /*
     * 全部删除
     */
    int deleteAll();
    
    /*
     * 通过学员号查询学员
     * @param studentCode
     */
    XdfStudentInfo selectByStudentCode(String studentCode);
    
    /*
     * 批量导出新东方学员
     * @param studentCode
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
}