package com.enigmacamp.repository;

import com.enigmacamp.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CourseArrayRepository implements ICourseRepository {
    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public Course create(Course course) {
        Random rand = new Random();
        int upperbound = 1000000;
        int int_random = rand.nextInt(upperbound);
        course.setCourseId(int_random);
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, Integer id) {
        for (Course existingCourse : courses) {
            if (existingCourse.getCourseId() == id) {
                existingCourse.setLink(course.getLink());
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                courses.remove(course);
                break;
            }
        }
    }
}
