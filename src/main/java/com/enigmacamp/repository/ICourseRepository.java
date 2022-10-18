package com.enigmacamp.repository;

import com.enigmacamp.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<Course> getAll() throws Exception;

    Course create(Course course) throws Exception;

    Optional<Course> findById(String id) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;
}
