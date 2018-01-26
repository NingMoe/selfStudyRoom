package com.human.examineelist.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.XdfClassInfo;
import com.human.examineelist.entity.ClassComments;
import com.human.examineelist.entity.ExamineeList;
import com.human.examineelist.service.AddExcleService;
import com.human.examineelist.service.ClassCommentsService;
import com.human.examineelist.service.ElvoService;
import com.human.examineelist.service.ExamineeListService;
import com.human.jw.service.JwService;
import com.human.jw.service.JwXdfClassInfoService;
import com.human.stuexam.entity.StuExam;
import com.human.stuexam.service.StuExamService;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping(value = "/Examinee/List/")
public class ExamineeListController {

    private final Logger logger = LogManager.getLogger(ExamineeListController.class);

    @Resource
    private ExamineeListService examineeListService;

    @Resource
    private JwService jwService;

    @Resource
    private StuExamService stuExamService;

    @Resource
    ClassCommentsService classCommentsService;

    @Resource
    AddExcleService addexcleService;

    @Resource
    ElvoService elvoService;
    
    @Resource
    JwXdfClassInfoService jwXdfService;
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.copyUrl}")
    private  String copyUrl;
    /**
     * 学生考试列表首页
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/northamerica/examineelist/list");
        return mav;
    }

    /**
     * 分页查询
     * 
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView, ExamineeList ex) {
        return examineeListService.query(pageView, ex);
    }

    /**
     * 初始化
     * 
     * @param managerUser
     * @param pageView
     * @return
     * @throws Exception
     */
    @SuppressWarnings("null")
    @RequestMapping("initialize")
    @ResponseBody
    public Map<String, Object> initialize(String classCode, HttpServletRequest request) throws Exception {
        String classCode1 = request.getParameter("classCode");
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            
            if (!("".equals(classCode1))) {
                List<Map<String, Object>> list = jwService.getStudentInfoClass(classCode1);
                // 删除学生信息表中所有数据
                // examineeListService.delete();
                List<ExamineeList> ClassCodeList = examineeListService.queryClassCode(classCode1);
                StringBuffer codeindex = new StringBuffer();
                codeindex.append("");
                StringBuffer codebuf = new StringBuffer();
                codebuf.append("");
                for (ExamineeList examineeList : ClassCodeList) {
                    String stuCode = examineeList.getCode();
                    codeindex.append(stuCode + ",");
                    
                }
                // examineeListService
                for (Map<String, Object> map : list) {
                    String Gender = String.valueOf((int) map.get("Gender"));
                    String Code = (String) map.get("Code");
                    // 假如学号不在数据库中进行新增
                    if ((codeindex.toString()).indexOf(Code) == -1 && (codebuf.toString()).indexOf(Code) == -1) {
                        if ("1".equals(Gender)) {
                            params.put("Gender", "男");
                        }
                        else {
                            params.put("Gender", "女");
                        }
                        String Name = (String) map.get("Name");
//                    String sCardCode=(String)map.get("sCardCode");
                        params.put("Name", Name);
                        params.put("classCode", classCode1);
                        params.put("Code", Code);
                        XdfClassInfo xdf=jwXdfService.selectByprimary(classCode1);
                        params.put("deptName", xdf.getsDeptName());
                        examineeListService.insertIntoStu(params);
                        codebuf.append(Code + ",");
                    }
                    
                }
                params.put("message", "初始化成功");
                params.put("flag", true);
                
            }
            else {
                params.put("message", "初始化失败");
            }
            request.setAttribute("classCode", classCode1);
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 获取查看成绩页面
     * 
     * @return
     */
    @RequestMapping("lookDetail")
    @ResponseBody
    public ModelAndView lookdetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/northamerica/examineelist/lookDetail");
        HttpSession session = request.getSession();
        session.setAttribute("code", request.getParameter("code"));
        session.setAttribute("deptName", request.getParameter("deptName"));
        return mav;
    }

    /**
     * 获取查看成绩页面参数
     * 
     * @param pageView
     * @param dm
     */
    @RequestMapping("queryData")
    @ResponseBody
    public Map<String, Object> queryData(ExamineeList em, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String deptName = (String) session.getAttribute("deptName");
        em.setCode(code);
        em.setDeptName(deptName);
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExamineeList> list = new ArrayList<ExamineeList>();
        list = examineeListService.queryData(em);
        map.put("data", list);
        request.setAttribute("code", code);
        return map;
    }

    /**
     * 编辑数据
     * 
     * @return
     */
    @RequestMapping(value = "editData")
    @ResponseBody
    public Map<String, Object> editData(String jsonStr, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ExamineeList> datas = new ArrayList<ExamineeList>();
        String code = request.getParameter("code");
        String classCode = "";
        List<ExamineeList> ClassCode = examineeListService.queryforClassCode(code);
        for (ExamineeList list : ClassCode) {
            classCode = list.getClassCode();
        }
        try {
            if (StringUtils.isNoneEmpty(jsonStr)) {
                datas = JSON.parseArray(jsonStr, ExamineeList.class);
            }
            examineeListService.editDicData(classCode, code, datas);
            map.put("flag", true);
            map.put("message", "修改成功");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            map.put("flag", false);
            map.put("message", "修改失败");
        }

        return map;
    }

    /**
     * 加载报表页面
     */
    @RequestMapping(value = "reportIndex")
    @ResponseBody
    public ModelAndView reportIndex( HttpServletRequest request,String code, String deptName,String name,String i) {
        String url = "";
        if ("pc".equals(i)) {
            url += "/northamerica/examineelist/reportIndex";
        }
        else {
            url += "/northamerica/examineelist/reportIndex_phone";
        }
        name =examineeListService.getStudentName(code);
        HttpSession session=request.getSession();
        session.setAttribute("Code", code);
        ModelAndView mav = new ModelAndView(url);
        // 查询出学生评价
        ClassComments cc =new ClassComments();
        if(classCommentsService.queryforcode(code)!=null){
            cc = classCommentsService.queryforcode(code);
        }
        List<StuExam> list = stuExamService.queryInfoByCode(code);
        // 预备阶段
        StuExam stupre = new StuExam();
        // 基础阶段
        StuExam stubas = new StuExam();
        // 强化阶段
        StuExam stustr = new StuExam();
        // 冲刺阶段
        StuExam studas = new StuExam();
        for (StuExam stuExam : list) {
            if ("预备".equals(stuExam.getStage())) {
                String classCode=stuExam.getStclassNo();
                Map<String, Object> yb=stuExamService.getBeginAndEndTime(classCode);
                if(yb!=null){
                    cc.setPreStarttime((String)yb.get("startTime"));
                    cc.setPreEndtime((String)yb.get("endTime"));
                }
                stupre = stuExam;
            }
            else if ("基础".equals(stuExam.getStage())) {
                String classCode=stuExam.getStclassNo();
                Map<String, Object> jc=stuExamService.getBeginAndEndTime(classCode);
                if(jc!=null){
                    cc.setBasStarttime((String)jc.get("startTime"));
                    cc.setBasEndtime((String)jc.get("endTime"));
                }
                stubas = stuExam;
            }
            else if ("强化".equals(stuExam.getStage())) {
                String classCode=stuExam.getStclassNo();
                Map<String, Object> qh=stuExamService.getBeginAndEndTime(classCode);
                if(qh!=null){
                    cc.setStrStarttime((String)qh.get("startTime"));
                    cc.setStrEndtime((String)qh.get("endTime"));
                }
                stustr = stuExam;
            }
            else if ("冲刺".equals(stuExam.getStage())) {
                String classCode=stuExam.getStclassNo();
                Map<String, Object> chongc=stuExamService.getBeginAndEndTime(classCode);
                if(chongc!=null){
                    cc.setDasStarttime((String)chongc.get("startTime"));
                    cc.setDasStarttime((String)chongc.get("endTime"));
                }
                studas = stuExam;
            }

        }
        mav.addObject("stupre", stupre);
        mav.addObject("stubas", stubas);
        mav.addObject("stustr", stustr);
        mav.addObject("studas", studas);
        mav.addObject("code", code);
        mav.addObject("name", name);
        mav.addObject("cc", cc);
        return mav;
    }

    /**
     * 各阶段随堂测成绩数据
     */
    @RequestMapping(value = "queryDetailReport")
    @ResponseBody
    public List<ExamineeList> queryDetailReport(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        code = (String) session.getAttribute("Code");
        String type = request.getParameter("type");
        Map<String, Object> map = new HashMap<String, Object>();
        if ("基础".equals(type)) {
            map.put("stage", "基础");
        }
        else if ("预备".equals(type)) {
            map.put("stage", "预备");
        }
        else if ("强化".equals(type)) {
            map.put("stage", "强化");
        }
        else if ("冲刺".equals(type)) {
            map.put("stage", "冲刺");
        }
        map.put("type", "随堂测");
        map.put("code", code);
        List<ExamineeList> list = examineeListService.queryByCode(map);
        return list;

    }

    /**
     * 高中预备阶段词测数据
     */
    @RequestMapping(value = "report_1")
    @ResponseBody
    public List<ExamineeList> report_1(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        code = (String) session.getAttribute("Code");
        String type = request.getParameter("type");
        Map<String, Object> map = new HashMap<String, Object>();
        if ("基础".equals(type)) {
            map.put("stage", "基础");
        }
        else if ("预备".equals(type)) {
            map.put("stage", "预备");
        }
        else if ("强化".equals(type)) {
            map.put("stage", "强化");
        }
        map.put("code", code);
        map.put("type", "词测");
        examineeListService.queryByCode(map);
        return examineeListService.queryByCode(map);
    }

    /**
     * 阶段测试数据、模考数据
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "stageReport")
    @ResponseBody
    public List<ExamineeList> stageReport(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("Code");
        String classCode = (String) session.getAttribute("classCode");
        String type = request.getParameter("type");
        String stage = request.getParameter("stage");
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        if ("阶段测试".equals(type)) {
            map.put("type", "阶段测试");
        }
        else if ("模考".equals(type) || "实考".equals(type)) {
            map.put("type", type);
        }
        map.put("classCode", classCode);
        examineeListService.queryForGrade(map);
        // 查询出该学生阶段测试成绩
        return examineeListService.queryForGrade(map);
    }

    /**
     * 删除
     * 
     * @param deleteIds
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("数据管理删除");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            examineeListService.delete(deleteIds);
            map.put("message", "删除成功");
            map.put("flag", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除数据管理失败,请稍后重试!");
        }
        return map;
    }

    /**
     * 管理上传excle
     * 
     * @return
     */
    @RequestMapping(value = "updateexcel")
    public ModelAndView updateexcelview(String code, String stage) {
        ModelAndView mav = new ModelAndView("/northamerica/examineelist/updateexcel");
        mav.addObject("code", code);
        mav.addObject("stage", stage);
        return mav;
    }

    /**
     * 导入excel
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upexcel")
    public Map<String, Object> upexcel(HttpServletRequest request, String stage) {
        String code = request.getParameter("code");
        String classCode = "";
        List<ExamineeList> ClassCode = examineeListService.queryforClassCode(code);
        for (ExamineeList list : ClassCode) {
            classCode = list.getClassCode();
        }
        if ("预备".equals(stage)) {
            return addexcleService.upexcel(request, classCode, code);
        }
        else {
            return elvoService.upexcel(request, classCode, code);
        }
    }

    @RequestMapping(value = "queryDetailReportTotal")
    @ResponseBody
    public List<ExamineeList> queryDetailReportTotal(HttpServletRequest request, String code) {
        HttpSession session = request.getSession();
        code = (String) session.getAttribute("Code");
        String classCode = (String) session.getAttribute("classCode");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
//        map.put("classCode", classCode);
        List<ExamineeList> list = examineeListService.queryByCodeTotal(map);
        int mkTime = 1;
        int skTime = 1;
        for (ExamineeList examineeList : list) {
            String type = examineeList.getType();
            String stri = String.valueOf(mkTime);
            String sk = String.valueOf(skTime);
            if ("模考".equals(type)) {
                examineeList.setType(type + stri);
                mkTime++;
            }
            if ("实考".equals(type)) {
                examineeList.setType(type + sk);
                skTime++;
            }
        }
        return list;

    }
    
    @RequestMapping("copyUrl")
    public ModelAndView copyUrl(String code,String classCode,String name){
        ModelAndView mav=new  ModelAndView("/northamerica/examineelist/copyUrl");
        mav.addObject("classCode", classCode);
        mav.addObject("code", code);
        mav.addObject("copyUrl", copyUrl);
        mav.addObject("name", name);
        return mav;
    }
}