package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.jzbTest.entity.CacheQuestion;
import com.human.jzbTest.entity.JzbCacheAnswer;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.JzbQuestionPaper;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.utils.PageView;

public interface JzbQuestionService {
    PageView selectQuestionPage(PageView pageView,JzbQuestion question);

    List<Integer> selectForQuestion(String month,String subject,String classType,String grade,String deptId);
    
    void addQuestion(JzbQuestion question,Map<String,String> imgMap);
    
    void addTkQuestion(JzbQuestion question);
    
    JzbQuestion getSimpleQusetion(String id);
    
    JzbQuestion getMultipleQusetion(String id);
    
    void editSimpleQuestion(JzbQuestion question,Map<String,String> imgMap);
    
    void editTkSimpleQuestion(JzbQuestion question);
    
    void editMultiQuestion(JzbQuestion question);
    
    void editTkMultiQuestion(JzbQuestion question);
    
    void editStatus(JzbQuestion question);
    
    void delQuestion(JzbQuestion question);
    
    List<AreaInfo> getAreaByGrade(Integer gradeId);
    
    String getQuestionPreCode(Integer mainConfigId);
    
    public List<JzbCacheAnswer> getCacheAnswer(String preCode);
    
    List<CacheQuestion> getCacheQuestion(String preCode,String qType);
    
    int insertPaper(JzbQuestionPaper paper);
    
    int updatePaper(JzbQuestionPaper paper);
    
    JzbQuestionPaper selectLastPaperByOpenId(String openId);
    
    int insertPaperQuestions(List<JzbPaperQuestion> questions);
    
    int setAnswerId(Integer paperId);
    
    JzbQuestion getPageQuestion(JzbPaperQuestion pq);
    
    Integer getExistTotalTimesByOpenId(JzbQuestionPaper paper);
    
    Integer getExistMonthTotalTimesByOpenId(JzbQuestionPaper paper);
    
    Integer getExistDayTimesByOpenId(JzbQuestionPaper paper);
    
    List<JzbQuestionPaper> selectByOpenId(String openId);
    
    JzbQuestionPaper selectPaperByPaperId(Integer paperId);
    
    Map<String,Object> sendBindMsg(String telephone);
    
    JzbQuestionAnswer getAnswerById(Integer answerId);
    
    int delImg(Integer answerId);
    
    List<DicData> getQuestionClassType();
    
    List<DicData> getQuestionSubjectByGrade(String grade);
    
    void refreshMonthConfigStatus(String preCode,String qCode);
    
    void insertBmRecord(JzbStudentBmRecord bmRecord);

    List<JzbQuestion> getquestionArr(String questionId,JzbQuestion jq);

    JzbQuestion selectPrimaryKey(String id); 
}
