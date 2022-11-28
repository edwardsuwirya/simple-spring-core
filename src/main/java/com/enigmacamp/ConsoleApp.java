package com.enigmacamp;

import com.enigmacamp.container.ConsoleRunner;
import com.enigmacamp.model.Course;
import com.enigmacamp.service.ICourseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleApp implements ConsoleRunner {
    private final ICourseService courseService;

    public ConsoleApp(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) {
        System.out.println("counterService object Id :" + courseService.hashCode());
        Course springCourse = new Course();
        springCourse.setDescription("Spring IoC");
        springCourse.setLink("https://www.javatpoint.com/ioc-container");
        springCourse.setTitle("Spring Framework");
        courseService.create(springCourse);

        System.out.println("counterService object Id :" + courseService.hashCode());
        Course goCourse = new Course();
        goCourse.setDescription("Goroutine");
        goCourse.setLink("https://gobyexample.com/goroutines");
        goCourse.setTitle("Golang");
        courseService.create(goCourse);
//        Contoh hasil cetak bean dengan scope
        printResult();
        courseService.delete(goCourse.getCourseId());
//        printResult();
    }

    private void printResult() {
        List<Course> courseList = courseService.list();
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }
}
