package com.ls.spt.manager.service;

import com.ls.spt.manager.entity.OplogEntity;
import com.ls.spt.utils.PageView;

public interface OpLogService {

    PageView query(OplogEntity oplog, PageView pageView);
    
    /**
     * 保存操作日志
     * @param opType 操作类型
     * @param opDesc 操作说明
     */
    void insertOpLog(Integer opType,String opDesc);

}
