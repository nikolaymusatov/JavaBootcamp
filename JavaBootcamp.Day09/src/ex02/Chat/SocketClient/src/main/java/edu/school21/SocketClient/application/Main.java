package edu.school21.SocketClient.application;


import edu.school21.SocketClient.client.Client;

public class Main {
    public static void main(String[] args) {
        int port = 12345;
        for (String arg : args) {
            if (arg.startsWith("--server-port=")) {
                port = Integer.parseInt(arg.substring("--server-port=" .length()));
            }
        }
        
        Client client = new Client("localhost", port);
        client.startClient();
    }
}