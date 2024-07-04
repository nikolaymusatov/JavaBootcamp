package edu.school21.SocketClient.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private String hostname;
    private int port;
    private volatile boolean running;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private BufferedReader consoleReader;
    
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.running = true;
    }
    
    public void startClient() {
        try {
            this.socket = new Socket(hostname, port);
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
            IncomingMessagesHandler messagesHandler =
                    new IncomingMessagesHandler(reader, this);
            Thread handlerThread = new Thread(messagesHandler);
            handlerThread.start();
            String messageToServer;
            while (running) {
                if (consoleReader.ready()) {
                    messageToServer = consoleReader.readLine();
                    writer.println(messageToServer);
                }
                Thread.sleep(100);
            }
            handlerThread.join();
        } catch (Exception e) {
            System.out.println("Clients error: " + e.getMessage());
        }
    }
    
    public void stop() {
        try {
            this.running = false;
            if (this.consoleReader != null) this.consoleReader.close();
            if (this.writer != null) this.writer.close();
            if (this.reader != null) this.reader.close();
            if (this.socket != null && !this.socket.isClosed()) this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
