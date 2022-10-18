package com.enigmacamp.repository;

import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class ErrorRepository {
    @Autowired
    IRandomStringGenerator randomStringGenerator;

    public void getRandom() {
        System.out.println(randomStringGenerator.random());
    }
}
