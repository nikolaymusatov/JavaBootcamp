package edu.school21.SocketServer.application;

import edu.school21.SocketServer.config.ApplicationConfig;
import edu.school21.SocketServer.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        int port = 12345;
        for (String arg : args) {
            if (arg.startsWith("--port=")) {
                port = Integer.parseInt(arg.substring("--port=" .length()));
            }
        }
        
        System.setProperty("server.port", String.valueOf(port));
        
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Server server = context.getBean(Server.class);
        server.startServer();
    }
}