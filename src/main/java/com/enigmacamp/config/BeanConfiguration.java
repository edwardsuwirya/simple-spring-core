package com.enigmacamp.config;

import com.enigmacamp.repository.CourseArrayRepository;
import com.enigmacamp.repository.ErrorRepository;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import com.enigmacamp.util.IRandomStringGenerator;
import com.enigmacamp.util.RandomInt;
import com.enigmacamp.util.UuidGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean
    public ErrorRepository errorRepository() {
        ErrorRepository errorRepository = new ErrorRepository();
        errorRepository.setRandomStringGenerator(getRandomInt());
        return errorRepository;
    }

    @Bean
    @Qualifier("randomInt")
    public IRandomStringGenerator getRandomInt() {
        return new RandomInt();
    }

    @Bean
    @Qualifier("randomUUID")
    public IRandomStringGenerator getRandomUUID() {
        return new UuidGenerator();
    }
}
