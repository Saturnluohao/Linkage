package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.util.DataUtil;
import com.f4.linkage.fileserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class IconController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Resource
    private FileUtil fileUtil;

    @Resource
    private DataUtil dataUtil;



    @PostMapping("/icon")
    ResponseEntity<String> uploadIcon(@RequestParam("Icon") MultipartFile icon){
        if(icon != null){
            if(fileUtil.saveIconFile(icon, "user")){
                dataUtil.updateIconUrl("user");
                return ResponseEntity.ok().body("http://www.saturnluo.cn:5000/icon/" + "usr");
            }
            else {
                return ResponseEntity.status(500).body("We can't save your icon file!");
            }
        }
        return ResponseEntity.status(400).body("We don't receive your icon file");
    }

    @PostMapping("/global_icon")
    ResponseEntity<String> uploadGlobalIcon(@RequestParam("Icon") MultipartFile icon){
        if(icon != null){
            if(fileUtil.saveGlobalIconFile(icon, "zzj")){
                dataUtil.updateGlobalIconUrl("zzj");
                return ResponseEntity.ok().body("http://www.saturnluo.cn:5000/global_icon/" + "zzj");
            }
            else {
                return ResponseEntity.status(500).body("We can't save your icon file!");
            }
        }
        return ResponseEntity.status(400).body("We don't receive your icon file");

    }
}
