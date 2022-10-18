package com.enigmacamp.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("number")
@Qualifier("number")
public class RandomInt implements IRandomStringGenerator {
    @Override
    public String random() {
        Random rand = new Random();
        int upperbound = 1000000;
        int int_random = rand.nextInt(upperbound);
        return String.valueOf(int_random);
    }
}
