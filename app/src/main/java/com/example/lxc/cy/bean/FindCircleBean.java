package com.example.lxc.cy.bean;

public class FindCircleBean {

    private String circle_name;
    private String circle_hot = "0";
    private String circle_img;
    private String circle_comment;
    private String circle_username;
    private String circle_date;

    public FindCircleBean(String circle_name, String circle_hot, String circle_img, String circle_comment, String circle_username, String circle_date) {
        this.circle_name = circle_name;
        this.circle_hot = circle_hot;
        this.circle_img = circle_img;
        this.circle_comment = circle_comment;
        this.circle_username = circle_username;
        this.circle_date = circle_date;
    }


    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public String getCircle_hot() {
        return circle_hot;
    }

    public void setCircle_hot(String circle_hot) {
        this.circle_hot = circle_hot;
    }


    public String getCircle_img() {
        return circle_img;
    }

    public void setCircle_img(String circle_img) {
        this.circle_img = circle_img;
    }

    public String getCircle_comment() {
        return circle_comment;
    }

    public void setCircle_comment(String circle_comment) {
        this.circle_comment = circle_comment;
    }

    public String getCircle_username() {
        return circle_username;
    }

    public void setCircle_username(String circle_username) {
        this.circle_username = circle_username;
    }

    public String getCircle_date() {
        return circle_date;
    }

    public void setCircle_date(String circle_date) {
        this.circle_date = circle_date;
    }
}
