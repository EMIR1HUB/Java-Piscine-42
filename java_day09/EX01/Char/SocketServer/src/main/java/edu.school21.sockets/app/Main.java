package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].matches("--port=\\d++")) {
            Server server = new Server(Integer.parseInt(args[0].split("=")[1]));
            server.startServer();
        }

//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
//        UsersServiceImpl service = ctx.getBean(UsersServiceImpl.class);
//
//        User user = new User("Marsel", "query007");
//        System.out.println(service.signUp(user));
//
//        ctx.close();
    }
}
