package edu.school21.SocketServer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@Component
public class Server {
    private int port;
    private Set<PrintWriter> clientWriters;
    
    @Autowired
    public Server(@Value("${server.port}") int port) {
        this.port = port;
        this.clientWriters = new HashSet<>();
    }
    
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);
                ClientHandler clientHandler = new ClientHandler(socket, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public synchronized void addWriter(PrintWriter writer) {
        this.clientWriters.add(writer);
    }
    
    public synchronized void removeWriter(PrintWriter writer) {
        this.clientWriters.remove(writer);
    }
    
    public synchronized void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}
