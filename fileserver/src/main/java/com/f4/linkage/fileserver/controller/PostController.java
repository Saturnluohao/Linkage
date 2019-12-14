package com.f4.linkage.fileserver.controller;

import com.f4.linkage.fileserver.dao.PostDao;
import com.f4.linkage.fileserver.model.*;
import com.f4.linkage.fileserver.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Resource
    FileUtil fileUtil;

    @Resource
    PostDao postDao;


    @PostMapping("/post")
    ResponseEntity<String> uploadPost(Principal principal, @RequestBody Map param){
        String username = principal.getName();
        String globalName = postDao.getGlobalName(username);
        if(globalName == null){
            return ResponseEntity.status(400).body("You don't have a global account!");
        }
        String abstracStr;
        try{
            abstracStr = param.get("Title").toString();
        }catch (Exception e){
            abstracStr = "No Title for this article!";
        }
        fileUtil.updatePostID();
        if(postDao.insertPost(new Object[]{globalName, param.get("PostHtml").toString(), abstracStr}, username)){
            return ResponseEntity.ok("Upload successfully, and your post id is " + FileUtil.postID);
        }else {
            return ResponseEntity.status(500).body("Sorry, maybe try again!");
        }
    }

    @PostMapping("/post/delete")
    ResponseEntity<String> deletePost(@RequestParam("id")int id){
        if(postDao.deletePost(id)){
            return ResponseEntity.ok("Deleted successfully");
        }else {
            return ResponseEntity.status(500).body("Sorry, maybe try again!");
        }
    }

    @PostMapping("/post/img")
    PostImgInfo handlePostImg(@RequestParam("Picture")MultipartFile[] imgs){
        PostImgInfo postImgInfo = new PostImgInfo();
        List<String> imgUrls = new ArrayList<>();
        if(fileUtil.saveFiles(imgs, FileKind.PostPicture, imgUrls)){
            postImgInfo.setData(imgUrls);
            postImgInfo.setErrno(0);
        }else {
            postImgInfo.setErrno(1);
            postImgInfo.setData(null);
        }
        return postImgInfo;
    }

    @GetMapping("/post/search")
    List<Post> search(Principal principal, @RequestParam("Keyword")String keyword){
        String username = principal.getName();
        return postDao.searchPost(keyword, username);
    }

    @GetMapping("/post/hot")
    List<Post> hot(Principal principal){
        String username = principal.getName();
        return postDao.getPosts(AdminController.hpList, username);
    }

    @GetMapping("/post/check")
    List<Post> checkPost(Principal principal){
        String username = principal.getName();
        LOGGER.info("Return the moment of " + username);
        return postDao.getPosts(username);
    }

    @GetMapping("/post/home")
    List<Post> homePost(HttpServletRequest request, Principal principal){
        String username, globalName;
        username = request.getParameter("username");
        if(username == null) {
            globalName = postDao.getGlobalName(principal.getName());
        }else {
            globalName = username;
        }
        LOGGER.info("Return " + globalName + "'s private moments");
        return postDao.getPrivatePosts(globalName);
    }

    @PostMapping("/post/like")
    ResponseEntity<List<Like>> likePost(Principal principal, @RequestParam("PostId")int postId, @RequestParam("Action")String action){
        String username = principal.getName();
        switch (action){
            case "like":
                if(postDao.updatePostLike(username, postId, true)){
                    return ResponseEntity.ok().body(postDao.getPostLikeList(postId));
                }else {
                    return ResponseEntity.status(500).body(new ArrayList<>());
                }
            case "cancel":
                if(postDao.updatePostLike(username, postId, false)){
                    return ResponseEntity.ok().body(postDao.getPostLikeList(postId));
                }else {
                    return ResponseEntity.status(500).body(new ArrayList<>());
                }
            default:
                return ResponseEntity.status(406).body(new ArrayList<>());
        }
    }

    @PostMapping("/post/comment/add")
    ResponseEntity<List<Comment>> addComment(Principal principal, @RequestBody Map params){
        String username = principal.getName();
        int postId = -1;
        String comment = "";
        try {
             postId = Integer.parseInt(params.get("PostId").toString());
             comment = params.get("Comment").toString();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ArrayList<>());
        }
        if(postDao.insertComment(username, postId, comment)){
            return ResponseEntity.ok(postDao.getPostCommentList(postId));
        }else {
            return ResponseEntity.status(500).body(new ArrayList<Comment>());
        }
    }

    @PostMapping("/post/comment/delete")
    ResponseEntity<List<Comment>> deleteComment(Principal principal, @RequestParam("PostId")int postId){
        String username = principal.getName();
        if(postDao.deleteComment(username, postId)){
            return ResponseEntity.ok(postDao.getPostCommentList(postId));
        }else {
            return ResponseEntity.status(500).body(new ArrayList<>());
        }
    }
}
