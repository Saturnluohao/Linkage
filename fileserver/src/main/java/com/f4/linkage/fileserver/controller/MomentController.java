package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.model.Moment;
import com.f4.linkage.fileserver.util.DataUtil;
import com.f4.linkage.fileserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

import com.f4.linkage.webserver.api.login.model.LoginUserInfo;

@RestController
public class MomentController {
    @Resource
    DataUtil dataUtil;

    @Resource
    FileUtil fileUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(MomentController.class);

    @GetMapping("/moment")
    public String upload(){
        return "The moment upload API";
    }

    @PostMapping("/moment")
    public ResponseEntity<String> uploadMoment(Principal principal, @RequestParam("Text")String text, @RequestParam("Picture") MultipartFile[] pictures, @RequestParam("Video")MultipartFile[] videos){
        LOGGER.info("pictures length is " + pictures.length);
        LOGGER.info("videos length is " + videos.length);
        Object[] args = new Object[4];

        args[0] = principal.getName();
        args[1] = text;

        fileUtil.updateMomentID();

        if(pictures != null){
            if(fileUtil.saveFiles(pictures, 0)){
                LOGGER.info("Pictures transferred successfully!");
                args[2] = pictures.length;
            }
            else {
                return ResponseEntity.status(500).body("Picture upload failed!");
            }
        }
        else{
            args[2] = null;
        }
        if(videos != null){
            if(fileUtil.saveFiles(videos, 1)){
                LOGGER.info("Videos transferred successfully!");
                args[3] = videos.length;
            }
            else {
                return ResponseEntity.status(500).body("Video upload failed!");
            }
        }
        else {
            args[3] = null;
        }
        if(!dataUtil.insertBlog(args)){
            return ResponseEntity.status(500).body("We don't make it to insert your blog record to our database!");
        }

        return ResponseEntity.ok().body("Upload successfully, your weblog id is " + FileUtil.momentID);
    }

    @PostMapping("/moment/delete")
    ResponseEntity<String> deleteMoment(@RequestParam("id") String id_str){
        LOGGER.info("Moment id to be deleted is " + id_str);
        int id;
        try{
            id = Integer.parseInt(id_str);
        }catch (Exception e){
            return ResponseEntity.status(400).body("Please ensure the id is correct!");
        }
        if(dataUtil.deleteMoment(id)){
            return ResponseEntity.ok("Delete successfully");
        }else {
            return ResponseEntity.status(500).body("We fail to delete your moment, try again!");
        }
    }

    @GetMapping("/moment/check")
    List<Moment> checkMoment(Principal principal) {
        String username = principal.getName();
        LOGGER.info("Return the moment of " + username);
        return dataUtil.getMoments(username);
    }

    @GetMapping("/moment/home")
    List<Moment> getMyMoments(Principal principal){
        String username = principal.getName();
        LOGGER.info("Return " + username + "'s private moments");
        return dataUtil.getPrivateMoments(username);
    }
}
