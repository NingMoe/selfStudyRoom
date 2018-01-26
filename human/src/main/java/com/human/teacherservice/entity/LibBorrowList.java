package com.human.teacherservice.entity;

import java.util.Date;

public class LibBorrowList {
    private Integer borrow_id;//借阅id

    private String borrow_schoolid;//学校id
    
    private String company_name;//公司名称
    
    private String dept_id;//部门id
    
    private String dept_name;//部门名称

    private String email_addr;//用户email前缀
    
    private String user_name;//姓名拼音
    
    private String name;//姓名

    private Integer book_id;//书籍id
    
    private String book_name;//书籍名称
    
    private Integer book_type;//分类id
    
    private String type_name;//分类名称
    
    private Date out_borrow_time;//超过多长时间
    
    private Date left_borrow_time;//最小借阅时间
    
    private Date right_borrow_time;//最大借阅时间

    private Date borrow_time; //借阅时间
    
    private Integer is_return; //是否归还
    
    private Date left_return_time;//最小归还时间
    
    private Date right_return_time;//最大归还时间

    private Date return_time;//归还时间
    
    private Integer num;//数量

    public Integer getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(Integer borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getBorrow_schoolid() {
        return borrow_schoolid;
    }

    public void setBorrow_schoolid(String borrow_schoolid) {
        this.borrow_schoolid = borrow_schoolid == null ? null : borrow_schoolid.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
    
    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getBook_type() {
        return book_type;
    }

    public void setBook_type(Integer book_type) {
        this.book_type = book_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    
    public Date getOut_borrow_time() {
        return out_borrow_time;
    }

    public void setOut_borrow_time(Date out_borrow_time) {
        this.out_borrow_time = out_borrow_time;
    }

    public Date getLeft_borrow_time() {
        return left_borrow_time;
    }

    public void setLeft_borrow_time(Date left_borrow_time) {
        this.left_borrow_time = left_borrow_time;
    }

    public Date getRight_borrow_time() {
        return right_borrow_time;
    }

    public void setRight_borrow_time(Date right_borrow_time) {
        this.right_borrow_time = right_borrow_time;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }

    public Integer getIs_return() {
        return is_return;
    }

    public void setIs_return(Integer is_return) {
        this.is_return = is_return;
    }

    public Date getLeft_return_time() {
        return left_return_time;
    }

    public void setLeft_return_time(Date left_return_time) {
        this.left_return_time = left_return_time;
    }

    public Date getRight_return_time() {
        return right_return_time;
    }

    public void setRight_return_time(Date right_return_time) {
        this.right_return_time = right_return_time;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}