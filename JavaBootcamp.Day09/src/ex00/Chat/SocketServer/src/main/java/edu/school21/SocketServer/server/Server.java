package edu.school21.SocketServer.server;

import edu.school21.SocketServer.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
    private int port;
    private UsersService usersService;
    
    @Autowired
    public Server(@Value("${server.port}") int port,
                  UsersService usersService) {
        this.port = port;
        this.usersService = usersService;
    }
    
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server is listening on port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");
            handleClient(socket);
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void handleClient(Socket socket) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer =
                     new PrintWriter(socket.getOutputStream(), true)
        ) {
            writer.println("Hello from server!");
            String textFromClient = reader.readLine();
            if (textFromClient.equals("signUp")) {
                writer.println("Enter login:");
                String login = reader.readLine();
                writer.println("Enter password:");
                String password = reader.readLine();
                writer.println(this.usersService.signUp(login, password));
            } else {

            }
        } catch (IOException ex) {
            System.out.println("Client handler exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
