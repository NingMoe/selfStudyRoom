package com.ls.spt.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ls.spt.manager.dao.OpLogDao;
import com.ls.spt.manager.entity.OplogEntity;
import com.ls.spt.manager.service.OpLogService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.PageView;


@Service
public class OpLogServiceImpl  implements OpLogService{
    
    private final  Logger logger = LogManager.getLogger(OpLogServiceImpl.class);
    
    @Resource
    private OpLogDao oplogDao;
    
    @Resource
    private HttpServletRequest request;
    
    @Override
    public PageView query(OplogEntity oplog, PageView pageView) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", oplog);
        List<OplogEntity> list =oplogDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void insertOpLog(Integer opType, String opDesc) {
      try{
          OplogEntity opLog=new OplogEntity();
          opLog.setOpUser(Common.getAuthenticatedUsername());
          opLog.setOpType(opType);
          opLog.setOpDesc(opDesc);
          opLog.setOpIp(Common.toIpAddr(request));
          oplogDao.insert(opLog);
      }catch(Exception e){
          logger.error("========================保存操作日志出错，错误信息如下:========================\n",e);
      }
    }
}
