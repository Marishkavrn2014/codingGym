package com.epam.codinggym;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
public class SomeWorkerTrackerBeanPostProcessor implements BeanPostProcessor {
    //    private final Logger logger = LoggerFactory.getLogger(SomeWorkerTrackerBeanPostProcessor.class);
    Map<String, Class> profiledBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(SomeTracker.class)) {
                profiledBeans.put(beanName, bean.getClass());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        Map<String, Method> annoMethods = new HashMap<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(SomeTracker.class)) {
                annoMethods.put(method.getName(), method);
            }
        }

        if (profiledBeans.containsKey(beanName)) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("Annotations: " + Arrays.toString(method.getDeclaredAnnotations()));
                    if (!annoMethods.containsKey(method.getName())) {
                        return method.invoke(bean, objects);
                    }

                    final long before = Instant.now().toEpochMilli();

                    Object invoke = method.invoke(bean, objects);

                    long after = Instant.now().toEpochMilli();

                    final long result = after - before;

//                    logger.info("Time : {}", result);
                    System.out.println(result);
                    return invoke;
                }
            });
        }
        return bean;
    }
}
