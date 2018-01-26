package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.dao.BaseDictionaryDao;
import com.human.basic.dao.DicDataDao;
import com.human.basic.entity.BaseDictionary;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.utils.PageView;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    
    @Autowired
    BaseDictionaryDao baseDictionaryDao;
    
    @Autowired
    DicDataDao dicDataDao;
    
    @Override
    public PageView getDicItemPage(PageView pageView, BaseDictionary baseDictionary) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", baseDictionary);
        List<BaseDictionary> list = baseDictionaryDao.selectByCondition(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public BaseDictionary selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return baseDictionaryDao.selectByPrimaryKey(id);
    }
    
    @Override
    public int addBaseDic(BaseDictionary baseDictionary) {
        // TODO Auto-generated method stub
        return baseDictionaryDao.insert(baseDictionary);
    }
    
    @Override
    public int editBaseDic(BaseDictionary baseDictionary) {
        // TODO Auto-generated method stub
        return baseDictionaryDao.updateByPrimaryKeySelective(baseDictionary);
    }
    
    @Override
    public List<DicData> getDataByKey(String key) {
        // TODO Auto-generated method stub
        return dicDataDao.selectByDicCode(key);
    }
    
    @Override
    @Transactional
    public void editDicData(Integer id,String name,List<DicData> datas) {
        BaseDictionary b = baseDictionaryDao.selectByPrimaryKey(id);
        if(!b.getName().equals(name)){
            b.setName(name);
            baseDictionaryDao.updateByPrimaryKey(b);
        }
        if(datas!=null && datas.size()>0){
            dicDataDao.deleteByDicCode(b.getKey());
            for(DicData data:datas){
                data.setStatus(1);
                data.setDicCode(b.getKey());
                dicDataDao.insert(data);
            }
        }
    }

    @Override
    public int queryByDataValue(String dataValue) {
        return dicDataDao.queryByDataValue(dataValue);
    }

    @Override
    public List<DicData> selectByDicCode(String dicCode) {
        return dicDataDao.selectByDicCode(dicCode);
    }

    @Override
    public List<DicData> selectByDicCodeAndCompany(DicData record) {
        return dicDataDao.selectByDicCodeAndCompany(record);
    }
}
