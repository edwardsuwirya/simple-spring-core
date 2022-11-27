package com.enigmacamp.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Anotation configuration sangat penting untuk membaca bean scope
// Apabila annotation configuration dihilangkan, aplikasi masih bisa berjalan, tetapi
// object getCourseRepository tidak akan menjadi singleton
@Configuration
@PropertySource("classpath:app.properties")
public class BeanConfiguration {

    @Bean
    public Faker apacheRandomString() {
        return new Faker();
    }
}
