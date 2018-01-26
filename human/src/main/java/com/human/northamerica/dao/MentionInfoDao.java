package com.human.northamerica.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.northamerica.entity.ClassTeach;
import com.human.northamerica.entity.MentionInfo;
import com.human.northamerica.entity.MentionInfoClass;
import com.human.northamerica.entity.MentionTeachInfo;
import com.human.northamerica.entity.USAClassInfo;

@Repository
public interface MentionInfoDao {

    List<MentionInfo> queryReportList(MentionInfo info);
    
    List<MentionInfo> queryReportListAll(MentionInfo info);

    int saveMentionInfo(MentionInfo mi);

    MentionInfo queryById(Long id);

    int editMention(MentionInfo info);

    int delMentionByIds(@Param("ids")String[] ids);

    List<MentionInfo> queryMentionInfo(MentionInfo info);

    List<MentionInfo> queryRefreshRows();

    int editMentionStudentName(MentionInfo mi);

    List<MentionInfo> queryMentionInfoList(MentionInfo info);

    int insertInfoClass(MentionInfoClass mic);

    int insertClass(USAClassInfo usaClass);

    int insertClassTeach(ClassTeach ct);

    List<MentionTeachInfo> queryMentionTeachReport(MentionInfo info);


}
