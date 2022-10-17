package com.enigmacamp.service;

import com.enigmacamp.model.Course;

import java.util.List;
import java.util.Optional;


// Penggunaan interface akan membuat kebutuhan dependensi menjadi loose coupling
// Maksudnya, object dependensi akan menjadi lebih fleksibel dalam implementasinya
// Sesuai dengan prinsip Open For Extension, Close For Modification
// Contoh sederhana, misalnya kita mau membuat CourseService baru yang menggunakan database,
// Selama CourseService baru ini mengimplementasikan Interface ICourseService, kita tidak perlu khawatir
// tentang class Application.
// Contoh kedua terkait Unit Testing, kita dengan mudahnya melakukan testing dengan memberikan object mock
public interface ICourseService {
    List<Course> list();

    Course create(Course course);

    Optional<Course> get(Integer id);

    void update(Course course, Integer id);

    void delete(Integer id);
}
