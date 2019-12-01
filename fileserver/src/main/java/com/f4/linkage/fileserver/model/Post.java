package com.f4.linkage.fileserver.model;

import java.util.List;

public class Post {
    private int id;
    private String poster_name;
    private String time;
    private String poster_icon;
    private String postAbstract;
    private List<Like> like;
    private List<Comment> comment;
    private boolean self_like;

    public String getPostAbstract() {
        return postAbstract;
    }

    public void setPostAbstract(String postAbstract) {
        this.postAbstract = postAbstract;
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

    public List<Like> getLike() {
        return like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public boolean isSelf_like() {
        return self_like;
    }

    public void setSelf_like(boolean self_like) {
        this.self_like = self_like;
    }
}
