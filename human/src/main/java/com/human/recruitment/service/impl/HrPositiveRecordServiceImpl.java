package com.human.recruitment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.recruitment.dao.HrPositiveRecordDao;
import com.human.recruitment.dao.HrResumeEntryhandlerDao;
import com.human.recruitment.entity.HrInterviewRecord;
import com.human.recruitment.entity.HrPositiveRecord;
import com.human.recruitment.service.HrPositiveRecordService;
import com.human.utils.PageView;

@Service
public class HrPositiveRecordServiceImpl implements HrPositiveRecordService {
    
    @Autowired
    private HrPositiveRecordDao recordDao; 
    
    @Autowired
    private HrResumeEntryhandlerDao entryhandlerDao;
    
    

    @Override
    @Transactional
    public void initPositiveRecord() {
        /**
         * 将需要转正的数据   拉到SMARTWORK
         * 
         */
        recordDao.insertPositiveRecords();
        
        List<HrPositiveRecord> records = recordDao.selectInitRecords();
        if(records!=null && records.size()>0){
            List<String> idCards = new ArrayList<String>();
            for(HrPositiveRecord r:records){
                idCards.add(r.getIdCard());
            }
            entryhandlerDao.updateStatusToPisitive(idCards);
        }
    }
    
    @Override
    public PageView getPositiveRecordPage(PageView pageView, HrPositiveRecord positiveRecord) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("record", positiveRecord);
        List<HrPositiveRecord> list = recordDao.selectPositiveRecordPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public void updateRecordList(List<HrPositiveRecord> records) {
       if(records!=null && records.size()>0){
           for(HrPositiveRecord p : records){
               recordDao.updateByPrimaryKeySelective(p);
           }
       }
    }
    
    @Override
    public HrPositiveRecord selectByPrimaryKey(Integer id) {
        return recordDao.selectByPrimaryKey(id);
    }
    
    @Override
    public HrInterviewRecord getInterviewRecord(Integer positiveId) {
        return recordDao.getInterviewRecord(positiveId);
    }
    
    @Override
    @Transactional
    public void updateInterviewRecord(HrInterviewRecord record) {
        HrPositiveRecord positiveRecord = new HrPositiveRecord();
        positiveRecord.setId(record.getPositiveId());
        positiveRecord.setMsStatus(2);
        recordDao.updateByPrimaryKeySelective(positiveRecord);
        recordDao.updateInterviewRecord(record);
    }
}
