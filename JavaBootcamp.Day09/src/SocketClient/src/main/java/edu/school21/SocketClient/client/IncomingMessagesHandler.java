package edu.school21.SocketClient.client;

import java.io.BufferedReader;

public class IncomingMessagesHandler implements Runnable {
    private BufferedReader reader;
    private volatile boolean running;
    private Client client;
    
    public IncomingMessagesHandler(BufferedReader reader, Client client) {
        this.reader = reader;
        this.running = true;
        this.client = client;
    }
    
    @Override
    public void run() {
        try {
            String messageFromServer;
            while (running) {
                messageFromServer = reader.readLine();
                if (messageFromServer.equals("/exit")) {
                    this.stop();
                } else {
                    System.out.println(messageFromServer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
         this.running = false;
         this.client.stop();
    }
}
