package com.enigmacamp.config;

import com.enigmacamp.repository.CourseArrayRepository;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// Anotation configuration sangat penting untuk membaca bean scope
// Apabila annotation configuration dihilangkan, aplikasi masih bisa berjalan, tetapi
// object getCourseRepository tidak akan menjadi singleton
@Configuration
public class BeanConfiguration {
    @Bean
    @Scope("prototype")
    public ICourseService getCourseService() {
        return new CourseService(getCourseRepository());
    }

    @Bean
    @Scope("singleton")
    public ICourseRepository getCourseRepository() {
        return new CourseArrayRepository();
    }
}
