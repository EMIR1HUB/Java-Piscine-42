package edu.school21.spring.service.application;

import edu.school21.spring.service.config.ApplicationConfig;
import edu.school21.spring.service.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();

//        Вывод сканированных компонентов
//        for (String beanName : ctx.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }
//        System.out.println();


        UsersServiceImpl service = ctx.getBean(UsersServiceImpl.class);
        System.out.println(service.signUp("popTemp@gmail.com"));

        ctx.close();

    }
}
