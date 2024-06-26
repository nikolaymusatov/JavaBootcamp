package edu.school21.Service.services;

import edu.school21.Service.models.User;
import edu.school21.Service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    
    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbc") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    public String signUp(String email) throws RuntimeException {
        String password = null;
        if (this.usersRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with such email is already " +
                    "signed up");
        } else {
            long id = this.usersRepository.findAll().size() + 1L;
            password = this.generateRandomPassword();
            this.usersRepository.save(new User(id, email, password));
        }
        return password;
    }
    
    private String generateRandomPassword() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int length = 10;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
