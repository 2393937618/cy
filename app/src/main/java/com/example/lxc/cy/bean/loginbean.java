package com.example.lxc.cy.bean;

public class loginbean {

    private static final long serialVersionUID = 4243745170036457009L;
    private int id;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
