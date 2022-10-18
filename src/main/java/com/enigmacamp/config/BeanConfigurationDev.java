package com.enigmacamp.config;

import com.enigmacamp.repository.CourseArrayRepository;
import com.enigmacamp.repository.ErrorRepository;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import com.enigmacamp.util.IDbMigration;
import com.enigmacamp.util.IRandomStringGenerator;
import com.enigmacamp.util.RandomInt;
import com.enigmacamp.util.SkipMigration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

// Anotation configuration sangat penting untuk membaca bean scope
// Apabila annotation configuration dihilangkan, aplikasi masih bisa berjalan, tetapi
// object getCourseRepository tidak akan menjadi singleton
@Profile("dev")
@Configuration
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class BeanConfigurationDev {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${client.header}")
    String clientHeader;

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
    public IDbMigration dbMigration() {
        return new SkipMigration();
    }

    @Bean
    public ErrorRepository errorRepository() {
        System.out.println("Profile :" + activeProfile);
        System.out.println("Header :" + clientHeader);
        ErrorRepository errorRepository = new ErrorRepository();
        errorRepository.setRandomStringGenerator(getRandomInt());
        return errorRepository;
    }

    @Bean
    public IRandomStringGenerator getRandomInt() {
        return new RandomInt();
    }
}
