package com.human.stuadmin.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.datamanger.controller.DataMangerController;
import com.human.examineelist.entity.ExamineeList;
import com.human.jw.service.JwService;
import com.human.stuadmin.entity.StuAdmin;
import com.human.stuadmin.service.StuAdminService;
import com.human.utils.PageView;

@Controller
@RequestMapping("/stu/admin/")
public class StuAdminController {
    private final Logger logger = LogManager.getLogger(StuAdminController.class);

    @Resource
    private StuAdminService stuadminservice;

    @Resource
    private JwService jwService;

    @Resource
    private DictionaryService dictionaryService;

    @RequestMapping("list")
    public ModelAndView list(String sClassCode) throws Exception {
        ModelAndView mav = new ModelAndView("/stuadmin/list");
        // 调接口查询出班级中的学生
        List<Map<String, Object>> list = jwService.getStudentInfoClass(sClassCode);
        Map<String, Object> statusMap=new HashMap<>();
        // 查询出该班号下所有学生
        StuAdmin sa = new StuAdmin();
        try {
            //将所有班级下学生状态改为2（挂起）
            statusMap.put("sClassCode", sClassCode);
            statusMap.put("status", 2);
            stuadminservice.updateStatusBySclassCode(statusMap);
            for (Map<String, Object> map : list) {
                String Code = (String) map.get("Code");
                String name = (String) map.get("Name");
                List<StuAdmin> adminList=stuadminservice.selectByCodeAndSclassCode(sClassCode,Code);
                sa.setCode(Code);
                sa.setStuName(name);
                sa.setsClassCode(sClassCode);
                Map<String, Object> resu=stuadminservice.getStuPhone(Code,25);
                sa.setStuPhone(resu.get("telephone1").toString());
                sa.setSchoolName(resu.get("schoolName").toString());
                sa.setStatus(1);
                if(sa.getIsSub()==null){
                    sa.setIsSub("否");
                }
                // 假如查询出学生的学号在数据中没有，插入这条数据，否则不做操作
               if(adminList.size()==0){
                    stuadminservice.insert(sa);
                }else{
                    statusMap.put("code",Code);
                    statusMap.put("status", 1);
                    stuadminservice.updateStatusBySclassCode(statusMap);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
       
        List<DicData> stuOrigin = dictionaryService.getDataByKey("stu_origin");
        List<DicData> schoolName = dictionaryService.getDataByKey("school");
        mav.addObject("sClassCode", sClassCode);
        mav.addObject("stuOrigin", stuOrigin);
        mav.addObject("schoolName", schoolName);
        return mav;
    }

    /**
     * 分页查询
     * 
     * @param pageView
     * @param xci
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView index(PageView pageView, StuAdmin sa) {
        return stuadminservice.query(pageView, sa);
    }

    /**
     * 跳转
     * 
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toedit(String id) {
        ModelAndView mav = new ModelAndView("stuadmin/edit");
        StuAdmin sa = stuadminservice.selectByprimary(id);
        String test=sa.getTestAcce();
        if(test!=null){
            sa.setIsSub("是");
        }else{
            sa.setIsSub("否");
            }
        List<DicData> schoolName = dictionaryService.getDataByKey("school");
        List<DicData> Origin = dictionaryService.getDataByKey("stu_origin");
        mav.addObject("schoolName", schoolName);
        mav.addObject("Origin", Origin);
        mav.addObject("sa", sa);
        return mav;
    }
    /**
     * 跳转查看附件页面
     */
    @RequestMapping("tolookfile")
    public ModelAndView tolookfile(String id) {
        ModelAndView mav = new ModelAndView("stuadmin/lookfile");
        StuAdmin sa = stuadminservice.selectByprimary(id);
        mav.addObject("sa", sa);
        return mav;
    }
    /**
     * 删除附件
     */
    @RequestMapping("deleteAcce")
    @ResponseBody
    public Map<String, Object> deleteAcce(StuAdmin sa){
        Map<String, Object> map=new HashMap<>();
        String acce=sa.getTestAcce();
        if("".equals(acce)||acce==null){
            sa.setIsSub("否");
        }
        try {
            stuadminservice.deleteAcce(sa);
            map.put("flag", true);
            map.put("message", "删除成功!");
        }
        catch (Exception e) {
            logger.error("编辑失败");
            e.getMessage();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
       
                
        return map;
    }
    /**
     * 编辑
     * 
     * @param sa
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(StuAdmin sa) {
        Map<String, Object> map = new HashMap();
        try {
            stuadminservice.update(sa);
            map.put("message", "编辑成功");
            map.put("flag", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑失败");
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }

        return map;
    }

    // 附件上传
    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, String sClassCode, String stuName) throws FileNotFoundException {
        return stuadminservice.upload(request, sClassCode, stuName);
    }

    /**
     * 附件下载
     * 
     * @throws FileNotFoundException
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, StuAdmin sa) throws FileNotFoundException {
        stuadminservice.download(request, response, sa);
    }
}
