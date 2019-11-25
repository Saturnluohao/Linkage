package com.f4.linkage.fileserver.util;

import com.f4.linkage.fileserver.mapper.MomentMapper;
import com.f4.linkage.fileserver.model.Moment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataUtil.class);

    @Value("${app.linkage.fileRoot}")
    private String fileRoot;

    @Resource
    JdbcTemplate jdbcTemplate;

    public boolean insertBlog(Object[] args){
        String sql = "INSERT INTO moment(poster_name, text, picture_num, video_num) VALUES (?,?,?,?)";
        if(jdbcTemplate.update(sql, args) == 1) {
            String poster = args[0].toString();
            List<String> friends = getFriends(poster);
            for (String friend: friends
                 ) {
                insertWSSM(friend, FileUtil.momentID);
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean updateIconUrl(String username){
        String iconUrl = "/icon/" + username;
        String sql = "Update user set iconUrl = ? where username = ?";
        if(jdbcTemplate.update(sql, new Object[]{iconUrl, username}) == 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateGlobalIconUrl(String username){
        String iconUrl = "/global_icon/" + username;
        String sql = "Update global_user set iconUrl = ? where username = ?";
        if(jdbcTemplate.update(sql, new Object[]{iconUrl, username}) == 1){
            return true;
        }else {
            return false;
        }
    }

    public List<String> getFriends(String username){
        String sql = "select friend_username from friend where my_username=?";
        List<String> friends = jdbcTemplate.queryForList(sql, new Object[]{username}, String.class);
        friends.add(username);
        return friends;
    }

    public void insertWSSM(String username, int moment_id){
        String sql = "insert into who_should_see_moment values(null, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{moment_id, username});
    }

    public boolean deleteMoment(int id){
        String sql = "delete from moment where id=?";
        if(jdbcTemplate.update(sql, new Object[]{id}) == 1){
            return true;
        }
        else {
            return false;
        }
    }


    public Moment getMoment(int id){
        String sql = "select * from moment where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MomentMapper());
    }

    public List<Moment> getMoments(String username){
        String sql = "select moment_id from who_should_see_moment where username=?";
        String sql2 = "select * from moment where id=?";
        List<Integer> moment_ids = jdbcTemplate.queryForList(sql, new Object[]{username}, Integer.class);
        List<Moment> momentList = new ArrayList<>();
        MomentMapper momentMapper = new MomentMapper();
        for (int id: moment_ids
                 ) {
            momentList.add(getMoment(id));
        }
        return momentList;
    }

    public List<Moment> getPrivateMoments(String username){
        String sql = "select * from moment where poster_name=?";
        return jdbcTemplate.query(sql, new Object[]{username}, new MomentMapper());
    }

    public boolean updateMomentLike(String username, int momentId, boolean action) {
        String sql_query = "select count(*) from moment_like where liker_id=? and liked_id=?";
        String sql_insert = "insert into moment_like(liker_id, liked_id) value(?,?)";
        String sql_delete = "delete from moment_like where liked_id=? and liked_id=?";
        Object[] args = new Object[]{username, momentId};
        boolean exist = jdbcTemplate.queryForObject(sql_query, args, Integer.class).equals(0);
        if(action){
            if(exist || jdbcTemplate.update(sql_insert) == 1){
                return true;
            }else {
                return false;
            }
        }else {
            if(!exist || jdbcTemplate.update(sql_delete) == 1){
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean insertComment(String username, int momentId, String comment){
        String sql = "insert into moment_comment(commenter, moment_id, content) value (?,?,?)";
        if(jdbcTemplate.update(sql, new Object[]{username, momentId, comment}) == 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteComment(String username, int commentId){
        String sql = "delete from moment_comment where id=?";

        if(jdbcTemplate.update(sql, new Object[]{commentId}) == 1){
            return true;
        }else {
            return false;
        }
    }
}
