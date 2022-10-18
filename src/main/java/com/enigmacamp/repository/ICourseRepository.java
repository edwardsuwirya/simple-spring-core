package com.enigmacamp.repository;

import com.enigmacamp.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<Course> getAll() throws Exception;

    Course create(Course course) throws Exception;

    Optional<Course> findById(Integer id) throws Exception;

    void update(Course course, Integer id) throws Exception;

    void delete(Integer id) throws Exception;
}
