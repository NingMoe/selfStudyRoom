package com.ls.spt.question.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.question.dao.LstQuestionTypeDao;
import com.ls.spt.question.entity.LstQuestionType;
import com.ls.spt.question.service.LstQuestionTypeService;

@Service
public class LstQuestionTypeServiceImpl implements LstQuestionTypeService{
    
    @Autowired
    private LstQuestionTypeDao lqtDao;
    
    @Override
    public PageView query(PageView pageView, LstQuestionType lqt) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", lqt);
        List<LstQuestionType> list = lqtDao.query(map);
        pageView.setData(list);
        return pageView;
    }

    @Override
    public LstQuestionType selectById(Integer id) {
        return lqtDao.selectByPrimaryKey(id);
    }
    
    @Override
    public int insert(LstQuestionType lqt) {
//        return lqtDao.insert(lqt);
        return lqtDao.insertSelective(lqt);
    }
    
    @Override
    public int updateByPrimaryKey(LstQuestionType lqt) {
        return lqtDao.updateByPrimaryKey(lqt);
    }
    
    @Override
    public int updateByPrimaryKeySelective(LstQuestionType lqt) {
        return lqtDao.updateByPrimaryKeySelective(lqt);
    }
    
    @Override
    public List<LstQuestionType> getAll() {
        return lqtDao.getAll();
    }
    
}
