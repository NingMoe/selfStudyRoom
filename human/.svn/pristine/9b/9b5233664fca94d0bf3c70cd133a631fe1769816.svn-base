package com.human.northamerica.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.human.jw.entity.JwCourse;
import com.human.jw.service.JwService;
import com.human.northamerica.dao.MentionInfoDao;
import com.human.northamerica.entity.ActBean;
import com.human.northamerica.entity.BonusAlgorithm;
import com.human.northamerica.entity.ClassTeach;
import com.human.northamerica.entity.MentionInfo;
import com.human.northamerica.entity.MentionInfoClass;
import com.human.northamerica.entity.MentionTeachInfo;
import com.human.northamerica.entity.SatBean;
import com.human.northamerica.entity.ToffBean;
import com.human.northamerica.entity.USAClassInfo;
import com.human.northamerica.service.MentionService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;
import com.human.utils.RegExpValidatorUtils;

@Service
public class MentionServiceImpl implements MentionService {
    
    @Resource
    private MentionInfoDao mentionInfoDao;
    
    @Resource
    private JwService jwService;

    @Override
    public PageView queryInfoPage(PageView pageView, MentionInfo info) {
        List<MentionInfo> list = mentionInfoDao.queryMentionInfoList(info);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        list= list.subList(i, j);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @SuppressWarnings("all")
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> importScore(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        int examType = Integer.valueOf(request.getParameter("importType"));
        Map<String, Boolean> booleanChar = new HashMap<String, Boolean>();
        booleanChar.put("是", true);
        booleanChar.put("否", false);
        if (examType == 1) {
            // 导入托福成绩
            ExcelUtil<ToffBean> ex = new ExcelUtil<ToffBean>(1, 0);
            List<ToffBean> list = new ArrayList<ToffBean>();
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("toffFile");
            List<String> errorMsg = new ArrayList<String>();
            result.put("errorMsg", errorMsg);
            if (file.isEmpty()) {
                result.put("flag", false);
                result.put("msg", "文件内容为空，请重新选择文件!");
                return result;
            }
            try {
                Map<String, Object> empTeachMap = ex.checkAccount(file, ToffBean.class);
                if (null != empTeachMap && empTeachMap.get("flag").toString().equals("false")) {
                    result.put("flag", false); 
                    result.put("msg", "解析文档出错");
                    return result;
                }
                list = (List<ToffBean>) empTeachMap.get("list");
            }
            catch (Exception e) {
                e.printStackTrace();
                result.put("flag", false);
                result.put("msg", "解析导入文件出错!");
                return result;
            }
            if (list.size() == 0) {
                result.put("flag", false);
                result.put("msg", "导入文件为空文件!");
                return result;
            }
            int index = 1;
            String msg = "";
            for (ToffBean toff : list) {
                index++;
                boolean isHasEmp = false;
                String examDate = toff.getExamDate();
                if (null == examDate || examDate.trim().length() == 0) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】不能为空!";
                    errorMsg.add(msg);
                }
                if (!RegExpValidatorUtils.isDate(examDate)) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】格式不正确或日期非法!";
                    errorMsg.add(msg);
                }
                if (examDate.indexOf(" ") > -1) {
                    examDate = examDate.substring(0, examDate.indexOf(" "));
                    toff.setExamDate(examDate);
                }
                String studentCode = toff.getStudentCode();
                if (StringUtils.isBlank(studentCode)) {
                    msg = "第" + index + "行错误，错误信息：【学员号】不能为空!";
                    errorMsg.add(msg);
                }
                String isCollege = toff.getIsCollege();
                if (StringUtils.isBlank(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】输入不正确!";
                    errorMsg.add(msg);
                }

                String schoolName = toff.getSchoolName();
                if (StringUtils.isBlank(schoolName)) {
                    msg = "第" + index + "行错误，错误信息：【学校名】不能为空!";
                    errorMsg.add(msg);
                }

                String isFirst = toff.getIsFirst();
                if (StringUtils.isBlank(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】输入不正确!";
                    errorMsg.add(msg);
                }

                String totalScore = toff.getTotalScore();
                if (StringUtils.isBlank(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】成绩不合法!";
                    errorMsg.add(msg);
                }

                String readScore = toff.getReadScore();
                if (StringUtils.isBlank(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】成绩不合法!";
                    errorMsg.add(msg);
                }

                String listenScore = toff.getListenScore();
                if (StringUtils.isBlank(listenScore)) {
                    msg = "第" + index + "行错误，错误信息：【听力】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(listenScore)) {
                    msg = "第" + index + "行错误，错误信息：【听力】成绩不合法!";
                    errorMsg.add(msg);
                }

                String speakScore = toff.getSpeakScore();
                if (StringUtils.isBlank(speakScore)) {
                    msg = "第" + index + "行错误，错误信息：【口语】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(speakScore)) {
                    msg = "第" + index + "行错误，错误信息：【口语】成绩不合法!";
                    errorMsg.add(msg);
                }

                String writeScore = toff.getWriteScore();
                if (StringUtils.isBlank(writeScore)) {
                    msg = "第" + index + "行错误，错误信息：【写作】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(writeScore)) {
                    msg = "第" + index + "行错误，错误信息：【写作】成绩不合法!";
                    errorMsg.add(msg);
                }
            }
            if (errorMsg.size() > 0) {
                result.put("flag", false);
                result.put("msg", "文档内容不正确!");
                result.put("errorMsg", errorMsg);
                return result;
            }
            else {
                Long userId = Common.getMyUser().getUserid();
                for (ToffBean toff : list) {
                    MentionInfo mi = new MentionInfo();
                    mi.setExamDate(toff.getExamDate());
                    mi.setStudentCode(toff.getStudentCode());
                    mi.setExamType(1);
                    List<MentionInfo> s = mentionInfoDao.queryMentionInfo(mi);
                    mi.setManagerTeach(toff.getManagerTeach());
                    mi.setUploadUser(userId);
                    mi.setStudentName(toff.getStudentName());
                    mi.setGuideTeach(toff.getGuideTeach());
                    mi.setIsCollege(booleanChar.get(toff.getIsCollege()));
                    mi.setSchoolName(toff.getSchoolName());
                    mi.setIsFirst(booleanChar.get(toff.getIsFirst()));
                    mi.setTotalScore(toff.getTotalScore());
                    mi.setReadScore(toff.getReadScore());
                    mi.setListenScore(toff.getListenScore());
                    mi.setSpeakScore(toff.getSpeakScore());
                    mi.setWriteScore(toff.getWriteScore());
                    if (s.size() > 0) {
                        mi.setId(s.get(0).getId());
                        mi.setStudentName(s.get(0).getStudentName());
                        mentionInfoDao.editMention(mi);
                    }
                    else {
                        mentionInfoDao.saveMentionInfo(mi);
                    }
                }
            }
        } else if (examType == 2) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("actFile");
            List<String> errorMsg = new ArrayList<String>();
            result.put("errorMsg", errorMsg);
            if (file.isEmpty()) {
                result.put("flag", false);
                result.put("msg", "文件内容为空，请重新选择文件!");
                return result;
            }
            // 导入ACT成绩
            ExcelUtil<ActBean> ex = new ExcelUtil<ActBean>(1, 0);
            List<ActBean> list = new ArrayList<ActBean>();
            try {
                Map<String, Object> empTeachMap = ex.checkAccount(file, ActBean.class);
                if (null != empTeachMap && empTeachMap.get("flag").toString().equals("false")) {
                    result.put("flag", false);
                    result.put("msg", "解析文档出错");
                    return result;
                }
                list = (List<ActBean>) empTeachMap.get("list");
            }
            catch (Exception e) {
                e.printStackTrace();
                result.put("flag", false);
                result.put("msg", "解析导入文件出错!");
                return result;
            }
            if (list.size() == 0) {
                result.put("flag", false);
                result.put("msg", "导入文件为空文件!");
                return result;
            }
            int index = 1;
            String msg = "";
            for (ActBean act : list) {
                index++;
                boolean isHasEmp = false;
                String examDate = act.getExamDate();
                if (null == examDate || examDate.trim().length() == 0) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】不能为空!";
                    errorMsg.add(msg);
                }
                if (!RegExpValidatorUtils.isDate(examDate)) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】格式不正确或日期非法!";
                    errorMsg.add(msg);
                }
                if (examDate.indexOf(" ") > -1) {
                    examDate = examDate.substring(0, examDate.indexOf(" "));
                    act.setExamDate(examDate);
                }
                String studentCode = act.getStudentCode();
                if (StringUtils.isBlank(studentCode)) {
                    msg = "第" + index + "行错误，错误信息：【学员号】不能为空!";
                    errorMsg.add(msg);
                }
                String isCollege = act.getIsCollege();
                if (StringUtils.isBlank(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】输入不正确!";
                    errorMsg.add(msg);
                }

                String studentName = act.getSchoolName();
                if (StringUtils.isBlank(studentName)) {
                    msg = "第" + index + "行错误，错误信息：【学校名】不能为空!";
                    errorMsg.add(msg);
                }

                String isFirst = act.getIsFirst();
                if (StringUtils.isBlank(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】输入不正确!";
                    errorMsg.add(msg);
                }

                String joinToefl =  act.getJoinToefl();
                if(StringUtils.isBlank(joinToefl)&&"是".equals(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【入班托福成绩】不能为空!";
                    errorMsg.add(msg);
                }
                
                String totalScore = act.getTotalScore();
                if (StringUtils.isBlank(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】成绩不合法!";
                    errorMsg.add(msg);
                }

                String readScore = act.getReadScore();
                if (StringUtils.isBlank(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】成绩不合法!";
                    errorMsg.add(msg);
                }
                
                String grammarScore = act.getGrammarScore();
                if (StringUtils.isBlank(grammarScore)) {
                    msg = "第" + index + "行错误，错误信息：【文法】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(grammarScore)) {
                    msg = "第" + index + "行错误，错误信息：【文法】成绩不合法!";
                    errorMsg.add(msg);
                }

                String mathScore = act.getMatheScore();
                if (StringUtils.isBlank(mathScore)) {
                    msg = "第" + index + "行错误，错误信息：【数学】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(mathScore)) {
                    msg = "第" + index + "行错误，错误信息：【数学】成绩不合法!";
                    errorMsg.add(msg);
                }

                String reasonScore = act.getReasonScore();
                if (StringUtils.isBlank(reasonScore)) {
                    msg = "第" + index + "行错误，错误信息：【科推】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(reasonScore)) {
                    msg = "第" + index + "行错误，错误信息：【科推】成绩不合法!";
                    errorMsg.add(msg);
                }

                String writeScore = act.getWriteScore();
                if (!StringUtils.isBlank(writeScore)&&!RegExpValidatorUtils.isScore(writeScore)) {
                    msg = "第" + index + "行错误，错误信息：【写作】成绩不合法!";
                    errorMsg.add(msg);
                }
            }
            if (errorMsg.size() > 0) {
                result.put("flag", false);
                result.put("msg", "文档内容不正确!");
                result.put("errorMsg", errorMsg);
                return result;
            }
            else {
                Long userId = Common.getMyUser().getUserid();
                for (ActBean act : list) {
                    MentionInfo mi = new MentionInfo();
                    mi.setExamDate(act.getExamDate());
                    mi.setStudentCode(act.getStudentCode());
                    mi.setExamType(2);
                    List<MentionInfo> s = mentionInfoDao.queryMentionInfo(mi);
                    mi.setManagerTeach(act.getManagerTeach());
                    mi.setUploadUser(userId);
                    mi.setStudentName(act.getStudentName());
                    mi.setIsCollege(booleanChar.get(act.getIsCollege()));
                    mi.setSchoolName(act.getSchoolName());
                    mi.setIsFirst(booleanChar.get(act.getIsFirst()));
                    mi.setJoinToefl(act.getJoinToefl());
                    mi.setTotalScore(act.getTotalScore());
                    mi.setReadScore(act.getReadScore());
                    mi.setGrammarScore(act.getGrammarScore());
                    mi.setMatheScore(act.getMatheScore());
                    mi.setReasonScore(act.getReasonScore());
                    mi.setWriteScore(act.getWriteScore());
                    if (s.size() > 0) {
                        mi.setId(s.get(0).getId());
                        mi.setStudentName(s.get(0).getStudentName());
                        mentionInfoDao.editMention(mi);
                    }
                    else {
                        mentionInfoDao.saveMentionInfo(mi);
                    }
                }
            }
        } else  if (examType == 3) {
            // 导入SAT成绩
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("satFile");
            List<String> errorMsg = new ArrayList<String>();
            result.put("errorMsg", errorMsg);
            if (file.isEmpty()) {
                result.put("flag", false);
                result.put("msg", "文件内容为空，请重新选择文件!");
                return result;
            }
            ExcelUtil<SatBean> ex = new ExcelUtil<SatBean>(1, 0);
            List<SatBean> list = new ArrayList<SatBean>();
            try {
                Map<String, Object> empTeachMap = ex.checkAccount(file, SatBean.class);
                if (null != empTeachMap && empTeachMap.get("flag").toString().equals("false")) {
                    result.put("flag", false);
                    result.put("msg", "解析文档出错");
                    return result;
                }
                list = (List<SatBean>) empTeachMap.get("list");
            }
            catch (Exception e) {
                e.printStackTrace();
                result.put("flag", false);
                result.put("msg", "解析导入文件出错!");
                return result;
            }
            if (list.size() == 0) {
                result.put("flag", false);
                result.put("msg", "导入文件为空文件!");
                return result;
            }
            int index = 1;
            String msg = "";
            for (SatBean sat : list) {
                index++;
                boolean isHasEmp = false;
                String examDate = sat.getExamDate();
                if (null == examDate || examDate.trim().length() == 0) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】不能为空!";
                    errorMsg.add(msg);
                }
                if (!RegExpValidatorUtils.isDate(examDate)) {
                    msg = "第" + index + "行错误，错误信息：【考试日期】格式不正确或日期非法!";
                    errorMsg.add(msg);
                }
                if (examDate.indexOf(" ") > -1) {
                    examDate = examDate.substring(0, examDate.indexOf(" "));
                    sat.setExamDate(examDate);
                }
                String studentCode = sat.getStudentCode();
                if (StringUtils.isBlank(studentCode)) {
                    msg = "第" + index + "行错误，错误信息：【学员号】不能为空!";
                    errorMsg.add(msg);
                }
                String isCollege = sat.getIsCollege();
                if (StringUtils.isBlank(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isCollege)) {
                    msg = "第" + index + "行错误，错误信息：【是否大学生】输入不正确!";
                    errorMsg.add(msg);
                }

                String studentName = sat.getSchoolName();
                if (StringUtils.isBlank(studentName)) {
                    msg = "第" + index + "行错误，错误信息：【学校名】不能为空!";
                    errorMsg.add(msg);
                }

                String isFirst = sat.getIsFirst();
                if (StringUtils.isBlank(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!booleanChar.containsKey(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【是否首考】输入不正确!";
                    errorMsg.add(msg);
                }

                String joinToefl =  sat.getJoinToefl();
                if(StringUtils.isBlank(joinToefl)&&"是".equals(isFirst)) {
                    msg = "第" + index + "行错误，错误信息：【入班托福成绩】不能为空!";
                    errorMsg.add(msg);
                }
                
                String totalScore = sat.getTotalScore();
                if (StringUtils.isBlank(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(totalScore)) {
                    msg = "第" + index + "行错误，错误信息：【总分】成绩不合法!";
                    errorMsg.add(msg);
                }

                String readScore = sat.getReadScore();
                if (StringUtils.isBlank(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(readScore)) {
                    msg = "第" + index + "行错误，错误信息：【阅读】成绩不合法!";
                    errorMsg.add(msg);
                }
                
                String grammarScore = sat.getGrammarScore();
                if (StringUtils.isBlank(grammarScore)) {
                    msg = "第" + index + "行错误，错误信息：【文法】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(grammarScore)) {
                    msg = "第" + index + "行错误，错误信息：【文法】成绩不合法!";
                    errorMsg.add(msg);
                }

                String mathScore = sat.getMatheScore();
                if (StringUtils.isBlank(mathScore)) {
                    msg = "第" + index + "行错误，错误信息：【数学】不能为空!";
                    errorMsg.add(msg);
                }
                else if (!RegExpValidatorUtils.isScore(mathScore)) {
                    msg = "第" + index + "行错误，错误信息：【数学】成绩不合法!";
                    errorMsg.add(msg);
                }

                String writeScore = sat.getWriteScore();
                if (!StringUtils.isBlank(writeScore)&&!RegExpValidatorUtils.isScore(writeScore)) {
                    msg = "第" + index + "行错误，错误信息：【写作】成绩不合法!";
                    errorMsg.add(msg);
                }
            }
            if (errorMsg.size() > 0) {
                result.put("flag", false);
                result.put("msg", "文档内容不正确!");
                result.put("errorMsg", errorMsg);
                return result;
            }
            else {
                Long userId = Common.getMyUser().getUserid();
                for (SatBean sat : list) {
                    MentionInfo mi = new MentionInfo();
                    mi.setExamDate(sat.getExamDate());
                    mi.setStudentCode(sat.getStudentCode());
                    mi.setExamType(3);
                    List<MentionInfo> s = mentionInfoDao.queryMentionInfo(mi);
                    mi.setManagerTeach(sat.getManagerTeach());
                    mi.setUploadUser(userId);
                    mi.setStudentName(sat.getStudentName());
                    mi.setIsCollege(booleanChar.get(sat.getIsCollege()));
                    mi.setSchoolName(sat.getSchoolName());
                    mi.setIsFirst(booleanChar.get(sat.getIsFirst()));
                    mi.setJoinToefl(sat.getJoinToefl());
                    mi.setTotalScore(sat.getTotalScore());
                    mi.setReadScore(sat.getReadScore());
                    mi.setGrammarScore(sat.getGrammarScore());
                    mi.setMatheScore(sat.getMatheScore());
                    mi.setWriteScore(sat.getWriteScore());
                    if (s.size() > 0) {
                        mi.setId(s.get(0).getId());
                        mi.setStudentName(s.get(0).getStudentName());
                        mentionInfoDao.editMention(mi);
                    }
                    else {
                        mentionInfoDao.saveMentionInfo(mi);
                    }
                }
            }
        }else {
            result.put("flag", false);
            result.put("msg", "考试类型不正确!");
            return result;
        }
        result.put("flag", true);
        result.put("msg", "导入成功!");
        return result;
    }

    @Override
    public void saveMention(MentionInfo info) {
        List<MentionInfo> s = mentionInfoDao.queryMentionInfo(info);
        info.setUploadUser(Common.getMyUser().getUserid());
        if(s.size()>0){
            info.setId(s.get(0).getId());
            info.setStudentName(s.get(0).getStudentName());
            if(null==info.getIsCollege()) {
                info.setIsCollege(false);
            }
            if(null==info.getIsFirst()) {
                info.setIsFirst(false);
            }
            mentionInfoDao.editMention(info);
        }else{
            mentionInfoDao.saveMentionInfo(info);
        }
    }

    @Override
    public MentionInfo queryById(Long id) {
        return  mentionInfoDao.queryById(id);
    }

    @Override
    public void editMention(MentionInfo info) {
        if(null==info.getIsCollege()) {
            info.setIsCollege(false);
        }
        if(null==info.getIsFirst()) {
            info.setIsFirst(false);
        }
          mentionInfoDao.editMention(info);
    }

    @Override
    public void delMentionByIds(String deleteIds) {
        String[] ids=deleteIds.split(",");
        mentionInfoDao.delMentionByIds(ids);
    }

    @Override
    public PageView queryMentionReport(PageView pageView, MentionInfo info) {
        List<MentionInfo> list = mentionInfoDao.queryReportList(info);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        list= list.subList(i, j);
        pageView.setRecords(list);
        //计算奖金
        if(info.getExamType()==1) {
            //托福计算奖金
            for(MentionInfo mi:list){
                Double readBouns=getBouns(mi.getReadScore(),mi.getPreReadScore(), 1);
                Double listenBouns=getBouns(mi.getListenScore(),mi.getPreListenScore(), 2);
                Double speakBouns=getBouns(mi.getSpeakScore(),mi.getPreSpeakScore(), 3);
                Double writeBouns=getBouns(mi.getWriteScore(),mi.getPreWriteScore(), 4);
                Double q=doubleAdd(0.0, readBouns);
                q=doubleAdd(q, listenBouns);
                q=doubleAdd(q, speakBouns);
                q=doubleAdd(q, writeBouns);
                mi.setBonusListen(listenBouns);
                mi.setBonusRead(readBouns);
                mi.setBonusSpeak(speakBouns);
                mi.setBonusWrite(writeBouns);
                mi.setBonusTotal(q);
            }
        }
   
        pageView.setRecords(list);
        return pageView;
    }

   /**
    * 根据分数算奖金
    * @param score
    * @param type 1.阅读,2.听力,3.口语,4.写作
    * @return
    */
    private Double getBouns(String nowScoreStr,String preScoreStr,int type){
        int nowScore=Integer.parseInt(nowScoreStr);
        if(nowScore>30){
            nowScore=30;
        }
        int preScore=Integer.parseInt(preScoreStr);
        Double sum=0.0;
        if(type==1){
            for(int i=preScore+1;i<=nowScore;i++){
                sum=doubleAdd(sum,BonusAlgorithm.bonusAlg.get(i).getRead());
            }
        }
        if(type==2){
            for(int i=preScore+1;i<=nowScore;i++){
                sum=doubleAdd(sum,BonusAlgorithm.bonusAlg.get(i).getListen());
            }
        }
        if(type==3){
            for(int i=preScore+1;i<=nowScore;i++){
                sum=doubleAdd(sum,BonusAlgorithm.bonusAlg.get(i).getSpeak());
            }
        }
        if(type==4){
            for(int i=preScore+1;i<=nowScore;i++){
                sum=doubleAdd(sum,BonusAlgorithm.bonusAlg.get(i).getWrite());
            }
        }
        return sum;
    }
    
    public  Double doubleAdd(Double v1, Double v2) {  
        BigDecimal b1 = new BigDecimal(v1.toString());  
        BigDecimal b2 = new BigDecimal(v2.toString());  
        return new Double(b1.add(b2).doubleValue());  
     }  
    @Override
    public List<MentionInfo> queryMentionInfo(MentionInfo info) {
        return  mentionInfoDao.queryMentionInfo(info);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<String> refreshClassInfo() {
        List<MentionInfo> list = mentionInfoDao.queryRefreshRows();
        List<String> errorList = new ArrayList<String>();
        for (MentionInfo mi : list) {
            try {
                String getStudentCodeUrl = "http://wxapidata.xdf.cn/1/wechat/student/classes?accessToken=ef4e3a2b-ae21-4cad-ada8-86ec46a8a83g&appId=702&schoolId=25&studentCode=" + mi.getStudentCode();
                String result = HttpClientUtil.httpGetRequest(getStudentCodeUrl, null);
                Map<String, Object> jsonObj = JSONObject.parseObject(result);
                Map<String, Object> m = (Map<String, Object>) jsonObj.get("classesByStatus");
                if (null == m) {
                    continue;
                }
                mi.setStudentName(String.valueOf(jsonObj.get("studentName")));
                mentionInfoDao.editMentionStudentName(mi);
                List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> l = (List<Map<String, Object>>) m.get("2");
                if(l!=null){
                    resultList.addAll(l);
                }
                List<Map<String, Object>> n = (List<Map<String, Object>>) m.get("1");
                if(n!=null){
                    resultList.addAll(n);
                }
                updateRoomAndTeach(resultList,mi);
            }
            catch (Exception e) {
                e.printStackTrace();
                errorList.add("学员号:" + mi.getStudentCode() + "更新信息失败!");
            }
        }
        return errorList;
    }
    
    
    @SuppressWarnings("unchecked")
    private void updateRoomAndTeach( List<Map<String, Object>> list,MentionInfo info) throws Exception {
        String infoGrade=null;
        if(Integer.valueOf(1)==info.getExamType()){
            infoGrade="TOEFL";
        }else if(Integer.valueOf(2)==info.getExamType()){
            infoGrade="ACT";
        }else if (Integer.valueOf(3)==info.getExamType()){
            infoGrade="新SAT";
        }else{
            return;
        }
        List<Map<String,Object>> endClassList=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> nowClassList=new ArrayList<Map<String,Object>>();
            for(Map<String,Object> classRoom:list){
                String classCode=String.valueOf(classRoom.get("classCode")).trim().toUpperCase();
                Integer valid=(Integer) classRoom.get("valid");
                if(classCode.startsWith("TMK")||classCode.startsWith("VSZJ")||classCode.startsWith("VIPZJ")||valid==0) {
                    //托福模考班、VSJZ、VIPZJ开头班级排除,无效班级剔除
                    continue;
                }
           
                String getStudentCodeUrl = "http://wxapidata.xdf.cn/1/wechat/class_simple?schoolId=25&classCodes=" + classRoom.get("classCode");
                String result = HttpClientUtil.httpGetRequest(getStudentCodeUrl, null);
                JSONArray js = JSONArray.parseArray(result);
                Map<String, Object> teachResult = (Map<String, Object>) js.get(0);
                String gradeName=String.valueOf(teachResult.get("gradeName"));
                if(infoGrade.equals("新SAT")) {
                    if(gradeName.startsWith(infoGrade)) {
                        gradeName=infoGrade;
                    }
                }
                String classAddr=String.valueOf(teachResult.get("classAddress"));
                if(!gradeName.equals(infoGrade)){
                    //和考试不是同种班级，排除
                    continue;
                }
                if("新SAT".equals(infoGrade)||"ACT".equals(infoGrade)){
                    if(classAddr.contains("香港")) {
                        // SAT和ACT排除上课地址包含香港
                        continue;
                    }
                }
                String classStartDate=String.valueOf(teachResult.get("classStartDate")).substring(0,10);
                String classEndDate=String.valueOf(teachResult.get("classEndDate")).substring(0,10);
                List<Map<String, String>> teachList = (List<Map<String, String>>) teachResult.get("teachers");
               List<ClassTeach> ctList=new ArrayList<ClassTeach>();
                for (Map<String, String> m : teachList) {
                    ClassTeach ct=new ClassTeach();
                    ct.setTeachCode(m.get("teacherCode"));
                    ct.setClassCode(String.valueOf(classRoom.get("classCode")));
                    ct.setTeachName(m.get("teacherName"));
                    ctList.add(ct);
                }  
                String examDate=info.getExamDate();
                int res=examDate.compareTo(classEndDate);
                int res1=examDate.compareTo(classStartDate);
                Map<String,Object> map=new HashMap<String,Object>();
                if(res>=0&&!isTimeBefore(examDate,classEndDate)){
                    map.put("classCode",  classRoom.get("classCode"));
                    map.put("sortDate", classEndDate);
                    map.put("classStartDate", String.valueOf(teachResult.get("classStartDate")));
                    map.put("classEndDate", String.valueOf(teachResult.get("classEndDate")));
                    map.put("ctList", ctList);
                    endClassList.add(map);
                }else if(res<0&&res1>=0){
                    map.put("classCode",  classRoom.get("classCode"));
                    map.put("ctList", ctList);
                    map.put("sortDate", classStartDate);
                    map.put("classStartDate", String.valueOf(teachResult.get("classStartDate")));
                    map.put("classEndDate", String.valueOf(teachResult.get("classEndDate")));
                    nowClassList.add(map);
                }
            }
            //重新排序结课课程的结课时间
            Collections.sort(endClassList, new Comparator<Map<String, Object>>(){
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {  
                  int res=  String.valueOf(o1.get("sortDate")).compareTo(String.valueOf(o2.get("sortDate")));
                    if(res>0){  
                        return -1;  
                    }  
                    if(res==0){  
                        return 0;  
                    }  
                    return 1;  
                }  
            });
         int vsCount=0;
         //当前插入了几条
         int nowCount=0;
        for (int i = 0; i < endClassList.size(); i++) {
            if(nowCount>=3) {
                //大于三条结束结课班级的获取
                break;
            }
            if (info.getExamType() == 1 && String.valueOf(endClassList.get(i).get("classCode")).toUpperCase().startsWith("VS")) {
                //托福VS开头的班级只取一个
                if (vsCount == 0) {
                    insertClass(endClassList.get(i),info,0);
                    vsCount++;
                }else {
                    continue;
                }
            }else {
                insertClass(endClassList.get(i),info,0);
            }
            nowCount++;
        }
            Collections.sort(nowClassList, new Comparator<Map<String, Object>>(){
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {  
                    //按照学生的年龄进行升序排列  
                    int res=  String.valueOf(o1.get("sortDate")).compareTo(String.valueOf(o2.get("sortDate")));
                    if(res>0){  
                        return 1;  
                    }  
                    if(res==0){  
                        return 0;  
                    }  
                    return -1;  
                }  
            });
            int nowSize=nowClassList.size();
            if(nowSize>3){
                nowSize=3;
            }
            for (int i = 0; i <nowSize; i++) {
                  insertClass(nowClassList.get(i),info,1);
            }
    }

    
    @SuppressWarnings("unchecked")
    public void insertClass(Map<String,Object> map,MentionInfo info,Integer type) throws Exception {
        MentionInfoClass mic = new MentionInfoClass();
        mic.setInfoId(info.getId());
        mic.setRoomCode(String.valueOf(map.get("classCode")));
        mic.setType(type);
       int j=mentionInfoDao.insertInfoClass(mic);
       //插入成功，代表首次插入，进行班级信息录入
       if(j==1) {
           USAClassInfo usaClass = new USAClassInfo();
           usaClass.setClassCode(String.valueOf(map.get("classCode")));
           usaClass.setClassType(info.getExamType());
           usaClass.setStartTime(String.valueOf(map.get("classStartDate")));
           usaClass.setEndTime(String.valueOf(map.get("classEndDate")));
           int k=mentionInfoDao.insertClass(usaClass);
           //插入成功，代表班级首次录入，处理班级老师信息
           if(k==1) {
             //处理老师信息
               List<ClassTeach> ctList=(List<ClassTeach>) map.get("ctList");
               for(ClassTeach ct:ctList) {
                   List<JwCourse> jwList=jwService.getTeacherJwCourses(ct.getTeachCode(), usaClass.getStartTime().substring(0,10),usaClass.getEndTime().substring(0,10));
                   for(JwCourse jc:jwList) {
                       if(jc.getsClassCode().equals(mic.getRoomCode())&&jc.getsCourseName().indexOf("教辅课")==-1) {
                           ct.setTeachSub(courseToType(jc.getsCourseName(),info.getExamType()));
                           mentionInfoDao.insertClassTeach(ct);
                           break;
                       }
                   }
               }
           }
       }
    }
    
    public String getSubName(Integer subType) {
        //0.未知,1.阅读,2.听力,3.口语,4.写作,5.文法,6.数学.7.科推,8.预备
        switch (subType) {
            case 0:
                return  "未知";
            case 1:
                return  "阅读";
            case 2:
                return  "听力";
            case 3:
                return  "口语";
            case 4:
                return  "写作";
            case 5:
                return  "文法";
            case 6:
                return  "数学";
            case 7:
                return  "科推";
            case 8:
                return  "预备";
            default:
                return "";
        }
        
    }
    
    /**
     * 
     * @param courseName 科次名称
     * @param type 考试类型
     * @return 0.未知,1.阅读,2.听力,3.口语,4.写作,5.文法,6.数学.7.科推,8.预备
     */
    public Integer courseToType(String courseName,Integer type) {
        /**
         * 托福
         */
        if(type==1) {
            if(courseName.indexOf("预备")!=-1) {
                return 8;
            }
            if(courseName.indexOf("词汇")!=-1||courseName.indexOf("语法")!=-1||courseName.indexOf("词汇语法")!=-1||courseName.indexOf("听口")!=-1||courseName.indexOf("读写")!=-1||courseName.indexOf("听力口语")!=-1||courseName.indexOf("阅读写作")!=-1) {
                return 8;
            }
            if(courseName.indexOf("阅读")!=-1) {
                return 1;
            }
            if(courseName.indexOf("听力")!=-1) {
                return 2;
            }
            if(courseName.indexOf("口语")!=-1) {
                return 3;
            }
            if(courseName.indexOf("写作")!=-1) {
                return 4;
            }
        }
        
        /**
         * ACT
         */
        if(type==2) {
            if(courseName.indexOf("词汇")!=-1||courseName.indexOf("阅读")!=-1) {
                return 1;
            }
            if(courseName.indexOf("文法")!=-1||courseName.indexOf("英语")!=-1||courseName.indexOf("语法")!=-1) {
                return 5;
            }
            if(courseName.indexOf("数学")!=-1) {
                return 6;
            }
            if(courseName.indexOf("科推")!=-1||courseName.indexOf("科学推理")!=-1) {
                return 7;
            }
            if(courseName.indexOf("写作")!=-1) {
                return 4;
            }
        }
        
        /**
         * SAT
         */
        if(type==3) {
            if(courseName.indexOf("词汇")!=-1||courseName.indexOf("阅读")!=-1) {
                return 1;
            }
            if(courseName.indexOf("文法")!=-1||courseName.indexOf("英语")!=-1||courseName.indexOf("语法")!=-1) {
                return 5;
            }
            if(courseName.indexOf("数学")!=-1) {
                return 6;
            }
            if(courseName.indexOf("写作")!=-1) {
                return 4;
            }
        }
            return 0;
    }
    
//判断结课时间是否在考试时期的三个月之前
    @SuppressWarnings("static-access")
    public  boolean isTimeBefore(String examDate,String classEndDate) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(examDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, -3);  //设置为前3月
        String defaultStartDate = sdf.format(calendar.getTime());    //格式化前3月的时间
        System.out.println("前3个月的时间是：" + defaultStartDate);
        System.out.println("考试时间是：" + examDate);
        int res=defaultStartDate.compareTo(classEndDate);
        if(res<=0) {
            return false;
        }else {
            return true;
        }
    }
    
    @Override
    public void exportMentionReport(HttpServletRequest request, HttpServletResponse response, MentionInfo info) {
        try {
            List<MentionInfo> resultList = mentionInfoDao.queryReportList(info);
            String str = new SimpleDateFormat("_yyyy-MM-dd").format(new Date());
            XSSFWorkbook temWorkbook =  new XSSFWorkbook();
            XSSFFont   font   =   temWorkbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("微软雅黑");  
            font.setBold(true);
            XSSFCellStyle     titleStyle= temWorkbook.createCellStyle();
            titleStyle.setFont(font);
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            titleStyle.setWrapText(true);  
            // 数据填充的sheet
            XSSFSheet wsheet =temWorkbook.createSheet("提分列表");
            XSSFCellStyle     cStyle= temWorkbook.createCellStyle();
            cStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFFont   font1   =   temWorkbook.createFont();
            font1.setFontHeightInPoints((short) 11);
            font1.setFontName("微软雅黑");  
            cStyle.setFont(font1);
            cStyle.setWrapText(true);  
            //cell.setCellStyle(style);  
            //托福报表数据导出
            if(info.getExamType() == 1) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "学校名",titleStyle);
                
                ExcelUtil.setValue(wsheet, 0, 8, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "在读班级老师",titleStyle);
                
                ExcelUtil.setValue(wsheet, 0, 14, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "指导老师",titleStyle);
                
                ExcelUtil.setValue(wsheet, 0, 16, "总奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次听力",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次听力",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "听力提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "听力奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次口语",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次口语",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "口语提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "口语奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 32, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 33, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 34, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 35, "写作奖金",titleStyle);
                int startRow=1;
                str="托福_"+str;
                for (MentionInfo mi:resultList) {
                    ExcelUtil.setValue(wsheet, startRow, 0,mi.getManagerTeach(),cStyle);
                    Double readBouns = getBouns(mi.getReadScore(),mi.getPreReadScore(), 1);
                    Double listenBouns = getBouns(mi.getListenScore(),mi.getPreListenScore(), 2);
                    Double speakBouns = getBouns(mi.getSpeakScore(),mi.getPreSpeakScore(), 3);
                    Double writeBouns = getBouns(mi.getWriteScore(),mi.getPreWriteScore(), 4);
                    Double q = doubleAdd(0.0, readBouns);
                    q = doubleAdd(q, listenBouns);
                    q = doubleAdd(q, speakBouns);
                    q = doubleAdd(q, writeBouns);
                    mi.setBonusListen(listenBouns);
                    mi.setBonusRead(readBouns);
                    mi.setBonusSpeak(speakBouns);
                    mi.setBonusWrite(writeBouns);
                    mi.setBonusTotal(q);
                    
                    ExcelUtil.setValue(wsheet, startRow, 1, "托福",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getSchoolName(),cStyle);
                    
                    
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 8,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getGuideTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, String.valueOf(mi.getBonusTotal()),cStyle);
                    
                    XSSFCellStyle xcs=upScore(temWorkbook,mi.getTotalScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, mi.getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(mi.getTotalScore())-Integer.valueOf(mi.getPreTotalScore())),xcs);
                    
                    xcs=upScore(temWorkbook,mi.getReadScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, mi.getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, mi.getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(mi.getReadScore())-Integer.valueOf(mi.getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, String.valueOf(mi.getBonusRead()),xcs);
                    
                    xcs=upScore(temWorkbook,mi.getListenScore(),mi.getPreListenScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, mi.getListenScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, mi.getPreListenScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(mi.getListenScore())-Integer.valueOf(mi.getPreListenScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, String.valueOf(mi.getBonusListen()),xcs);
                    
                    xcs=upScore(temWorkbook,mi.getSpeakScore(),mi.getPreSpeakScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, mi.getSpeakScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, mi.getPreSpeakScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(mi.getSpeakScore())-Integer.valueOf(mi.getPreSpeakScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, String.valueOf(mi.getBonusSpeak()),xcs);

                    xcs=upScore(temWorkbook,mi.getWriteScore(),mi.getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, mi.getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, mi.getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(mi.getWriteScore())-Integer.valueOf(mi.getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35, String.valueOf(mi.getBonusWrite()),xcs);
                    startRow++;
                }
                
            }
         
            //ACT报表导出
            if(info.getExamType() == 2) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "在读班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "总奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "文法提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "文法奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "数学提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "数学奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 32, "本次科推",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 33, "上次科推",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 34, "科推提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 35, "科推奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 36, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 37, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 38, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 39, "写作奖金",titleStyle);
                int startRow=1;
                str="ACT_"+str;
                for (MentionInfo mi:resultList) {
                    ExcelUtil.setValue(wsheet, startRow, 0,mi.getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1, "ACT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getSchoolName(),cStyle);
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 8,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getJoinToefl(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, "-",cStyle);
                    
                    XSSFCellStyle xcs=upScore(temWorkbook,mi.getTotalScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, mi.getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(mi.getTotalScore())-Integer.valueOf(mi.getPreTotalScore())),xcs);
                    
                    xcs=upScore(temWorkbook,mi.getReadScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, mi.getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, mi.getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(mi.getReadScore())-Integer.valueOf(mi.getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, "-",xcs);
                    
                    xcs=upScore(temWorkbook,mi.getGrammarScore(),mi.getPreGrammarScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, mi.getGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, mi.getPreGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(mi.getGrammarScore())-Integer.valueOf(mi.getPreGrammarScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, "-",xcs);
                    
                    xcs=upScore(temWorkbook,mi.getMatheScore(),mi.getPreMatheScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, mi.getMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, mi.getPreMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(mi.getMatheScore())-Integer.valueOf(mi.getPreMatheScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, "-",xcs);

                    xcs=upScore(temWorkbook,mi.getReasonScore(),mi.getPreReasonScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, mi.getReasonScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, mi.getPreReasonScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(mi.getReasonScore())-Integer.valueOf(mi.getPreReasonScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35,"-",xcs);
                    
                    xcs=upScore(temWorkbook,mi.getWriteScore(),mi.getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 36, mi.getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 37, mi.getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 38, String.valueOf(Integer.valueOf(mi.getWriteScore())-Integer.valueOf(mi.getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 39,"-",xcs);
                    startRow++;
                }
                
            }
            
            //ACT报表导出
            if(info.getExamType() == 3) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "在读班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "总奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "文法提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "文法奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "数学提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "数学奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 32, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 33, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 34, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 35, "写作奖金",titleStyle);
                int startRow=1;
                str="SAT_"+str;
                for (MentionInfo mi:resultList) {
                    ExcelUtil.setValue(wsheet, startRow, 0,mi.getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1, "SAT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getSchoolName(),cStyle);
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 8,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getJoinToefl(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, "-",cStyle);
                    
                    XSSFCellStyle xcs=upScore(temWorkbook,mi.getTotalScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, mi.getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(mi.getTotalScore())-Integer.valueOf(mi.getPreTotalScore())),xcs);
                    
                    xcs=upScore(temWorkbook,mi.getReadScore(),mi.getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, mi.getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, mi.getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(mi.getReadScore())-Integer.valueOf(mi.getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, "-",xcs);
                    
                    xcs=upScore(temWorkbook,mi.getGrammarScore(),mi.getPreGrammarScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, mi.getGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, mi.getPreGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(mi.getGrammarScore())-Integer.valueOf(mi.getPreGrammarScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, "-",xcs);
                    
                    xcs=upScore(temWorkbook,mi.getMatheScore(),mi.getPreMatheScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, mi.getMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, mi.getPreMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(mi.getMatheScore())-Integer.valueOf(mi.getPreMatheScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, "-",xcs);

                    xcs=upScore(temWorkbook,mi.getWriteScore(),mi.getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, mi.getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, mi.getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(mi.getWriteScore())-Integer.valueOf(mi.getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35,"-",xcs);
                    startRow++;
                }
                
            }
            
            wsheet.autoSizeColumn((short)0);  
            wsheet.autoSizeColumn((short)1);  
            wsheet.autoSizeColumn((short)2);  
            wsheet.autoSizeColumn((short)3);  
            wsheet.autoSizeColumn((short)4);  
            wsheet.autoSizeColumn((short)5);  
            wsheet.autoSizeColumn((short)6);  
            wsheet.autoSizeColumn((short)7);  
            wsheet.autoSizeColumn((short)8);  
            wsheet.autoSizeColumn((short)9);  
            wsheet.autoSizeColumn((short)10);  
            wsheet.autoSizeColumn((short)11);  
            wsheet.autoSizeColumn((short)12);  
            wsheet.autoSizeColumn((short)13);  
            wsheet.autoSizeColumn((short)14);
            wsheet.autoSizeColumn((short)15);
            wsheet.autoSizeColumn((short)16);
            wsheet.autoSizeColumn((short)17);
            wsheet.autoSizeColumn((short)18);
            wsheet.autoSizeColumn((short)19);
            wsheet.autoSizeColumn((short)20);
            wsheet.autoSizeColumn((short)21);
            wsheet.autoSizeColumn((short)22);
            wsheet.autoSizeColumn((short)23);
            wsheet.autoSizeColumn((short)24);
            wsheet.autoSizeColumn((short)25);
            wsheet.autoSizeColumn((short)26);
            wsheet.autoSizeColumn((short)27);
            wsheet.autoSizeColumn((short)28);
            wsheet.autoSizeColumn((short)29);
            wsheet.autoSizeColumn((short)30);
            wsheet.autoSizeColumn((short)31);
            wsheet.autoSizeColumn((short)32);
            wsheet.autoSizeColumn((short)33);
            wsheet.autoSizeColumn((short)34);
            wsheet.autoSizeColumn((short)35);
            wsheet.autoSizeColumn((short)36);
            wsheet.autoSizeColumn((short)37);
            wsheet.autoSizeColumn((short)38);
            wsheet.autoSizeColumn((short)39);
            // 写到输出流并关闭资源
            OutputStream output = response.getOutputStream();
            String fileName = "提分数据_" + str + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition",
                    "attachment;filename=".concat(fileName));
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            temWorkbook.write(output);
            temWorkbook.close();
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String formatMentionScore(String str,String preStr,Double bouns){
        if(null==bouns){
            return preStr+"/"+str;
        }
        return preStr+"/"+str+"("+String.valueOf(bouns)+")";
    }

    public XSSFCellStyle upScore(XSSFWorkbook temWorkbook,String str,String preStr){
        XSSFCellStyle     cStyle= temWorkbook.createCellStyle();
        cStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        XSSFFont   font1   =   temWorkbook.createFont();
        font1.setFontHeightInPoints((short) 11);
        font1.setFontName("微软雅黑");  
        cStyle.setFont(font1);
        Double to=Double.valueOf(str)-Double.valueOf(preStr);
        if(to>0){
            cStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            cStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        if(to<0){
            cStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        return cStyle;
    }

    @Override
    public void exportMentionInfo(HttpServletRequest request, HttpServletResponse response, MentionInfo info) {
        try {
            List<MentionInfo> list = mentionInfoDao.queryMentionInfoList(info);
            String str = new SimpleDateFormat("_yyyy-MM-dd").format(new Date());
            XSSFWorkbook temWorkbook =  new XSSFWorkbook();
            XSSFFont   font   =   temWorkbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("微软雅黑");  
            font.setBold(true);
            XSSFCellStyle     titleStyle= temWorkbook.createCellStyle();
            titleStyle.setFont(font);
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            titleStyle.setWrapText(true);  
            // 数据填充的sheet
            XSSFSheet wsheet =temWorkbook.createSheet();
            XSSFCellStyle     cStyle= temWorkbook.createCellStyle();
            cStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cStyle.setWrapText(true);  
            XSSFFont   font1   =   temWorkbook.createFont();
            font1.setFontHeightInPoints((short) 11);
            font1.setFontName("微软雅黑");  
            cStyle.setFont(font1);
            //托福基础数据导出
            if(info.getExamType() == 1) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "指导老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "听力",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "口语",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "写作",titleStyle);
                int startRow=1;
                str="托福_"+str;
                for (MentionInfo mi : list) {
                    ExcelUtil.setValue(wsheet, startRow, 0, mi.getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1, "托福",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getSchoolName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8, mi.getGuideTeach(),cStyle);
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, mi.getTotalScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getReadScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getListenScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, mi.getSpeakScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getWriteScore(), cStyle);
                    startRow++;
                }
            }
            
          //ACT基础数据导出
            if(info.getExamType() == 2) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "科推",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "写作",titleStyle);
                int startRow=1;
                str="ACT_"+str;
                for (MentionInfo mi : list) {
                    ExcelUtil.setValue(wsheet, startRow, 0, mi.getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1, "ACT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getSchoolName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8, mi.getJoinToefl(),cStyle);
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, mi.getTotalScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getReadScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getGrammarScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, mi.getMatheScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getReasonScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 18, mi.getWriteScore(), cStyle);
                    startRow++;
                }
            }
            
            
          //SAT基础数据导出
            if(info.getExamType() == 3) {
                ExcelUtil.setValue(wsheet, 0, 0, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10, "结课班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "在读班级老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "写作",titleStyle);
                int startRow=1;
                str="SAT_"+str;
                for (MentionInfo mi : list) {
                    ExcelUtil.setValue(wsheet, startRow, 0, mi.getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1, "SAT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2, mi.getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3, mi.getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4, mi.getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5, mi.getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6, mi.getSchoolName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7, mi.getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8, mi.getJoinToefl(),cStyle);
                    String nowRoom="";
                    String nowTeach="";
                    String endRoom="";
                    String endRoomTeach="";
                    for(MentionInfoClass mic:mi.getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                nowTeach=nowTeach+ct.getTeachName()+",";
                            }
                           if(nowTeach.length()>0) {
                               nowTeach=nowTeach.substring(0, nowTeach.length()-1)+";";
                           }
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            for(ClassTeach ct: mic.getCtList()) {
                                endRoomTeach=endRoomTeach+ct.getTeachName()+",";
                            }
                           if(endRoomTeach.length()>0) {
                               endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1)+";";
                           }
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomTeach.length()>0) {
                        endRoomTeach=endRoomTeach.substring(0, endRoomTeach.length()-1);
                    }
                    if(nowTeach.length()>0) {
                        nowTeach=nowTeach.substring(0, nowTeach.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 9,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,endRoomTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11, nowRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12, nowTeach,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 13, mi.getTotalScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14, mi.getReadScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, mi.getGrammarScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, mi.getMatheScore(), cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 17, mi.getWriteScore(), cStyle);
                    startRow++;
                }
            }
            wsheet.autoSizeColumn((short)0);  
            wsheet.autoSizeColumn((short)1);  
            wsheet.autoSizeColumn((short)2);  
            wsheet.autoSizeColumn((short)3);  
            wsheet.autoSizeColumn((short)4);  
            wsheet.autoSizeColumn((short)5);  
            wsheet.autoSizeColumn((short)6);  
            wsheet.autoSizeColumn((short)7);  
            wsheet.autoSizeColumn((short)8);  
            wsheet.autoSizeColumn((short)9);  
            wsheet.autoSizeColumn((short)10);  
            wsheet.autoSizeColumn((short)11);  
            wsheet.autoSizeColumn((short)12); 
            wsheet.autoSizeColumn((short)13);
            wsheet.autoSizeColumn((short)14); 
            wsheet.autoSizeColumn((short)15); 
            wsheet.autoSizeColumn((short)16); 
            wsheet.autoSizeColumn((short)17); 
            wsheet.autoSizeColumn((short)18); 
            // 写到输出流并关闭资源
            OutputStream output = response.getOutputStream();
            String fileName = "成绩记录_" + str + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition",
                    "attachment;filename=".concat(fileName));
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            temWorkbook.write(output);
            temWorkbook.close();
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public PageView queryMentionTeachReport(PageView pageView, MentionInfo info) {
        List<MentionTeachInfo> list = mentionInfoDao.queryMentionTeachReport(info);
        pageView.setRowCount(list.size());
        int j=pageView.getPageNow()*pageView.getPageSize();
        if(j>list.size()){
            j=list.size();
        }
        int i=(pageView.getPageNow()-1)*pageView.getPageSize();
        list= list.subList(i, j);
        pageView.setRecords(list);
        for(MentionTeachInfo mti:list) {
                //计算奖金
                if(mti.getMi().getExamType()==1) {
                    //托福计算奖金
                        
                        Double readBouns=((int)mti.getSub()!=1)?0.0:getBouns(mti.getMi().getReadScore(),mti.getMi().getPreReadScore(), 1);
                        Double listenBouns=((int)mti.getSub()!=2)?0.0:getBouns(mti.getMi().getListenScore(),mti.getMi().getPreListenScore(), 2);
                        Double speakBouns=((int)mti.getSub()!=3)?0.0:getBouns(mti.getMi().getSpeakScore(),mti.getMi().getPreSpeakScore(), 3);
                        Double writeBouns=((int)mti.getSub()!=4)?0.0:getBouns(mti.getMi().getWriteScore(),mti.getMi().getPreWriteScore(), 4);
                        Double q=doubleAdd(0.0, readBouns);
                        q=doubleAdd(q, listenBouns);
                        q=doubleAdd(q, speakBouns);
                        q=doubleAdd(q, writeBouns);
                        mti.getMi().setBonusListen(listenBouns);
                        mti.getMi().setBonusRead(readBouns);
                        mti.getMi().setBonusSpeak(speakBouns);
                        mti.getMi().setBonusWrite(writeBouns);
                        mti.getMi().setBonusTotal(q);
                    }
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void exportTeachMentionReport(HttpServletRequest request, HttpServletResponse response, MentionInfo info) {
        try {
            List<MentionTeachInfo> list = mentionInfoDao.queryMentionTeachReport(info);
            String str = new SimpleDateFormat("_yyyy-MM-dd").format(new Date());
            XSSFWorkbook temWorkbook =  new XSSFWorkbook();
            XSSFFont   font   =   temWorkbook.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("微软雅黑");  
            font.setBold(true);
            XSSFCellStyle     titleStyle= temWorkbook.createCellStyle();
            titleStyle.setFont(font);
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            titleStyle.setWrapText(true);  
            // 数据填充的sheet
            XSSFSheet wsheet =temWorkbook.createSheet("提分列表");
            XSSFCellStyle     cStyle= temWorkbook.createCellStyle();
            cStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFFont   font1   =   temWorkbook.createFont();
            font1.setFontHeightInPoints((short) 11);
            font1.setFontName("微软雅黑");  
            cStyle.setFont(font1);
            cStyle.setWrapText(true);  
            //cell.setCellStyle(style);  
            //托福报表数据导出
            if(info.getExamType() == 1) {
                ExcelUtil.setValue(wsheet, 0, 0, "老师编号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "教学科目",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0,10, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0,11, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 12, "指导老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次听力",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次听力",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "听力提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "听力奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次口语",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次口语",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "口语提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "口语奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 32, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 33, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 34, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 35, "写作奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 36, "总奖金",titleStyle);
                int startRow=1;
                str="托福_"+str;
                for (MentionTeachInfo m:list) {
                    ExcelUtil.setValue(wsheet, startRow, 0,m.getTeacherCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1,m.getTeacherName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2,"托福",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3,getSubName(m.getSub()),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4,m.getMi().getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5,m.getMi().getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6,m.getMi().getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7,m.getMi().getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8,m.getMi().getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,m.getMi().getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,m.getMi().getSchoolName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11,m.getMi().getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12,m.getMi().getGuideTeach(),cStyle);
                    String nowRoom="";
                    String endRoom="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:m.getMi().getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 13,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, nowRoom,cStyle);

                    XSSFCellStyle xcs=upScore(temWorkbook,m.getMi().getTotalScore(),m.getMi().getPreTotalScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, m.getMi().getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, m.getMi().getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(m.getMi().getTotalScore())-Integer.valueOf(m.getMi().getPreTotalScore())),xcs);
                    
                    Double readBouns =((int)m.getSub()!=1)?0.0: getBouns(m.getMi().getReadScore(),m.getMi().getPreReadScore(), 1);
                    Double listenBouns =((int)m.getSub()!=2)?0.0: getBouns(m.getMi().getListenScore(),m.getMi().getPreListenScore(), 2);
                    Double speakBouns =((int)m.getSub()!=3)?0.0: getBouns(m.getMi().getSpeakScore(),m.getMi().getPreSpeakScore(), 3);
                    Double writeBouns =((int)m.getSub()!=4)?0.0: getBouns(m.getMi().getWriteScore(),m.getMi().getPreWriteScore(), 4);
                    Double q = doubleAdd(0.0, readBouns);
                    q = doubleAdd(q, listenBouns);
                    q = doubleAdd(q, speakBouns);
                    q = doubleAdd(q, writeBouns);
                    m.getMi().setBonusListen(listenBouns);
                    m.getMi().setBonusRead(readBouns);
                    m.getMi().setBonusSpeak(speakBouns);
                    m.getMi().setBonusWrite(writeBouns);
                    m.getMi().setBonusTotal(q);
                    xcs=upScore(temWorkbook,m.getMi().getReadScore(),m.getMi().getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, m.getMi().getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, m.getMi().getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(m.getMi().getReadScore())-Integer.valueOf(m.getMi().getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, String.valueOf(m.getMi().getBonusRead()),xcs);

                    xcs=upScore(temWorkbook,m.getMi().getListenScore(),m.getMi().getPreListenScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, m.getMi().getListenScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, m.getMi().getPreListenScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(m.getMi().getListenScore())-Integer.valueOf(m.getMi().getPreListenScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, String.valueOf(m.getMi().getBonusListen()),xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getSpeakScore(),m.getMi().getPreSpeakScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, m.getMi().getSpeakScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, m.getMi().getPreSpeakScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(m.getMi().getSpeakScore())-Integer.valueOf(m.getMi().getPreSpeakScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, String.valueOf(m.getMi().getBonusSpeak()),xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getWriteScore(),m.getMi().getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, m.getMi().getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, m.getMi().getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(m.getMi().getWriteScore())-Integer.valueOf(m.getMi().getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35, String.valueOf(m.getMi().getBonusWrite()),xcs);
                    
                    ExcelUtil.setValue(wsheet, startRow, 36, String.valueOf(m.getMi().getBonusTotal()),xcs);
                    startRow++;
                }
                
            }
         
            //ACT报表导出
            if(info.getExamType() == 2) {
                ExcelUtil.setValue(wsheet, 0, 0, "老师编号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "教学科目",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0,12, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "文法提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "文法奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "数学提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "数学奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 32, "本次科推",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 33, "上次科推",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 34, "科推提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 35, "科推奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 36, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 37, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 38, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 39, "写作奖金",titleStyle);
                int startRow=1;
                str="ACT_"+str;
                for (MentionTeachInfo m:list) {
                    ExcelUtil.setValue(wsheet, startRow, 0,m.getTeacherCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1,m.getTeacherName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2,"ACT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3,getSubName(m.getSub()),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4,m.getMi().getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5,m.getMi().getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6,m.getMi().getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7,m.getMi().getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8,m.getMi().getJoinToefl(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,m.getMi().getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,m.getMi().getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11,m.getMi().getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12,m.getMi().getSchoolName(),cStyle);
                    String nowRoom="";
                    String endRoom="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:m.getMi().getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 13,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, nowRoom,cStyle);
                    
                    XSSFCellStyle xcs=upScore(temWorkbook,m.getMi().getTotalScore(),m.getMi().getPreTotalScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, m.getMi().getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, m.getMi().getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(m.getMi().getTotalScore())-Integer.valueOf(m.getMi().getPreTotalScore())),xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getReadScore(),m.getMi().getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, m.getMi().getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, m.getMi().getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(m.getMi().getReadScore())-Integer.valueOf(m.getMi().getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getGrammarScore(),m.getMi().getPreGrammarScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, m.getMi().getGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, m.getMi().getPreGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(m.getMi().getGrammarScore())-Integer.valueOf(m.getMi().getPreGrammarScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getMatheScore(),m.getMi().getPreMatheScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, m.getMi().getMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, m.getMi().getPreMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(m.getMi().getMatheScore())-Integer.valueOf(m.getMi().getPreMatheScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getReasonScore(),m.getMi().getPreReasonScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, m.getMi().getReasonScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, m.getMi().getPreReasonScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(m.getMi().getReasonScore())-Integer.valueOf(m.getMi().getPreReasonScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getWriteScore(),m.getMi().getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 36, m.getMi().getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 37, m.getMi().getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 38, String.valueOf(Integer.valueOf(m.getMi().getWriteScore())-Integer.valueOf(m.getMi().getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 39, String.valueOf(m.getMi().getBonusWrite()),xcs);
                    startRow++;
                }
            }
            //SAT报表导出
            if(info.getExamType() == 3) {
                ExcelUtil.setValue(wsheet, 0, 0, "老师编号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 1, "老师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 2, "考试类型",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 3, "教学科目",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 4, "学员号",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 5, "学员名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 6, "考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 7, "是否首考",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 8, "入班托福成绩",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 9, "学管师",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 10,"上次考试日期",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 11, "是否大学生",titleStyle);
                ExcelUtil.setValue(wsheet, 0,12, "学校名",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 13, "结课班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 14, "结课班级开课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 15, "结课班级结课时间",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 16, "在读班级",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 17, "上次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 18, "本次总分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 19, "总分提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 20, "本次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 21, "上次阅读",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 22, "阅读提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 23, "阅读奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 24, "本次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 25, "上次文法",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 26, "文法提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 27, "文法奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 28, "本次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 29, "上次数学",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 30, "数学提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 31, "数学奖金",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 36, "本次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 37, "上次写作",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 38, "写作提分",titleStyle);
                ExcelUtil.setValue(wsheet, 0, 39, "写作奖金",titleStyle);
                int startRow=1;
                str="ACT_"+str;
                for (MentionTeachInfo m:list) {
                    ExcelUtil.setValue(wsheet, startRow, 0,m.getTeacherCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 1,m.getTeacherName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 2,"ACT",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 3,getSubName(m.getSub()),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 4,m.getMi().getStudentCode(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 5,m.getMi().getStudentName(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 6,m.getMi().getExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 7,m.getMi().getIsFirst()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 8,m.getMi().getJoinToefl(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 9,m.getMi().getManagerTeach(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 10,m.getMi().getPreExamDate(),cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 11,m.getMi().getIsCollege()?"是":"否",cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 12,m.getMi().getSchoolName(),cStyle);
                    String nowRoom="";
                    String endRoom="";
                    String endRoomStartTime="";
                    String endRoomEndTime="";
                    for(MentionInfoClass mic:m.getMi().getClassList()) {
                        if(mic.getType()==1) {
                            nowRoom=nowRoom+mic.getRoomCode()+";";
                        }
                        if(mic.getType()==0) {
                            endRoom=endRoom+mic.getRoomCode()+";";
                            endRoomStartTime=endRoomStartTime+mic.getStartTime()+";";
                            endRoomEndTime=endRoomEndTime+mic.getEndTime()+";";
                        }
                    }
                    if(nowRoom.length()>0) {
                        nowRoom=nowRoom.substring(0, nowRoom.length()-1);
                    }
                    if(endRoom.length()>0) {
                        endRoom=endRoom.substring(0, endRoom.length()-1);
                    }
                    if(endRoomStartTime.length()>0) {
                        endRoomStartTime=endRoomStartTime.substring(0, endRoomStartTime.length()-1);
                    }
                    if(endRoomEndTime.length()>0) {
                        endRoomEndTime=endRoomEndTime.substring(0, endRoomEndTime.length()-1);
                    }
                    ExcelUtil.setValue(wsheet, startRow, 13,endRoom,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 14,endRoomStartTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 15, endRoomEndTime,cStyle);
                    ExcelUtil.setValue(wsheet, startRow, 16, nowRoom,cStyle);
                    
                    XSSFCellStyle xcs=upScore(temWorkbook,m.getMi().getTotalScore(),m.getMi().getPreTotalScore());
                    ExcelUtil.setValue(wsheet, startRow, 17, m.getMi().getPreTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 18, m.getMi().getTotalScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 19,String.valueOf(Integer.valueOf(m.getMi().getTotalScore())-Integer.valueOf(m.getMi().getPreTotalScore())),xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getReadScore(),m.getMi().getPreReadScore());
                    ExcelUtil.setValue(wsheet, startRow, 20, m.getMi().getReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 21, m.getMi().getPreReadScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 22, String.valueOf(Integer.valueOf(m.getMi().getReadScore())-Integer.valueOf(m.getMi().getPreReadScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 23, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getGrammarScore(),m.getMi().getPreGrammarScore());
                    ExcelUtil.setValue(wsheet, startRow, 24, m.getMi().getGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 25, m.getMi().getPreGrammarScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 26, String.valueOf(Integer.valueOf(m.getMi().getGrammarScore())-Integer.valueOf(m.getMi().getPreGrammarScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 27, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getMatheScore(),m.getMi().getPreMatheScore());
                    ExcelUtil.setValue(wsheet, startRow, 28, m.getMi().getMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 29, m.getMi().getPreMatheScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 30, String.valueOf(Integer.valueOf(m.getMi().getMatheScore())-Integer.valueOf(m.getMi().getPreMatheScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 31, "-",xcs);
                    
                    xcs=upScore(temWorkbook,m.getMi().getWriteScore(),m.getMi().getPreWriteScore());
                    ExcelUtil.setValue(wsheet, startRow, 32, m.getMi().getWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 33, m.getMi().getPreWriteScore(),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 34, String.valueOf(Integer.valueOf(m.getMi().getWriteScore())-Integer.valueOf(m.getMi().getPreWriteScore())),xcs);
                    ExcelUtil.setValue(wsheet, startRow, 35, String.valueOf(m.getMi().getBonusWrite()),xcs);
                    startRow++;
                }
            }
            wsheet.autoSizeColumn((short)0);  
            wsheet.autoSizeColumn((short)1);  
            wsheet.autoSizeColumn((short)2);  
            wsheet.autoSizeColumn((short)3);  
            wsheet.autoSizeColumn((short)4);  
            wsheet.autoSizeColumn((short)5);  
            wsheet.autoSizeColumn((short)6);  
            wsheet.autoSizeColumn((short)7);  
            wsheet.autoSizeColumn((short)8);  
            wsheet.autoSizeColumn((short)9);  
            wsheet.autoSizeColumn((short)10);  
            wsheet.autoSizeColumn((short)11);  
            wsheet.autoSizeColumn((short)12);  
            wsheet.autoSizeColumn((short)13);  
            wsheet.autoSizeColumn((short)14);
            wsheet.autoSizeColumn((short)15);
            wsheet.autoSizeColumn((short)16);
            wsheet.autoSizeColumn((short)17);
            wsheet.autoSizeColumn((short)18);
            wsheet.autoSizeColumn((short)19);
            wsheet.autoSizeColumn((short)20);
            wsheet.autoSizeColumn((short)21);
            wsheet.autoSizeColumn((short)22);
            wsheet.autoSizeColumn((short)23);
            wsheet.autoSizeColumn((short)24);
            wsheet.autoSizeColumn((short)25);
            wsheet.autoSizeColumn((short)26);
            wsheet.autoSizeColumn((short)27);
            wsheet.autoSizeColumn((short)28);
            wsheet.autoSizeColumn((short)29);
            wsheet.autoSizeColumn((short)30);
            wsheet.autoSizeColumn((short)31);
            wsheet.autoSizeColumn((short)32);
            wsheet.autoSizeColumn((short)33);
            wsheet.autoSizeColumn((short)34);
            wsheet.autoSizeColumn((short)35);
            wsheet.autoSizeColumn((short)36);
            wsheet.autoSizeColumn((short)37);
            wsheet.autoSizeColumn((short)38);
            wsheet.autoSizeColumn((short)39);
            // 写到输出流并关闭资源
            OutputStream output = response.getOutputStream();
            String fileName = "教师提分数据_" + str + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition",
                    "attachment;filename=".concat(fileName));
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            temWorkbook.write(output);
            temWorkbook.close();
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
