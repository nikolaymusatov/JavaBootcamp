package edu.school21.SocketServer.services;

import edu.school21.SocketServer.models.User;

public interface UsersService {
    boolean signUp(String login, String password);
    boolean signIn(String login, String password);
    void logout();
    User getAuthorizedUser();
}
