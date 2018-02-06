package cn.xdf.selfStudyRoom.domain.entity;

import java.util.List;

/**
 * 一级菜单
 * @author liuwei63
 *
 */
public class MenuFirstList {
    
    /**
     * 模块ID
     */
    private Long id;
    
    /**
     * 模块名称
     */
    private String title;
    
    /**
     * 模块默认显示的主页，使用页面ID
     */
    private String href;
    
    /**
     * 图标 
     */
    private String icon;
    
    /**
     * 菜单的集合，是一个数组，其中每个对象代表一个二级菜单
     */
    private List<MenuSecondList> menu;

    public List<MenuSecondList> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuSecondList> menu) {
        this.menu = menu;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

}
