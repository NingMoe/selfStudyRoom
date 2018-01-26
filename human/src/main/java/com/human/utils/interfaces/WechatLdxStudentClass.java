package com.human.utils.interfaces;

/**
 * 获取网报学生报班记录
 * @author xdfhf-zx
 *
 */
public class WechatLdxStudentClass {
    
	/**
	 * 班号
	 */
    private String classCode;
    
    /**
     * 班级名称
     */
    private String className;

    /**
     * 开课时间
     */
    private String beginDate;

    /**
     * 结课时间
     */
    private String endDate;

    /**
     * 上课时间
     */
    private String sectBegin;

    /**
     * 价格
     */
    private Integer fee;
    
    /**
     * 离班方式
     * 0 正常
     * 2 转班
     * 3 退班
     */
    private Integer outType;
    
    /**
     * 管理部门
     */
    private String managerDptName;
    
    /**
     * 部门
     */
    private String dptName;
    
    /**
     * 年级
     */
    private String projectName;
    
    /**
     * 听课证号
     */
    private String cardCode;
    
    /**
     * 教学区
     */
    private String areaAddress;

    /**
     * 上课地点
     */
    private String printAddress;
    
    /**
     * 报名渠道
     */
    private Integer channel;
    
    private String studentName;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSectBegin() {
		return sectBegin;
	}

	public void setSectBegin(String sectBegin) {
		this.sectBegin = sectBegin;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getOutType() {
		return outType;
	}

	public void setOutType(Integer outType) {
		this.outType = outType;
	}

	public String getManagerDptName() {
		return managerDptName;
	}

	public void setManagerDptName(String managerDptName) {
		this.managerDptName = managerDptName;
	}

	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

	public String getPrintAddress() {
		return printAddress;
	}

	public void setPrintAddress(String printAddress) {
		this.printAddress = printAddress;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}