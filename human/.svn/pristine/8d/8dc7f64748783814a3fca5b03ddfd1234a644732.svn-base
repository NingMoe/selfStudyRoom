package com.human.basic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.EmpSuper;
import com.human.basic.entity.EmpTeach;
import com.human.basic.service.EmployeeService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrUser;
import com.human.manager.entity.Users;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/employee/")
public class EmployeeController {
    
    private final Logger logger = LogManager.getLogger(EmployeeController.class);
    
    @Resource
    private UserDeptService udService;
    
    @Resource
    private EmployeeService empService;
    
    /**
     * 员工管理
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/employee/list");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    @RequestMapping("queryEmp")
    @ResponseBody
    public PageView queryEmp(Users user,String deptIds,PageView pageView) {
        user.setId(Common.getMyUser().getUserid());
        return  empService.queryEmp(pageView, user,deptIds);
    }
    
    /**
     * 配置汇报人页面
     * @return
     */
    @RequestMapping("toCfigSuper")
    public ModelAndView toCfigSuper(Users user) {
        ModelAndView mav=new ModelAndView("/basic/employee/cfig_super");
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping("querySupserList")
    @ResponseBody
    public Map<String, Object> querySupserList(String empId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("supList", empService.querySupserList(empId));
        return m;
    }
    /**
     * 获取所选用户的信息以及已有导师信息
     * @return
     */
    @RequestMapping("cfigSuper")
    public ModelAndView cfigSuper(Long userId) {
        ModelAndView mav=new ModelAndView("/basic/employee/cfig_super");
        mav.addObject("userId", userId);
        return mav;
    }

    @RequestMapping("queryUserByEmail")
    @ResponseBody
    public Map<String,Object> queryUserByEmail(String emailAddr) {
        Map<String,Object> map=new HashMap<String,Object>();
        HrUser hu=empService.queryUserByEmail(emailAddr);
        if(null==hu){
            map.put("isHas", false);
        }else{
            map.put("isHas", true);
            map.put("hu", hu);
        }
        return map;
    }
    
    @RequestMapping("saveUserSupser")
    @ResponseBody
    public Map<String,Object> saveUserSupser(EmpSuper empSupser) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag", true);
        map.put("msg", "操作成功!");
        try{
            HrUser hu=empService.queryUserByEmail(empSupser.getEmailAddr().split("@")[0]);
            if(null==hu){
                map.put("flag", false);
                map.put("msg", "无法找到对应人员,请确认邮箱是否正确!!");
                return map;
            }
            empSupser.setSuperId(hu.getEmplId());
            empService.saveUserSupser(empSupser);
        }catch(Exception e){
            logger.error("save user Supser is error",e);
            map.put("flag", false);
            map.put("msg", "操作失败!");
        }
        return map;
    }
    
