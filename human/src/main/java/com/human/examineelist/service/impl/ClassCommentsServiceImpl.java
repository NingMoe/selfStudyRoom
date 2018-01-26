package com.human.examineelist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.examineelist.dao.ClassCommentsDao;
import com.human.examineelist.entity.ClassComments;
import com.human.examineelist.service.ClassCommentsService;


@Service
public class ClassCommentsServiceImpl implements ClassCommentsService {
    private final  Logger logger = LogManager.getLogger(ClassCommentsServiceImpl.class);
    
    @Resource
    private ClassCommentsDao ccDao;

    @Override
    public int insert(ClassComments cc) {
        return ccDao.insert(cc);
    }

    @Override
    public ClassComments queryforcode(String code) {
        // TODO Auto-generated method stub
        return ccDao.queryforcode(code);
    }

    @Override
    public int update(ClassComments cc) {
        // TODO Auto-generated method stub
        return ccDao.updateByPrimaryKey(cc);
    }
    @Override
    public Map<String, Object> queryforcode2(String code) {
        // TODO Auto-generated method stub
        return ccDao.queryforcode2(code);
    }



}
