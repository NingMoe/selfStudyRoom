package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.XdfClassInfo;
import com.human.jzbTest.entity.JzbGradeSubjectClass;

@Repository
public interface XdfClassInfoDao {
    int deleteByPrimaryKey(String sClassCode);

    int insert(XdfClassInfo record);

    int insertSelective(XdfClassInfo record);

    XdfClassInfo selectByPrimaryKey(String sClassCode);

    int updateByPrimaryKeySelective(XdfClassInfo record);

    int updateByPrimaryKey(XdfClassInfo record);
    
    List<XdfClassInfo> query(Map<Object, Object> map);
    
    /**
     * 通过推荐课程规则查询班级
     * @param record
     * @return
     */
    List<XdfClassInfo> queryClassByRule(JzbGradeSubjectClass record);
    
    List<XdfClassInfo> queryClassByCondition(XdfClassInfo classInfo);

    /**
     * 通过邮箱前缀，获取教师当前班级结课时间在今天以后的班级信息
     * @return
     */
    List<XdfClassInfo> selectTeacherStartClassesOnByEmrilAddr(Map<String, Object> mapparam);
}