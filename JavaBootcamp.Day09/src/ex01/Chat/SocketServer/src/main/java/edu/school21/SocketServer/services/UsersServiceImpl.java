package edu.school21.SocketServer.services;

import edu.school21.SocketServer.exceptions.AlreadyRegisteredException;
import edu.school21.SocketServer.exceptions.NotAuthorizedException;
import edu.school21.SocketServer.exceptions.NotRegisteredException;
import edu.school21.SocketServer.models.User;
import edu.school21.SocketServer.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    private User authorizedUser;
    private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public boolean signUp(String login, String rawPassword) throws RuntimeException {
        if (this.usersRepository.findByLogin(login).isPresent()) {
            throw new AlreadyRegisteredException("User with such email is already " +
                    "signed up");
        } else {
            long id = this.usersRepository.findAll().size() + 1L;
            String encodedPassword = this.passwordEncoder.encode(rawPassword);
            this.usersRepository.save(new User(login, encodedPassword, false));
        }
        return true;
    }
    
    @Override
    public boolean signIn(String login, String password) {
        Optional<User> optionalUser = this.usersRepository.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new NotRegisteredException("No such user, please, sign up " +
                    "first");
        }
        boolean result = optionalUser
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
        optionalUser.get().setAuthorized(true);
        this.usersRepository.update(optionalUser.get());
        this.authorizedUser = optionalUser.get();
        System.out.println("AUTHORIZED USER: ");
        System.out.println(this.authorizedUser);
        return result;
    }
    
    @Override
    public void logout() {
        if (this.authorizedUser == null) {
            throw new NotAuthorizedException("No user to logout");
        }
        this.authorizedUser.setAuthorized(false);
        this.usersRepository.update(this.authorizedUser);
        System.out.println("USER TO LOGOUT: ");
        System.out.println(this.authorizedUser);
        this.authorizedUser = null;
    }
    
    @Override
    public User getAuthorizedUser() {
        return this.authorizedUser;
    }
}
