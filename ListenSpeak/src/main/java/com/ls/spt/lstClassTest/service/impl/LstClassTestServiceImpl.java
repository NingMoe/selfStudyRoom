package com.ls.spt.lstClassTest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClassTest.dao.LstClassTestDao;
import com.ls.spt.lstClassTest.entity.LstClassTest;
import com.ls.spt.lstClassTest.service.LstClassTestService;


@Service
public class LstClassTestServiceImpl implements LstClassTestService {
    
    @Resource
    LstClassTestDao lctDao;
    
    @Override
    public PageView query(PageView pageView, LstClassTest lct) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("t", lct);
        map.put("paging", pageView);
        List<Map<String, Object>> list=lctDao.query(map);
        pageView.setData(list);
        return pageView;
    }

    @Override
    public int insert(LstClassTest lct) {
        // TODO Auto-generated method stub
        return lctDao.insertSelective(lct);
    }

    @Override
    public LstClassTest selectPrimaryKey(LstClassTest lct) {
        // TODO Auto-generated method stub
        return lctDao.selectByPrimaryKey(lct.getId());
    }

    @Override
    public int update(LstClassTest lct) {
        // TODO Auto-generated method stub
        return lctDao.updateByPrimaryKeySelective(lct);
    }

    @Override
    public int delete(String ids) {
        String[] deleteIds=ids.split(",");
        Map<String, Object> map=new HashMap<>();
        map.put("ids", deleteIds);
        return lctDao.bathDelete(map);
    }

    @Override
    public int insertToTestCl(LstClassTest lct) {
        String[] classCodes=lct.getClassCodes().split(",");
        Map<String, Object> map=new HashMap<>();
        int result=0;
        map.put("testId", lct.getId());
        for (String classCode : classCodes) {
            map.put("classCode", classCode);
            result= lctDao.insertToTestCl(map);
        }
        // TODO Auto-generated method stub
        return result;
    }

    @Override
    public List<Map<String, Object>> getTestInfo(Integer id) {
        // TODO Auto-generated method stub
        return lctDao.getTestInfo(id);
    }

    @Override
    public int deleteToTestCl(LstClassTest lct) {
        // TODO Auto-generated method stub
        return lctDao.deleteToTestCl(lct);
    }

    @Override
    public LstClassTest selectPaperInfo(Integer id) {
        // TODO Auto-generated method stub
        return lctDao.selectByPrimaryKey(id);
    }

    @Override
    public int deletePaper(LstClassTest lct) {
        // TODO Auto-generated method stub
        return lctDao.updateByPrimaryKeySelective(lct);
    }
    
    
}
