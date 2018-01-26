package com.ls.spt.basic.service;

import java.util.List;

import com.ls.spt.basic.entity.BaseDictionary;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;

public interface DictionaryService {
    /**
     * 分页查询
     * @param pageView
     * @param baseDictionary
     * @return
     */
    public PageView getDicItemPage(PageView pageView, BaseDictionary baseDictionary);
    
    public BaseDictionary selectByPrimaryKey(Integer id);
    
    public List<DicData> getDataByKey(String key);
    
    public int addBaseDic(BaseDictionary baseDictionary);
    
    public int editBaseDic(BaseDictionary baseDictionary);
    
    public void editDicData(Integer id,String name,List<DicData> datas);
    
    /**
     * 根据字典内容模糊查询
     * @param dicCode
     * @return
     */
    int queryByDataValue(String dataValue);
    
    /**
     * 根据编码获取数据
     * @param dicCode
     * @return
     */
    List<DicData> selectByDicCode(String dicCode);
    
    /**
     * 根据字典编码及公司ID查询
     * @param record
     * @return
     */
    List<DicData> selectByDicCodeAndCompany(DicData record);
}
