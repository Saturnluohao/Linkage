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


    @GetMapping("/weblog/{blog_id}/picture/{picture_index}")
    void pictureTransferer(@PathVariable int blog_id, @PathVariable int picture_index, HttpServletResponse response){
        String fileName = "" + blog_id + "_p_" + picture_index;
        fileUtil.transfer(response, fileName);
    }

    @GetMapping("/weblog/{blog_id}/video/{video_index}")
    void videoTransferer(@PathVariable int blog_id, @PathVariable int video_index, HttpServletResponse response){
        String fileName = "" + blog_id + "_v_" + video_index;
        fileUtil.transfer(response, fileName);
        response.setContentType("video/mp4");
    }
}
