package com.example.lxc.cy.bean;

public class TravelBean {

    private String travel_img;
    private String travel_name;
    private String travel_num;

    public TravelBean(String travel_img, String travel_name, String travel_num) {
        this.travel_img = travel_img;
        this.travel_name = travel_name;
        this.travel_num = travel_num;
    }

    public String getTravel_img() {
        return travel_img;
    }

    public void setTravel_img(String travel_img) {
        this.travel_img = travel_img;
    }

    public String getTravel_name() {
        return travel_name;
    }

    public void setTravel_name(String travel_name) {
        this.travel_name = travel_name;
    }

    public String getTravel_num() {
        return travel_num;
    }

    public void setTravel_num(String travel_num) {
        this.travel_num = travel_num;
    }
}
