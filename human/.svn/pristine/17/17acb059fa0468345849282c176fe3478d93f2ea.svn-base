package com.human.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.recruitment.dao.HrEntryBaseDao;
import com.human.recruitment.dao.HrResumeEntryhandlerDao;
import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrEntryCertif;
import com.human.recruitment.entity.HrEntryContactaddr;
import com.human.recruitment.entity.HrEntryEduexp;
import com.human.recruitment.entity.HrEntryEmergency;
import com.human.recruitment.entity.HrEntryFamily;
import com.human.recruitment.entity.HrEntryIdentification;
import com.human.recruitment.entity.HrEntryWorkexp;
import com.human.recruitment.entity.HrResumeEntryhandler;
import com.human.recruitment.service.SeekerEntryService;
import com.human.security.MyUser;
import com.human.utils.PageView;

@Service
public class SeekerEntryServiceImpl implements SeekerEntryService {
    
    @Autowired
    private HrResumeEntryhandlerDao entryhandlerDao;
    
    @Autowired
    private HrEntryBaseDao entryBaseDao;

    @Override
    public PageView getPositionPage(PageView pageView, HrResumeEntryhandler entryhandler,MyUser myUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", entryhandler);
        map.put("u", myUser);
        List<HrResumeEntryhandler> list = entryhandlerDao.selectEntryPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    
    @Override
    public HrResumeEntryhandler selectByPrimaryKey(Integer id) {
        // TODO Auto-generated method stub
        return entryhandlerDao.selectByPrimaryKey(id);
    }
    
    @Override
    public HrResumeEntryhandler selectByIdCard(String idCard) {
        // TODO Auto-generated method stub
        return entryhandlerDao.selectByIdCard(idCard).get(0);
    }
    
    @Override
    public int updateByPrimaryKeySelective(HrResumeEntryhandler entryhandler) {
        // TODO Auto-generated method stub
        return entryhandlerDao.updateByPrimaryKeySelective(entryhandler);
    }
    
    @Override
    public HrEntryBase getBaseSeeker(Integer entryHandlerId) {
        // TODO Auto-generated method stub
        return entryBaseDao.selectByEntryHandler(entryHandlerId);
    }
    
    @Override
    public HrEntryBase getComplexSeeker(Integer id) {
        // TODO Auto-generated method stub
        return entryBaseDao.selectComplexByPrimaryKey(id);
    }
    
    @Override
    @Transactional
    public void addSeekerDetail(HrEntryBase entryBase) {
        // TODO Auto-generated method stub
        entryBaseDao.insert(entryBase);
        Integer entryBaseId= entryBase.getId();
        HrEntryIdentification identification = entryBase.getIdenti();
        if(identification!=null){
            identification.setEntryBaseId(entryBaseId);
            entryBaseDao.insertIdenti(identification);
        }
        
        HrEntryContactaddr contactaddr = entryBase.getContactaddr();
        if(contactaddr!=null){
            contactaddr.setEntryBaseId(entryBaseId);
            entryBaseDao.insertContactaddr(contactaddr);
        }
        
        List<HrEntryCertif> certifs = entryBase.getCertifs();
        if(certifs!=null && certifs.size()>0){
            for(HrEntryCertif c:certifs){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertCertifs(certifs);
        }
        
        List<HrEntryEduexp> edus = entryBase.getEdus();
        if(edus!=null && edus.size()>0){
            for(HrEntryEduexp c:edus){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertEdus(edus);
        }
        
        List<HrEntryEmergency> emergencys = entryBase.getEmergencys();
        if(emergencys!=null && emergencys.size()>0){
            for(HrEntryEmergency c:emergencys){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertEmergencys(emergencys);
        }
        
        List<HrEntryFamily> familys = entryBase.getFamilys();
        if(familys!=null && familys.size()>0){
            for(HrEntryFamily c:familys){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertFamilys(familys);
        }
        
        List<HrEntryWorkexp> workexps = entryBase.getWorkexps();
        if(workexps!=null && workexps.size()>0){
            for(HrEntryWorkexp c:workexps){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertWorkexps(workexps);
        }
    }
    
    @Override
    public void editSeekerDetail(HrEntryBase entryBase) {
        // TODO Auto-generated method stub
        Integer entryBaseId = entryBase.getId(); 
        entryBaseDao.updateByPrimaryKeySelective(entryBase);
        
        entryBaseDao.deleteIdentiByBaseId(entryBaseId);
        HrEntryIdentification identification = entryBase.getIdenti();
        if(identification!=null){
            identification.setEntryBaseId(entryBaseId);
            entryBaseDao.insertIdenti(identification);
        }
        
        entryBaseDao.deleteContactaddrByBaseId(entryBaseId);
        HrEntryContactaddr contactaddr = entryBase.getContactaddr();
        if(contactaddr!=null){
            contactaddr.setEntryBaseId(entryBaseId);
            entryBaseDao.insertContactaddr(contactaddr);
        }
        
        entryBaseDao.deleteCertifsByBaseId(entryBaseId);
        List<HrEntryCertif> certifs = entryBase.getCertifs();
        if(certifs!=null && certifs.size()>0){
            for(HrEntryCertif c:certifs){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertCertifs(certifs);
        }
        
        entryBaseDao.deleteEdusByBaseId(entryBaseId);
        List<HrEntryEduexp> edus = entryBase.getEdus();
        if(edus!=null && edus.size()>0){
            for(HrEntryEduexp c:edus){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertEdus(edus);
        }
        
        entryBaseDao.deleteEmergencysByBaseId(entryBaseId);
        List<HrEntryEmergency> emergencys = entryBase.getEmergencys();
        if(emergencys!=null && emergencys.size()>0){
            for(HrEntryEmergency c:emergencys){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertEmergencys(emergencys);
        }
        
        entryBaseDao.deleteFamilysByBaseId(entryBaseId);
        List<HrEntryFamily> familys = entryBase.getFamilys();
        if(familys!=null && familys.size()>0){
            for(HrEntryFamily c:familys){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertFamilys(familys);
        }
        
        entryBaseDao.deleteWorkexpsByBaseId(entryBaseId);
        List<HrEntryWorkexp> workexps = entryBase.getWorkexps();
        if(workexps!=null && workexps.size()>0){
            for(HrEntryWorkexp c:workexps){
                c.setEntryBaseId(entryBaseId);
            }
            entryBaseDao.insertWorkexps(workexps);
        }
        
    }
    
    
    @Override
    public void sendOffer(HrResumeEntryhandler entryhandler) {
        // TODO Auto-generated method stub
        //发送入职邮件以及体检邮件
    }
}
