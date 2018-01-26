package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.basic.dao.AreaInfoDao;
import com.human.basic.dao.CollegeDao;
import com.human.basic.entity.College;
import com.human.basic.service.CollegeService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.ReadCollegeUtil;
@Service
public class CollegeServiceImpl implements CollegeService{
    
    @Value("${collegeUrl}")
    private String collegeUrl; 
    
    @Resource
    private CollegeDao collegeDao;
    
    @Resource
    private AreaInfoDao areaInfoDao;
    

    @Override
    public PageView query(PageView pageView, College college) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", college);
        List<College> list = collegeDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public int insertSelective(College college) {
        
        return collegeDao.insertSelective(college);
    }


    @Override
    public College selectByPrimaryKey(Long id) {
        return collegeDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(College record) {
        return collegeDao.updateByPrimaryKeySelective(record);
    }


    @Override
    public Map<String, Object> updateStatus(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] userIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", userIds);
            paraMap.put("updateUser", Common.getAuthenticatedUsername());
            paraMap.put("updateTime", TimeUtil.getCurrentTimestamp());
            collegeDao.updateByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public void refreshCollege(){
        List<College> list=ReadCollegeUtil.dealHtmlResumeByResource("");
        for(College c:list){
            String provinceId=areaInfoDao.getAreaByAreaName(c.getProvinceName()).getId().toString() ;
            c.setProvinceId(provinceId);
            c.setCreateUser(Common.getAuthenticatedUsername());
            collegeDao.insertSelective(c);
        }        
    }     
}
