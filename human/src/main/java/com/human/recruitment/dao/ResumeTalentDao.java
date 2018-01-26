package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.recruitment.entity.ResumeTalent;
@Repository
public interface ResumeTalentDao {
    
    ResumeTalent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResumeTalent record);
    
    int insertOrUpdate(ResumeTalent record);
    
    int  deleteByResumeId(Integer resumeId);
    
    /*
     * 分页查询人才库
     */
    List<ResumeTalent> query(Map<String,Object> map);
    
    /*
     * 人才库导出本页
     */
    List<Map<String,Object>> exporThisPage(Map<String,Object> map);
    
    /*
     * 导出选择的人才库
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
    /**
     * 移出人才库
     * @param map
     */
    int deleteByIds(Map<String,Object> map);
    
    int updateByIds(Map<String,Object> map);

}