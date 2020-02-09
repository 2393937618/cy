package com.example.lxc.cy.bean;

public class DestinationBean {
    private String scenery_img;
    private String scenery_name;
    private String scenery_text;

    public DestinationBean(String scenery_img, String scenery_name, String scenery_text) {
        this.scenery_img = scenery_img;
        this.scenery_name = scenery_name;
        this.scenery_text = scenery_text;
    }

    public String getScenery_img() {
        return scenery_img;
    }

    public void setScenery_img(String scenery_img) {
        this.scenery_img = scenery_img;
    }

    public String getScenery_name() {
        return scenery_name;
    }

    public void setScenery_name(String scenery_name) {
        this.scenery_name = scenery_name;
    }

    public String getScenery_text() {
        return scenery_text;
    }

    public void setScenery_text(String scenery_text) {
        this.scenery_text = scenery_text;
    }
}
