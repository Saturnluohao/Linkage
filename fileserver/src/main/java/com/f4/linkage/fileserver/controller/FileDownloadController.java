package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.model.FileKind;
import com.f4.linkage.fileserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileDownloadController {

    @Resource
    FileUtil fileUtil;


    @GetMapping("/moment/{id}/picture/{picture_index}")
    void momentPictureTransferer(@PathVariable int id, @PathVariable int picture_index, HttpServletResponse response){
        String fileName = "moment/img/" + id + "_" + picture_index;
        fileUtil.transfer(response, fileName, FileKind.MomentPicture);
    }

    @GetMapping("/moment/{id}/video/{video_index}")
    void momentVideoTransferer(@PathVariable int id, @PathVariable int video_index, HttpServletResponse response){
        String fileName = "moment/video/" + id + "_" + video_index;
        fileUtil.transfer(response, fileName, FileKind.MomentVideo);
        response.setContentType("video/mp4");
    }

    @GetMapping("/post/{id}.html")
    void postHtml(@PathVariable int id, HttpServletResponse response){
        String fileName = "post/html/" + id + ".html";
        fileUtil.transfer(response, fileName, FileKind.Html);
    }

    @GetMapping("/post/picture/{picture_index}")
    void postPictureTransferer(@PathVariable int id, @PathVariable int picture_index, HttpServletResponse response){
        String fileName = "post/img/" + id + "_" + picture_index;
        fileUtil.transfer(response, fileName, FileKind.MomentPicture);
    }

    @GetMapping("/post/video/{video_index}")
    void postVideoTransferer(@PathVariable int id, @PathVariable int video_index, HttpServletResponse response){
        String fileName = "post/video/" + id + "_" + video_index;
        fileUtil.transfer(response, fileName, FileKind.MomentVideo);
        response.setContentType("video/mp4");
    }

    @GetMapping("/icon/{user}")
    void iconTransferer(@PathVariable String user, HttpServletResponse response){
        String fileName = "icon/" + user;
        fileUtil.transfer(response, fileName, FileKind.ICON);
    }

    @GetMapping("/global_icon/{user}")
    void global_iconTransferer(@PathVariable String user, HttpServletResponse response){
        String fileName = "global_icon/" + user;
        fileUtil.transfer(response, fileName, FileKind.GLOBAL_ICON);
    }
}
