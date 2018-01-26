package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.RecommendClass;
@Repository
public interface RecommendClassDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(RecommendClass record);

    RecommendClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecommendClass record);
    
    /*
     * 批量插入
     * @param list
     */
    void insertByBatch(List<RecommendClass> list);
    
    /*
     * 删除某规则下全部学员-推荐班级数据
     * @param ruleId
     * @return
     */
    int deleteByRuleId(Long ruleId);
    
    /*
     * 删除学员-推荐班级数据
     */
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 查询学员-推荐班级数据(早期的逻辑）
     * @param map
     * @return
     */
    List<RecommendClass> selectRecommendClass(Map<String, Object> map);
    
    /*
     * 查询学员-推荐班级数据
     * @param map
     * @return
     */
    List<RecommendClass> selectRecommendClass2(Map<String, Object> map);
    
    /*
     * 查询学员推荐班级总数
     */
    long selectCountOfRecommendClass(ClassMatch cm);
    
    
    /*
     * 分页查询学员-推荐班级明细数据
     */
    List<RecommendClass> query(Map<Object, Object> map);
    
    /*
     * 导出学员-推荐班级数据
     * @param map
     * @return
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
    /*
     * 导出班级-推荐班级数据
     */
    List<Map<String,Object>> exportSelectByRuleId(long ruleId);

}