package edu.school21.services;

import edu.school21.exeptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository usersRepository;
    
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    public boolean authenticate(String login, String password) {
        boolean result = false;
        User user = this.usersRepository.findByLogin(login);
        if (user.getAuthenticated()) {
            throw new AlreadyAuthenticatedException(
                    "The user has been already authenticated");
        }
        if (user.getPassword().equals(password)) {
            user.setAuthenticated(true);
            this.usersRepository.update(user);
            result = true;
        }
        return result;
    }
}
