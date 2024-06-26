package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public String signUp(String login, String rawPassword) throws RuntimeException {
        if (this.usersRepository.findByLogin(login).isPresent()) {
            throw new RuntimeException("User with such email is already " +
                    "signed up");
        } else {
            long id = this.usersRepository.findAll().size() + 1L;
            String encodedPassword = this.passwordEncoder.encode(rawPassword);
            this.usersRepository.save(new User(id, login, encodedPassword));
        }
        return "Successful!";
    }
}
