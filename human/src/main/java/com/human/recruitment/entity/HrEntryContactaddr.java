package com.human.recruitment.entity;

public class HrEntryContactaddr {
    private Integer id;
    
    private Integer entryBaseId;

    private Integer dzType;

    private String dzProvince;

    private String dzCity;

    private String dzYoubian;

    private String dzDetailAddr;
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntryBaseId() {
        return entryBaseId;
    }

    public void setEntryBaseId(Integer entryBaseId) {
        this.entryBaseId = entryBaseId;
    }

    public Integer getDzType() {
        return dzType;
    }

    public void setDzType(Integer dzType) {
        this.dzType = dzType;
    }

    public String getDzProvince() {
        return dzProvince;
    }

    public void setDzProvince(String dzProvince) {
        this.dzProvince = dzProvince == null ? null : dzProvince.trim();
    }

    public String getDzCity() {
        return dzCity;
    }

    public void setDzCity(String dzCity) {
        this.dzCity = dzCity == null ? null : dzCity.trim();
    }

    public String getDzYoubian() {
        return dzYoubian;
    }

    public void setDzYoubian(String dzYoubian) {
        this.dzYoubian = dzYoubian == null ? null : dzYoubian.trim();
    }

    public String getDzDetailAddr() {
        return dzDetailAddr;
    }

    public void setDzDetailAddr(String dzDetailAddr) {
        this.dzDetailAddr = dzDetailAddr == null ? null : dzDetailAddr.trim();
    }
}