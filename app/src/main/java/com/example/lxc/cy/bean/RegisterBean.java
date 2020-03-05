package com.example.lxc.cy.bean;

public class RegisterBean {
    private String tel;
    private String password;

    public RegisterBean(String tel, String password) {
        this.tel = tel;
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
