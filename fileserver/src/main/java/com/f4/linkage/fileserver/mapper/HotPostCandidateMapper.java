package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.HotPostCandidate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotPostCandidateMapper implements RowMapper<HotPostCandidate> {
    @Override
    public HotPostCandidate mapRow(ResultSet resultSet, int i) throws SQLException {
        HotPostCandidate hpc = new HotPostCandidate();
        hpc.setId(resultSet.getInt("id"));
        hpc.setAuthor(resultSet.getString("poster_name"));
        hpc.setTitle(getTitle(resultSet.getString("text")));
        hpc.setVisitTimes(resultSet.getInt("visits"));
        return hpc;
    }

    private String getTitle(String str){
        String content = "Tis article has no title!";
        int maxLen = 30;
        int start = str.indexOf("<h1>");
        int end = str.indexOf("</h1>");
        if(start != -1 && end != -1){
            content = str.substring(start + 4, end);
        }
        if(content.length() > maxLen){
            return content.substring(0, maxLen);
        }else {
            return content;
        }
    }
}
