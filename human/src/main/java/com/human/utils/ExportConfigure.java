package com.human.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出配置
 * 
 * @author 刘威 2016年8月5日上午11:14:22
 * 
 * @version 1.0
 * 
 */
public class ExportConfigure {
    /**
     * 
     * @description 数字转百分比
     * @author 刘威
     * @create 2016年8月5日上午11:14:22
     * @version 1.0
     * @param bigDecimal
     *            bigDecimal
     * @return 返回百分比
     */
    public static String dataToPercent(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0%";
        }
        String result = "0.00%";
        try {
            NumberFormat nt = NumberFormat.getPercentInstance();
            // 设置百分数精确度2即保留两位小数
            nt.setMinimumFractionDigits(0);
            result = nt.format(bigDecimal).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 面试安排导出
     */
    public static Map<String, List<Map<String, Object>>> exportInterview = new HashMap<String, List<Map<String, Object>>>();
   
    /**
     * 人才库导出
     */
    public static Map<String, List<Map<String, Object>>> exportTalent = new HashMap<String, List<Map<String, Object>>>();
    
    /**
     * 面试观察员导出
     */
    public static Map<String, List<Map<String, Object>>> exportWatcher = new HashMap<String, List<Map<String, Object>>>();
    
    /**
     * 我的内推导出
     */
    public static Map<String, List<Map<String, Object>>> exportMyInsideRecommend = new HashMap<String, List<Map<String, Object>>>();
    
        
    /**
     * 内推管理导出
     */
    public static Map<String, List<Map<String, Object>>> exportInsideRecommendManager = new HashMap<String, List<Map<String, Object>>>();
    
    /**
     * 校招管理导出
     */
    public static Map<String, List<Map<String, Object>>> exportSchoolRecruitmentManager = new HashMap<String, List<Map<String, Object>>>();
    
    /**
     * 面试安排信息导出
     */
    public static final List<String> EXPORTINTERVIEW = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("positionName");
            add("circulationName");
            add("seekerName");
            add("telephone");
            add("interviewTime");
            add("interviewLocation");
        }
    };  
    
    /**
     * 人才库信息导出
     */
    public static final List<String> EXPORTTALENT = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("positionName");
            add("applyPosition");
            add("createTime");            
            add("circulationName");
            add("seekerName");
            add("telephone");
            add("graSchool");
            add("highEdu");
            add("major");
        }
    };
    
    /**
     * 面试观察员信息导出
     */
    public static final List<String> EXPORTWATCHER = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("positionName");
            add("applyPosition");
            add("name");
            add("telephone");
            add("graSchool");
            add("highEdu");
            add("major");
            add("resumeStatus");
            add("insideRecommend");
            add("deliveryDate");
            add("approveTime");
        }
    };
    
    /**
     * 我的内推信息导出
     */
    public static final List<String> EXPORTMYINSIDERECOMMEND = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("positionName");
            add("applyPosition");
            add("name");
            add("telephone");
            add("graSchool");
            add("highEdu");
            add("major");
            add("resumeStatus");
            add("deliveryDate");
            add("approveTime");
        }
    };
    
    /**
     * 内推管理信息导出
     */
    public static final List<String> EXPORTINSIDERECOMMENDMANAGER = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("name");
            add("totalCount");
            add("noDealCount");
            add("interviewCount");
            add("eliminateCount");
            add("entryCount");
            add("propagandaLink");
        }
    };
    
    /**
     * 校招管理信息导出
     */
    public static final List<String> EXPORTSCHOOLRECRUITMENTMANAGER = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;
        {
            add("companyName");
            add("deptName");
            add("name");
            add("createUser");
            add("createTime");
            add("totalCount");
            add("noDealCount");
            add("interviewCount");
            add("eliminateCount");
            add("entryCount");
        }
    };
    
}
