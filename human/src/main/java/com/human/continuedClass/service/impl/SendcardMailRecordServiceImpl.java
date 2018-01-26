package com.human.continuedClass.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.human.continuedClass.dao.SendcardMailRecordDao;
import com.human.continuedClass.entity.SendcardMailRecord;
import com.human.continuedClass.service.SendcardMailRecordService;
import com.human.utils.PageView;
@Service
public class SendcardMailRecordServiceImpl implements SendcardMailRecordService {
    
    @Resource
    private SendcardMailRecordDao smrDao; 
    
    @Override
    public PageView query(PageView pageView, SendcardMailRecord smr) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", smr);
        List<SendcardMailRecord> list = smrDao.queryClassCardMail(map);
        pageView.setRecords(list);
        return pageView;
    }

}
