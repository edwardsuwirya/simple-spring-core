package com.enigmacamp;

import com.enigmacamp.model.Course;
import com.enigmacamp.service.ICourseService;
import com.enigmacamp.util.annotation.HelloApplication;

import java.util.List;

@HelloApplication
public class Application {
    private final ICourseService courseService;

    public Application(ICourseService courseService) {
        this.courseService = courseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    public void run() {
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
