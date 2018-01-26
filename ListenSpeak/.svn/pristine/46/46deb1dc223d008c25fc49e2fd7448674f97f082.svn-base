package com.ls.spt.lstBasePaper.service;

import java.util.List;
import java.util.Map;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.entity.LstBasePaper;

public interface LstBasePaperService {

    PageView query(PageView pageView, LstBasePaper lbp);

    int delete(String ids);

    LstBasePaper getLstBasePaperInfo(String id);

    int insert(LstBasePaper lst);

    List<Map<String, Object>> getQuestionType();

    LstBasePaper getPaperInfo(LstBasePaper lst);

    int update(int status);

    int insertQuestionType(String num, String typeId, String paperId);

    List<Map<String, Object>> getTypeAndNum(Integer valueOf);

    int update(String paperId, String i);

    List<Map<String, Object>> getQuestionTypeNum(LstBasePaper lst);

    int updatePaperInfo(LstBasePaper lst);

    int updateQuestionType(String num, String typeId, String paperId);

    int updateXh(String paperId, String code,String xh);

    LstBasePaper selectPaperInfo(Integer id);

    int getTotalNum(Integer paperId);

    int getquestionNum(Integer paperId);



}
