package com.human.feedback.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.bean.FeedBackOper;
import com.human.manager.entity.FeedbackParamBean;
import com.human.manager.entity.HrOrganization;
import com.human.utils.PageView;

public interface FeedBackUploadService {

    List<HrOrganization> queryHfXdfOrg();

    Map<String, Object>  insertFeedBack(FeedBackBase fbb, FeedBackDetail fbd, MultipartFile[] imagefile) ;

    PageView  getMyFeedBack(PageView pageView,String userName);

    void closeFeedBack(FeedbackParamBean fpb);

    void jxfeedback(FeedBackDetail fbd, MultipartFile[] imagefile);
    
    PageView getMyOperFeedBack(PageView pageView,String userName);
    
    PageView getMyOperFeedBackEd(PageView pageView,String userName);

    Map<String,Object> fbFeedBack(FeedBackDetail fbd,MultipartFile[] imagefile);

    PageView query(PageView pageView,String companyId);

    List<FeedBackOper> queryOper(FeedBackOper ho);

    Map<String, Object> removeConfig(FeedBackOper ho);

    Map<String, Object> addConfig(FeedBackOper ho);

    List<HrOrganization> queryUserOrg(String authenticatedUsername);

    PageView queryFeedBackListPage(PageView pageView, FeedbackParamBean fpb);

    void closeFeedBackByIds(FeedbackParamBean fpb);

    List<FeedBackBase> queryParamFeedback(FeedbackParamBean fpb);


}
