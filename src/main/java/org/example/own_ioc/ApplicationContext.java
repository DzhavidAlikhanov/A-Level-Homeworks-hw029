package org.example.own_ioc;

import org.example.own_ioc.exceptions.BeanNotFoundException;
import org.example.own_ioc.exceptions.DuplicateBeanException;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private Map<String, Object> beanMap = new HashMap<>();

    public ApplicationContext(String packageName) {
        BeanScanner.scan(packageName, this);
    }

    public Object getBean(Class<?> clazz) {
        for (Object bean : beanMap.values()) {
            if (clazz.isInstance(bean)) {
                return bean;
            }
        }
        throw new BeanNotFoundException("Bean of type '" + clazz.getName() + "' not found.");
    }

    public Object getBean(String beanName) {
        if (beanMap.containsKey(beanName)) {
            return beanMap.get(beanName);
        } else {
            throw new BeanNotFoundException("Bean with name '" + beanName + "' not found.");
        }
    }

    public void registerBean(String beanName, Object bean) {
        if (beanMap.containsKey(beanName)) {
            throw new DuplicateBeanException("Duplicate bean definition for name '" + beanName + "'.");
        }
        beanMap.put(beanName, bean);
    }
}
