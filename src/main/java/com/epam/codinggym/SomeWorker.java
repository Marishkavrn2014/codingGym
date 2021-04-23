package com.epam.codinggym;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SomeWorker implements SomeInterface {

    @SomeTracker
    public void someWork() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Working...");
        }
    }

    public void someRest() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Rest...");
        }
    }
}
