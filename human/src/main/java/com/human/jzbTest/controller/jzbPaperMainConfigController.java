package com.human.jzbTest.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.entity.JzbConfig;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbPaperConfigDetail;
import com.human.jzbTest.entity.JzbPaperMainMessage;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbPaperMonthLevel;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.jzbTest.service.jzbPaperConfigDetailService;
import com.human.jzbTest.service.jzbPaperMainConfigService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.QRCodeUtil;

@Controller
@RequestMapping(value = "/jzbTest/jpConfig/")
public class jzbPaperMainConfigController {
    private final Logger logger = LogManager.getLogger(jzbPaperMainConfigController.class);

    @Resource
    jzbPaperMainConfigService jzbService;

    @Autowired
    private JzbDeptService deptService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private JzbGradeService gradeService;
    @Autowired
    private JzbQuestionService jzbquestionService;

    @Resource
    private jzbKnowledgePointService jzbknowledgePointService;

    @Resource
    private JzbPaperMonthConfigService jzbpapermonthService;

    @Resource
    private jzbPaperConfigDetailService jzbpaperconfigdetailService;
    
    @Value("${rb.qrUrl}") 
    private String qrUrl;
    /**
     * 试卷列表
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/list");
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("dep", deptId.intValue());
        mav.addObject("dept", deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        return mav;
    }

    /**
     * 分页查询
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView, jzbPaperMainConfig jzb) {

        return jzbService.query(pageView, jzb);
    }

    /**
     * 跳转限制页面
     */
    @RequestMapping("tolimit")
    public ModelAndView tolimit(String id) {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/limit");
        jzbPaperMainConfig jzb = jzbService.selectByPrimaryKey(Integer.valueOf(id));
        mav.addObject("jzb", jzb);
        return mav;
    }

    /**
     * 更新限制信息
     */
    @RequestMapping("limit")
    @ResponseBody
    public Map<String, Object> limit(jzbPaperMainConfig jzb) {
        jzbPaperMainConfig jpm = jzbService.selectByPrimaryKey(jzb.getId());
        jpm.setDayTimes(jzb.getDayTimes());
        jpm.setTotalTimes(jzb.getTotalTimes());
        jpm.setMonthTimes(jzb.getMonthTimes());
        Map<String, Object> map = new HashMap<>();
        try {
            jzbService.update(jpm);
            map.put("flag", true);
            map.put("message", "更新限制信息成功");
        }
        catch (Exception e) {
            // TODO: handle exception
            map.put("flag", false);
            map.put("message", "更新限制信息失败");
            logger.error("更新限制信息失败：" + e.getMessage());
        }
        return map;
    }

    @RequestMapping("toTestPaper")
    public ModelAndView toTestPaper(String id) {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/testPaper");
        jzbPaperMainConfig jzb = jzbService.selectByPrimaryKey(Integer.valueOf(id));
        List<JzbPaperMainMessage> mess = jzbService.getMessageById(Integer.valueOf(id));
        mav.addObject("messages", mess);
        mav.addObject("jzb", jzb);
        return mav;
    }

    @RequestMapping("testPaper")
    @ResponseBody
    public Map<String, Object> testPaper(jzbPaperMainConfig jzb) {
        int mainId = jzb.getId();
        jzbPaperMainConfig jpm = jzbService.selectByPrimaryKey(jzb.getId());
        List<JzbPaperMainMessage> list = (List<JzbPaperMainMessage>) JSON.parseArray(JSON.parseObject(jzb.getJstr()).getString("Data"), JzbPaperMainMessage.class);
        jpm.setHeadMessage(jzb.getHeadMessage());
        Map<String, Object> map = new HashMap<>();
        try {
            // 删除message表中主键为mainID的数据
            jzbService.deleteMessage(mainId);
            // 更新message数据
            for (JzbPaperMainMessage me : list) {
                if (me.getMessage() != "") {
                    jzbService.insertMessage(me);
                }
            }
            jzbService.update(jpm);
            map.put("flag", true);
            map.put("message", "更新限制信息成功");
        }
        catch (Exception e) {
            // TODO: handle exception
            map.put("flag", false);
            map.put("message", "更新限制信息失败");
            logger.error("更新限制信息失败：" + e.getMessage());
        }
        return map;
    }

