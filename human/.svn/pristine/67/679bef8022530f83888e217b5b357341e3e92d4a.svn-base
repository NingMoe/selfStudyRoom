package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.DicData;
import com.human.jzbTest.entity.JzbPaperMainMessage;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.utils.PageView;

public interface jzbPaperMainConfigService {

    PageView query(PageView pageView, jzbPaperMainConfig jzb);

    int update(jzbPaperMainConfig jzb);

    jzbPaperMainConfig selectByPrimaryKey(int id);

    int  insert(jzbPaperMainConfig jzb);

    Map<String, Object> delete(String deleteIds);

    Map<String, Object> save(String str);
    
    jzbPaperMainConfig getPaperMainConfig(jzbPaperMainConfig jzb);

    Map<String, Object> getSubjectDicData(String dic_code, String subject);

    jzbPaperMainConfig selectPaperInfo(jzbPaperMainConfig jzb);
    
    List<DicData> getVaidClassTypeByGrade(String gradeIds);
    
    List<DicData> selectValidSubject(String gradeIds,String classType);

    List<Map<String, Object>> getList(String deleteIds);

    int deleteMonth(String deleteIds);

    List<JzbPaperMainMessage> getMessageById(Integer id);
    
    List<JzbPaperMainMessage> getMessageUseById(Integer id);

    int insertMessage(JzbPaperMainMessage me);

    int deleteMessage(int mainId);

}
