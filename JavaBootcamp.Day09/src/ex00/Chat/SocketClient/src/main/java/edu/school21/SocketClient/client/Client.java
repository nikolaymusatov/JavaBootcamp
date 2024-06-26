package edu.school21.SocketClient.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String hostname;
    private int port;
    
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    
    public void startClient() {
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(reader.readLine());
            String response;
            String text;
            do {
                text = consoleReader.readLine();
                writer.println(text);
                response = reader.readLine();
                if (response != null) {
                    System.out.println(response);
                }
            } while (!text.equals("exit"));
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