    /**
     * 跳转新增页面
     * 
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/add");
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("dep", deptId.intValue());
        mav.addObject("dept", deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        return mav;
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(jzbPaperMainConfig jzb) {
        Map<String, Object> map = new HashMap<>();
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        jzb.setDept(deptId);
        jzbPaperMainConfig jPM = jzbService.selectPaperInfo(jzb);
        try {
            if (jPM == null) {
                jzbService.insert(jzb);
                map.put("flag", true);
                map.put("message", "新增成功");
            }
            else {
                map.put("flag", false);
                map.put("message", "该试卷已经存在");
            }
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "新增失败");
            logger.error("试卷新增失败:" + e.getMessage());
        }
        return map;
    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("数据管理删除");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // map=jzbService.delete(deleteIds);
            List<Map<String, Object>> l = jzbService.getList(deleteIds);
            if (l != null) {
                jzbService.deleteMonth(deleteIds);
            }
            map.put("flag", true);
            map.put("message", "删除成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("删除失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除失败,请联系管理员!");
        }
        return map;
    }

    /**
     * 跳转考试配置页面
     * 
     * @return
     */
    @RequestMapping("toExamConfig")
    public ModelAndView toExamConfig(String id, String grade, String subject, String classType) {
        Map<String, Object> sub = jzbService.getSubjectDicData("subject", subject);
        Map<String, Object> cType = jzbService.getSubjectDicData("class_type", classType);
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        String gradeCode = "";
        for (JzbGrade jzbGrade : grades) {
            if (grade.equals(jzbGrade.getGradeName())) {
                gradeCode = String.valueOf(jzbGrade.getId());
            }
        }
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/examlist");
        jzbPaperMainConfig jzb = jzbService.selectByPrimaryKey(Integer.valueOf(id));
        mav.addObject("jzb", jzb);
        List<JzbPaperMonthConfig> jzbMonCon = jzbpapermonthService.selectByMainId(id);
        for (JzbPaperMonthConfig jzbPaperMonthConfig : jzbMonCon) {
            String month = jzbPaperMonthConfig.getMonth();
            int monthId = jzbPaperMonthConfig.getId();
            List<JzbPaperMonthLevel> levelList = jzbpapermonthService.getMonthUseLevel(monthId);
            jzbPaperMonthConfig.setLevel(levelList);
            if ("A".equals(month)) {
                jzbPaperMonthConfig.setMonth("10");
            }
            else if ("B".equals(month)) {
                jzbPaperMonthConfig.setMonth("11");
            }
            else if ("C".equals(month)) {
                jzbPaperMonthConfig.setMonth("12");
            }
        }
        mav.addObject("jmc", jzbMonCon);
        mav.addObject("size", jzbMonCon.size());
        mav.addObject("sub", sub);
        mav.addObject("gradeCode", gradeCode);
        mav.addObject("cType", cType);
        return mav;
    }

    @RequestMapping("month")
    @ResponseBody
    public List<jzbKnowledgePoint> Month(String id, String monthArray, String subject, String classType, String grade) {
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        String[] mon = monthArray.split(",");
        String questionId = "";
        List<Integer> allQuestionIdList = new ArrayList<Integer>();
        for (String month : mon) {
            List<Integer> questionIdList = jzbquestionService.selectForQuestion(month, subject, classType, grade, String.valueOf(deptId));
            if (CollectionUtils.isNotEmpty(questionIdList)) {
                allQuestionIdList.addAll(questionIdList);
            }
        }
        // 去重
        if (CollectionUtils.isNotEmpty(allQuestionIdList)) {
            Set<Integer> list = new HashSet<Integer>(allQuestionIdList);
            allQuestionIdList = new ArrayList<Integer>(list);
        }
        for (Integer integer : allQuestionIdList) {
            questionId += integer + ",";
        }
        // 查询知识点 ，及知识点最大可配置
        List<jzbKnowledgePoint> jzbpoint = jzbknowledgePointService.selectCount(questionId);
        for (jzbKnowledgePoint jzbKnowledgePoint : jzbpoint) {
            int titleNum = jzbKnowledgePoint.getTitleNum();
            int knowledgeId = jzbKnowledgePoint.getId();
            if (titleNum > 1) {
                jzbKnowledgePoint jzbpointNum = jzbknowledgePointService.selectByPrimaryKey(knowledgeId);
                jzbKnowledgePoint.setTitleNum(jzbpointNum.getTitleNum());
                ;
            }
        }
        return jzbpoint;
    }

