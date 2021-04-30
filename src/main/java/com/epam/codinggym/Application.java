package com.epam.codinggym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        GreetingGenerator bean = context.getBean(GreetingGenerator.class);

//        Person person = bean.generatePerson();
//
//        System.out.println(person.getName());
//
        bean.printHello("Simba");

        bean.say();
    }

}
