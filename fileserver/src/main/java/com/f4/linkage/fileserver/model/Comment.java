package com.f4.linkage.fileserver.model;

public class Comment {
    private int id;
    private String commenter;
    private int commented_id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public int getCommented_id() {
        return commented_id;
    }

    public void setCommented_id(int commented_id) {
        this.commented_id = commented_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
