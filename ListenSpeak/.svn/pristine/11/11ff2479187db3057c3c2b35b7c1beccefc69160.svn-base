package com.ls.spt.lstBasePaper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.dao.LstBasePaperDao;
import com.ls.spt.lstBasePaper.entity.LstBasePaper;
import com.ls.spt.lstBasePaper.entity.LstQuestion;
import com.ls.spt.lstBasePaper.service.LstBasePaperService;


@Service
public class LstBasePaperServiceImpl implements LstBasePaperService {
    
    private final  Logger logger = LogManager.getLogger(LstBasePaperServiceImpl.class);
    
    @Resource LstBasePaperDao lbpDao;

    @Override
    public PageView query(PageView pageView, LstBasePaper lbp) {
        logger.info("------------分页查询开始---------------");
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", lbp);
        List<LstBasePaper> list=new ArrayList<LstBasePaper>();
        if(lbp.getIsConfig()==null){
            list =lbpDao.query(map);
        }else{
            list =lbpDao.queryForTestCl(map);
        }
        for (LstBasePaper l : list) {
            int useNum=lbpDao.getUseNum(l);
            l.setUseTimes(useNum);
            int totalNum=lbpDao.getTotalNum(l);
            l.setTestNumber(totalNum);
        }
        pageView.setData(list);
        return pageView;
    }

    @Override
    public int delete(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] idArr = ids.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", idArr);
            lbpDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }

    @Override
    public LstBasePaper getLstBasePaperInfo(String id) {
        // TODO Auto-generated method stub
        return lbpDao.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public int insert(LstBasePaper lst) {
        
        return lbpDao.insertSelective(lst);
    }

    @Override
    public List<Map<String, Object>> getQuestionType() {
        // TODO Auto-generated method stub
        return lbpDao.getQuestionType();
    }

    @Override
    public LstBasePaper getPaperInfo(LstBasePaper lst) {
        // TODO Auto-generated method stub
        return lbpDao.selectByPrimaryKey(lst.getId());
    }

    @Override
    public int update(String paperId,String status) {
        LstBasePaper lst=new LstBasePaper();
        lst.setId(Integer.valueOf(paperId));
        lst.setStatus(Integer.valueOf(status));
        // TODO Auto-generated method stub
        return lbpDao.updateByPrimaryKeySelective(lst);
    }

    @Override
    public int insertQuestionType(String num, String typeId, String paperId) {
        Map<String,Object> map=new HashMap<>();
        map.put("num", Integer.valueOf(num));
        map.put("typeId", Integer.valueOf(typeId));
        map.put("paperId", Integer.valueOf(paperId));
        return lbpDao.insertQuestionType(map);
    }

    @Override
    public List<Map<String, Object>> getTypeAndNum(Integer id) {
        // TODO Auto-generated method stub
        return lbpDao.getTypeAndNum(id);
    }

    @Override
    public int update(int status) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Map<String, Object>> getQuestionTypeNum(LstBasePaper lst) {
        // TODO Auto-generated method stub
        return lbpDao.getQuestionTypeNum(lst);
    }

    @Override
    public int updatePaperInfo(LstBasePaper lst) {
        // TODO Auto-generated method stub
        return lbpDao.updateByPrimaryKeySelective(lst);
    }

    @Override
    public int updateQuestionType(String num, String typeId, String paperId) {
        Map<String, Object> map=new HashMap<>();
        map.put("num", Integer.valueOf(num));
        map.put("typeId", Integer.valueOf(typeId));
        map.put("paperId", Integer.valueOf(paperId));
        return lbpDao.updateQuestionType(map);
    }

    @Override
    public int updateXh(String paperId, String code,String xh) {
        Map<String, Object> map=new HashMap<>();
        map.put("paperId", Integer.valueOf(paperId));
        map.put("code", code);
        map.put("xh", Integer.valueOf(xh));
        // TODO Auto-generated method stub
        return lbpDao.updateXh(map);
    }

    @Override
    public LstBasePaper selectPaperInfo(Integer id) {
        // TODO Auto-generated method stub
        return lbpDao.selectByPrimaryKey(id);
    }

    @Override
    public int getTotalNum(Integer paperId) {
       LstBasePaper lbp=new LstBasePaper();
       lbp.setId(paperId);
        return lbpDao.getTotalNum(lbp);
    }

    @Override
    public int getquestionNum(Integer paperId) {
        // TODO Auto-generated method stub
        return lbpDao.getquestionNum(paperId);
    }

}
