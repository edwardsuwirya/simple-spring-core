package com.enigmacamp.service;

import com.enigmacamp.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseService implements ICourseService {
    private List<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    @Override
    public List<Course> list() {
        return courses;
    }

    @Override
    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> get(String id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) {

    }

    @Override
    public void delete(String id) {

    }
}
