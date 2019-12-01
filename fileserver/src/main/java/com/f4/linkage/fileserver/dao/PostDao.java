package com.f4.linkage.fileserver.dao;

import com.f4.linkage.fileserver.mapper.CommentMapper;
import com.f4.linkage.fileserver.mapper.LikeMapper;
import com.f4.linkage.fileserver.mapper.PostMapper;
import com.f4.linkage.fileserver.model.Comment;
import com.f4.linkage.fileserver.model.Like;
import com.f4.linkage.fileserver.model.Post;
import com.f4.linkage.fileserver.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostDao.class);

    @Value("${app.linkage.fileRoot}")
    private String fileRoot;

    @Resource
    JdbcTemplate jdbcTemplate;

    public boolean insertPost(Object[] args){
        String sql = "INSERT INTO post(poster_name, text, picture_num, video_num) VALUES (?,?,?,?)";
        if(jdbcTemplate.update(sql, args) == 1) {
            String poster = args[0].toString();
            List<String> friends = getSubscribers(poster);
            for (String friend: friends
            ) {
                insertWSSP(friend, FileUtil.postID);
            }
            return true;
        }else {
            return false;
        }
    }


    public List<String> getSubscribers(String username){
        String sql = "select global_username from follow where local_username=?";
        List<String> friends = jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
        friends.add(username);
        return friends;
    }

    public void insertWSSP(String username, int post_id){
        String sql = "insert into who_should_see_post values(null, ?, ?)";
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
                "(select post_id from who_should_see_post where global_username=?)";
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

    public List<Post> getPrivatePosts(String username){
        String sql = "select * from post where poster_name=?";

        List<Post> postList = jdbcTemplate.query(sql, new Object[]{username}, new PostMapper());
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

    public List<Like> getPostLikeList(int postId){
        String like_sql = "select * from post_like where liked_id=?";
        return jdbcTemplate.query(like_sql, new Object[]{postId}, new LikeMapper());
    }

    public List<Comment> getPostCommentList(int postId){
        String comment_sql = "select * from post_comment where commented_id=?";
        return jdbcTemplate.query(comment_sql, new Object[]{postId}, new CommentMapper());
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
        String sql_query = "select count(*) from post_like where liker_id=? and liked_id=?";
        String sql_insert = "insert into post_like(liker_id, liked_id) value(?,?)";
        String sql_delete = "delete from post_like where liker_id=? and liked_id=?";
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
}
