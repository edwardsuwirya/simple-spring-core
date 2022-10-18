package com.enigmacamp;

import com.enigmacamp.config.BeanConfiguration;
import com.enigmacamp.model.Course;
import com.enigmacamp.repository.ErrorRepository;
import com.enigmacamp.service.ICourseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    static ICourseService courseService;

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

//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.register(BeanConfiguration.class);
//        ctx.refresh();
//        Alternative
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);

//        Pembentukan object yang salah, karena tidak dibentuk oleh IoC
//        new ErrorRepository().getRandom();
        ctx.getBean(ErrorRepository.class).getRandom();

        courseService = ctx.getBean(ICourseService.class);
        System.out.println("counterService object Id :" + courseService.hashCode());
        Course springCourse = new Course();
        springCourse.setDescription("Spring IoC");
        springCourse.setLink("https://www.javatpoint.com/ioc-container");
        springCourse.setTitle("Spring Framework");
        courseService.create(springCourse);

        courseService = ctx.getBean(ICourseService.class);
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

    static private void printResult() {
        List<Course> courseList = courseService.list();
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }
}
