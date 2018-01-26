package com.human.datamanger.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.continuedClass.entity.ContinuedClassRule;
import com.human.datamanger.entity.DataManger;
import com.human.utils.PageView;

public interface DataMangerService {
    /**
     * 分页查询续班规则
     */
    PageView query(PageView pageView,DataManger dm);

    Map<String, Object> delete(String deleteIds);

    DataManger selectByPrimaryKey(long id);

    Map<String, Object> edit(DataManger dm);

    int addData(DataManger dm);
    /**
     * 导入excel
     * @param request
     * @return
     */
    Map<String, Object> upexcel(HttpServletRequest request);
    
}
