package com.example.lxc.cy.bean;

import java.util.PropertyResourceBundle;

public class UserBean {
    private String people_img;
    private String people_name;
    private String people_num;

    public UserBean(String people_img, String people_name, String people_num) {
        this.people_img = people_img;
        this.people_name = people_name;
        this.people_num = people_num;
    }

    public String getPeople_img() {
        return people_img;
    }

    public void setPeople_img(String people_img) {
        this.people_img = people_img;
    }

    public String getPeople_name() {
        return people_name;
    }

    public void setPeople_name(String people_name) {
        this.people_name = people_name;
    }

    public String getPeople_num() {
        return people_num;
    }

    public void setPeople_num(String people_num) {
        this.people_num = people_num;
    }
}
