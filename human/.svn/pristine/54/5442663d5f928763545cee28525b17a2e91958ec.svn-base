package com.human.recruitment.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.recruitment.dao.PositionProcessDao;
import com.human.recruitment.dao.PositionProcessNodeConfigDao;
import com.human.recruitment.dao.PositionProcessScoreItemDao;
import com.human.recruitment.dao.PositionProcessUserDao;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.service.PositionProcessService;

@Service
public class PositionProcessServiceImpl implements PositionProcessService {

    @Autowired
    private PositionProcessUserDao processUserDao;
    
    @Autowired
    private PositionProcessNodeConfigDao configDao;
    
    @Autowired
    private PositionProcessDao positionProcessDao;
    
    @Autowired
    private PositionProcessScoreItemDao scoreItemDao;
    
    @Override
    public PositionProcess getPositionProcessById(Integer id) {
        // TODO Auto-generated method stub
        return positionProcessDao.selectByPrimaryKey(id);
    }
    
    @Override
    @Transactional
    public void addPositionProcess(PositionProcess positionProcess) {
        positionProcessDao.insert(positionProcess);
        if(positionProcess.getConfigList()!=null && positionProcess.getConfigList().size()>0){
            for(PositionProcessNodeConfig c:positionProcess.getConfigList()){
                c.setPositionProcessId(positionProcess.getId());
            }
            configDao.insertNodeConfigs(positionProcess.getConfigList());
        }
        if(positionProcess.getScoreItemList()!=null && positionProcess.getScoreItemList().size()>0){
            for(PositionProcessScoreItem s:positionProcess.getScoreItemList()){
                s.setPositionProcessId(positionProcess.getId());
            }
            scoreItemDao.insertScoreItems(positionProcess.getScoreItemList());
        }
    }
    
    @Override
    public void editPositionProcess(PositionProcess positionProcess) {
        positionProcessDao.updateByPrimaryKeySelective(positionProcess);
        configDao.deleteByProcessId(positionProcess.getId());
        if(positionProcess.getConfigList()!=null && positionProcess.getConfigList().size()>0){
            configDao.insertNodeConfigs(positionProcess.getConfigList());
        }
        scoreItemDao.deleteByProcessId(positionProcess.getId());
        if(positionProcess.getScoreItemList()!=null && positionProcess.getScoreItemList().size()>0){
            scoreItemDao.insertScoreItems(positionProcess.getScoreItemList());
        }
        
    }
    
    @Override
    public void disablePositionProcess(PositionProcess positionProcess) {
        //positionProcessDao.getPositionProcessByPrimaryCondition(positionProcess);
        positionProcessDao.disablePositionProcess(positionProcess);
    }
    
    @Override
    public int addProcessUser(PositionProcessUser user) {
        // TODO Auto-generated method stub
        return processUserDao.insert(user);
    }
    
    @Override
    public PositionProcess getPositionProcessByPrimaryCondition(PositionProcess positionProcess) {
        // TODO Auto-generated method stub
        return positionProcessDao.getPositionProcessByPrimaryCondition(positionProcess);
    }
    
    @Override
    public int delProcessUser(Integer id) {
        // TODO Auto-generated method stub
        return processUserDao.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<PositionProcessUser> getProcessUserByCondition(PositionProcessUser processUser) {
        // TODO Auto-generated method stub
        return processUserDao.getProcessUserByCondition(processUser);
    }
    
    @Override
    public int updatePositionNodeConfig(PositionProcessNodeConfig nodeConfig) {
        return configDao.updateByPrimaryKey(nodeConfig);
    }
    
    @Override
    public PositionProcessNodeConfig selectNodeConfigById(Integer configId) {
        // TODO Auto-generated method stub
        return configDao.selectByPrimaryKey(configId);
    }
    
    @Override
    public PositionProcessNodeConfig selectNodeConfigByProcessIdAndNodeId(PositionProcessNodeConfig nodeConfig){
        // TODO Auto-generated method stub
        return configDao.selectByProcessIdAndNodeId(nodeConfig);
    }
    
    @Override
    public List<PositionProcessScoreItem> getScoreItemsByProcessId(Integer positionProcessId) {
        // TODO Auto-generated method stub
        return scoreItemDao.getScoreItemsByProcessId(positionProcessId);
    }

    @Override
    public List<PositionProcess> getListByDeptId(String deptId) {
       
        return positionProcessDao.getListByDeptId(deptId);
    }
}
