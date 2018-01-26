package com.human.customer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.customer.entity.CenterModel;
import com.human.utils.PageView;

public interface CustomerCenterConfigModelService {

    PageView query(PageView pageView,CenterModel cd);

    Map<String, Object> add(CenterModel cd, HttpServletRequest request);

    void delByIds(String ids);

    CenterModel queryById(Long id);

    Map<String, Object> edit(CenterModel cd, HttpServletRequest request);

    List<CenterModel> getModels();

}
