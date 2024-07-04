package edu.school21.SocketServer.exceptions;

public class NotAuthorizedException extends  RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
