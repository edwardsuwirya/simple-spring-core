package com.enigmacamp.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbMigration implements IDbMigration {
    JdbcTemplate jdbcTemplate;

    boolean forMigration;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setForMigration(boolean forMigration) {
        this.forMigration = forMigration;
    }
    @Override
    public DbMigrationResult migrate() {
        if (forMigration) {
            try {
                String CREATE_MST_COURSE = "create table mst_course(courseId varchar(40) primary key, title varchar(100), description varchar(250), link varchar(250))";
                jdbcTemplate.execute(CREATE_MST_COURSE);
                return DbMigrationResult.MIGRATION_SUCCESS;
            } catch (DataAccessException e) {
                return DbMigrationResult.MIGRATION_FAILED;
            }
        } else {
            return DbMigrationResult.MIGRATION_SKIP;
        }
    }
}
