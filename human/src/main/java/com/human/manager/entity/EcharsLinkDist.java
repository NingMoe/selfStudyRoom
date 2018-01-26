package com.human.manager.entity;


import java.util.List;

/**
 * 报表返回
 * @author HF-121093-01
 *
 */
public class EcharsLinkDist {
    
    private String name;
    
    private String type;
    
    private String stack;
    
    private Lable label;
    
    private List<SeriesData> data;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Lable getLabel() {
        return label;
    }

    public void setLabel(Lable label) {
        this.label = label;
    }

    public List<SeriesData> getData() {
        return data;
    }

    public void setData(List<SeriesData> data) {
        this.data = data;
    }

}
