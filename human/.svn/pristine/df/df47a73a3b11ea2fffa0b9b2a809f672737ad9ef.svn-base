package com.human.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.basic.service.DictionaryService;
import com.human.customer.dao.CustomerSelectDao;
import com.human.customer.entity.CustomerSelect;
import com.human.customer.service.CustomerSelectService;
import com.human.utils.Common;

@Service
public class CustomerSelectServiceImpl  implements CustomerSelectService{
    
    @Resource
    private CustomerSelectDao selectDao;
    
    @Resource
    private DictionaryService dictionaryService;

    @Override
    public List<CustomerSelect> findAll() {
       return selectDao.queryAll();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update(List<CustomerSelect> selectList) {
        String userName = Common.getAuthenticatedUsername();
        selectDao.deleteAll(userName);
        for (CustomerSelect c : selectList) {
          /*  // 判断是否存在，存在删了重新加入
            CustomerSelect cs = selectDao.queryByKey(c.getKey());
            c.setUserName(userName);
            if (null != cs) {
                selectDao.delete(c.getKey());
            }*/
            c.setUserName(userName);
            selectDao.insert(c);
        }
    }

    @Override
    public List<CustomerSelect> queryByParam(CustomerSelect cs) {
        return selectDao.queryByParam(cs);
    }

}
