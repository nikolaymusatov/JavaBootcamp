package edu.school21.Service.application;

import edu.school21.Service.models.User;
import edu.school21.Service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findByEmail("qwerty@mail.ru").orElse(null));
    }
}