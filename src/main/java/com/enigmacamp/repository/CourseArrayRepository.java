package com.enigmacamp.repository;

import com.enigmacamp.model.Course;
import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseArrayRepository implements ICourseRepository {
    private final IRandomStringGenerator randomStringGenerator;

    public CourseArrayRepository(BeanFactory beanFactory, @Value("${generator.type}")
    String randomGenerator) {
        this.randomStringGenerator = (IRandomStringGenerator) beanFactory.getBean(randomGenerator);
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
