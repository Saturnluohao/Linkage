package com.f4.linkage.fileserver.model;

public class HotPostCandidate {
    int id;
    String title;
    int visitTimes;
    String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isTrend() {
        return isTrend;
    }

    public void setTrend(boolean trend) {
        isTrend = trend;
    }

    boolean isTrend;
}
