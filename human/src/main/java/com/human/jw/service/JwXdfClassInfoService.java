package com.human.jw.service;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.DicData;
import com.human.basic.entity.XdfClassInfo;
import com.human.utils.PageView;
public interface JwXdfClassInfoService {

    PageView query(PageView pageView, XdfClassInfo xci,List<DicData> xq);

    XdfClassInfo selectByprimary(String sClassCode);

    void updateForMap(Map<String, Object> xdf);

    void insertForClass(String sClassCode);

    Map<String, Object> selectByprimaryForRm(String sClassCode);

    Map<String, Object> initClassCode(String sClassCode);

    List<XdfClassInfo> queryTotalNum();

    int updateNCurrentNum(List<XdfClassInfo> list);

}
