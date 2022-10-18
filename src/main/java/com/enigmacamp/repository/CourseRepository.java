package com.enigmacamp.repository;

import com.enigmacamp.model.Course;
import com.enigmacamp.model.mapper.CourseMapper;
import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements ICourseRepository {
    @Autowired
    IRandomStringGenerator randomStringGenerator;
    JdbcTemplate jdbcTemplate;
    private final String SQL_FIND_COURSE = "select * from mst_course where courseId = ?";
    private final String SQL_DELETE_COURSE = "delete from mst_course where courseId = ?";
    private final String SQL_UPDATE_COURSE = "update mst_course set title = ?, description = ?, link  = ? where courseId = ?";
    private final String SQL_GET_ALL = "select * from mst_course";
    private final String SQL_INSERT_COURSE = "insert into mst_course values(?,?,?,?)";

    public CourseRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Course> getAll() throws Exception {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new CourseMapper());
        } catch (DataAccessException e) {
            throw new Exception("Failed to get course");
        }
    }

    @Override
    public Course create(Course course) throws Exception {
        try {
            course.setCourseId(randomStringGenerator.random());
            int result = jdbcTemplate.update(SQL_INSERT_COURSE, course.getCourseId(), course.getTitle(), course.getDescription(), course.getLink());
            if (result <= 0) {
                throw new Exception("Failed to insert course");
            }
            return course;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        try {
            Course course = jdbcTemplate.queryForObject(SQL_FIND_COURSE, new CourseMapper(), new Object[]{id});
            return Optional.ofNullable(course);
        } catch (DataAccessException e) {
            throw new Exception("Failed to get course");
        }
    }

    @Override
    public void update(Course course, String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_UPDATE_COURSE, course.getTitle(), course.getDescription(), course.getLink(),
                    course.getCourseId());
        } catch (DataAccessException e) {
            throw new Exception("Failed to update course");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            jdbcTemplate.update(SQL_DELETE_COURSE, id);
        } catch (DataAccessException e) {
            throw new Exception("Failed to delete course");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bulk(List<Course> courses) throws Exception {
        int jobCount = courses.size();
        try {
            for (Course course : courses) {
                course.setCourseId(randomStringGenerator.random());
                if (jobCount == 2) {
                    throw new Exception("Failed to insert course");
                }
                int result = jdbcTemplate.update(SQL_INSERT_COURSE, course.getCourseId(), course.getTitle(), course.getDescription(), course.getLink());
                if (result <= 0) {
                    throw new Exception("Failed to insert course");
                }
                jobCount--;
            }

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
