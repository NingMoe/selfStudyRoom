package com.human.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.human.customer.dao.CustomerMailFoxDao;
import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerMailFoxBasePhoto;
import com.human.customer.entity.CustomerMailFoxPhoto;
import com.human.customer.entity.CustomerMailFoxRecord;
import com.human.customer.service.CustomerMailFoxService;
import com.human.customer.service.CustomerMailFoxUserWebService;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class CustomerMailFoxUserWebServiceImpl implements CustomerMailFoxUserWebService {

    @Resource
    private HttpServletRequest request;
    
    @Resource
    private CustomerMailFoxDao  mailFoxDao;
    
    @Resource
    private CustomerMailFoxService  cmfService;
    
    @Value("${oss.customer.photo}")
    private String customerPath;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private String bucketName;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addMail(CustomerMailFox cmf, MultipartFile[] imagefile) {
        Map<String, Object> map=new HashMap<String,Object>();
        String openId ="123";//(String) request.getSession().getAttribute(BindingConstants.BINDING_OPENID);
        if(null==cmf||StringUtils.isBlank(cmf.getDesc())||StringUtils.isBlank(cmf.getType())|StringUtils.isBlank(cmf.getGrade())) {
            map.put("flag", false);
            map.put("msg", "信息输入有误,请完整填写必填项!");
            return map;
        }
        cmf.setOpenId(openId);
        cmf.setAcctype("校长信箱");
        cmf.setState(0);
        mailFoxDao.addMail(cmf);
        for (MultipartFile mf : imagefile) {
            String fileName = customerPath + openId + String.valueOf(System.currentTimeMillis()) + "."
                    + mf.getContentType().substring(mf.getContentType().indexOf("/") + 1, mf.getContentType().length());
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, mf);
            if ((boolean) uploadResult.get("flag")) {
                CustomerMailFoxBasePhoto cmp = new CustomerMailFoxBasePhoto();
                cmp.setRecordId(cmf.getId());
                cmp.setUrl((String) uploadResult.get("objectkey"));
                mailFoxDao.inserBasePhoto(cmp);
            }
        }
        map.put("flag", true);
        map.put("msg", "提交成功");
       // emailService.sendFeedbackMsgMail(fbb.getId(),1);
        return map;
    }

    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addMailRecord(CustomerMailFoxRecord cmr, MultipartFile[] imagefile) {
        Map<String, Object> map=new HashMap<String,Object>();
        String openId ="123";//(String) request.getSession().getAttribute(BindingConstants.BINDING_OPENID);
        if(null==cmr||StringUtils.isBlank(cmr.getDesc())||(1!=cmr.getType()&&0!=cmr.getType())) {
            map.put("flag", false);
            map.put("msg", "信息输入有误,请完整填写必填项!");
            return map;
        }
        mailFoxDao.addMailfoxRecord(cmr);
        //更新信息未读状态
        cmfService.updateReadType(cmr.getBaseId(), cmr.getType(), false);
        for (MultipartFile mf : imagefile) {
            String fileName = customerPath + openId + String.valueOf(System.currentTimeMillis()) + "."
                    + mf.getContentType().substring(mf.getContentType().indexOf("/") + 1, mf.getContentType().length());
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, fileName, mf);
            if ((boolean) uploadResult.get("flag")) {
                CustomerMailFoxPhoto cmp = new CustomerMailFoxPhoto();
                cmp.setRecordId(cmr.getId());
                cmp.setUrl((String) uploadResult.get("objectkey"));
                mailFoxDao.inserRecordPhoto(cmp);
            }
        }
        map.put("flag", true);
        map.put("msg", "提交成功");
       // emailService.sendFeedbackMsgMail(fbb.getId(),1);
        return map;
    }

    @Override
    public PageView getMailList(PageView pageView,CustomerMailFox cmf) {
        List<CustomerMailFox> list = mailFoxDao.getMailList(cmf);
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
    public CustomerMailFox getMailById(CustomerMailFox cmf,Integer searchType) {
        CustomerMailFox cm=mailFoxDao.getMailById(cmf);
        if(cm.getReadType()==searchType&&!cm.getIsRead()) {
            cmfService.updateReadType(cmf.getId(),null, true);
        }
        return cm;
    }


    @Override
    public void subScore(CustomerMailFox cmf) {
         mailFoxDao.subScore(cmf);
    }


    @Override
    public PageView queryManagerList(PageView pageView, Map<String,Object> map) {
        List<CustomerMailFox> list = mailFoxDao.queryManagerList(map);
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


}
