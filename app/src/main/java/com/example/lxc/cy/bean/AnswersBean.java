package com.example.lxc.cy.bean;

public class AnswersBean {
    private String answer_content;
    private String answer_describe;
    private String answer_num;

    public AnswersBean(String answer_content, String answer_describe, String answer_num) {
        this.answer_content = answer_content;
        this.answer_describe = answer_describe;
        this.answer_num = answer_num;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getAnswer_describe() {
        return answer_describe;
    }

    public void setAnswer_describe(String answer_describe) {
        this.answer_describe = answer_describe;
    }

    public String getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(String answer_num) {
        this.answer_num = answer_num;
    }
}
