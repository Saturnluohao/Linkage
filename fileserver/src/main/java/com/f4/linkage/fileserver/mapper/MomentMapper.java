package com.f4.linkage.fileserver.mapper;

import com.f4.linkage.fileserver.model.Moment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MomentMapper implements RowMapper<Moment> {

    @Override
    public Moment mapRow(ResultSet resultSet, int i) throws SQLException {
        Moment moment = new Moment();
        String poster_name = resultSet.getString("poster_name");
        int moment_id = resultSet.getInt("id");
        int picture_num = resultSet.getInt("picture_num");
        int video_num = resultSet.getInt("video_num");

        List<String> picture = new ArrayList<>();
        List<String> videos = new ArrayList<>();

        for(int j = 0; j < picture_num; j++){
            picture.add("/moment/" + moment_id + "/picture/" + j);
        }

        for(int j = 0; j < video_num; j++){
            videos.add("/moment/" + moment_id + "/video/" + j);
        }

        moment.setId(moment_id);
        moment.setPoster_name(poster_name);
        moment.setText(resultSet.getString("text"));
        moment.setImg(picture);
        moment.setVideo(videos);
        moment.setTime(resultSet.getString("time"));
        moment.setPoster_icon("/icon/" + poster_name);
        return moment;
    }
}
