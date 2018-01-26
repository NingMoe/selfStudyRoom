package com.ls.spt.lstBasePaper.service;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;

public interface LstPaperQuestionService {

    PageView query(LstPaperQuestion lpq, PageView pageView);

    int delete(LstPaperQuestion lpq);

    LstPaperQuestion getPaperQuestionInfo(String paperId, String code, String xh);


}
