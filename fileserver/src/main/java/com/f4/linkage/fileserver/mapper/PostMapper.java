package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();
        String poster_name = resultSet.getString("poster_name");

        post.setId(resultSet.getInt("id"));
        post.setPoster_name(poster_name);
        post.setPostAbstract(getAbstract(resultSet.getString("text")));

        post.setTime(resultSet.getString("time"));
        post.setPoster_icon("/global_icon/" + poster_name);
        return post;
    }

    private String getAbstract(String str){
        String content = str;
        int start = str.indexOf("<p>");
        int end = str.indexOf("</p>");
        if(start != -1 && end != -1){
            content = str.substring(start + 3, end);
        }
        if(content.length() > 20){
            return content.substring(0, 20);
        }else {
            return content;
        }
    }
}
