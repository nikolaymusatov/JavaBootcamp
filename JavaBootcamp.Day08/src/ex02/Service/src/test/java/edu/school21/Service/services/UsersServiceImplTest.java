package edu.school21.Service.services;

import edu.school21.Service.config.TestApplicationConfig;
import edu.school21.Service.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class UsersServiceImplTest {
    AnnotationConfigApplicationContext context;
    UsersRepository usersRepository;
    UsersService usersService;
    
    @Test
    void signUpTest_userNotSigned_success() {
        this.context =
                new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        this.usersRepository = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class);
        this.usersService = context.getBean(
                "usersServiceImpl", UsersServiceImpl.class);
        
        String result = this.usersService.signUp("test@example.com");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
