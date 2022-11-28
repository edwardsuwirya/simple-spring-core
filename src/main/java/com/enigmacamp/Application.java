package com.enigmacamp;

import com.enigmacamp.container.AppContainer;
import com.enigmacamp.util.annotation.HelloApplication;

@HelloApplication
public class Application {
    public static void main(String[] args) {
        AppContainer.run(Application.class);
    }
}
