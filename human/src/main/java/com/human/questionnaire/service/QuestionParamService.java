package com.human.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.human.questionnaire.entity.ParamBean;
import com.human.utils.PageView;

public interface QuestionParamService {

    PageView query(PageView pageView, ParamBean bean);

    List<ParamBean> queryParam(ParamBean bean);

    int saveParam(ParamBean bean);

    int updateParam(ParamBean bean);

    Map<String, Object> updateStatus(String deleteIds, Integer status);
    
    List<ParamBean> queryFormParam(Long id);
}
