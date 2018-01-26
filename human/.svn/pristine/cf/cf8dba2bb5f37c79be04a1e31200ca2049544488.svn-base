package com.human.jw.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.basic.entity.XdfClassInfo;
import com.human.datamanger.entity.DataManger;

@Repository
public interface JwXdfClassInfoDao {
    int deleteByPrimaryKey(String sClassCode);

    int insert(XdfClassInfo record);

    int insertSelective(XdfClassInfo record);

    XdfClassInfo selectByPrimaryKey(String sClassCode);

    int updateByPrimaryKeySelective(XdfClassInfo record);

    int updateByPrimaryKey(XdfClassInfo record);

    List<XdfClassInfo> query(Map<Object, Object> map);

    void updateForMap(Map<String, Object> xdf);

    void insertForClass(String sClassCode);

    Map<String, Object> selectByprimaryForRm(String sClassCode);

    List<XdfClassInfo> queryTotalNum();

    int updateNCurrentNum(List<XdfClassInfo> list);

}