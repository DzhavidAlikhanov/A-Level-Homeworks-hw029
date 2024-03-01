package org.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Entitlement ent = (Entitlement) ctx.getBean("entitlement");
        System.out.println(ent.getName());
        System.out.println(ent.getTime());

        ctx.close();
    }
}
