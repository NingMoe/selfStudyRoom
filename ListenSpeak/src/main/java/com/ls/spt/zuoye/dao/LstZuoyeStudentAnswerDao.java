package com.ls.spt.zuoye.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;


@Repository
public interface LstZuoyeStudentAnswerDao {
    int deleteByPrimaryKey(Integer ID);

    int insert(LstZuoyeStudentAnswer record);

    int insertSelective(LstZuoyeStudentAnswer record);

    LstZuoyeStudentAnswer selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(LstZuoyeStudentAnswer record);

    int updateByPrimaryKey(LstZuoyeStudentAnswer record);
    
    LstZuoyeStudentAnswer selectByCondition(LstZuoyeStudentAnswer answer);
    
    /**
     * 通过学生id和作业id获取完成情况
     * @param map
     * @return
     */
    public List<LstZuoyeStudentAnswer> selectComplete(Map<String, Object> map);

    public int updateByStudentidClasscodeZuoyeidAndQuestionid(LstZuoyeStudentAnswer lstZuoyeStudentAnswer);
}