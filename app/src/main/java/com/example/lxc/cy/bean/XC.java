package com.example.lxc.cy.bean;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2020/2/13.
 */

public class XC {
    private String id;
    private String name;
    private String url;

    public XC(String id,String name,String url){
        this.id=id;
        this.name=name;
        this.url=url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
