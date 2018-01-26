package com.human.activity.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONObject;
import com.human.activity.dao.ActivityInfoDao;
import com.human.activity.dao.BuyerInfoDao;
import com.human.activity.dao.ProductDao;
import com.human.activity.entity.ActivityInfo;
import com.human.activity.entity.ActivityInfoDto;
import com.human.activity.entity.PayInfoDto;
import com.human.activity.entity.Product;
import com.human.activity.service.ActivityInfoService;
import com.human.activity.service.BuyerInfoService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;


@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {
    
    @Resource
    private ActivityInfoDao aIDao;
    
    @Resource  
    private ProductDao  productDao ;
    
    @Resource  
    private BuyerInfoDao  buyerInfoDao ;
    
    @Resource  
    private BuyerInfoService buyerInfoService;
    
    @Value("${humanServer}")
    private String humanServer;
    
    @Value("${activityUrl}")
    private String activityUrl;
    
    @Override
    public PageView queryActivityByPage(PageView pageView, ActivityInfo info) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", info);
        List<ActivityInfoDto> list = aIDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> addActivity(String jstr, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ActivityInfo aInfo=JSONObject.parseObject(jstr, ActivityInfo.class);
            aInfo.setCreateUser(Common.getMyUser().getUsername());
            aInfo.setCreateTime(new Date());            
            aIDao.insertSelective(aInfo);
            //设置访问链接
            aInfo.setCodeUrl(humanServer+activityUrl+"index.html?activityId="+aInfo.getId());
            aIDao.updateByPrimaryKeySelective(aInfo);
            if(CollectionUtils.isNotEmpty(aInfo.getProductList())){
                for(com.human.activity.entity.ActivityInfo.Product product:aInfo.getProductList()){
                    Product pro=new Product();
                    pro.setName(product.getName());
                    pro.setPrice(product.getPrice());
                    pro.setTotal(product.getTotal());
                    pro.setActivtiyId(aInfo.getId());
                    productDao.insertSelective(pro);                   
                }
            } 
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
    public ActivityInfo selectByPrimaryKey(Long id) {      
        return aIDao.selectByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> editActivity(String jstr, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ActivityInfo aInfo=JSONObject.parseObject(jstr, ActivityInfo.class);
            aInfo.setUpdateUser(Common.getMyUser().getUsername());
            aInfo.setUpdateTime(new Date());
            aIDao.updateByPrimaryKeySelective(aInfo);
            long activtiyId=aInfo.getId();
            if(CollectionUtils.isNotEmpty(aInfo.getProductList())){
                for(com.human.activity.entity.ActivityInfo.Product product:aInfo.getProductList()){                   
                    Product pro=new Product();
                    pro.setName(product.getName());
                    pro.setPrice(product.getPrice());
                    pro.setTotal(product.getTotal());
                    pro.setActivtiyId(activtiyId);
                    if(product.getId()!=null){
                        pro.setId(product.getId());
                        productDao.updateByPrimaryKeySelective(pro);
                    }else{
                        productDao.insertSelective(pro); 
                    }                                      
                }
            } 
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
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        String[] idarray = ids.split(",");
        list=Arrays.asList(idarray);
        map.put("s", list);      
        List<Map<String,Object>> maplist =aIDao.exportSelect(map);
        ExcelUtil<ActivityInfoDto> ex=new ExcelUtil<ActivityInfoDto>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp")+"/";
        System.out.println("导出路径===="+path);
        try {
            ex.writeExcel(path+"exportActivityInfo.xlsx", ActivityInfoDto.class, maplist, response, "活动列表信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();        
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }


    @Override
    public Map<String, Object> exportSelectPayInfo(Long activityId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        ActivityInfo  activityInfo =selectByPrimaryKey(activityId);
        String activityName="";
        if(activityInfo!=null){
            activityName=activityInfo.getActivityName();
        }       
        List<Map<String,Object>> maplist =buyerInfoDao.exportSelectPayInfo(activityId);
        for(Map<String,Object> map1:maplist){
            String name=(String) map1.get("name");
            String telephone=(String) map1.get("telephone");
            System.out.println("姓名:"+name+",手机号:"+telephone);
            String studentCode=buyerInfoService.getStudentCodeByPhone(name,telephone);
            map1.put("studentCode", studentCode);
        }
        ExcelUtil<PayInfoDto> ex=new ExcelUtil<PayInfoDto>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp")+"/";
        System.out.println("导出路径===="+path);
        try {
            ex.writeExcel(path+"exportPayInfo.xlsx", PayInfoDto.class, maplist, response, activityName+"支付明细", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();        
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> closeAccount(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] activityIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", activityIds);
            paraMap.put("updateUser", Common.getAuthenticatedUsername());
            aIDao.updateAccountStatusByIds(paraMap);
            map.put("flag", true);
            map.put("message", "封账成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "封账失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    

}
