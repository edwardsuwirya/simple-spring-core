package com.enigmacamp.util;

import java.util.Random;

public class RandomInt implements IRandomStringGenerator {
    @Override
    public String random() {
        Random rand = new Random();
        int upperbound = 1000000;
        int int_random = rand.nextInt(upperbound);
        return String.valueOf(int_random);
    }
}
