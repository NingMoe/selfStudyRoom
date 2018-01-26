package com.human.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.customer.entity.CustomerSelect;

@Repository
public interface CustomerSelectDao {

    List<CustomerSelect> queryAll();

    int deleteAll(String userName);

    int insert(CustomerSelect c);

    List<CustomerSelect> queryByParam(CustomerSelect cs);

    CustomerSelect queryByKey(String key);

    int delete(String key);

}