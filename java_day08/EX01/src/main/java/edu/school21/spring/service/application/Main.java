package edu.school21.spring.service.application;

import edu.school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("userRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
//        System.out.println(usersRepository.findById(1L).get());
//        System.out.println(usersRepository.findByEmail("'jnidorin@student.21-school.ru'").get());
        usersRepository = context.getBean("userRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
//        System.out.println(usersRepository.findById(1L).get());
//        usersRepository.save(new User("emir230301@gmail.com"));
//        usersRepository.update(new User(6L, "gmenger@student.21-school.ru"));
//        usersRepository.delete(6l);
        System.out.println(usersRepository.findByEmail("'evalorie@student.21-school.ru'").get());
    }
}
