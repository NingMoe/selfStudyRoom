package com.human.questionnaire.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.questionnaire.entity.DisableIP;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.entity.Qform;
import com.human.utils.PageView;

public interface QuestionCollectService {

    PageView query(PageView pageView, Qform bean);

    void saveForm(Qform bean);

    List<Qform> queryNoPage(Qform bean);

    void updateParam(Qform bean);

    void saveCollect(Qform qfrom, List<ParamBean> pbList,HttpServletRequest request);

    PageView queryResult(PageView pageView, Long id);

    void delCollect(String uid);

    List<DisableIP> queryDisableIP(DisableIP ip);

    PageView queryDisableIpList(PageView pageView, DisableIP bean);

    void delIp(String deleteIds);

    void addDisableIp(DisableIP bean);
    

    void exportResult(HttpServletRequest request, HttpServletResponse response);

    void deleteForm(String deleteIds);
}
