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
        hpc.setTitle(resultSet.getString("abstract"));
        hpc.setVisitTimes(resultSet.getInt("visits"));
        return hpc;
    }
}
