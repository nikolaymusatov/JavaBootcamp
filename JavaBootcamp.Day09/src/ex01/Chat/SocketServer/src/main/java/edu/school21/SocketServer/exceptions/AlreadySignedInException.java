package edu.school21.SocketServer.exceptions;

public class AlreadySignedInException extends RuntimeException {
    public AlreadySignedInException(String message) {
        super(message);
    }
}
