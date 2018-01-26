package com.human.customer.service;

import com.human.customer.entity.CustomerMailFox;
import com.human.utils.PageView;

public interface CustomerMailFoxService {

    PageView query(PageView pageView, CustomerMailFox mailFox);

    void save(CustomerMailFox mailFox);

    CustomerMailFox queryById(Integer id);

    void customerUpdate(CustomerMailFox mailFox);

    void managerUpdate(CustomerMailFox mailFox);

    void updateTracer(CustomerMailFox mailFox);

    void updateReadType(Long id, Integer readType, Boolean isRead);

}
