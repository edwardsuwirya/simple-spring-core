package com.enigmacamp.repository;

import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ErrorRepository {
    @Autowired
    @Qualifier("randomInt")
    IRandomStringGenerator randomStringGenerator;

    public void getRandom() {
        System.out.println(randomStringGenerator.random());
    }
}
