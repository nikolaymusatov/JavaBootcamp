package edu.school21.SocketServer.server;

import edu.school21.SocketServer.models.Message;
import edu.school21.SocketServer.services.MessagesService;
import edu.school21.SocketServer.services.UsersService;
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
    private Set<ClientHandler> clients;
    private UsersService usersService;
    private MessagesService messagesService;
    
    
    @Autowired
    public Server(@Value("${server.port}") int port,
                  UsersService usersService, MessagesService messagesService) {
        this.port = port;
        this.clients = new HashSet<>();
        this.usersService = usersService;
        this.messagesService = messagesService;
    }
    
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);
                ClientHandler clientHandler = new ClientHandler(socket, this,
                        usersService, messagesService);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public synchronized void removeClient(ClientHandler client) {
        this.clients.remove(client);
    }
    
    public synchronized void broadcastMessage(Message message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
