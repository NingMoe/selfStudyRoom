package com.human.questionnaire.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.questionnaire.entity.ParamBean;

@Repository
public interface QuestionParamDao {

    List<ParamBean> query(Map<Object, Object> map);

    int saveParam(ParamBean bean);

    int editParam(ParamBean bean);

    List<ParamBean> queryParam(ParamBean bean);

    int updateByIds(Map<String, Object> paraMap);

    List<ParamBean> queryFormParam(Long id);

}
