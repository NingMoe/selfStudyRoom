package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.basic.dao.XdfClassInfoDao;
import com.human.basic.dao.XdfClassOrderInfoDao;
import com.human.basic.entity.ClassBizBatch;
import com.human.basic.entity.ClassStudentInfo;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.XdfClassInfoService;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class XdfClassInfoServiceImpl implements XdfClassInfoService{
    
    @Autowired
    private XdfClassInfoDao xdfClassInfoDao;
    
    @Autowired
    private XdfClassOrderInfoDao orderDao;
    
    @Override
    public Integer syncClassInfo(XdfClassInfo xci) {
        XdfClassInfo x = xdfClassInfoDao.selectByPrimaryKey(xci.getsClassCode());
        if(x==null){
            xdfClassInfoDao.insertSelective(xci);
        }else{
            xdfClassInfoDao.updateByPrimaryKeySelective(xci);
        }
        return null;
    }

    @Override
    public PageView query(PageView pageView, XdfClassInfo cf) {
        Map<Object, Object> map = new HashMap<Object, Object>();        
        map.put("paging", pageView);
        map.put("t", cf);
        map.put("companyId", Common.getMyUser().getCompanyId());
        List<XdfClassInfo> list = xdfClassInfoDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    @Transactional
    public void insertXdfClassOrderInfo(ClassBizBatch order) {
        orderDao.insert(order);
        Integer orderInfoId = order.getId();
        List<ClassStudentInfo> orderBatchItemDtos = order.getOrderBatchItemDtos();
        for(ClassStudentInfo i:orderBatchItemDtos){
            i.setOrderInfoId(orderInfoId);
        }
        orderDao.insertOrderItems(orderBatchItemDtos);
    }

    @Override
    public List<XdfClassInfo> queryClassByRule(JzbGradeSubjectClass record) {       
        return xdfClassInfoDao.queryClassByRule(record);
    }
    
    @Override
    public List<XdfClassInfo> queryClassByCondition(XdfClassInfo xci) {
        return xdfClassInfoDao.queryClassByCondition(xci);
    }
}
