package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.MomentLike;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MomentLikeMapper implements RowMapper<MomentLike> {
    @Override
    public MomentLike mapRow(ResultSet resultSet, int i) throws SQLException {
        MomentLike momentLike = new MomentLike();
        momentLike.setLiker(resultSet.getString("liker_id"));
        momentLike.setMoment_id(resultSet.getInt("liked_id"));
        return momentLike;
    }
}
