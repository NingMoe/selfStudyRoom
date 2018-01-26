package com.human.questionnaire.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.questionnaire.dao.QuestionParamDao;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.service.QuestionParamService;
import com.human.utils.PageView;

@Service
public class QuestionParamServiceImpl implements QuestionParamService{

    @Resource
    private QuestionParamDao qpDao;
    
    @Override
    public PageView query(PageView pageView, ParamBean bean) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", bean);
        List<ParamBean> list = qpDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<ParamBean> queryParam(ParamBean bean) {
        return qpDao.queryParam(bean);
    }

    @Override
    public int saveParam(ParamBean bean) {
        return qpDao.saveParam(bean);
    }

    @Override
    public int updateParam(ParamBean bean) {
        return qpDao.editParam(bean);
    }

    @Override
    public Map<String, Object> updateStatus(String deleteIds, Integer status) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
        String[] ids=deleteIds.split(",");
        Map<String,Object> paraMap=new HashMap<String,Object>();
        paraMap.put("ids", ids);
        paraMap.put("status", status);
        qpDao.updateByIds(paraMap);
        map.put("flag", true);
        map.put("message", "操作成功");
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", "操作失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    

    @Override
    public List<ParamBean> queryFormParam(Long id) {
        List<ParamBean> list= qpDao.queryFormParam(id);
        return list;
    }
}
