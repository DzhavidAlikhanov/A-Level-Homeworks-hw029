package org.example.own_ioc;

import org.example.own_ioc.annotations.Bean;
import org.reflections.Reflections;

import java.util.Set;

public class BeanScanner {
    public static void scan(String packageName, ApplicationContext applicationContext) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Bean.class);

        for (Class<?> clazz : classes) {
            Bean annotation = clazz.getAnnotation(Bean.class);
            String beanName = annotation.value();

            if (beanName.isEmpty()) {
                beanName = clazz.getSimpleName();
            }

            Object beanInstance;
            try {
                beanInstance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating an instance of bean class: " + clazz.getName(), e);
            }

            applicationContext.registerBean(beanName, beanInstance);
        }
    }
}
