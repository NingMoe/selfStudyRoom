package com.human.teacherservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.manager.dao.HrUserDao;
import com.human.manager.entity.HrUser;
import com.human.teacherservice.dao.LibFeedBackDao;
import com.human.teacherservice.entity.LibFeedBack;
import com.human.teacherservice.service.LibFeedBackService;
import com.human.utils.BindingConstants;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class LibFeedBackServiceImpl implements LibFeedBackService {

    @Resource
    private HrUserDao hrUserDao;
    
    @Resource
    private LibFeedBackDao libFeedBackDao;
    
    
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveMyFeedBack2(HttpServletRequest request, LibFeedBack info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String emailAddr = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR); 
            if(StringUtils.isNotEmpty(emailAddr)){
                HrUser hrUser1 = new HrUser();
                hrUser1.setEmailAddr(emailAddr);
                hrUser1 = hrUserDao.selectByPrimaryKey(hrUser1);
                if(hrUser1!=null){
                    info.setCompanyId(hrUser1.getCompany());
                    info.setEmailAddr(emailAddr);
                    info.setCreateTime(new Date());
                    libFeedBackDao.insertSelective(info);
                }else{
                    map.put("flag", false);
                    map.put("message", "没有该用户信息");
                    return map;
                }
            }else{
                map.put("flag", false);
                map.put("message", "session已过期,请重新登录!");
                return map;
            }            
            map.put("flag", true);
            map.put("message", "保存我的建议/意见成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "保存我的建议/意见失败,请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public PageView query(PageView pageView, LibFeedBack info) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        info.setCompanyId(Common.getMyUser().getCompanyId());
        map.put("paging", pageView);
        map.put("t", info);
        List<LibFeedBack> list = libFeedBackDao.query(map);       
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();              
        map.put("companyId", Common.getMyUser().getCompanyId());      
        List<Map<String,Object>> maplist =libFeedBackDao.exportAll(map);
        ExcelUtil<LibFeedBack> ex=new ExcelUtil<LibFeedBack>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp")+"/";
        System.out.println("导出路径===="+path);
        try {
            ex.writeExcel(path+"exportLibraryFeedBack.xlsx", LibFeedBack.class, maplist, response, "图书馆意见反馈列表信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();        
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

}
