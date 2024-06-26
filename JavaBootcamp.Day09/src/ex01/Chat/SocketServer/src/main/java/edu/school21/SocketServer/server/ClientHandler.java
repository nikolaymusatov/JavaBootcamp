package edu.school21.SocketServer.server;

import edu.school21.SocketServer.services.MessagesService;
import edu.school21.SocketServer.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    private BufferedReader reader;
    private UsersService usersService;
    private MessagesService messagesService;
    
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }
    
    @Override
    public void run() {
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.server.addWriter(writer);
            writer.println("Hello from server!");
            while (true) {
                String message = reader.readLine();
                System.out.println("Received message: " + message);
                this.server.broadcastMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.server.removeWriter(this.writer);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean signUp() throws IOException {
        this.writer.println("Enter login:");
        String login = reader.readLine();
        writer.println("Enter password:");
        String password = reader.readLine();
        return this.usersService.signUp(login, password);
    }
    
    private boolean signIn() throws IOException {
        this.writer.println("Enter login:");
        String login = reader.readLine();
        writer.println("Enter password:");
        String password = reader.readLine();
        return this.usersService.signIn(login, password);
    }
    
    private void logout() {
        this.usersService.logout();
    }
    
    private void getMessage() throws IOException {
        this.writer.println("Enter message:");
        String text = reader.readLine();
        this.messagesService.saveMessage(this.usersService.getAuthorizedUser(), text);
    }
}
