package com.human.feedback.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.bean.FeedBackOper;
import com.human.feedback.bean.FeedBackPhoto;
import com.human.feedback.dao.FbSubDao;
import com.human.feedback.service.FeedBackEmailService;
import com.human.feedback.service.FeedBackUploadService;
import com.human.manager.entity.FeedbackParamBean;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.utils.BindingConstants;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.execl.StringUtil;

@Service
public class FeedBackUploadServiceImpl implements FeedBackUploadService {

    @Resource
    private RedisTemplateUtil redisUtil;

    @Value("${oss.bucket}")
    private String bucketName;
    

    @Resource
    private OSSUtil ossUtil;

    @Value("${oss.feedback.photo}")
    private String feedbackPath;

    @Resource
    private HttpServletRequest request;

    
    @Value("${urlPreKey}")
    private String urlPreKey;
    
    @Resource
    private FbSubDao fbDao;
    
    @Resource
    private FeedBackEmailService emailService;
    
    private final Logger logger = LogManager.getLogger(FeedBackUploadServiceImpl.class);
    
    @Resource
    private UserService userService;
    @SuppressWarnings("unchecked")
    @Override
    public List<HrOrganization> queryHfXdfOrg() {
        List<HrOrganization> hoList = new ArrayList<HrOrganization>();
        Users user = (Users) request.getSession().getAttribute(BindingConstants.BINDING_USERS);
        if(user==null) {
            logger.info("=============sessiong中获取user为null");
            return hoList;
        }
        if (redisUtil.isExist("feecback_deptList_"+user.getCompanyId())) {
            logger.info("=============从缓存中获取部门集合，key[feecback_deptList_"+user.getCompanyId()+"]");
            hoList = (List<HrOrganization>) redisUtil.get("feecback_deptList_"+user.getCompanyId());
        }
        else {
            hoList = fbDao.queryXdfOrg(user.getCompanyId());
            redisUtil.setList("feecback_deptList_"+user.getCompanyId(), hoList, Long.valueOf(60 * 60 * 24));
            logger.info("=============从缓存中存储部门集合，key[feecback_deptList_"+user.getCompanyId()+"]");
        }
        return hoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  Map<String, Object>  insertFeedBack(FeedBackBase fbb, FeedBackDetail fbd, MultipartFile[] imagefile) {
        Map<String, Object> map=new HashMap<String,Object>();
        String userName =(String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        List<FeedBackBase> nowList=fbDao.getMyNowFeedback(userName);
        if(nowList.size()>0) {
            map.put("flag", false);
            map.put("msg", "对不起，你已经提交过一个待处理的反馈了，请等待回复后再反馈新反馈!");
            return map;
        }
        if(null==fbb||StringUtils.isBlank(fbb.getTitle())||StringUtils.isBlank(fbb.getDeptId())||null==fbd||StringUtils.isBlank(fbd.getDesc())) {
            map.put("flag", false);
            map.put("msg", "信息输入有误,请完整填写必填项!");
            return map;
        }
        fbb.setUserName(userName);
        logger.info("=====================================校长信箱新反馈，base内容如下=====================================");
        logger.info(JSON.toJSONString(fbb));
        fbDao.insertFeedBackBase(fbb);
        if(fbb.getId()==null) {
            map.put("flag", false);
            map.put("msg", "基础数据插入有误,请完整填写必填项!");
            return map;
        }
        fbd.setBaseId(fbb.getId());
        fbd.setOperUser(userName);
        logger.info("=====================================校长信箱新反馈，detail内容如下=====================================");
        logger.info(JSON.toJSONString(fbb));
        fbDao.inserFeedBaceDetail(fbd);
        for (MultipartFile mf : imagefile) {
            String fileName = feedbackPath + userName + String.valueOf(System.currentTimeMillis()) + "."
                    + mf.getContentType().substring(mf.getContentType().indexOf("/") + 1, mf.getContentType().length());
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, mf);
            if ((boolean) uploadResult.get("flag")) {
                FeedBackPhoto fbp = new FeedBackPhoto();
                fbp.setDetailId(fbd.getId());
                fbp.setUrl((String) uploadResult.get("objectkey"));
                fbDao.inserDetailPhoto(fbp);
            }
        }
        map.put("flag", true);
        map.put("msg", "提交成功");
        emailService.sendFeedbackMsgMail(fbb.getId(),1);
        return map;
    }

    @Override
    public PageView  getMyFeedBack(PageView pageView,String userName) {
        List<FeedBackBase> list = fbDao.getMyFeedBack(userName);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }       
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        if(i>j){
            i=j;
        }
        list= list.subList(i, j);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void closeFeedBack(FeedbackParamBean fpb) {
        fbDao.closeFeedBack(fpb);
        if(fpb.getCloseType()==1){
            //如果是管理关闭，邮件通知提交者
            emailService.sendOperMsgMail(fpb.getId());
        }
        if(fpb.getCloseType()==0){
            //如果是管理关闭，邮件通知负责审核的管理者
            emailService.sendFeedbackMsgMail(fpb.getId(),3);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void jxfeedback(FeedBackDetail fbd, MultipartFile[] imagefile) {
        fbDao.inserFeedBaceDetail(fbd);
        for (MultipartFile mf : imagefile) {
            String fileName = feedbackPath + fbd.getOperUser() + String.valueOf(System.currentTimeMillis()) + "."
                    + mf.getContentType().substring(mf.getContentType().indexOf("/") + 1, mf.getContentType().length());
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, mf);
            if ((boolean) uploadResult.get("flag")) {
                FeedBackPhoto fbp = new FeedBackPhoto();
                fbp.setDetailId(fbd.getId());
                fbp.setUrl((String) uploadResult.get("objectkey"));
                fbDao.inserDetailPhoto(fbp);
            }
        }
        FeedBackBase fbb=new FeedBackBase();
        fbb.setId(fbd.getBaseId());
        fbb.setState(0);
        fbDao.updateFeedBackState(fbb);
        emailService.sendFeedbackMsgMail(fbb.getId(),2);
    }

    @Override
    public PageView getMyOperFeedBack(PageView pageView,String userName) {
        List<FeedBackBase> list= fbDao.getMyOperFeedBack(userName);
       /* pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }       
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        if(i>j){
            i=j;
        }
        list= list.subList(i, j);*/
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public PageView getMyOperFeedBackEd(PageView pageView,String userName) {
        List<FeedBackBase> list= fbDao.getMyOperFeedBackEd(userName);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        if(i>j){
            i=j;
        }
        list= list.subList(i, j);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> fbFeedBack(FeedBackDetail fbd,MultipartFile[] imagefile) {
        FeedBackBase fbb = new FeedBackBase();
        fbb.setId(fbd.getBaseId());
        fbb.setState(1);
        fbd.setType(1);
        fbb.setOldState(0);
        Map<String, Object> map = new HashMap<String, Object>();
        fbDao.updateFeedBackState(fbb);
        fbDao.inserFeedBaceDetail(fbd);
        if (null != imagefile) {
            for (MultipartFile mf : imagefile) {
                String fileName = feedbackPath + fbd.getOperUser() + String.valueOf(System.currentTimeMillis()) + "."
                        + mf.getContentType().substring(mf.getContentType().indexOf("/") + 1, mf.getContentType().length());
                Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, mf);
                if ((boolean) uploadResult.get("flag")) {
                    FeedBackPhoto fbp = new FeedBackPhoto();
                    fbp.setDetailId(fbd.getId());
                    fbp.setUrl((String) uploadResult.get("objectkey"));
                    fbDao.inserDetailPhoto(fbp);
                }
            }
        }
        map.put("flag", true);
        map.put("msg", "操作成功");
        emailService.sendOperMsgMail(fbd.getBaseId());
        return map;
    }

    @Override
    public PageView query(PageView pageView,String companyId) {
        List<Map<String,String>> list = fbDao.queryConfig(companyId);
        pageView.setRecords(list);
        return pageView;
    }

    
    @Override
    public List<FeedBackOper> queryOper(FeedBackOper fbo) {
        List<FeedBackOper> list= fbDao.queryOper(fbo);
        return list;
    }

    @Override
    public Map<String, Object> removeConfig(FeedBackOper ho) {
        Map<String, Object> map=new HashMap<String,Object>();
        if(StringUtil.isEmptyOrBlank(ho.getDeptId())){
            map.put("flag", false);
            map.put("msg", "无法获取部门信息！");
            return map;
        }
        if(StringUtil.isEmptyOrBlank(ho.getUserName())){
            map.put("flag", false);
            map.put("msg", "无法获取用户信息！");
            return map;
        }
        fbDao.removeConfig(ho);
        map.put("flag", true);
        map.put("msg", "删除成功！");
        return map;
    }

    @Override
    public Map<String, Object> addConfig(FeedBackOper ho) {
        Map<String, Object> map=new HashMap<String,Object>();
        if(StringUtil.isEmptyOrBlank(ho.getDeptId())){
            map.put("flag", false);
            map.put("msg", "无法获取部门信息！");
            return map;
        }
        if(StringUtil.isEmptyOrBlank(ho.getUserName())){
            map.put("flag", false);
            map.put("msg", "无法获取用户信息！");
            return map;
        }
        Users u=new Users();
        u.setLoginName(ho.getUserName());
        u.setStatus(0);
      // 验证用户账号与密码是否正确
      List<Users> users = userService.queryUser(u);
      if(users.size()==0){
          map.put("flag", false);
          map.put("msg", "无法找到对应用户，请检查输入！");
      }else{
          List<FeedBackOper> list= fbDao.queryOper(ho);
          if(list.size()>0){
              map.put("flag", false);
              map.put("msg", "该人员已经添加，无法重复添加！");
              return map;
          }
          fbDao.addConfig(ho);
          ho.setName(users.get(0).getName());
          map.put("flag", true);
          map.put("obj", ho);
          map.put("msg", "新增成功！");
      }
        return map;
    }

    @Override
    public List<HrOrganization> queryUserOrg(String userName) {
        return  fbDao.queryUserOrg(userName);
    }

    @Override
    public PageView queryFeedBackListPage(PageView pageView, FeedbackParamBean fpb) {
        fpb.setUserName(Common.getAuthenticatedUsername());
        List<FeedBackBase> list = fbDao.queryFeedBackList(fpb);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }       
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        if(i>j){
            i=j;
        }
        list= list.subList(i, j);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void closeFeedBackByIds(FeedbackParamBean fpb) {
        String[] ids=fpb.getIds().split(",");
        fpb.setUserName(Common.getAuthenticatedUsername());
        fbDao.closeFeedBackByIds(ids,fpb);
        for(String id:ids){
            emailService.sendOperMsgMail(Long.valueOf(id));
        }
    }

    @Override
    public List<FeedBackBase> queryParamFeedback(FeedbackParamBean fpb) {
        List<FeedBackBase> list = fbDao.queryFeedBackListById(fpb);
        return list;
    }

}
