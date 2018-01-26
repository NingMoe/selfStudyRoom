package com.human.ielts.entity;

import java.util.Date;
import java.util.List;

public class IeltsStudentInfo {
    private Integer id;

    private String student_name;

    private String student_phone;

    private String advisor;

    private String student_manager;

    private String school;

    private String grade_name;

    private Integer is_planning;

    private Date creat_time;

    private Integer valid;
    
    private Double total;

    private Double listening;

    private Double reading;

    private Double writing;

    private Double oral;
    
    private Integer is_target;

    private Date test_time;
    
    private Date left_integral_time;
    
    private Date right_integral_time;
    
    private List<IeltsBookInfo> ielts_book_info_list;
    
    private List<IeltsClassType> ielts_class_type_list;
    
    private List<IeltsTeacherInfo> ielts_teacher_info_list;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name == null ? null : student_name.trim();
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone == null ? null : student_phone.trim();
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor == null ? null : advisor.trim();
    }

    public String getStudent_manager() {
        return student_manager;
    }

    public void setStudent_manager(String student_manager) {
        this.student_manager = student_manager == null ? null : student_manager.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public Integer getIs_planning() {
        return is_planning;
    }

    public void setIs_planning(Integer is_planning) {
        this.is_planning = is_planning;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getListening() {
        return listening;
    }

    public void setListening(Double listening) {
        this.listening = listening;
    }

    public Double getReading() {
        return reading;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }

    public Double getWriting() {
        return writing;
    }

    public void setWriting(Double writing) {
        this.writing = writing;
    }

    public Double getOral() {
        return oral;
    }

    public void setOral(Double oral) {
        this.oral = oral;
    }

    public Integer getIs_target() {
        return is_target;
    }

    public void setIs_target(Integer is_target) {
        this.is_target = is_target;
    }

    public Date getTest_time() {
        return test_time;
    }

    public void setTest_time(Date test_time) {
        this.test_time = test_time;
    }

    public Date getLeft_integral_time() {
        return left_integral_time;
    }

    public void setLeft_integral_time(Date left_integral_time) {
        this.left_integral_time = left_integral_time;
    }

    public Date getRight_integral_time() {
        return right_integral_time;
    }

    public void setRight_integral_time(Date right_integral_time) {
        this.right_integral_time = right_integral_time;
    }

    public List<IeltsBookInfo> getIelts_book_info_list() {
        return ielts_book_info_list;
    }

    public void setIelts_book_info_list(List<IeltsBookInfo> ielts_book_info_list) {
        this.ielts_book_info_list = ielts_book_info_list;
    }

    public List<IeltsClassType> getIelts_class_type_list() {
        return ielts_class_type_list;
    }

    public void setIelts_class_type_list(List<IeltsClassType> ielts_class_type_list) {
        this.ielts_class_type_list = ielts_class_type_list;
    }

    public List<IeltsTeacherInfo> getIelts_teacher_info_list() {
        return ielts_teacher_info_list;
    }

    public void setIelts_teacher_info_list(List<IeltsTeacherInfo> ielts_teacher_info_list) {
        this.ielts_teacher_info_list = ielts_teacher_info_list;
    }
}