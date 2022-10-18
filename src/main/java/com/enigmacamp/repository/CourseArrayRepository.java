package com.enigmacamp.repository;

import com.enigmacamp.model.Course;
import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseArrayRepository implements ICourseRepository {
    //    @Autowired
//    @Qualifier("randomUUID")
    IRandomStringGenerator randomStringGenerator;


    public CourseArrayRepository(IRandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
    }

    private List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public Course create(Course course) {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) {
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
    public void delete(String id) {
        for (Course course : courses) {
            if (course.getCourseId() == id) {
                courses.remove(course);
                break;
            }
        }
    }
}
