package com.human.customer.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerMailFoxRecord;
import com.human.utils.PageView;

public interface CustomerMailFoxUserWebService {

    Map<String, Object> addMail(CustomerMailFox cmf, MultipartFile[] imagefile);
    
    Map<String, Object> addMailRecord(CustomerMailFoxRecord cmr, MultipartFile[] imagefile);

    PageView getMailList(PageView pageView,CustomerMailFox cmf);

    CustomerMailFox getMailById(CustomerMailFox cmf,Integer searchType);

    void subScore(CustomerMailFox cmf);

    PageView queryManagerList(PageView pageView, Map<String,Object> map);
    
}
