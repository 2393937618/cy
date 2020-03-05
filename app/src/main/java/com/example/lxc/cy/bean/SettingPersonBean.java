package com.example.lxc.cy.bean;

public class SettingPersonBean {

    private String name;
    private String sex;
    private String bir;
    private String place;
    private String show;

    public SettingPersonBean(String name, String sex, String bir, String place, String show) {
        this.name = name;
        this.sex = sex;
        this.bir = bir;
        this.place = place;
        this.show = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
