package com.enigmacamp.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("uuid")
@Qualifier("uuid")
public class UuidGenerator implements IRandomStringGenerator {
    @Override
    public String random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
