package com.human.continuation.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.basic.dao.DicDataDao;
import com.human.basic.entity.DicData;
import com.human.continuation.dao.TeacherContinuationConfigDao;
import com.human.continuation.entity.SchoolSbq;
import com.human.continuation.entity.TeacherContinuationConfig;
import com.human.continuation.service.TeacherContinuationConfigService;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.entity.HrCompany;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;

@Service
public class TeacherContinuationConfigServiceImpl implements TeacherContinuationConfigService {
    
    @Resource
    private TeacherContinuationConfigDao teacherContinuationConfigDao;
    
    @Resource
    private HrCompanyDao hrCompanyDao;
    
    @Resource
    private DicDataDao dicDataDao;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    @Value("${rb.client_id}") 
    private String client_id;
    
    @Value("${rb.client_secret}") 
    private String client_secret;
    
    @Value("${rb.username}") 
    private String username;
    
    @Value("${rb.password}") 
    private String password;
    
    @Value("${rb.tokenUrl}") 
    private String tokenUrl;
    
    @Value("${rb.queryUrl}") 
    private String queryUrl;
    
    @Value("${rb.addUrl}") 
    private String addUrl;
    
    @Value("${rb.delUrl}") 
    private String delUrl;
    
    /**
     * 分页获取配置信息
     * @param pageView
     * @param teacherContinuationConfig
     * @return
     */
    public PageView query(PageView pageView, TeacherContinuationConfig teacherContinuationConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        
        try {
            MyUser myUser = Common.getMyUser();
            teacherContinuationConfig.setSchool_id(myUser.getCompanyId());
            map.put("paging", pageView); 
            map.put("t", teacherContinuationConfig);
            
            List<TeacherContinuationConfig> list = teacherContinuationConfigDao.query(map);
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }

    /**
     * 新增配置信息
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationConfig teacherContinuationConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationConfig == null){
            map.put("flag", false);
            map.put("message", "请选择管理部门");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getManager_dept_code())){
            map.put("flag", false);
            map.put("message", "请选择管理部门");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getOld_fiscal_year())){
            map.put("flag", false);
            map.put("message", "请填写原班财年");
            return map;
        }
        
        if(teacherContinuationConfig.getOld_class_quarter() == null){
            map.put("flag", false);
            map.put("message", "请填写原班季度");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getNew_fiscal_year())){
            map.put("flag", false);
            map.put("message", "请填写续班财年");
            return map;
        }
        
        if(teacherContinuationConfig.getNew_class_quarter() == null){
            map.put("flag", false);
            map.put("message", "请填写续班季度");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getSbq_config_name())){
            map.put("flag", false);
            map.put("message", "请填写升班期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsWindowPeriodName())){
            map.put("flag", false);
            map.put("message", "请选择窗口期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsWindowPeriodId())){
            map.put("flag", false);
            map.put("message", "请选择窗口期id");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsStageName())){
            map.put("flag", false);
            map.put("message", "请选择续班期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsStageId())){
            map.put("flag", false);
            map.put("message", "请选择续班期id");
            return map;
        }
        
        try {
            MyUser myUser = Common.getMyUser();
            teacherContinuationConfig.setSchool_id(myUser.getCompanyId());
            teacherContinuationConfigDao.insertSelective(teacherContinuationConfig);
            map.put("flag", true);
            map.put("message", "新增配置成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增配置异常。");
        }
        
        return map;
    }

    /**
     * 修改配置信息
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> update(TeacherContinuationConfig teacherContinuationConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationConfig == null){
            map.put("flag", false);
            map.put("message", "请选择管理部门");
            return map;
        }
        
        if(teacherContinuationConfig.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要求改的配置");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getManager_dept_code())){
            map.put("flag", false);
            map.put("message", "请选择管理部门");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getOld_fiscal_year())){
            map.put("flag", false);
            map.put("message", "请填写原班财年");
            return map;
        }
        
        if(teacherContinuationConfig.getOld_class_quarter() == null){
            map.put("flag", false);
            map.put("message", "请填写原班季度");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getNew_fiscal_year())){
            map.put("flag", false);
            map.put("message", "请填写续班财年");
            return map;
        }
        
        if(teacherContinuationConfig.getNew_class_quarter() == null){
            map.put("flag", false);
            map.put("message", "请填写续班季度");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getSbq_config_name())){
            map.put("flag", false);
            map.put("message", "请填写升班期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsWindowPeriodName())){
            map.put("flag", false);
            map.put("message", "请选择窗口期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsWindowPeriodId())){
            map.put("flag", false);
            map.put("message", "请选择窗口期id");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsStageName())){
            map.put("flag", false);
            map.put("message", "请选择续班期名称");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationConfig.getsStageId())){
            map.put("flag", false);
            map.put("message", "请选择续班期id");
            return map;
        }
        
        try {
            teacherContinuationConfigDao.updateByPrimaryKeySelective(teacherContinuationConfig);
            map.put("flag", true);
            map.put("message", "修改配置成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改配置异常。");
        }
        
        return map;
    }

    /**
     * 查询关系配置
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> select(TeacherContinuationConfig teacherContinuationConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationConfig.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的配置");
            return map;
        }
        try {
            TeacherContinuationConfig teacherContinuationConfig1 = teacherContinuationConfigDao.selectByPrimaryKey(teacherContinuationConfig.getId());
            if(teacherContinuationConfig1 != null){
                map.put("flag", true);
                map.put("message", "获取配置成功");
                map.put("coutinuationConfig", teacherContinuationConfig1);
            }else{
                map.put("flag", false);
                map.put("message", "获取配置为空");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取配置异常。");
        }
        return map;
    }

    /**
     * 删除关系配置
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationConfig teacherContinuationConfig) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationConfig.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的配置");
            return map;
        }
        try {
            teacherContinuationConfigDao.deleteByPrimaryKey(teacherContinuationConfig.getId());
            map.put("flag", true);
            map.put("message", "删除配置成功");
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除配置异常。");
        }
        return map;
    }

    /**
     * 获取数据词典中管理部门
     * @return
     */
    public Map<String, Object> selectManagerDept() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DicData> list = dicDataDao.selectByDicCode("managerDept");
            map.put("flag", true);
            map.put("message", "成功");
            map.put("list", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            map.put("message", "获取管理部门异常："+e);
        }
        return map;
    }
    
