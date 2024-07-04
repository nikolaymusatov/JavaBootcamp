package edu.school21.SocketServer.exceptions;

public class NoSuchRoomException extends RuntimeException {
    public NoSuchRoomException(String message) {
        super(message);
    }
}
