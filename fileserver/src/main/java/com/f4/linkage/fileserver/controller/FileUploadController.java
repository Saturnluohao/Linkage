package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.util.DataUtil;
import com.f4.linkage.fileserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class FileUploadController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Resource
    private FileUtil fileUtil;

    @GetMapping("/weblog")
    public String upload(){
        return "The upload API";
    }

    @PostMapping("/weblog")
    @ResponseBody
    public String upload(@RequestParam("Text")String text, @RequestParam("Picture")MultipartFile[] pictures, @RequestParam("Video")MultipartFile[] videos){

        LOGGER.info("pictures length is " + pictures.length);
        LOGGER.info("videos length is " + videos.length);
        Object[] args = new Object[5];

        args[0] = null;
        args[1] = 666;
        args[2] = text;

        fileUtil.updateWeblogID();

        if(pictures != null){
            if(fileUtil.saveFiles(pictures, 0)){
                LOGGER.info("Pictures transferred successfully!");
                args[3] = pictures.length;
            }
        }
        else{
            args[3] = null;
        }
        if(videos != null){
            if(fileUtil.saveFiles(videos, 1)){
                LOGGER.info("Videos transferred successfully!");
                args[4] = videos.length;
            }
        }
        else {
            args[4] = null;
        }
        DataUtil.insertBlog(args);
        return "Upload successfully, this weblog id is " + FileUtil.weblogID;
    }
}
