package com.human.continuation.entity;

import java.util.List;

public class TeacherContinuationStudentInfo {
    
    private String student_name;
    
    private String student_code;
    
    private String class_code;
    
    private List<TeacherContinuationClass> list;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public List<TeacherContinuationClass> getList() {
        return list;
    }

    public void setList(List<TeacherContinuationClass> list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((student_code == null) ? 0 : student_code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeacherContinuationStudentInfo other = (TeacherContinuationStudentInfo) obj;
        if (student_code == null) {
            if (other.student_code != null)
                return false;
        }
        else if (!student_code.equals(other.student_code))
            return false;
        return true;
    }

}
