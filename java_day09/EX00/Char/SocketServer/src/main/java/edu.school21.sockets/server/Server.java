package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter out;
    private AnnotationConfigApplicationContext ctx;
    private UsersService service;

    public Server(int port) {
        try (ServerSocket ss = new ServerSocket(port)) {
            socket = ss.accept();

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            ctx = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
//            ctx.register(SocketsApplicationConfig.class);
//            ctx.refresh();

//            Вывод сканированных компонентов
//            for (String beanName : ctx.getBeanDefinitionNames()) {
//                System.out.println(beanName);
//            }
//            System.out.println();

            service = ctx.getBean(UsersServiceImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            out.println("Hello from server!");
            String regisCommand = input.readLine();
            while (!(regisCommand.equalsIgnoreCase("SignUp"))) {
                out.println("Please enter what do you want to do");
                regisCommand = input.readLine();
            }
            out.println("Enter username:");
            String userName = input.readLine();
            out.println("Enter password:");
            String password = input.readLine();

            User user = new User(userName, password);
            if (service.signUp(user)) {
                out.println("Successful!");
            } else {
                out.println("User with this name present in the table");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ctx.close();
                socket.close();
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
