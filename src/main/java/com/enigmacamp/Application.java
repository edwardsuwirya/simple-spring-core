package com.enigmacamp;

import com.enigmacamp.model.Course;
import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {
//        System.out.println("Hello Spring...");
//        Course[] courses = new Course[10];
//        Optional<Course> empty = Optional.empty();
//        System.out.println(empty.orElse(new Course()));
//        Optional<Course> value = Optional.ofNullable(courses[5]);
//        System.out.println(value.orElse(new Course()));

        // Ngga banget yaaa... walaupun aplikasi bisa dijalankan dengan baik
        // class Application membutuhkan class CourseService, CourseService adalah dependensi dari class Application
        // Teknik yang digunakan dibawah Ini yang disebut Hardcoded Dependency dan tight coupling
        // Dimana class Application yang bertanggung jawab membuat object courseService dan dalam pembuatan object nya
        // digunakan Class CourseService bukan Interface

        // Kita coba terapkan IoC, dimana tanggung jawab pembuatan object dependensi diserahkan ke spring framework
        // Deklarasi object dependensi bisa dibuat dengan 2 cara
        // 1. berbasis XML (cara tradisional)
        // 2. berbasis java code
        // Object dependensi yang didaftarkan dalam Spring IoC container disebut Bean
        // Untuk menggunakan spring bean di class yang membutuhkan, kita menggunakan spring context

        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ICourseService courseService = (ICourseService) ctx.getBean("courseService");
        Course springCourse = new Course();
        springCourse.setCourseId("123");
        springCourse.setDescription("Spring IoC");
        springCourse.setLink("https://www.javatpoint.com/ioc-container");
        springCourse.setTitle("Spring Framework");
        courseService.create(springCourse);

        List<Course> courseList = courseService.list();
        for (Course course : courseList) {
            System.out.printf(course.toString());
        }
    }
}
