package com.human.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.human.customer.dao.CustomerCenterConfigModelDao;
import com.human.customer.entity.CenterModel;
import com.human.customer.service.CustomerCenterConfigModelService;
import com.human.manager.service.UserService;
import com.human.utils.PageView;

@Service
public class CustomerCenterConfigModelServiceImpl implements CustomerCenterConfigModelService {

    @Resource
    private CustomerCenterConfigModelDao cfgModelDao;

    @Resource
    private UserService userService;
    
    @Override
    public PageView query(PageView pageView,CenterModel cd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", cd); 
        List<CenterModel> list = cfgModelDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public void delByIds(String  ids) {
        cfgModelDao.delByIds(ids.split(","));
    }

    @Override
    public Map<String, Object> add(CenterModel cd,HttpServletRequest request) {
        Map<String, Object> map=new HashMap<String,Object>();
      // 验证用户账号与密码是否正确
      List<CenterModel> deptList = cfgModelDao.queryByName(cd.getModelName());
      if(deptList.size()>0){
          map.put("flag", false);
          map.put("msg", "对不起，该模块已经存在！");
      }else{
          cfgModelDao.add(cd);
          map.put("flag", true);
          map.put("msg", "处理成功！");
      }
        return map;
    }


    @Override
    public CenterModel queryById(Long id) {
        return cfgModelDao.queryById(id);
    }


    @Override
    public Map<String, Object> edit(CenterModel cd, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        cfgModelDao.edit(cd);
        map.put("flag", true);
        map.put("msg", "操作成功");
        return map;
    }


    @Override
    public List<CenterModel> getModels() {
        return  cfgModelDao.getModels();
    }

}
