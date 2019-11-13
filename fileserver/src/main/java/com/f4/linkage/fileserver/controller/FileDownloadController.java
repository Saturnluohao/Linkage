package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileDownloadController {

    @Resource
    FileUtil fileUtil;


    @GetMapping("/moment/{id}/picture/{picture_index}")
    void pictureTransferer(@PathVariable int id, @PathVariable int picture_index, HttpServletResponse response){
        String fileName = "img/" + id + "_" + picture_index;
        fileUtil.transfer(response, fileName);
    }

    @GetMapping("/moment/{id}/video/{video_index}")
    void videoTransferer(@PathVariable int id, @PathVariable int video_index, HttpServletResponse response){
        String fileName = "video/" + id + "_" + video_index;
        fileUtil.transfer(response, fileName);
        response.setContentType("video/mp4");
    }

    @GetMapping("/icon/{user}")
    void iconTransferer(@PathVariable String user, HttpServletResponse response){
        String fileName = user;
        fileUtil.transfer(response, fileName);
    }
}
