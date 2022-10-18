package com.enigmacamp.service;

import com.enigmacamp.model.Course;
import com.enigmacamp.repository.ICourseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class CourseService implements ICourseService {

    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        System.out.println("Counter repository object Id: " + this.courseRepository.hashCode());
    }

    @Override
    public List<Course> list() {
        try {
            return courseRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBulk(List<Course> courses) {
        try {
            courseRepository.bulk(courses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
