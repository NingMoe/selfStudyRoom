package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.basic.dao.EmployeeDao;
import com.human.basic.entity.EmpSuper;
import com.human.basic.entity.EmpTeach;
import com.human.basic.service.EmployeeService;
import com.human.manager.dao.HrUserDao;
import com.human.manager.dao.HrUserExtendDao;
import com.human.manager.entity.HrUser;
import com.human.manager.entity.HrUserExtend;
import com.human.manager.entity.Users;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Resource
    private EmployeeDao  employeeDao;
    
    @Resource
    private HrUserDao hrUserDao;
    
    @Resource
    private HrUserExtendDao userExtDao;

    @Override
    public PageView queryEmp(PageView pageView, Users user,String deptIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(null!=deptIds&&deptIds.length()>0){
            String[] deptIdArry=deptIds.split(",");
            map.put("deptIds", deptIdArry);
        }
        map.put("paging", pageView);
        map.put("t", user);
        List<Users> list = employeeDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<EmpSuper> querySupserList(String empId) {
        return employeeDao.querySupserList(empId);
    }

    @Override
    public HrUser queryUserByEmail(String emailAddr) {
        HrUser hu=new HrUser();
        hu.setHrStatus("A");
        hu.setEmailAddr(emailAddr);
        return hrUserDao.selectByPrimaryKey(hu);
    }

    @Override
    public int saveUserSupser(EmpSuper empSuper) {
        return employeeDao.saveUserSupser(empSuper);
    }

    @Override
    public int delSuper(EmpSuper empSuper) {
        return employeeDao.delSuper(empSuper);
    }

    @Override
    public List<EmpTeach> queryTeachList(String empId) {
        return employeeDao.queryTeachList(empId);
    }

    @Override
    public int saveUserTeach(EmpTeach empTeach) {
        return employeeDao.saveUserTeach(empTeach);
    }

    @Override
    public int delTeach(EmpTeach empTeach) {
        return employeeDao.delTeach(empTeach);
    }

    @Override
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> ImportSuper(HttpServletRequest request) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
        result.put("flag", flag);
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("file");
        if(file.isEmpty()){
           result.put("msg","文件内容为空，请重新选择文件!");
           return result;
        }
            ExcelUtil<EmpSuper> ex=new ExcelUtil<EmpSuper>(1,0);
            //获取城市下地区
            Map<String,Object> empSuperMap=ex.checkAccount(file,EmpSuper.class);
            if(null!=empSuperMap&&empSuperMap.get("flag").toString().equals("false")){
                return empSuperMap;
            }
            List<EmpSuper> list=(List<EmpSuper>) empSuperMap.get("list");
            if(list.size()==0){
               result.put("msg", "导入文件为空文件!");
               return result;
            }
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("id", Common.getMyUser().getUserid());
            param.put("showEs", true);
            List<Users> myEmpList=employeeDao.queryUserEmps(param);
             for(EmpSuper es:list){
                    index++;
                    boolean isHasEmp=false;
                    boolean isHasSuper=false;
                    String type=es.getType().trim();
                    es.setEmailAddr(es.getEmailAddr().trim().split("@")[0]);
                    es.setEmpEmail(es.getEmpEmail().trim().split("@")[0]);
                    if(es.getEmpId().trim().length()==0||es.getEmpEmail().length()==0||es.getEmailAddr().length()==0||type.length()==0){
                        msg="第"+index+"行错误，错误信息：信息填写不完整!";
                        throw new RuntimeException();
                    }
                    if(!type.equals("A")&&!type.equals("D")){
                        msg="第"+index+"行错误，错误信息：操作类型无法识别，请注意大小写!";
                        throw new RuntimeException();
                    }
                    for (Users user : myEmpList) {
                        if (es.getEmailAddr().equals(user.getEmailAddr())) {
                            // 导师邮箱存在
                            isHasSuper = true;
                            es.setSuperId(user.getEmpId());
                            break;
                        }
                    }
                if (!isHasSuper) {
                    msg = "第" + index + "行错误，错误信息：无法找到对应汇报人邮箱或无权限操作!";
                    throw new RuntimeException();
                }
                    
                    for(Users user:myEmpList){
                        if(es.getEmpEmail().equals(user.getEmailAddr())){
                            //邮箱存在
                            isHasEmp=true;
                            if(!es.getEmpId().equals(user.getEmpId())){
                                msg="第"+index+"行错误，错误信息：员工工号与邮箱不对应,!";
                                throw new RuntimeException();
                            }else{
                                if (type.equals("A")) {
                                    for (EmpSuper es1 : user.getListSuper()) {
                                        if (es1.getSuperId().equals(es.getSuperId())) {
                                            msg = "第" + index + "行错误，错误信息:系统已存在该员工和汇报对象关系，请勿重复添加,!";
                                            throw new RuntimeException();
                                        }
                                    }
                                    employeeDao.saveUserSupser(es);
                                    break;
                                } else if(type.equals("D")){
                                    employeeDao.delSuper(es);
                                    break;
                                }
                            }
                        }
                    }
                    if(!isHasEmp){
                        msg="第"+index+"行错误，错误信息：无法找到对应员工邮箱或无权限操作!";
                        throw new RuntimeException();
                    }
                }
            flag=true;
            msg="导入成功,成功导入"+list.size()+"条汇报关系配置";
        }catch(Exception e){
            System.out.println("异常行:"+index);
           throw new RuntimeException(e); 
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            result.put("flag", flag);
            result.put("msg", msg);
            return result;
        }
    }

    @Override
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> ImportTeach(HttpServletRequest request) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
        result.put("flag", flag);
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("file");
        if(file.isEmpty()){
           result.put("msg","文件内容为空，请重新选择文件!");
           return result;
        }
            ExcelUtil<EmpTeach> ex=new ExcelUtil<EmpTeach>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,EmpTeach.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                return empTeachMap;
            }
            List<EmpTeach> list=(List<EmpTeach>) empTeachMap.get("list");
            if(list.size()==0){
               result.put("msg", "导入文件为空文件!");
               return result;
            }
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("id", Common.getMyUser().getUserid());
            param.put("showEt", true);
            List<Users> myEmpList=employeeDao.queryUserEmps(param);
             for(EmpTeach es:list){
                    index++;
                    boolean isHasEmp=false;
                    boolean isHasTeach=false;
                    String type=es.getType().trim();
                    es.setEmailAddr(es.getEmailAddr().trim().split("@")[0]);
                    es.setEmpEmail(es.getEmpEmail().trim().split("@")[0]);
                    if(es.getEmpId().trim().length()==0||es.getEmpEmail().length()==0||es.getEmailAddr().length()==0||type.length()==0){
                        msg="第"+index+"行错误，错误信息：信息填写不完整!";
                        throw new RuntimeException();
                    }
                    if(!type.equals("A")&&!type.equals("D")){
                        msg="第"+index+"行错误，错误信息：操作类型无法识别，请注意大小写!";
                        throw new RuntimeException();
                    }
                    
                    for (Users user : myEmpList) {
                        if (es.getEmailAddr().equals(user.getEmailAddr())) {
                            // 导师邮箱存在
                            isHasTeach = true;
                            es.setTeachId(user.getEmpId());
                            break;
                        }
                    }
                    if(!isHasTeach){
                        msg="第"+index+"行错误，错误信息：无法找到对应导师邮箱或无权限操作!";
                        throw new RuntimeException();
                    }
                    
                    for(Users user:myEmpList){
                        if(es.getEmpEmail().equals(user.getEmailAddr())){
                            //邮箱存在
                            isHasEmp=true;
                            if(!es.getEmpId().equals(user.getEmpId())){
                                msg="第"+index+"行错误，错误信息：员工工号与邮箱不对应,!";
                                throw new RuntimeException();
                            }else{
                                if (type.equals("A")) {
                                    for (EmpTeach es1 : user.getListTeach()) {
                                        if (es1.getTeachId().equals(es.getTeachId())) {
                                            msg = "第" + index + "行错误，错误信息:系统已存在该员工和导师关系，请勿重复添加,!";
                                            throw new RuntimeException();
                                        }
                                    }
                                    employeeDao.saveUserTeach(es);
                                    break;
                                }else if(type.equals("D")){
                                    employeeDao.delTeach(es);
                                    break;
                                }
                            }
                        }
                    }
                    if(!isHasEmp){
                        msg="第"+index+"行错误，错误信息：无法找到对应员工邮箱或无权限操作!";
                        throw new RuntimeException();
                    }
                }
            flag=true;
            msg="导入成功,成功导入"+list.size()+"条导师关系配置";
        }catch(Exception e){
            System.out.println("异常行:"+index);
           throw new RuntimeException(e); 
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            result.put("flag", flag);
            result.put("msg", msg);
            return result;
        }
    }

    @SuppressWarnings("all")
    @Override
    @Transactional
    public Map<String, Object> ImportPhone(HttpServletRequest request) {
        Map<String,Object> result=new HashMap<String,Object>();
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
        result.put("flag", flag);
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("file");
        if(file.isEmpty()){
           result.put("msg","文件内容为空，请重新选择文件!");
           return result;
        }
            ExcelUtil<HrUserExtend> ex=new ExcelUtil<HrUserExtend>(1,0);
            //获取城市下地区
            Map<String,Object> hrUserMap=ex.checkAccount(file,HrUserExtend.class);
            if(null!=hrUserMap&&hrUserMap.get("flag").toString().equals("false")){
                return hrUserMap;
            }
            List<HrUserExtend> list=(List<HrUserExtend>) hrUserMap.get("list");
            if(list.size()==0){
               result.put("msg", "所选择文件为空文件!");
               return result;
            }
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("id", Common.getMyUser().getUserid());
            param.put("showEt", true);
            List<Users> myEmpList=employeeDao.queryUserEmps(param);
             for(HrUserExtend es:list){
                    index++;
                    boolean isHasEmp=false;
                    es.setUserName(es.getUserName().trim().split("@")[0]);
                    if(es.getUserName().trim().length()==0||es.getCellPhoneNumber().length()==0){
                        msg="第"+index+"行错误，错误信息：信息填写不完整!";
                        throw new RuntimeException();
                    }
                    
                    for (Users user : myEmpList) {
                        if (es.getUserName().equals(user.getEmailAddr())) {
                            // 邮箱存在
                            isHasEmp = true;
                            break;
                        }
                    }
                    if(!isHasEmp){
                        msg="第"+index+"行错误，错误信息：无法找到对应邮箱或无权限操作此员工!";
                        throw new RuntimeException();
                    }
                    userExtDao.updateUserPhone(es);
                }
            flag=true;
            msg="更新成功,成功更新"+list.size()+"个员工手机号";
        }catch(Exception e){
            System.out.println("异常行:"+index);
           throw new RuntimeException(e); 
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            result.put("flag", flag);
            result.put("msg", msg);
            return result;
        }
    }

}
