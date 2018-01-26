package com.human.stuexam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.datamanger.entity.DataManger;
import com.human.stuexam.entity.StuExam;
import com.human.stuexam.service.StuExamService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/student/exam/")
public class StuExamController {
    
    private final  Logger logger = LogManager.getLogger(StuExamController.class);
    
    @Resource
    private StuExamService stuexamservice;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    /**
     * 学生考试列表首页
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/northamerica/stuexam/list");
        return mav;
    }
    
    @RequestMapping("index")
    public ModelAndView list() {
        ModelAndView mav=new ModelAndView("/northamerica/examineelist/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,StuExam se) {
        return  stuexamservice.query(pageView, se);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("数据管理删除");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=stuexamservice.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除数据管理失败,请稍后重试!");
        }
        return map;
    }
    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/northamerica/stuexam/add");
        List<DicData> examtime = dictionaryService.getDataByKey("exam_time");
        mav.addObject("examtime", examtime);
        return mav; 
    }
    /**
     * 添加操作
     * @param dm
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(StuExam se) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            stuexamservice.addData(se);
            map.put("message", "新增成功");
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "新增失败，请稍后重试!");
        }
        return map;
    }
    /**
     * 跳转编辑页面
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/northamerica/stuexam/edit");
        StuExam se=stuexamservice.selectByPrimaryKey(id);
        List<DicData> examtime = dictionaryService.getDataByKey("exam_time");
        List<DicData> stage = dictionaryService.getDataByKey("stage");
        mav.addObject("se", se);
        mav.addObject("stage", stage);
        mav.addObject("examtime", examtime);
        return mav; 
    }
    /**
     * 编辑操作
     * @param dm
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(StuExam se) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            stuexamservice.update(se);
            map.put("message", "编辑成功");
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    @RequestMapping("lookforTearcher")
    @ResponseBody
    public StuExam lookforTearcher(StuExam se) {
        ModelAndView mav=new ModelAndView("/northamerica/stuexam/edit");
        StuExam ss=new StuExam();
        List<StuExam> list=stuexamservice.lookforTearcher(se);
        for (StuExam stuExam : list) {
            ss=stuExam;
        }
        return ss; 
    }
    
    //跳转新增页面
    @RequestMapping("toAddExcel")
    public ModelAndView addExcel(){
        ModelAndView mav=new ModelAndView("/northamerica/stuexam/addexcel");
        return mav;
    }
    @RequestMapping("addExcle")
    @ResponseBody
    public Map<String, Object> addExcle(HttpServletRequest request){
        Map<String, Object> map=new HashMap<>();
        try {
            map=stuexamservice.addexcle(request);
            map.put("message", "批量新增成功");
        }
        catch (Exception e) {
            // TODO: handle exception
            map.put("message", "批量新增失败");
            logger.error("批量新增失败："+e.getMessage());
        }
        return map;
    }
}
