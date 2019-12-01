package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommenter(resultSet.getString("commenter"));
        comment.setCommented_id(resultSet.getInt("commented_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setId(resultSet.getInt("id"));
        return comment;
    }
}
