package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.Like;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeMapper implements RowMapper<Like> {
    @Override
    public Like mapRow(ResultSet resultSet, int i) throws SQLException {
        Like like = new Like();
        like.setLiker(resultSet.getString("liker"));
        like.setLiked_id(resultSet.getInt("liked_id"));
        return like;
    }
}
