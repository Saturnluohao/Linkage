package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.MomentComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MomentCommentMapper implements RowMapper<MomentComment> {
    @Override
    public MomentComment mapRow(ResultSet resultSet, int i) throws SQLException {
        MomentComment momentComment = new MomentComment();
        momentComment.setCommenter(resultSet.getString("commenter"));
        momentComment.setMoment_id(resultSet.getInt("moment_id"));
        momentComment.setContent(resultSet.getString("content"));
        momentComment.setId(resultSet.getInt("id"));
        return momentComment;
    }
}
