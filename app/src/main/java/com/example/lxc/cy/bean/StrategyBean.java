package com.example.lxc.cy.bean;

public class StrategyBean {
    private String strategy_img;
    private String strategy_name;
    private String strategy_num;

    public StrategyBean(String strategy_img, String strategy_name, String strategy_num) {
        this.strategy_img = strategy_img;
        this.strategy_name = strategy_name;
        this.strategy_num = strategy_num;
    }

    public String getStrategy_img() {
        return strategy_img;
    }

    public void setStrategy_img(String strategy_img) {
        this.strategy_img = strategy_img;
    }

    public String getStrategy_name() {
        return strategy_name;
    }

    public void setStrategy_name(String strategy_name) {
        this.strategy_name = strategy_name;
    }

    public String getStrategy_num() {
        return strategy_num;
    }

    public void setStrategy_num(String strategy_num) {
        this.strategy_num = strategy_num;
    }
}
