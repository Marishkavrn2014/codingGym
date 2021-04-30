package com.epam.codinggym.aspect;

import com.epam.codinggym.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ChangeSomeName {

    @Pointcut("execution(* *generatePerson(..))")
    public void isGeneratePerson() {}

    @AfterReturning(value = "isGeneratePerson()", returning = "retval")
    public Person someRealPerson(Person retval) {
        retval.setName("Muphasa");
        return retval;
    }


    @Around(value = "execution(* *printHello(..)) && args(name)")
    public Object printSomeName(ProceedingJoinPoint pjp, String name) throws Throwable {
        System.out.println("I AM HERE!!!!");
        final Signature signature = pjp.getSignature();
        System.out.println(pjp.getSignature());
        System.out.println("___________________");
        System.out.println(signature.getName());
        System.out.println(signature.getDeclaringType());
        return pjp.proceed(new Object[]{"some" + name});
    }
}
