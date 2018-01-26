package com.human.customer.dao;

import java.util.List;
import java.util.Map;

import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerMailFoxBasePhoto;
import com.human.customer.entity.CustomerMailFoxPhoto;
import com.human.customer.entity.CustomerMailFoxRecord;
import com.human.customer.entity.CustomerMailfoxDemand;

public interface CustomerMailFoxDao {

    List<CustomerMailFox> query(Map<String, Object> map);

    int save(CustomerMailFox mailFox);

    CustomerMailFox queryById(Integer id);

    int delDemand(Long id);

    int insertDemand(CustomerMailfoxDemand cmd);

    int update(CustomerMailFox mailFox);

    int managerUpdate(CustomerMailFox mailFox);

    int updateTracer(CustomerMailFox mailFox);

    int addMail(CustomerMailFox cmf);

    int inserBasePhoto(CustomerMailFoxBasePhoto cmp);

    int addMailfoxRecord(CustomerMailFoxRecord cmr);

    int inserRecordPhoto(CustomerMailFoxPhoto cmp);

    CustomerMailFox getMailById(CustomerMailFox cmf);

    List<CustomerMailFox> getMailList(CustomerMailFox cmf);
    
    List<CustomerMailFox> queryManagerList(Map<String, Object> map);
    
    int subScore(CustomerMailFox cmf);

    int updateReadType(CustomerMailFox cmf);
}
