package com.human.feedback.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.bean.FeedBackOper;
import com.human.feedback.bean.FeedBackPhoto;
import com.human.manager.entity.FeedbackParamBean;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.Users;

@Repository
public interface FbSubDao {

    List<HrOrganization> queryXdfOrg(String companyId);

    void insertFeedBackBase(FeedBackBase fbb);

    void inserFeedBaceDetail(FeedBackDetail fbd);

    void inserDetailPhoto(FeedBackPhoto fbp);

    List<FeedBackBase> getMyFeedBack(String userName);
    
    List<FeedBackBase> getMyNowFeedback(String userName);
    

    void closeFeedBack(FeedbackParamBean fpb);

    int updateFeedBackState(FeedBackBase fbb);

    List<FeedBackBase> getMyOperFeedBack(String userName);
    
    List<FeedBackBase> getMyYestodayOperFeedBack(String userName);
    
    List<FeedBackBase> getMyOperFeedBackEd(String userName);
    
    List<Map<String, String>> queryConfig(String companyId);

    List<FeedBackOper> queryOper(FeedBackOper fbo);

    int removeConfig(FeedBackOper ho);

    void addConfig(FeedBackOper ho);

    List<HrOrganization> queryUserOrg(String userName);

    List<FeedBackBase> queryFeedBackList(FeedbackParamBean fpb);

    void closeFeedBackByIds(@Param("ids")String[] ids, @Param("fpb")FeedbackParamBean fpb);
    
    List<FeedBackBase> queryFeedBackListById(FeedbackParamBean fpb);
    
    List<Users> queryEmailUserList(FeedBackOper fbo);

    FeedBackBase getBaseById(Long id);

    FeedBackBase getBaseDetailById(Long id);
    

}
