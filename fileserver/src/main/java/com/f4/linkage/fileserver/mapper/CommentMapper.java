package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment momentComment = new Comment();
        momentComment.setCommenter(resultSet.getString("commenter"));
        momentComment.setMoment_id(resultSet.getInt("moment_id"));
        momentComment.setContent(resultSet.getString("content"));
        momentComment.setId(resultSet.getInt("id"));
        return momentComment;
    }
}
