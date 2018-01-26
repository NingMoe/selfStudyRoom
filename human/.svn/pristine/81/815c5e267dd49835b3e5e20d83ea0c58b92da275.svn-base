package com.human.basic.service;

import com.human.basic.entity.XdfClassInfo;
import com.human.jzbTest.entity.JzbGradeSubjectClass;

import java.util.List;

import com.human.basic.entity.ClassBizBatch;
import com.human.utils.PageView;
public interface XdfClassInfoService {
    /**
     * 新增或者更新
     * @param xci
     * @return
     */
    Integer syncClassInfo(XdfClassInfo xci);
    
    /**
     * 分页查询
     * @param xci
     * @return
     */
    PageView query(PageView pageView,XdfClassInfo cf);
    
    /**
     * 插入消息中的订单信息
     * @param order
     * @return
     */
    void insertXdfClassOrderInfo(ClassBizBatch order);
    
    /**
     * 通过推荐课程规则查询班级
     * @param record
     * @return
     */
    List<XdfClassInfo> queryClassByRule(JzbGradeSubjectClass record);
    
    /**
     * 通过推荐课程规则查询班级
     * @param record
     * @return
     */
    List<XdfClassInfo> queryClassByCondition(XdfClassInfo xci);
}
