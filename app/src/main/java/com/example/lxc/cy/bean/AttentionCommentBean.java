package com.example.lxc.cy.bean;

public class AttentionCommentBean {

    private String user_pic;
    private String user_name;
    private String comment;

    public AttentionCommentBean(String user_pic, String user_name, String comment) {
        this.user_pic = user_pic;
        this.user_name = user_name;
        this.comment = comment;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
