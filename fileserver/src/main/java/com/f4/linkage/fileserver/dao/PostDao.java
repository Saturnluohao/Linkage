package com.f4.linkage.fileserver.dao;

import com.f4.linkage.fileserver.controller.AdminController;
import com.f4.linkage.fileserver.mapper.CommentMapper;
import com.f4.linkage.fileserver.mapper.HotPostCandidateMapper;
import com.f4.linkage.fileserver.mapper.LikeMapper;
import com.f4.linkage.fileserver.mapper.PostMapper;
import com.f4.linkage.fileserver.model.Comment;
import com.f4.linkage.fileserver.model.HotPostCandidate;
import com.f4.linkage.fileserver.model.Like;
import com.f4.linkage.fileserver.model.Post;
import com.f4.linkage.fileserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostDao.class);

    @Value("${app.linkage.fileRoot}")
    private String fileRoot;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    FileUtil fileUtil;

    public boolean insertPost(Object[] args, String localName){
        String sql = "INSERT INTO post(poster_name, text, abstract) VALUES (?,?,?)";
        if(jdbcTemplate.update(sql, args) == 1) {
            fileUtil.saveHtml(args[1].toString(), fileRoot + "post/html/" + FileUtil.postID + ".html");
            String poster = args[0].toString();
            List<String> friends = getFollowers(poster);
            friends.add(localName);
            for (String friend: friends
            ) {
                insertWSSP(friend, FileUtil.postID);
            }
            return true;
        }else {
            return false;
        }
    }


    public List<String> getFollowers(String username){
        String sql = "select local_username from follow where global_username=?";
        List<String> followers = jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
        return followers;
    }

    public void insertWSSP(String username, int post_id){
        String sql = "insert into who_should_see_post(post_id, username) values(?, ?)";
        jdbcTemplate.update(sql, new Object[]{post_id, username});
    }

    public boolean deletePost(int id){
        String sql = "delete from post where id=?";
        if(jdbcTemplate.update(sql, new Object[]{id}) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public List<Post> getPosts(String username){
        String post_sql = "select * from post where id in " +
                "(select post_id from who_should_see_post where username=?) order by id desc ";
        Object[] arg = new Object[]{username};
        List<Post> postList = jdbcTemplate.query(post_sql, new Object[]{username}, new PostMapper());

        for (Post post:postList
        ) {
            int postId = post.getId();
            post.setSelf_like(false);
            List<Like> likeList = getPostLikeList(postId);
            post.setLike(likeList);
            post.setComment(getPostCommentList(postId));
            post.setSelf_like(amILiker(likeList, username));
        }
        return postList;
    }

    public List<Post> getPrivatePosts(String username, String localName){
        String sql = "select * from post where poster_name=? order by id desc ";
        List<Post> postList = jdbcTemplate.query(sql, new Object[]{username}, new PostMapper());
        for (Post post:postList
        ) {
            int postId = post.getId();
            List<Like> likeList = getPostLikeList(postId);
            post.setLike(likeList);
            post.setComment(getPostCommentList(postId));
            post.setSelf_like(amILiker(likeList, localName));
        }
        return postList;
    }

    public List<Post> getPosts(List<Integer> idList, String username){
        List<Post> postList = new ArrayList<>();
        PostMapper postMapper = new PostMapper();
        String sql = "select * from post where id=?";
        for (Integer id: idList
        ) {
            if(id.intValue() != -1) {
                Post post = jdbcTemplate.queryForObject(sql, new Object[]{id.intValue()}, postMapper);
                List<Like> likeList = getPostLikeList(id.intValue());
                post.setComment(getPostCommentList(id.intValue()));
                post.setLike(likeList);
                post.setSelf_like(amILiker(likeList, username));
                postList.add(post);
            }
        }
        return postList;
    }

    public List<Like> getPostLikeList(int postId){
        String like_sql = "select * from post_like where liked_id=?";
        List<Like> likeList = jdbcTemplate.query(like_sql, new Object[]{postId}, new LikeMapper());
        if(likeList == null){
            return new ArrayList<>();
        }else {
            return likeList;
        }
    }

    public List<Comment> getPostCommentList(int postId){
        String comment_sql = "select * from post_comment where commented_id=?";
        List<Comment> commentList = jdbcTemplate.query(comment_sql, new Object[]{postId}, new CommentMapper());
        if(commentList == null){
            return new ArrayList<>();
        }else {
            return commentList;
        }
    }

    private boolean amILiker(List<Like> likeList, String username){
        for (Like postLike:likeList
        ) {
            if(postLike.getLiker().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean updatePostLike(String username, int postId, boolean action) {
        String sql_query = "select count(*) from post_like where liker=? and liked_id=?";
        String sql_insert = "insert into post_like(liker, liked_id) value(?,?)";
        String sql_delete = "delete from post_like where liker=? and liked_id=?";
        Object[] args = new Object[]{username, postId};
        boolean exist = jdbcTemplate.queryForObject(sql_query, args, Integer.class).equals(1);
        if(action){
            if(exist || jdbcTemplate.update(sql_insert, args) == 1){
                return true;
            }else {
                return false;
            }
        }else {
            if(!exist || jdbcTemplate.update(sql_delete, args) == 1){
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean insertComment(String username, int postId, String comment){
        String sql = "insert into post_comment(commenter, commented_id, content) value (?,?,?)";
        if(jdbcTemplate.update(sql, new Object[]{username, postId, comment}) == 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteComment(String username, int commentId){
        String sql = "delete from post_comment where id=?";

        if(jdbcTemplate.update(sql, new Object[]{commentId}) == 1){
            return true;
        }else {
            return false;
        }
    }

    public List<Post> searchPost(String keyword, String username){
        String sql = "select * from post where text like ?";
        List<Post> postList = jdbcTemplate.query(sql, new Object[]{'%' + keyword + '%'}, new PostMapper());
        for (Post post:postList
        ) {
            int postId = post.getId();
            List<Like> likeList = getPostLikeList(postId);
            post.setLike(likeList);
            post.setComment(getPostCommentList(postId));
            post.setSelf_like(amILiker(likeList, username));
        }
        return postList;
    }

    public void addVisitItem(int id){
        String sql = "insert into post_visit(id) values (?)";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    public List<HotPostCandidate> getVisit(long interval, int amount){
        long min = new Date().getTime() / 1000 - interval;
        LOGGER.info("Now time stamp is " + new Date().getTime());
        String sql = " select post.id,poster_name,abstract,visits from post natural join " +
                "(select id,count(*) as visits from post_visit where unix_timestamp(visitTime)>? " +
                "group by id order by visits desc limit ?) as visitNum order by post.id desc ";
        return jdbcTemplate.query(sql, new Object[]{min, amount}, new HotPostCandidateMapper());
    }

    public String getGlobalName(String localName){
        String sql = "select global_username from local_global_user where local_username=?";
        String globalName = null;
        try {
            globalName = jdbcTemplate.queryForObject(sql, new Object[]{localName}, String.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return globalName;
    }
}
