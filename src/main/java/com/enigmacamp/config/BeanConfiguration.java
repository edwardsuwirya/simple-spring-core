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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

// Anotation configuration sangat penting untuk membaca bean scope
// Apabila annotation configuration dihilangkan, aplikasi masih bisa berjalan, tetapi
// object getCourseRepository tidak akan menjadi singleton
@Configuration
@PropertySource("classpath:app.properties")
public class BeanConfiguration {
//    private final Environment environment;
//    private final String RANDOM_GENERATOR = "generator.type";
//
//    public BeanConfiguration(Environment environment) {
//        this.environment = environment;
//    }

    @Value("${generator.type}")
    String randomGenerator;

    @Bean
    @Scope("prototype")
    public ICourseService getCourseService() {
        return new CourseService(getCourseRepository());
    }

    @Bean
    @Scope("singleton")
    public ICourseRepository getCourseRepository() {

//        String randomGenerator = environment.getProperty(RANDOM_GENERATOR);
        if (randomGenerator.equals("uuid")) {
            return new CourseArrayRepository(getRandomUUID());
        }
        return new CourseArrayRepository(getRandomInt());

    }

    @Bean
    public ErrorRepository errorRepository() {
        return new ErrorRepository();
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
