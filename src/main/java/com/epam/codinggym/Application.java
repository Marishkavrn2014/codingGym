package com.epam.codinggym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        SomeInterface someWorker = context.getBean("someWorker", SomeInterface.class);
        someWorker.someWork();
        someWorker.someRest();
    }

}
