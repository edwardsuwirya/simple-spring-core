package com.enigmacamp.service;

import com.enigmacamp.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseService {
    private List<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    public List<Course> list() {
        return courses;
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Optional<Course> get(Integer id) {
        return Optional.empty();
    }

    public void update(Course course, Integer id) {

    }

    public void delete(Integer id) {

    }
}
