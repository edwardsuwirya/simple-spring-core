package com.enigmacamp.config;

import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ICourseService getCourseService() {
        return new CourseService();
    }
}
