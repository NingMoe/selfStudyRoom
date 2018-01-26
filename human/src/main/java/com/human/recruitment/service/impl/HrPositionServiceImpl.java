package com.human.recruitment.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.human.basic.dao.SmsTempDao;
import com.human.basic.entity.SmsRecord;
import com.human.bpm.entity.ActNode;
import com.human.recruitment.dao.HrPositionDao;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.PositionProcessDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.NoAccDegree;
import com.human.recruitment.entity.PositionAlias;
import com.human.recruitment.entity.PositionHighLight;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionJobCity;
import com.human.recruitment.entity.PositionMsUser;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionWatcher;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.HrPositionService;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.entity.ResumeBase;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;

@Service
public class HrPositionServiceImpl implements HrPositionService {
    
    @Autowired
    private HrPositionDao hrPositionDao;
    
    @Autowired
    private PositionProcessDao positionProcessDao;
    
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    @Autowired
    private ResumeBaseDao resumeBaseDao;
    
    @Autowired
    private HrResumeFlowDao flowDao;
    
    @Autowired
    private SmsTempDao smsTempDao;
    
    @Autowired
    private RecruitAcceptanceDao seekerDao;
    
    @Value("${redis.positionkeyPre}") 
    private String positionkeyPre;
    
    @Value("${redis.positionExpire}") 
    private Long positionExpire;
    
