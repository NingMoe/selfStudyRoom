package com.human.jzbTest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbGrade/")
public class JzbGradeController {
    
 private final  Logger logger = LogManager.getLogger(JzbGradeController.class);
            
    @Resource
    private JzbGradeService jzbGradeService;
        
    @Resource
    private JzbDeptService deptService;
                  
    /**
     * 年级科目配置信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGrade/list");
        List<JzbDept> jzbDepts=new ArrayList<JzbDept>();
        String email=Common.getMyUser().getEmailAddr();
        JzbDept jzbDept = deptService.getManageDeptByEmail(email);
        jzbDepts.add(jzbDept);
        mav.addObject("jzbDepts", jzbDepts);
        return mav;
    }
    
    /**
     * 分页查询年级科目配置信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbGrade cf) {
        return  jzbGradeService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGrade/add");
        List<JzbDept> jzbDepts=new ArrayList<JzbDept>();
        String email=Common.getMyUser().getEmailAddr();
        JzbDept jzbDept = deptService.getManageDeptByEmail(email);
        jzbDepts.add(jzbDept);
        mav.addObject("jzbDepts", jzbDepts);
        return mav;  
    }
    
    /**
     * 保存年级科目配置信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(JzbGrade cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbGradeService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存年级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGrade/edit"); 
        List<JzbDept> jzbDepts=new ArrayList<JzbDept>();
        String email=Common.getMyUser().getEmailAddr();
        JzbDept jzbDept = deptService.getManageDeptByEmail(email);
        jzbDepts.add(jzbDept);
        mav.addObject("jzbDepts", jzbDepts);
        JzbGrade jzbGrade=jzbGradeService.selectById(id);
        mav.addObject("jzbGrade", jzbGrade);
        return mav; 
    }
    
    /**
     * 编辑年级科目配置信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(JzbGrade cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbGradeService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑年级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除年级科目配置信息（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除年级数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbGradeService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除年级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除年级数据失败,请稍后重试!");
        }
        return map;
    }

    
    /**
     * 获取部门下的年级
     * @param deptId
     * @return
     */
    @RequestMapping("selectGradeByDeptId")
    @ResponseBody
    public List<JzbGrade> selectByDeptId(Long deptId) {
        return jzbGradeService.selectByDeptId(deptId);
    }
    
}
