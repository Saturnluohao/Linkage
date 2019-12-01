package com.f4.linkage.fileserver.model;

import java.util.List;

public class PostImgInfo {
    int errno;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    List<String> data;
}
