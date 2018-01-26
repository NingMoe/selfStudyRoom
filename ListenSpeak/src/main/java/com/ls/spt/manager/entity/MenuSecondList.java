package com.ls.spt.manager.entity;

/**
 * 二级菜单
 * @author HF-121093-01
 *
 */
public class MenuSecondList {
    
    private Long id;
    /**
     * 文本
     */
    private String text;
    
    private String href;
    
    private String icon;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