    /**
     * 获取数据词典中管理部门
     * @return
     */
    public Map<String, Object> selectFiscalYear() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DicData> list = dicDataDao.selectByDicCode("fiscalYear");
            map.put("y_flag", true);
            map.put("y_message", "成功");
            map.put("y_list", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            map.put("message", "获取管理部门异常："+e);
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public String getToken() {
            if(redisTemplateUtil.isExist("SBTOKEN")){
                return (String) redisTemplateUtil.get("SBTOKEN");
            }
            String access_token = "";
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("grant_type","password");
            params.put("client_id",client_id);
            params.put("client_secret",client_secret);
            params.put("username",username);
            params.put("password",password);
            try {
                String res = HttpClientUtil.httpPostRequest(tokenUrl,null, params);
                Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
                access_token = map.get("access_token").toString();
                redisTemplateUtil.set("SBTOKEN", access_token,29*60L);
                return access_token;
            }catch(Exception e){
                e.printStackTrace();
                return "";
            }
    }
    
    @SuppressWarnings("rawtypes")
    public List<SchoolSbq> getSbqList() {
        String token = getToken();
        if(StringUtils.isEmpty(token)){
            return null;
        }
        String res ="";
        try {
            MyUser myUser = Common.getMyUser();
            HrCompany hrCompany = hrCompanyDao.selectByPrimaryKey(myUser.getCompanyId());
            res = HttpClientUtil.httpPostJson(queryUrl,hrCompany.getMessageId(),token);
            Map map = (Map) JSON.parse(res);
            String State = map.get("State").toString();
            if("1".equals(State)){
                String sbqs = map.get("Data").toString();
                List<SchoolSbq> list = JSON.parseArray(sbqs, SchoolSbq.class);
                return list;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
