package com.epam.codinggym;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GreetingGenerator {

    protected void printHello(String name) {
        if (name == null) {
            System.out.println("Hello you!");
        } else {
            System.out.println("Hello, " + name);
        }

    }

    public Person generatePerson() {
        List<String> names = List.of("Bart", "Lisa", "Homer", "Marge", "Maggie");
        Person person = new Person();
        person.setName(names.get(ThreadLocalRandom.current().nextInt(names.size())));
        return person;
    }

    public void say() {
        this.printHello(generatePerson().getName());
    }
}
