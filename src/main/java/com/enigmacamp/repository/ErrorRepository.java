package com.enigmacamp.repository;

import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ErrorRepository {
    IRandomStringGenerator randomStringGenerator;

    public IRandomStringGenerator getRandomStringGenerator() {
        return randomStringGenerator;
    }

    public void setRandomStringGenerator(IRandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
    }


    public void getRandom() {
        System.out.println(getRandomStringGenerator().random());
    }
}
