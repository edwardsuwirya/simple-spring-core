package com.enigmacamp.repository;

import com.enigmacamp.util.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorRepository {
    private final IRandomStringGenerator randomStringGenerator;

    public ErrorRepository(@Qualifier("number") IRandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
    }

    public void getRandom() {
        System.out.println(randomStringGenerator.random());
    }
}
