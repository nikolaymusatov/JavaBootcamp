package edu.school21.Service.application;

import edu.school21.Service.config.ApplicationConfig;
import edu.school21.Service.models.User;
import edu.school21.Service.repositories.UsersRepository;
import edu.school21.Service.services.UsersService;
import edu.school21.Service.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersRepository usersRepository = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class);
        UsersService usersService = context.getBean(
                "usersServiceImpl", UsersServiceImpl.class);
        System.out.println(usersRepository.findAll());
        usersService.signUp("piter228@yandex.ru");
        System.out.println(usersRepository.findAll());
    }
}