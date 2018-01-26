package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbPaperConfigDetail;

public interface jzbPaperConfigDetailService {

    int insert(JzbPaperConfigDetail jzbpaperconfigdetail);

     List<JzbPaperConfigDetail> selectByMonth(Integer monthId);

    List<Map<String, Object>> searchZsdDetail(Integer id, Integer month, Integer knowledgeId);

    List<Map<String, Object>> selectKnowledge(String valueOf, int mainConfigId,String subject,String classType,String grade);

    List<Map<String, Object>> queryByCondition(String month, int mainConfigId, int knowledgeId);

    void delete(int mainConfigId,String month);

    List<Map<String, Object>> searchZsd(String month, Integer id);

    Map<String, Object> getMonthId(int mainConfigId, String paperMonth);

    int deleteByMonthId(int monthId);


}
