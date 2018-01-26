package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.CommunicationDesc;
import com.human.recruitment.entity.CommunicationRecord;

@Repository
public interface CommunicationRecordDao {
    /**
     * 查询应聘者沟通记录
     * @param rs
     * @return
     */
    List<CommunicationRecord> linkQuery(CommunicationRecord cr);

    /**
     * 查询主题的沟通内容
     * @param cd
     * @return
     */
    List<CommunicationDesc> queryCommunicationDesc(CommunicationDesc cd);

    /**
     * 新增主题，并返回主键
     * @param cr
     * @return
     */
    int inserRecord(CommunicationRecord cr);

    /**
     * 新增沟通内容
     * @param cd
     * @return
     */
    int inserDesc(CommunicationDesc cd);


}
