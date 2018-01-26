package com.human.examineelist.entity;

import java.util.Date;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 数据管理实体
 * @author yaolu
 *
 */
public class ExamineeList {
    /**
     * 主键
     */
    private long id;
    /**
     * 班号
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="考试类型")
    private String type;
    /**
     * 阶段
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="考试阶段")
    private String stage;
    /**
     * 次数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="第几次")
    private String frequency;
    /**
     * 听口
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="听口成绩")
    private String tkTearcher;
    /**
     * 读写
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="读写成绩")
    private String dTearcher;
    /**
     * 语法
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="语法成绩")
    private String yfTearcher;
    /**
     * L
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="听力")
    private String lTearcher;
    /**
     * R
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="阅读")
    private String rTearcher;
    /**
     * S
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="口语")
    private String sTearcher;
    /**
     * W
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="写作")
    private String wTearcher;
    /**
     * 词测
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="词测成绩")
    private String cice ;
    /**
     * 姓名
     */
    private String name;
    /**
     * 编号
     */
  private String classCode;
    
    /**
     * 类型
     */
    private String code;
    /**
     * 性别
     */
    private String gender;
    /**
     * 时间
     * @return
     */
    private String time;
    
    private String deptName;
    
    private int status;
    
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getTime() {
        return time;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getCice() {
        return cice;
    }
    public void setCice(String cice) {
        this.cice = cice;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public String getTkTearcher() {
        return tkTearcher;
    }
    public void setTkTearcher(String tkTearcher) {
        this.tkTearcher = tkTearcher;
    }
    public String getdTearcher() {
        return dTearcher;
    }
    public void setdTearcher(String dTearcher) {
        this.dTearcher = dTearcher;
    }
    public String getYfTearcher() {
        return yfTearcher;
    }
    public void setYfTearcher(String yfTearcher) {
        this.yfTearcher = yfTearcher;
    }
    public String getrTearcher() {
        return rTearcher;
    }
    public void setrTearcher(String rTearcher) {
        this.rTearcher = rTearcher;
    }
    public String getlTearcher() {
        return lTearcher;
    }
    public void setlTearcher(String lTearcher) {
        this.lTearcher = lTearcher;
    }
    public String getsTearcher() {
        return sTearcher;
    }
    public void setsTearcher(String sTearcher) {
        this.sTearcher = sTearcher;
    }
    public String getwTearcher() {
        return wTearcher;
    }
    public void setwTearcher(String wTearcher) {
        this.wTearcher = wTearcher;
    }
    
    
}