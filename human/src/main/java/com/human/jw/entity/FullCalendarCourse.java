package com.human.jw.entity;

public class FullCalendarCourse {
    private String title;
    
    private String start;
    
    private String end;
    
    private boolean allDay;
    
    public FullCalendarCourse(JwCourse jwCourse){
        this.title = jwCourse.getsClassCode()+"-"+jwCourse.getsClassName()+"-"+jwCourse.getsPrintAddress();
        this.start = jwCourse.getSectBegin();
        this.end = jwCourse.getSectEnd();
        this.allDay = false;
    }
    
    public FullCalendarCourse(){
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
    
    
    
}
