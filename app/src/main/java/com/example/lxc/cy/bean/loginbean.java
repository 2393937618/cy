package com.example.lxc.cy.bean;

public class loginbean {

    private static final long serialVersionUID = 4243745170036457009L;
    private String tel;
    private String password;

    public loginbean(String tel, String password) {
        this.tel = tel;
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
