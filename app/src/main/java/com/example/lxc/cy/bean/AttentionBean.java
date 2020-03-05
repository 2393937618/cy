package com.example.lxc.cy.bean;

import java.util.List;

public class AttentionBean {
        private String user_pic; //用户图片地址
        private String user_name;  //用户名
        private String create_time;  //建立时间
        private String content_text;  //评论内容
        private String circle_name;  //圈子名称
        private String place_name;  //地点名称
        private String comment_num;  //评论数量
        private String like_num;  //喜欢数量
        private int collect_num;  //收藏数量
        private int type;  //标识名称：0：游记 1：攻略 2：动态
        private String notes_id;  //评论id
        private String owner_id;  //用户id
        private int place_id;  //地点id
        private String like_TF;
        private List<FindImgBean> findImgBeans;

        public AttentionBean(String like_TF,String notes_id,String owner_id,String user_pic, String user_name, String create_time, String content_text, String place_name, String comment_num, String like_num,List<FindImgBean> findImgBeans) {
            this.like_TF = like_TF;
            this.notes_id = notes_id;
            this.owner_id = owner_id;
            this.user_pic = user_pic;
            this.user_name = user_name;
            this.create_time = create_time;
            this.content_text = content_text;
            this.place_name = place_name;
            this.comment_num = comment_num;
            this.like_num = like_num;
            this.findImgBeans = findImgBeans;
        }

        public String getLike_TF() {
            return like_TF;
        }

        public void setLike_TF(String like_TF) {
            this.like_TF = like_TF;
        }

        public String getNotes_id() {
            return notes_id;
        }

        public void setNotes_id(String notes_id) {
            this.notes_id = notes_id;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent_text() {
            return content_text;
        }

        public void setContent_text(String content_text) {
            this.content_text = content_text;
        }

        public String getCircle_name() {
            return circle_name;
        }

        public void setCircle_name(String circle_name) {
            this.circle_name = circle_name;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public int getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(int collect_num) {
            this.collect_num = collect_num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public int getPlace_id() {
            return place_id;
        }

        public void setPlace_id(int place_id) {
            this.place_id = place_id;
        }

    public List<FindImgBean> getFindImgBeans() {
        return findImgBeans;
    }

    public void setFindImgBeans(List<FindImgBean> findImgBeans) {
        this.findImgBeans = findImgBeans;
    }
}
