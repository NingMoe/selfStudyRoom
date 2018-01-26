package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.InterAppr;

@Repository
public interface RecruitAcceptanceDao {

    List<ResumeSeeker> queryResumeSeeker(ResumeSeeker rs);

    int saveResumeSeeker(ResumeSeeker rs);
    
    /*
     * 通过手机号和姓名检查该应聘者是否存在
     * @param name
     * @param phone
     * @return
     */
    ResumeSeeker checkResumeByNameAndPhone(@Param("name")String name,@Param("phone")String phone);
    
    int updateByPrimaryKeySelective(ResumeSeeker rs);
    
    ResumeSeeker selectByPrimaryKey(Long id);
    
    int insert(ResumeSeeker rs);

    //更新应聘者信息
    int updateResumeSeeker(ResumeSeeker rs);

    /**
     * 查询待沟通人
     * @param map
     * @return
     */
    List<InterAppr> toLinkQuery(Map<String, Object> map);

    /**
     * 通过微信openId查询应聘者
     * @param openId
     * @return
     */
    ResumeSeeker selectByOpenId(String openId);
    
    /**
     * 微信解除绑定
     * @param resumeSeekerId
     * @return
     */
    int removeBinding(Long id);
    
    /**
     * 
     * @param flowCode
     * @return
     */
    ResumeSeeker selectByFlowCode(String flowCode);
    
    /**
     * 
     * @param resumeId
     * @return
     */
    ResumeSeeker selectByResumeBaseId(Integer resumeId);
}
