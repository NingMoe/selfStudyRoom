package com.ls.spt.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.spt.basic.dao.BaseDictionaryDao;
import com.ls.spt.basic.dao.DicDataDao;
import com.ls.spt.basic.entity.BaseDictionary;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;

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
        pageView.setData(list);
        return pageView;
    }
    
    @Override
    public BaseDictionary selectByPrimaryKey(Integer id) {
        return baseDictionaryDao.selectByPrimaryKey(id);
    }
    
    @Override
    public int addBaseDic(BaseDictionary baseDictionary) {
        return baseDictionaryDao.insert(baseDictionary);
    }
    
    @Override
    public int editBaseDic(BaseDictionary baseDictionary) {
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