    @Override
    public PageView getPositionPage(PageView pageView, HrPosition hrPosition) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", hrPosition);
        List<HrPosition> list = hrPositionDao.selectPositionPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<HrPosition> getPositionList(HrPosition hrPosition) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("t", hrPosition);
        return hrPositionDao.selectPositionPage(map);
    }
    
    @Override
    public List<HrPosition> getValidPositionList(HrPosition hrPosition) {
        return hrPositionDao.getValidPositionList(hrPosition);
    }
    
    @Override
    @Transactional
    public void addHrPosition(HrPosition hrPosition) {
        if(hrPosition.getIsRelease()==1){
            hrPosition.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        }
        hrPositionDao.insert(hrPosition);
        List<PositionJobCity> jobCitys = hrPosition.getJobCity();
        if(jobCitys!=null && jobCitys.size()>0){
            for(PositionJobCity pjc:jobCitys){
                pjc.setPositionId(hrPosition.getId()+"");
            } 
            hrPositionDao.insertJobCitys(jobCitys);
        }
        
        List<PositionHighLight> highLights = hrPosition.getHighLight();
        if(highLights!=null && highLights.size()>0){
            for(PositionHighLight phl:highLights){
                phl.setPositionId(hrPosition.getId()+"");
            } 
            hrPositionDao.insertHighLight(highLights);
        }
        refreshPositionCache(hrPosition.getComid());
    }
    
    @Override
    @Transactional
    public void editHrPosition(HrPosition hrPosition) {
        if(hrPosition.getIsRelease()==1 && "0".equals(hrPosition.getIsHasReleaseTime())){
            hrPosition.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        }
        if(hrPosition.getIsLongEffective()==null){
            hrPosition.setIsLongEffective("0");
        }
        if(hrPosition.getIsFeedback()==null){
            hrPosition.setIsFeedback(0);
        }
        hrPositionDao.updateByPrimaryKeySelective(hrPosition);
        List<PositionJobCity> jobCitys = hrPosition.getJobCity();
        if(jobCitys!=null && jobCitys.size()>0){
            hrPositionDao.deleteJobCityByPositionId(hrPosition.getId());
            for(PositionJobCity pjc:jobCitys){
                pjc.setPositionId(hrPosition.getId()+"");
            } 
            hrPositionDao.insertJobCitys(jobCitys);
        }
        
        List<PositionHighLight> highLights = hrPosition.getHighLight();
        if(highLights!=null && highLights.size()>0){
            hrPositionDao.deleteHighLightByPositionId(hrPosition.getId());
            for(PositionHighLight phl:highLights){
                phl.setPositionId(hrPosition.getId()+"");
            } 
            hrPositionDao.insertHighLight(highLights);
        }
        refreshPositionCache(hrPosition.getComid());
    }
    
    @Override
    public HrPosition selectPostionDetailById(Integer id) {
        return hrPositionDao.selectDetailById(id);
    }
    
    @Override
    public List<PositionProcess> getProcessByPositionId(Integer positionId) {
        return positionProcessDao.getListByPositionId(positionId);
    }
    
    @Override
    public List<HrPosition> getCachePositionListByCondition(HrPosition position) {
        List<HrPosition> list = getCachePositionList(position.getComid());
        List<HrPosition> result = new ArrayList<HrPosition>();
        if(StringUtils.isNotEmpty(position.getPositionAttribute())){
            String attr = position.getPositionAttribute();
            for(HrPosition p:list){
                if(p.getPositionAttribute().equals(attr)){
                    result.add(p);
                }
            }
        }
        return result;
    }
    
    @Override
    public HrPosition getPositionSuperConfig(Integer positionId) {
        // TODO Auto-generated method stub
        HrPosition p = hrPositionDao.selectByPrimaryKey(positionId);
        if(p!=null){
            List<PositionHrUser> hrUsers = hrPositionDao.getPositionHrUsers(positionId);
            List<PositionWatcher> watchers = hrPositionDao.getPositionWatchers(positionId);
            //List<PositionAlias> aliases = hrPositionDao.getPositionAliases(positionId);
            List<PositionMsUser> msUsers = hrPositionDao.getPositionMsUsers(positionId);
            //List<NoAccDegree> nodegrees = hrPositionDao.getPositionNogrees(positionId);
            p.setHrUsers(hrUsers);
            p.setWatchers(watchers);
            //p.setPositionAlias(aliases);
            p.setMsUsers(msUsers);
           // p.setNoDegrees(nodegrees);
        }
        return p;
    }
    
    @Override
    public boolean isPositionUserExist(PositionHrUser hrUser) {
        PositionHrUser u = hrPositionDao.getHrUserByUserId(hrUser);
        if(u!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isPositionWatcherExist(PositionWatcher watcher) {
        PositionWatcher w = hrPositionDao.getWatcherByWatcherId(watcher);
        if(w!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isPositionMsUserExist(PositionMsUser msUser) {
        PositionMsUser w = hrPositionDao.getMsUserByMsId(msUser);
        if(w!=null){
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional
    public void insertPositionHrUser(PositionHrUser hrUser) {
        boolean isExist = false;
        hrPositionDao.insertPositionHrUser(hrUser);
        String roleName = hrUser.getPositionId()+HrPosition.RLQR_ROLE;
        Group g = identityService.createGroupQuery().groupId(roleName).singleResult();
        if(g==null){
            g = identityService.newGroup(roleName);
            identityService.saveGroup(g);
           
        }
        User user = identityService.createUserQuery().userId(hrUser.getUserId()).singleResult();
        if(user==null){
            user = identityService.newUser(hrUser.getUserId());
            identityService.saveUser(user);
        }
        List<User> tmp = identityService.createUserQuery().memberOfGroup(roleName).list();
        for(User u:tmp){
            if(u.getId().equals(hrUser.getUserId())){
                isExist = true;
                break;
            }
        }
        if(!isExist){
            identityService.createMembership(hrUser.getUserId(), g.getId());
        }
    }
    
    @Override
    public int insertPositionMsUser(PositionMsUser msUser) {
        return hrPositionDao.insertPositionMsUser(msUser);
    }
    
    @Override
    public int insertPositionWatcher(PositionWatcher watcher) {
        return hrPositionDao.insertPositionWatcher(watcher);
    }
    
    @Override
    public int delPositionHrUser(PositionHrUser hrUser) {
        return hrPositionDao.deleteHrUser(hrUser);
    }
    
    @Override
    public int delPositionWatcher(PositionWatcher watcher) {
        return hrPositionDao.deleteWatcher(watcher);
    }
    
    @Override
    public int delPositionMsUser(PositionMsUser msUser) {
        return hrPositionDao.deleteMsUser(msUser);
    }
    
    @Override
    @Transactional
    public void editHrPositionSuperConfig(HrPosition hrPosition) {
        hrPositionDao.updateByPrimaryKeySelective(hrPosition);
        hrPositionDao.deleteAliasByPositionId(hrPosition.getId());
        List<PositionAlias> aliases = hrPosition.getPositionAlias();
        if(aliases!=null && aliases.size()>0){
            hrPositionDao.insertPositionAlias(aliases);
        }
        
        hrPositionDao.deleteNoaccDegreesByPositionId(hrPosition.getId());
        List<NoAccDegree> degrees = hrPosition.getNoDegrees();
        if(degrees!=null && degrees.size()>0){
            hrPositionDao.insertNoaccDegrees(degrees);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<HrPosition> getCachePositionList(String comid) {
        String key = positionkeyPre+"_"+comid;
        if(redisTemplateUtil.isExist(key)){
            return (List<HrPosition>) redisTemplateUtil.getObject(key,List.class);
        }else{
           return setPositionCache(comid);
        }
    }
    
    private void refreshPositionCache(String comid){
        String key = positionkeyPre+"_"+comid;
        if(redisTemplateUtil.isExist(key)){
            redisTemplateUtil.del(key);
        }
        setPositionCache(comid);
    }
    
    private List<HrPosition> setPositionCache(String comid){
        String key = positionkeyPre+"_"+comid;
        List<HrPosition> positions = hrPositionDao.getPositionForFrontByCompany(comid);
        redisTemplateUtil.setList(key, positions, positionExpire);
        return positions;
    }
    
    @Override
    public HrPosition getPositionForFrontById(Integer id) {
        return hrPositionDao.getPositionForFrontById(id);
    }
    
    @Override
    public List<PositionMsUser> getPositionMsUsers(Integer positionId) {
        return hrPositionDao.getPositionMsUsers(positionId);
    }
    
    @Override
    public Integer getFrontResumeCntByOpenId(String openId) {
        return resumeBaseDao.getFrontResumeCntByOpenId(openId);
    }
    
    @Override
    public Integer getFrontMsgCntByOpenId(String openId) {
        return null;
    }
    
    @Override
    public ResumeSeeker getResumeSeekerByOpenId(String openId) {
        return seekerDao.selectByOpenId(openId);
    }
    
    @Override
    public List<ResumeBase> getFrontResumeByOpenId(String openId) {
        return resumeBaseDao.getFrontResumeByOpenId(openId);
    }
    
    @Override
    public List<ActNode> getNodesByResumeId(Integer resumeId) {
        HrResumeFlow flow = flowDao.selectNewFlowResumeId(resumeId);
        if(flow==null){
            return null;
        }
        if(flow.getCurrentNode().equals(HrPosition.ENDNODE)||flow.getCurrentNode().equals(HrPosition.RENLINODE)){
            //从ACT_NODE_COMMENT中去获取
            List<ActNode> nodes = flowDao.getNodesByResumeId(resumeId);
            for(ActNode n:nodes){
                n.setStatus("1");
            }
            return nodes;
        }else if(flow.getCurrentNode().equals(HrPosition.FIRSTNODE)){
            //一面进行中
            ActNode n = flowDao.getFirstNodeByResumeNode(resumeId, HrPosition.FIRSTNODE);
            n.setStatus("2");
            List<ActNode> nodes = new ArrayList<ActNode>();
            nodes.add(n);
            return nodes;
        }else{
            return getNodeListByCurr(resumeId,flow.getCurrentNode());
        }
    }
    
    @Override
    public List<SmsRecord> getSmsByResumeId(Integer resumeId) {
        return smsTempDao.querySmsByResumeId(resumeId);
    }
    
    private List<ActNode> getNodeListByCurr(Integer resumeId,String currNodeId){
        if("ermianTask".equals(currNodeId)){
            List<ActNode> nodes = new ArrayList<ActNode>();
            ActNode n1 = flowDao.getFirstNodeByResumeNode(resumeId, HrPosition.FIRSTNODE);
            n1.setStatus("1");
            ActNode n2 = flowDao.getFirstNodeByResumeNode(resumeId, "ermianTask");
            n2.setStatus("2");
            nodes.add(n1);
            nodes.add(n2);
            return nodes;
        }
        
        if("sanmianTask".equals(currNodeId)){
            List<ActNode> nodes = new ArrayList<ActNode>();
            ActNode n1 = flowDao.getFirstNodeByResumeNode(resumeId, HrPosition.FIRSTNODE);
            n1.setStatus("1");
            ActNode n2 = flowDao.getFirstNodeByResumeNode(resumeId, "ermianTask");
            n2.setStatus("1");
            ActNode n3 = flowDao.getFirstNodeByResumeNode(resumeId, "sanmianTask");
            n2.setStatus("2");
            nodes.add(n1);
            nodes.add(n2);
            nodes.add(n3);
            return nodes;
        }
        
        if("simianTask".equals(currNodeId)){
            List<ActNode> nodes = new ArrayList<ActNode>();
            ActNode n1 = flowDao.getFirstNodeByResumeNode(resumeId, HrPosition.FIRSTNODE);
            n1.setStatus("1");
            ActNode n2 = flowDao.getFirstNodeByResumeNode(resumeId, "ermianTask");
            n2.setStatus("1");
            ActNode n3 = flowDao.getFirstNodeByResumeNode(resumeId, "sanmianTask");
            n2.setStatus("1");
            ActNode n4 = flowDao.getFirstNodeByResumeNode(resumeId, "simianTask");
            n2.setStatus("2");
            nodes.add(n1);
            nodes.add(n2);
            nodes.add(n3);
            nodes.add(n4);
            return nodes;
        }
        
        if("wumianTask".equals(currNodeId)){
            List<ActNode> nodes = new ArrayList<ActNode>();
            ActNode n1 = flowDao.getFirstNodeByResumeNode(resumeId, HrPosition.FIRSTNODE);
            n1.setStatus("1");
            ActNode n2 = flowDao.getFirstNodeByResumeNode(resumeId, "ermianTask");
            n2.setStatus("1");
            ActNode n3 = flowDao.getFirstNodeByResumeNode(resumeId, "sanmianTask");
            n2.setStatus("1");
            ActNode n4 = flowDao.getFirstNodeByResumeNode(resumeId, "simianTask");
            n2.setStatus("1");
            ActNode n5 = flowDao.getFirstNodeByResumeNode(resumeId, "wumianTask");
            n2.setStatus("1");
            nodes.add(n1);
            nodes.add(n2);
            nodes.add(n3);
            nodes.add(n4);
            nodes.add(n5);
            return nodes;
        }
        return null;
    }
}
