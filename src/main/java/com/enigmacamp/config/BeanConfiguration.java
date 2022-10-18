package com.enigmacamp.config;

import com.enigmacamp.repository.CourseRepository;
import com.enigmacamp.repository.ErrorRepository;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.service.CourseService;
import com.enigmacamp.service.ICourseService;
import com.enigmacamp.util.DbMigration;
import com.enigmacamp.util.IDbMigration;
import com.enigmacamp.util.IRandomStringGenerator;
import com.enigmacamp.util.UuidGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// Anotation configuration sangat penting untuk membaca bean scope
// Apabila annotation configuration dihilangkan, aplikasi masih bisa berjalan, tetapi
// object getCourseRepository tidak akan menjadi singleton
@Profile("prod")
@Configuration
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class BeanConfiguration {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${client.header}")
    String clientHeader;
    @Value("${db.migration}")
    String dbMigration;
    @Value("${url}")
    String url;
    @Value("${dbuser}")
    String dbUser;
    @Value("${dbpassword}")
    String dbPassword;
    @Value("${driver}")
    String dbDriver;

    @Bean
    @Scope("prototype")
    public ICourseService getCourseService() {
        return new CourseService(getCourseRepository());
    }

    @Bean
    @Scope("singleton")
    public ICourseRepository getCourseRepository() {
        return new CourseRepository(dataSource());
    }

    @Bean
    public ErrorRepository errorRepository() {
        System.out.println("Profile :" + activeProfile);
        System.out.println("Header :" + clientHeader);
        ErrorRepository errorRepository = new ErrorRepository();
        errorRepository.setRandomStringGenerator(getRandomUUID());
        return errorRepository;
    }

    @Bean
    public IDbMigration dbMigration() {
        DbMigration migration = new DbMigration();
        migration.setJdbcTemplate(new JdbcTemplate(dataSource()));
        migration.setForMigration(dbMigration.equalsIgnoreCase("yes"));
        return migration;
    }

    @Bean
    public IRandomStringGenerator getRandomUUID() {
        return new UuidGenerator();
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);
        return driverManagerDataSource;
    }
}
