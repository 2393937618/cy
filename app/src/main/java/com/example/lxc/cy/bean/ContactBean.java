package com.example.lxc.cy.bean;

public class ContactBean {
    private String user_name;
    private String user_pic;
    private String user_show;

    public ContactBean(String user_name, String user_pic, String user_show) {
        this.user_name = user_name;
        this.user_pic = user_pic;
        this.user_show = user_show;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getUser_show() {
        return user_show;
    }

    public void setUser_show(String user_show) {
        this.user_show = user_show;
    }
}
