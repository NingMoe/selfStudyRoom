package com.human.recruitment.dao;

import java.util.List;

import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrEntryCertif;
import com.human.recruitment.entity.HrEntryContactaddr;
import com.human.recruitment.entity.HrEntryEduexp;
import com.human.recruitment.entity.HrEntryEmergency;
import com.human.recruitment.entity.HrEntryFamily;
import com.human.recruitment.entity.HrEntryIdentification;
import com.human.recruitment.entity.HrEntryWorkexp;

public interface HrEntryBaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HrEntryBase record);

    HrEntryBase selectByPrimaryKey(Integer id);
    
    HrEntryBase selectComplexByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrEntryBase record);
    
    HrEntryBase selectByEntryHandler(Integer entryHandlerId);
    
    int insertIdenti(HrEntryIdentification identification);
    
    int deleteIdentiByBaseId(Integer entryBaseId);
    
    int insertContactaddr(HrEntryContactaddr contactaddr);
    
    int deleteContactaddrByBaseId(Integer entryBaseId);
    
    int insertCertifs(List<HrEntryCertif> certifs);
    
    int deleteCertifsByBaseId(Integer entryBaseId);
    
    int insertEdus(List<HrEntryEduexp> edus);
    
    int deleteEdusByBaseId(Integer entryBaseId);
    
    int insertWorkexps(List<HrEntryWorkexp> workexps);
    
    int deleteWorkexpsByBaseId(Integer entryBaseId);
    
    int insertFamilys(List<HrEntryFamily> familys);
    
    int deleteFamilysByBaseId(Integer entryBaseId);
    
    int insertEmergencys(List<HrEntryEmergency> emergencys);
    
    int deleteEmergencysByBaseId(Integer entryBaseId);

}