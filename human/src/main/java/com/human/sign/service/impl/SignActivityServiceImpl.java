package com.human.sign.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import com.human.sign.dao.SignActivityDao;
import com.human.sign.dao.SignInfoDao;
import com.human.sign.entity.SignActivity;
import com.human.sign.entity.SignInfoDto;
import com.human.sign.service.SignActivityService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;

@Service
public class SignActivityServiceImpl implements SignActivityService {

    @Resource
    private SignActivityDao saDao;
    
    @Resource
    private SignInfoDao siDao;
    
    @Value("${humanServer}")
    private String humanServer;
    
    @Value("${signUrl}")
    private String signUrl;
    
    @Resource
    private RedisTemplateUtil redisTemplateUtil;
    
    @Override
    public PageView queryActivityByPage(PageView pageView, SignActivity info) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("userId",Common.getMyUser().getUserid());
        map.put("paging", pageView);
        map.put("t", info);
        List<SignActivity> list = saDao.query(map);
        if(CollectionUtils.isNotEmpty(list)){
            for(SignActivity sa:list) {
                List<String>classCodeList=siDao.getClassCodeList(sa.getId());
                if(CollectionUtils.isNotEmpty(classCodeList)) {
                    long conversion=0;
                    for(String classCode:classCodeList) {
                        String[] classList=classCode.split(",");
                        conversion+=classList.length;
                    }
                    sa.setConversion(conversion);
                }
            }
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> addActivity(SignActivity info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            info.setCreateUser(Common.getMyUser().getUsername());
            info.setCreateTime(new Date());
            //获取当前时间戳
            long activityTime=System.currentTimeMillis();
            info.setActivityTime(activityTime);
            //设置访问链接
            info.setCodeUrl(humanServer+signUrl+activityTime+".html");
            //判断是否勾选发送短信
            if("1".equals(info.getIsSend())){
                info.setTemDesc("${sign_name}您好，您于${sign_time}成功签到${sign_activityName}，感谢您的参与！"); 
            }
            saDao.insertSelective(info);            
            map.put("flag", true);
            map.put("message", "新增成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public SignActivity selectByPrimaryKey(Long id) {
        return saDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> editActivity(SignActivity info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            info.setUpdateUser(Common.getMyUser().getUsername());
            info.setUpdateTime(new Date());
            //判断是否勾选发送短信
            if("1".equals(info.getIsSend()) && StringUtils.isEmpty(info.getTemDesc())){
                info.setTemDesc("${sign_name}您好，您于${sign_time}成功签到${sign_activityName}，感谢您的参与！"); 
            }
            saDao.updateByPrimaryKeySelective(info);
            map.put("flag", true);
            map.put("message", "编辑成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public SignActivity selectByActivityTime(Long activityTime) {
        return saDao.selectByActivityTime(activityTime);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> selectById(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("activityId", id);
            SignActivity signActivity=saDao.selectById(id);
            String key="signInfoList"+id;
            List<SignInfoDto> list=null;
            if(redisTemplateUtil.isExist(key)){
                list=redisTemplateUtil.getObject(key, List.class);
            }else{
                list=siDao.selectInfoGroupByDept(id);
                redisTemplateUtil.setList(key, list,Long.valueOf(1440 * 60));
            }
            if(CollectionUtils.isNotEmpty(list)){
                for(SignInfoDto sid:list){
                    if("空".equals(sid.getDeptorschool())){
                        paraMap.put("deptorschool", null);
                    }else{
                        paraMap.put("deptorschool", sid.getDeptorschool()); 
                    }              
                    //统计签到人数
                    long signTotal=siDao.selectHasSignGroupByDept(paraMap);
                    sid.setSignTotal(signTotal);
                    //统计签到率
                    long total=sid.getTotal();
                    System.out.println("signTotal=="+signTotal+",total==="+total);
                    long checkRate=0L;
                    if(signTotal!=0){
                        BigDecimal bigDecimal=new BigDecimal(signTotal).divide(new BigDecimal(total),2, BigDecimal.ROUND_HALF_UP);
                        System.out.println("bigDecimal=="+bigDecimal);
                        checkRate=bigDecimal.multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP).longValue(); 
                    }                 
                    sid.setCheckRate(checkRate);
                }
            }
            //按照签到率倒叙排序
            if(CollectionUtils.isNotEmpty(list)&&list.size()>1 ){
                list.sort(new Comparator<SignInfoDto>() {
                                @Override
                                public int compare(SignInfoDto o1, SignInfoDto o2) {
                                    long checkRate1=o1.getCheckRate();                                
                                    long checkRate2=o2.getCheckRate();         
                                    return checkRate2>checkRate1?1:-1;
                                } 
                            });
            }        
            map.put("signActivity", signActivity);
            map.put("signInfoList", list); 
        }catch(Exception e){
           e.printStackTrace(); 
        }       
        return map;
    }

    

}
