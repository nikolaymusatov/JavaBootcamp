package edu.school21.SocketServer.services;

import edu.school21.SocketServer.exceptions.AlreadyRegisteredException;
import edu.school21.SocketServer.exceptions.NotRegisteredException;
import edu.school21.SocketServer.exceptions.WrongPasswordException;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public synchronized boolean signUp(String login, String rawPassword) throws RuntimeException {
        if (this.usersRepository.findByLogin(login).isPresent()) {
            throw new AlreadyRegisteredException("User with such email is already " +
                    "signed up");
        } else {
            String encodedPassword = this.passwordEncoder.encode(rawPassword);
            this.usersRepository.save(new User(login, encodedPassword, false));
        }
        return true;
    }
    
    @Override
    @Transactional
    public synchronized User signIn(String login, String password) {
        Optional<User> optionalUser = this.usersRepository.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new NotRegisteredException("No such user, please, sign up first");
        }
        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setAuthorized(true);
            this.usersRepository.update(user);
            return user;
        } else {
            throw new WrongPasswordException("Wrong password!");
        }
    }
    
    @Override
    @Transactional
    public void logout(User user) {
        user.setAuthorized(false);
        this.usersRepository.update(user);
    }
}
