package com.f4.linkage.fileserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataUtil.class);

    private static String insertStatement = "INSERT INTO weblog VALUES (?,?,?,?,?)";

    public static boolean insertBlog(Object[] args){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ContextUtil.getBean("jdbcTemplate");
        LOGGER.info("jdbc template is " + jdbcTemplate);
        if(jdbcTemplate.update(insertStatement, args) == 1) {
            return true;
        }else {
            return false;
        }
    }
}