    @RequestMapping("delSuper")
    @ResponseBody
    public Map<String,Object> delSuper(EmpSuper empSuper) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag", true);
        map.put("msg", "操作成功!");
        try{
            if(empSuper.getEmpId()==null||empSuper.getEmpId().length()==0||empSuper.getSuperId()==null||empSuper.getSuperId().length()==0){
                map.put("flag", false);
                map.put("msg", "解除失败，无法获取必须信息!!");
                return map;
            }
            empService.delSuper(empSuper);
        }catch(Exception e){
            logger.error("delSuper user Supser is error",e);
            map.put("flag", false);
            map.put("msg", "操作失败!");
        }
        return map;
    }
    
    /**
     * 配置导师页面
     * @return
     */
    @RequestMapping("toCfigTeach")
    public ModelAndView toCfigTeach(Users user) {
        ModelAndView mav=new ModelAndView("/basic/employee/cfig_teach");
        mav.addObject("user", user);
        return mav;
    }
    
    @RequestMapping("queryTeachList")
    @ResponseBody
    public Map<String, Object> queryTeachList(String empId) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("teachList", empService.queryTeachList(empId));
        return m;
    }
    
    @RequestMapping("saveUserTeach")
    @ResponseBody
    public Map<String,Object> saveUserTeach(EmpTeach empTeach) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag", true);
        map.put("msg", "操作成功!");
        try{
            HrUser hu=empService.queryUserByEmail(empTeach.getEmailAddr().split("@")[0]);
            if(null==hu){
                map.put("flag", false);
                map.put("msg", "无法找到对应人员,请确认邮箱是否正确!!");
                return map;
            }
            empTeach.setTeachId(hu.getEmplId());
            empService.saveUserTeach(empTeach);
        }catch(Exception e){
            logger.error("save user Supser is error",e);
            map.put("flag", false);
            map.put("msg", "操作失败!");
        }
        return map;
    }
    
    @RequestMapping("delTeach")
    @ResponseBody
    public Map<String,Object> delTeach(EmpTeach empTeach) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag", true);
        map.put("msg", "操作成功!");
        try{
            if(empTeach.getEmpId()==null||empTeach.getEmpId().length()==0||empTeach.getTeachId()==null||empTeach.getTeachId().length()==0){
                map.put("flag", false);
                map.put("msg", "解除失败，无法获取必须信息!!");
                return map;
            }
            empService.delTeach(empTeach);
        }catch(Exception e){
            logger.error("delSuper user Supser is error",e);
            map.put("flag", false);
            map.put("msg", "操作失败!");
        }
        return map;
    }
    
    
    @RequestMapping("importTeachView")
    public ModelAndView importTeachView() {
        ModelAndView mav=new ModelAndView("/basic/employee/import_teach");
        return mav;
    }
    
    @RequestMapping("importSuperView")
    public ModelAndView importSuperView() {
        ModelAndView mav=new ModelAndView("/basic/employee/import_super");
        return mav;
    }
    
    @RequestMapping("importPhoneView")
    public ModelAndView importPhoneView() {
        ModelAndView mav=new ModelAndView("/basic/employee/import_phone");
        return mav;
    }
  /**
   * 下载模版公共方法
   * @param newFileName  下载到本地的文件名
   * @param temFileName  模版文件名
   * @param request
   * @param response
   * @return
   * @throws UnsupportedEncodingException
   */
    @RequestMapping(value="downTemp")
    @ResponseBody
    public Map<String, Object> downTemp(String newFileName,String temFileName,HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        response.setCharacterEncoding("iso-8859-1");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + new String(newFileName.getBytes("GBK"),"iso-8859-1"));
        try {
            String path = request.getSession().getServletContext()
                    .getRealPath("/static/temp/");
            if (!path.endsWith(java.io.File.separator)) {
                path = path + java.io.File.separator;
            }
            InputStream inputStream = new FileInputStream(new File(path
                    + temFileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            map.put("flag", false);
            map.put("message", "文件没有找到");
            e.printStackTrace();
        } catch (IOException e) {
            map.put("flag", false);
            map.put("message", "下载失败");
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 批量导入公立学校
     * @return
     */
    @RequestMapping(value = "ImportSuper")
    @ResponseBody
    public Map<String,Object> ImportSuper(HttpServletRequest request) {
        Map<String,Object> result=empService.ImportSuper(request);
        return result;
    }
    
    
    /**
     * 批量导入公立学校
     * @return
     */
    @RequestMapping(value = "ImportTeach")
    @ResponseBody
    public Map<String,Object> ImportTeach(HttpServletRequest request) {
        Map<String,Object> result=empService.ImportTeach(request);
        return result;
    }
    
    /**
     * 批量导入员工手机号
     * @return
     */
    @RequestMapping(value = "ImportPhone")
    @ResponseBody
    public Map<String,Object> ImportPhone(HttpServletRequest request) {
        Map<String,Object> result=empService.ImportPhone(request);
        return result;
    }
    
}
