package com.f4.linkage.fileserver.model;

import java.util.List;

public class Moment {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List getImg() {
        return img;
    }

    public void setImg(List img) {
        this.img = img;
    }

    public List getVideo() {
        return video;
    }

    public void setVideo(List video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPoster_icon() {
        return poster_icon;
    }

    public void setPoster_icon(String poster_icon) {
        this.poster_icon = poster_icon;
    }


    private int id;
    private String poster_name;
    private List img;
    private List video;
    private String text;
    private String time;
    private String poster_icon;
}
