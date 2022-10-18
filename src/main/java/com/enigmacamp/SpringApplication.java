package com.enigmacamp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void run(Class<Application> applicationClass) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(applicationClass);
        ctx.registerBean("app", applicationClass);
        String[] beans = ctx.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println(bean);
        }
        ((Application) ctx.getBean("app")).run();
    }
}
