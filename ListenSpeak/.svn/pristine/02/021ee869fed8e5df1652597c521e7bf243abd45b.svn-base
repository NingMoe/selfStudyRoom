package com.ls.spt.basic.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.ls.spt.basic.dao.SchoolDao;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.entity.School;
import com.ls.spt.basic.service.SchoolService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.ExcelUtil;


@Service
public class SchoolServiceImpl implements SchoolService {
       
    @Resource
    private SchoolDao  schoolDao;
            
 
    @Override
    public PageView query(PageView pageView, School cf) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", cf);
        List<School> list = schoolDao.query(map);
//        pageView.setRecords(list);
        pageView.setData(list);
        return pageView;
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(School cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(String.valueOf(Common.getMyUser().getUserid()));
            cf.setCreateTime(new Date());
            schoolDao.insertSelective(cf); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "你选择的省市区下已经存在该公立学校，请输入其它公立学校名称!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public School selectById(long id) {
        return schoolDao.selectByPrimaryKey(id);
    }
    
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(School cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(String.valueOf(Common.getMyUser().getUserid()));
            cf.setUpdateTime(new Date());
            schoolDao.updateByPrimaryKeySelective(cf); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "你选择的省市区下已经存在该公立学校，请输入其它公立学校名称!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds,Integer status) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            paraMap.put("status", status);
            paraMap.put("updateUser", Common.getMyUser().getUserid());            
            schoolDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "操作失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public void downLoadSchoolExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileTmp = request.getSession().getServletContext().getRealPath("/static/temp/importSchool.xlsx");
        String fileName = "导入公立学校模板.xlsx";
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
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> uploadLoadSchoolExcel(MultipartFile file,School cf) {
        Map<String,Object> map = new HashMap<String, Object>();
        String createUser=String.valueOf(Common.getMyUser().getUserid());
        Date  createTime=new Date();
        try{
            ExcelUtil<School> ex=new ExcelUtil<School>(1,0);
            Map<String,Object> result=ex.checkAccount(file,School.class);
            if(null!=result&&result.get("flag").toString().equals("true")){
                @SuppressWarnings("unchecked")
                List<School> list=(List<School>)result.get("list");
                if(list == null || list.size()==0){
                    map.put("flag", false);
                    map.put("message", "表格数据不能为空！");
                    return map;
                }
                //第一步：先检验execl表格内容是否符合模板要求，不符合直接导入不成功
                int z = 1;
                for(School school:list){
                    z++;  
                    //判断学校名称是否为空
                    if(StringUtils.isEmpty(school.getSchoolName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行学校名称为空!");
                        return map;
                    }
                    //判断学校类型是否为空
                    if(StringUtils.isEmpty(school.getSchoolType())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行学校类型为空!");
                        return map;
                    }
                    //判断学校类型填写正确
                    if(Integer.valueOf(school.getSchoolType())>5){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行学校类型填写错误!");
                        return map; 
                    }
                }
                for(School school:list){
                    school.setSchoolProvince(cf.getSchoolProvince());
                    school.setSchoolCity(cf.getSchoolCity());
                    school.setSchoolArea(cf.getSchoolArea());
                    school.setCreateUser(createUser);
                    school.setCreateTime(createTime);
                }
                //第三步:批量保存公立学校数据
                schoolDao.insertSchoolByBatch(list);
                map.put("flag", true);
                map.put("message", "上传公立学校数据成功！");  
            }else{
                map.put("flag", false);
                map.put("message",result.get("errorMessage"));    
            } 
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "你选择的省市区下存在相同的公立学校!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("message", "上传公立学校数据失败!");
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public List<School>getSchoolByParam(School cf) {
        List<School> list =schoolDao.getSchoolByParam(cf);
        return list; 
    }


}
