package com.human.continuedClass.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import com.human.continuedClass.dao.CombinationClassDao;
import com.human.continuedClass.dao.SchoolAreaDao;
import com.human.continuedClass.entity.CombinationClass;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.CombinationClassService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
@Service
public class CombinationClassServiceImpl implements CombinationClassService {
 
    @Resource
    private CombinationClassDao ccDao;
    
    @Resource
    private SchoolAreaDao saDao;

    @Override
    public PageView query(PageView pageView, CombinationClass cc) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", cc);
        List<CombinationClass> list = ccDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void downLoadClassExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileTmp = request.getSession().getServletContext().getRealPath("/static/temp/importCombinationClass.xlsx");
        String fileName = "导入套餐组合模板.xlsx";
        try {
            File file = new File(fileTmp);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
            InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            int size = (int) file.length();
            byte[] b = new byte[size];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }          
    }

    @Override
    public Map<String, Object> uploadLoadClassExcel(CombinationClass cc, MultipartFile file) {
        Map<String,Object> map = new HashMap<String, Object>();
        String createUser=Common.getMyUser().getUsername();
        Date  createTime=new Date();
        long ruleId=cc.getRuleId();
        try{
            ExcelUtil<CombinationClass> ex=new ExcelUtil<CombinationClass>(1,0);
            Map<String,Object> result=ex.checkAccount(file,CombinationClass.class);
            if(null!=result&&result.get("flag").toString().equals("true")){
                @SuppressWarnings("unchecked")
                List<CombinationClass> list=(List<CombinationClass>)result.get("list");
                if(list == null || list.size()==0){
                    map.put("flag", false);
                    map.put("message", "表格数据不能为空！");
                    return map;
                }
                //第一步：先检验execl表格内容是否符合模板要求，不符合直接导入不成功
                int z = 1;
                SchoolArea sa=new SchoolArea();
                for(CombinationClass cft:list){
                    z++;  
                    //判断校区是否为空
                    if(StringUtils.isEmpty(cft.getSchoolAreaName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行校区为空!");
                        return map;
                    }
                    //判断校区是否存在
                    sa=saDao.selectByName(cft.getSchoolAreaName());
                    if(sa==null){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行校区不存在!请在校区基础数据模块添加!");
                        return map;
                    }
                    //判断年级是否为空
                    if(StringUtils.isEmpty(cft.getGrade())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行年级为空!");
                        return map;
                    }
                    //判断套餐是否为空
                    if(StringUtils.isEmpty(cft.getCombinationData())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行套餐内容为空!");
                        return map;
                    }

                }
                //第二步:保存套餐数据
                for(CombinationClass cft:list){
                    cft.setRuleId(ruleId);
                    cft.setCreateUser(createUser);
                    cft.setCreateTime(createTime);
                    //ccDao.insertSelective(cft); 
                }
                //批量插入
                ccDao.insertByBatch(list);
                map.put("flag", true);
                map.put("message", "上传套餐数据成功！");  
            }else{
                map.put("flag", false);
                map.put("message",result.get("errorMessage"));    
            } 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("message", "上传套餐数据失败!失败原因:"+e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            ccDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败!失败原因:"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAll(CombinationClass cc) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{            
            ccDao.deleteAll(cc);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败!失败原因:"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public Map<String, Object> add(CombinationClass cc) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cc.setCreateUser(Common.getMyUser().getUsername());
            cc.setCreateTime(new Date());
            ccDao.insertSelective(cc); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败!失败原因:"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;    }

    @Override
    public CombinationClass selectByPrimaryKey(CombinationClass cc) {
        return ccDao.selectByPrimaryKey(cc.getId());
    }

    @Override
    public Map<String, Object> edit(CombinationClass cc) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cc.setUpdateUser(Common.getMyUser().getUsername());
            cc.setUpdateTime(new Date());
            ccDao.updateByPrimaryKeySelective(cc); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败!失败原因:"+e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String ids=ServletRequestUtils.getStringParameter(request, "ids");
            if(ids!=null && ids.length()>0 ){
                List<String> list = new ArrayList<String>();
                String[] idarray = ids.split(",");
                list=Arrays.asList(idarray);
                map.put("s", list);
            }
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            map.put("ruleId", ruleId);
            List<Map<String,Object>> maplist =ccDao.exportSelect(map);
            ExcelUtil<CombinationClass> ex=new ExcelUtil<CombinationClass>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportCombinationClass.xlsx", CombinationClass.class, maplist, response, TimeUtil.getCurrTime()+"套餐数据信息", 0, 1);
            map.put("flag", true);
            map.put("message", "套餐数据导出成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "套餐数据导出失败!");
        }
        return map;
    }
}
