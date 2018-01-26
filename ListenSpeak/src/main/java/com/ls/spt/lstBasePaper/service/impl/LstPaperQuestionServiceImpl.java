package com.ls.spt.lstBasePaper.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.dao.LstPaperQuestionDao;
import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;
import com.ls.spt.lstBasePaper.service.LstPaperQuestionService;


@Service
public class LstPaperQuestionServiceImpl implements LstPaperQuestionService {
    
    @Resource 
    LstPaperQuestionDao lbqDao;
    
    @Override
    public PageView query(LstPaperQuestion lpq, PageView pageView) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", lpq);
        List<LstPaperQuestion> list =lbqDao.query(map);
        pageView.setData(list);
        return pageView;
    }

    @Override
    public int delete(LstPaperQuestion lpq) {
        String[] codes=lpq.getQuestionCode().split(",");
        lpq.setCodes(codes);
        return lbqDao.deleteByCode(lpq);
    }

    @Override
    public LstPaperQuestion getPaperQuestionInfo(String paperId, String code, String xh) {
        Map<String, Object> map=new HashMap<>();
        map.put("paperId", Integer.valueOf(paperId));
        map.put("code", code);
        map.put("xh", Integer.valueOf(xh));
        return lbqDao.getPaperQuestionInfo(map);
    }
    
}
