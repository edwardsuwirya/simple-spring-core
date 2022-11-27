package com.enigmacamp.util.annotation;

import com.enigmacamp.util.IRandomStringGenerator;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component("ext")
public class ExternalLibRandom implements IRandomStringGenerator {
    private Faker faker;

    public ExternalLibRandom(Faker faker) {
        this.faker = faker;
    }

    @Override
    public String random() {
        return faker.animal().name();
    }
}
