package com.human.basic.entity;

import java.sql.Timestamp;
/**
 * 大学POJO
 * @author liuwei63
 *
 */
public class College {
    /*
     * 主键
     */
    private Long schoolId;
    /*
     * 学校名称
     */
    private String name;
    /*
     * 是否211 0：否 1：是
     */
    private Integer is211;
    /*
     * 是否985 0：否 1：是
     */
    private Integer is985;
    /*
     * 列表排序
     */
    private Long rank;
    /*
     * 办学类型 1大学  2学院  3高等专科学校   4高等职业技术学校  5高等学校分校   6独立学院   7短期职业大学  8成人高等学校   9管理干部学院   10教育学院   11其它
     */
    private Integer type;
    /*
     * 官网地址
     */
    private String site;
    /*
     * 院校分类 1综合 2工科  3农林 4医药 5师范 6语言 7财经 8政法 9体育 10艺术 11民族 12军事 13其它
     */
    private Integer schoolProperty;
    /*
     * 是否教育部直属 0：否 1：是
     */
    private Integer isEdudirect;
    /*
     * 全国排名
     */
    private Long ranking;
    /*
     * 同性质学校排名
     */
    private Long rankingCollegeType;
    /*
     * 学校简述
     */
    private String info;
    /*
     * 省份ID
     */
    private String  provinceId;
    /*
     * 省份名称
     */
    private String provinceName;
    
    /*
     * 城市ID
     */
    private String cityId;
    
    /*
     * 城市ID
     */
    private String cityName;    
    /*
     * 来源:0 同步 1 新建
     */
    private Integer sourceType;
    /*
     * 创建人
     */
    private String createUser;
    /*
     * 创建时间
     */
    private Timestamp createTime;
    /*
     * 修改人
     */
    private String updateUser;
    /*
     * 修改时间
     */
    private Timestamp updateTime;
    /*
     * 是否有效 0 有效 1 无效
     */
    private Integer isValid;
    /*
     * 学历层次 0：本科 1：高职（专科）
     */
    private Integer educationalLevel;
    
    
    public Long getSchoolId() {
        return schoolId;
    }
    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIs211() {
        return is211;
    }
    public void setIs211(Integer is211) {
        this.is211 = is211;
    }
    public Integer getIs985() {
        return is985;
    }
    public void setIs985(Integer is985) {
        this.is985 = is985;
    }
    public Long getRank() {
        return rank;
    }
    public void setRank(Long rank) {
        this.rank = rank;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public Integer getSchoolProperty() {
        return schoolProperty;
    }
    public void setSchoolProperty(Integer schoolProperty) {
        this.schoolProperty = schoolProperty;
    }
    public Integer getIsEdudirect() {
        return isEdudirect;
    }
    public void setIsEdudirect(Integer isEdudirect) {
        this.isEdudirect = isEdudirect;
    }
    public Long getRanking() {
        return ranking;
    }
    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }
    public Long getRankingCollegeType() {
        return rankingCollegeType;
    }
    public void setRankingCollegeType(Long rankingCollegeType) {
        this.rankingCollegeType = rankingCollegeType;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getSourceType() {
        return sourceType;
    }
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getIsValid() {
        return isValid;
    }
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Integer getEducationalLevel() {
        return educationalLevel;
    }
    public void setEducationalLevel(Integer educationalLevel) {
        this.educationalLevel = educationalLevel;
    }
    
    
    
    
}