    @RequestMapping("toAddExam")
    @ResponseBody
    public ModelAndView toAddExam(String month, int id, String subject, String classType, String grade) {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/exam");
        List<JzbPaperMonthLevel> jzbScores = jzbpapermonthService.getMonthLevel(0);
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        mav.addObject("id", id);
        mav.addObject("month", month);
        mav.addObject("subject", subject);
        mav.addObject("classType", classType);
        mav.addObject("grade", grade);
        mav.addObject("jzbScores", jzbScores);
        mav.addObject("deptId", deptId);
        mav.addObject("qrUrl", qrUrl);
        return mav;
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(String str) {
        logger.info("配置试卷内容开始");
        String jstr = str.replaceAll("'", "\"");
        boolean f;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<JzbConfig> list = (List<JzbConfig>) JSON.parseArray(JSON.parseObject(jstr).getString("Data"), JzbConfig.class);
        List<JzbPaperMonthLevel> levels = new ArrayList<>();
        try {
            // 试卷内容配置
            JzbPaperConfigDetail jzbpaperconfigdetail = new JzbPaperConfigDetail();

            boolean flag = true;
            Integer monthConfigId = 0;
            Integer mainConfigId = 0;
            for (JzbConfig jzbConfig : list) {
                int difficultnum = jzbConfig.getDifficultnum();
                // 试卷月份配置
                if (difficultnum > 0) {
                    JzbPaperMonthConfig jzbpapermonthconfig = new JzbPaperMonthConfig();
                    String preCode = jzbquestionService.getQuestionPreCode(jzbConfig.getMainConfigId());
                    jzbpapermonthconfig.setMainConfigId(jzbConfig.getMainConfigId());
                    jzbpapermonthconfig.setMonth(jzbConfig.getPaperMonth());
                    jzbpapermonthconfig.setPaperTime(jzbConfig.getPaperTime());
                    jzbpapermonthconfig.setTotalQNum(jzbConfig.getTotalQNum());
                    jzbpapermonthconfig.setTkMonth(jzbConfig.getTkMonth());
                    jzbpapermonthconfig.setMinTime(jzbConfig.getMinTime());
                    jzbpapermonthconfig.setPreCode(preCode);
                    logger.info("月份配置表插入数据开始-----------------");
                    if (flag) {
                        // 查询出测试名称
                        Map<String, Object> examNameMap = jzbpapermonthService.selectExamName(jzbpapermonthconfig);
                        jzbpapermonthconfig.setExamName((String) examNameMap.get("examName"));
                        jzbpapermonthconfig.setStatus(1);
                        flag = jzbpapermonthService.insert(jzbpapermonthconfig, flag);
                        mainConfigId = jzbConfig.getMainConfigId();
                        monthConfigId = jzbpapermonthconfig.getId();
                        levels = jzbConfig.getMonthLevel();
                        // 向monthLevel表中插入数据
                        for (JzbPaperMonthLevel le : levels) {
                            le.setMonthConfigId(monthConfigId);
                            if (le.getNum() != null) {
                                jzbpapermonthService.insertMonthLevel(le);
                            }
                        }
                    }
                    jzbpaperconfigdetail.setMonthConfigId(monthConfigId);
                    jzbpaperconfigdetail.setDifficulty(jzbConfig.getDiflevel());
                    jzbpaperconfigdetail.setNum(jzbConfig.getDifficultnum());
                    jzbpaperconfigdetail.setKnowledgeId(jzbConfig.getKnowledgeId());
                    logger.info("尖子班试卷配置detail 插入-------");
                    jzbpaperconfigdetailService.insert(jzbpaperconfigdetail);
                }
            }
            result = jzbpapermonthService.checkMonthConfigStatus(mainConfigId, monthConfigId);
            f = (boolean) result.get("flag");
            if (f == true) {
                jzbpapermonthService.updateStatus(monthConfigId);
                map.put("flag", true);
                map.put("message", "配置试卷成功！");
            }
            else {
                map.put("flag", false);
                map.put("message", "对应知识点的题目不够，请重新配置！");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("配置试卷失败！", e.getMessage());
            map.put("flag", false);
            map.put("message", "配置试卷失败！");
        }
        return map;
    }

    // 查询知识点
    @RequestMapping("searchZsd")
    @ResponseBody
    public List<Map<String, Object>> searchZsd(String month, Integer id) {
        logger.info("通过月份和主表id查询出知识点");
        List<Map<String, Object>> list = jzbpaperconfigdetailService.searchZsd(month, id);
        return list;
    }

    /**
     * 配置试卷修改页面
     */
    @RequestMapping("toPaperEdit")
    @ResponseBody
    public ModelAndView toPaperEdit(String month, int mainConfigId, String subject, String classType, String grade, int monthId) {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/paperedit");
        JzbPaperMonthConfig jzb = jzbpapermonthService.selectByMonthAndId(month, mainConfigId);
        List<JzbPaperMonthLevel> M = jzbpapermonthService.getMonthLevel(monthId);
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        // 查询知识点
        List<Map<String, Object>> zsdList = jzbpaperconfigdetailService.selectKnowledge(month, mainConfigId, subject, classType, grade);
        mav.addObject("levels", M);
        mav.addObject("subject", subject);
        mav.addObject("classType", classType);
        mav.addObject("grade", grade);
        mav.addObject("jzb", jzb);
        mav.addObject("monthId", monthId);
        mav.addObject("zsdList", zsdList);
        mav.addObject("deptId", deptId);
        return mav;
    }

    /**
     * 查询知识点
     */
    @RequestMapping("todifficulty")
    @ResponseBody
    public List<Map<String, Object>> toDifficulty(String month, int mainConfigId, int knowledgeId) {
        List<Map<String, Object>> map = jzbpaperconfigdetailService.queryByCondition(month, mainConfigId, knowledgeId);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(String str) {
        logger.info("配置试卷内容开始");
        String jstr = str.replaceAll("'", "\"");
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        // List<JzbPaperConfigDetail> alldetails= request.getParameter("alldetails");
        List<JzbConfig> list = (List<JzbConfig>) JSON.parseArray(JSON.parseObject(jstr).getString("Data"), JzbConfig.class);
        List<JzbPaperMonthLevel> levels = new ArrayList<>();
        try {
            // 试卷内容配置
            JzbPaperConfigDetail jzbpaperconfigdetail = new JzbPaperConfigDetail();

            boolean flag = true;
            Integer monthConfigId = 0;
            Integer mainConfigId = 0;
            boolean f;
            for (JzbConfig jzbConfig : list) {
                int difficultnum = jzbConfig.getDifficultnum();
                // 试卷月份配置
                if (difficultnum > 0) {
                    JzbPaperMonthConfig jzbpapermonthconfig = new JzbPaperMonthConfig();
                    jzbpapermonthconfig.setId(jzbConfig.getId());
                    jzbpapermonthconfig.setMainConfigId(jzbConfig.getMainConfigId());
                    jzbpapermonthconfig.setMonth(jzbConfig.getPaperMonth());
                    jzbpapermonthconfig.setPaperTime(jzbConfig.getPaperTime());
                    jzbpapermonthconfig.setTotalQNum(jzbConfig.getTotalQNum());
                    jzbpapermonthconfig.setTkMonth(jzbConfig.getTkMonth());
                    jzbpapermonthconfig.setMinTime(jzbConfig.getMinTime());
                    Map<String, Object> map2 = jzbpaperconfigdetailService.getMonthId(jzbConfig.getMainConfigId(), jzbConfig.getPaperMonth());
                    logger.info("月份配置表插入数据开始-----------------");
                    if (flag) {
                        Map<String, Object> examNameMap = jzbpapermonthService.selectExamName(jzbpapermonthconfig);
                        jzbpapermonthconfig.setExamName((String) examNameMap.get("examName"));
                        jzbpaperconfigdetailService.deleteByMonthId((int) map2.get("id"));
                        flag = jzbpapermonthService.updateByprimary(jzbpapermonthconfig, flag);
                        mainConfigId = jzbConfig.getMainConfigId();
                        monthConfigId = jzbpapermonthconfig.getId();
                        levels = jzbConfig.getMonthLevel();
                        jzbpapermonthService.deleteMonthLevelByMonthId(jzbpapermonthconfig);
                        // 向monthLevel表中插入数据
                        for (JzbPaperMonthLevel le : levels) {
                            le.setMonthConfigId(monthConfigId);
                            if (le.getNum() != null) {
                                jzbpapermonthService.insertMonthLevel(le);
                            }
                        }
                    }
                    jzbpaperconfigdetail.setMonthConfigId(monthConfigId);
                    jzbpaperconfigdetail.setDifficulty(jzbConfig.getDiflevel());
                    jzbpaperconfigdetail.setNum(jzbConfig.getDifficultnum());
                    jzbpaperconfigdetail.setKnowledgeId(jzbConfig.getKnowledgeId());
                    logger.info("尖子班试卷配置detail 插入-------");
                    jzbpaperconfigdetailService.insert(jzbpaperconfigdetail);
                    map.put("flag", true);
                    map.put("message", "配置试卷成功！");
                }
            }
            result = jzbpapermonthService.checkMonthConfigStatus(mainConfigId, monthConfigId);
            f = (boolean) result.get("flag");
            if (f == true) {
                jzbpapermonthService.updateStatus(monthConfigId);
                map.put("flag", true);
                map.put("message", "配置试卷成功！");
            }
            else {
                map.put("flag", false);
                map.put("message", "对应知识点的题目不够，请重新配置！");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("配置试卷失败！", e.getMessage());
            map.put("flag", false);
            map.put("message", "配置试卷失败！");
        }
        return map;
    }

    @RequestMapping("todelete")
    @ResponseBody
    public Map<String, Object> todelete(JzbPaperMonthConfig jzbpapermonthconfig) {
        Map<String, Object> map = new HashMap<>();
        try {
            jzbpaperconfigdetailService.delete(jzbpapermonthconfig.getMainConfigId(), jzbpapermonthconfig.getMonth());
            jzbpapermonthService.delete(jzbpapermonthconfig);
            // 删除monthlevel数据
            jzbpapermonthService.deleteMonthLevel(jzbpapermonthconfig.getId());
            map.put("msg", "删除成功");
            map.put("flag", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "删除失败");
            map.put("flag", false);
            // TODO: handle exception
        }
        return map;
    }

    @RequestMapping("queryByKnow")
    @ResponseBody
    public List<Map<String, Object>> queryByKnow(int knowledgeId, HttpServletRequest request) {
        String subject = request.getParameter("subject");
        String classType = request.getParameter("classType");
        String grade = request.getParameter("grade");
        String month = request.getParameter("month");
        String monthArray = request.getParameter("qMonth");
        String mainConfigId = request.getParameter("mainConfigId");
        String type = request.getParameter("type");
        String[] qmonth = monthArray.split(",");
        String questionId = "";
        List<Integer> allQuestionIdList = new ArrayList<Integer>();
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        for (String mon : qmonth) {
            List<Integer> questionIdList = jzbquestionService.selectForQuestion(mon, subject, classType, grade, String.valueOf(deptId));
            if (CollectionUtils.isNotEmpty(questionIdList)) {
                allQuestionIdList.addAll(questionIdList);
            }
        }
        // 去重
        if (CollectionUtils.isNotEmpty(allQuestionIdList)) {
            Set<Integer> list = new HashSet<Integer>(allQuestionIdList);
            allQuestionIdList = new ArrayList<Integer>(list);
        }
        for (Integer integer : allQuestionIdList) {
            questionId += integer + ",";
        }

        List<Map<String, Object>> jzb = new ArrayList<>();
        if ("add".equals(type)) {
            jzb = jzbknowledgePointService.queryByKnowledge(knowledgeId, questionId);
        }
        else {
            jzb = jzbknowledgePointService.queryByKnow(knowledgeId, questionId, month, mainConfigId);
        }
        return jzb;
    }

    @RequestMapping("view")
    public ModelAndView view_question(JzbQuestion jq) {
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        jq.setqDept(String.valueOf(deptId));
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/view_question");
        String[] mon = jq.getqMonth().split(",");
        String questionId = "";
        List<Integer> allQuestionIdList = new ArrayList<Integer>();
        for (String month : mon) {
            List<Integer> questionIdList = jzbquestionService.selectForQuestion(month, jq.getqSubject(), jq.getqClasstype(), jq.getqGrade(), String.valueOf(deptId));
            if (CollectionUtils.isNotEmpty(questionIdList)) {
                allQuestionIdList.addAll(questionIdList);
            }
        }
        // 去重
        if (CollectionUtils.isNotEmpty(allQuestionIdList)) {
            Set<Integer> list = new HashSet<Integer>(allQuestionIdList);
            allQuestionIdList = new ArrayList<Integer>(list);
        }
        for (Integer integer : allQuestionIdList) {
            questionId += integer + ",";
        }
        List<jzbKnowledgePoint> jzbpoint = jzbknowledgePointService.selectCount(questionId);
        for (jzbKnowledgePoint jP : jzbpoint) {
            jq.setqKnowledge(jP.getKnowledge());
            String ids = jP.getIds();
            List<JzbQuestion> jzbq = jzbquestionService.getquestionArr(ids, jq);
            jP.setQuestions(jzbq);
            for (JzbQuestion q : jP.getQuestions()) {
                jq.setqCode(q.getqCode());
                List<JzbQuestion> qByCode = jzbknowledgePointService.getQuestionByCode(jq);
                q.setQuestions(qByCode);
                for (JzbQuestion JzbQ : q.getQuestions()) {
                    int id = JzbQ.getId();
                    // 是否是填空题
                    String tkAnswer = JzbQ.getTkAnswer();
                    List<String> answer = new ArrayList<String>();
                    if (tkAnswer != null && tkAnswer.indexOf("@@@") > -1) {
                        String[] answerArr = tkAnswer.split("@@@");
                        for (String t : answerArr) {
                            if (StringUtils.isNotEmpty(t)) {
                                answer.add(t);
                            }
                        }
                    }
                    else {
                        answer.add(tkAnswer);
                    }
                    JzbQ.setTkAnswerList(answer);
                    JzbQ.setAnswers(jzbknowledgePointService.getQuesAnswerList(id));
                }
                // 查询后将qCode设为空,防止非查询阶段getquestionArr方法以qCode未查询条件
                jq.setqCode("");
            }
        }
        mav.addObject("questions", jzbpoint);
        mav.addObject("jq", jq);
        return mav;
    }

    @RequestMapping("questionByCode")
    @ResponseBody
    public ModelAndView questionByCode(JzbQuestion jq) {
        logger.info("----------------根据code的查询题目开始-------------");
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/look_question");
        String questionIds = jzbknowledgePointService.getIds(jq.getqCode());
        List<JzbQuestion> question_id = jzbquestionService.getquestionArr(questionIds, jq);
        List<JzbQuestion> questions = new ArrayList<>();
        JzbQuestion ques = new JzbQuestion();
        if(question_id!=null){
            String[] ids = questionIds.split(",");
            for (String id : ids) {
                ques = jzbquestionService.selectPrimaryKey(id);
                questions.add(ques);
            }
            jq.setQuestions(questions);
            jq.setqMainDesc(question_id.get(0).getqMainDesc());
            jq.setqType(question_id.get(0).getqType());
            mav.addObject("jq", jq);
            mav.addObject("num", question_id.get(0).getqType());
        }else {
            mav.addObject("error", "1");
        }
        return mav;
    }

    @RequestMapping("getQrcode")
    @ResponseBody
    public void getQrcode(HttpServletRequest request, HttpServletResponse response, String url) {
        logger.info("生成二维码");
        try {
            String code_url = url;
            BufferedImage bufferedImage = QRCodeUtil.createImage(code_url);
            // 生成二维码QRCode图片
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动端题目预览
     * 
     * @param jq
     * @return
     */
    @RequestMapping("view_phone")
    public ModelAndView view_phone(JzbQuestion jq) {
        ModelAndView mav = new ModelAndView("/jzbTest/TestConfig/jpConfig/multi_ques");
        String questionId = "";
        //当qCode不为空时是查询，根据code查到ids，否者根据其他条件查询到ids
        if (jq.getqCode() != null) {
            logger.info("----------------根据code查询ids---------------------");
            questionId = jzbknowledgePointService.getIds(jq.getqCode());
        }
        else {
            logger.info("----------------根据传入条件查询出ids---------------------");
            String[] mon = (jq.getqMonth()).split(",");
            List<Integer> allQuestionIdList = new ArrayList<Integer>();
            for (String month : mon) {
                List<Integer> questionIdList = jzbquestionService.selectForQuestion(month, jq.getqSubject(), jq.getqClasstype(), jq.getqGrade(), jq.getqDept());
                if (CollectionUtils.isNotEmpty(questionIdList)) {
                    allQuestionIdList.addAll(questionIdList);
                }
            }
            // 去重
            if (CollectionUtils.isNotEmpty(allQuestionIdList)) {
                Set<Integer> list = new HashSet<Integer>(allQuestionIdList);
                allQuestionIdList = new ArrayList<Integer>(list);
            }
            for (Integer integer : allQuestionIdList) {
                questionId += integer + ",";
            }
        }
        if(questionId!=null){
            logger.info("---------------根据id查询出 所有的id对应的所有code--------");
            List<JzbQuestion> q = jzbquestionService.getquestionArr(questionId, jq);
            JzbQuestion ques = new JzbQuestion();
            int totalNum = q.size();
            int num = 0;
            // 刚进入页面时是第一题
            if (q.size() != 0) {
                if (jq.getSort() != 0) {
                    num = jq.getSort();
                }
            }
            if (jq.getIsSearch() != null) {
                ques = q.get(0);
                mav.addObject("look_total", "1");
            }else {
                ques = q.get(num);
            }
            if (jq.getIsSearch() != null) {
                jq.setqCode(jq.getqCode());
            }else {
                jq.setqCode(ques.getqCode());
            }
            logger.info("--------------根据code查出每个code对应的question 集合------------------");
            List<JzbQuestion> questions = jzbknowledgePointService.getQuestionByCode(jq);
            ques.setQuestions(questions);
            for (JzbQuestion jzbq : ques.getQuestions()) {
                int id = jzbq.getId();
                String tkAnswer = jzbq.getTkAnswer();
                List<String> answer = new ArrayList<String>();
                if (tkAnswer != null && tkAnswer.indexOf("@@@") > -1) {
                    String[] answerArr = tkAnswer.split("@@@");
                    for (String t : answerArr) {
                        if (StringUtils.isNotEmpty(t)) {
                            answer.add(t);
                        }
                    }
                }else {
                    answer.add(tkAnswer);
                }
                jzbq.setTkAnswerList(answer);
                jzbq.setAnswers(jzbknowledgePointService.getQuesAnswerList(id));
            }
            mav.addObject("question", ques);
            mav.addObject("totalNum", totalNum);
            mav.addObject("jq", jq);
        }else{
            mav.addObject("error", "1");
        }
        return mav;
    }

}
