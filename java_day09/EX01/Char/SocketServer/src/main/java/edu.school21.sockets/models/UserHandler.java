package edu.school21.sockets.models;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class UserHandler implements Runnable {
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PrintWriter out;
    private String userName;

    private UsersService usersService;

    public UserHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            usersService = ctx.getBean(UsersServiceImpl.class);

            startSingUpOrSingIn();
            this.userName = bufferedReader.readLine();
            userHandlers.add(this);
            broadcastMessage("\nSERVER: " + userName + " has entered the chat");
            ctx.close();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (UserHandler userHandler : userHandlers) {
            try {
                if (!userHandler.userName.equals(userName)) {
                    userHandler.bufferedWriter.write(messageToSend);
                    userHandler.bufferedWriter.newLine();
                    userHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void startSingUpOrSingIn() {
        try {
            out.println("Hello from server!");
            String signCommand = bufferedReader.readLine();
            while (!(signCommand.equalsIgnoreCase("SignUp") || signCommand.equalsIgnoreCase("SignIn"))) {
                out.println("Please enter what do you want to do");
                signCommand = bufferedReader.readLine();
            }
            out.println("Enter username:");
            String userName = bufferedReader.readLine();
            out.println("Enter password:");
            String password = bufferedReader.readLine();

            User user = new User(userName, password);
            if (signCommand.equalsIgnoreCase("SignUp")) {
                if (usersService.signUp(user)) out.println("Successful!");
                else out.println("User with this name present in the table");
                startSingUpOrSingIn();
            }
            if (signCommand.equalsIgnoreCase("SignIn")) {
                if(usersService.signIn(user)) out.println("Successful!");
                else {
                    out.println("User data incorrect");
                    startSingUpOrSingIn();
                }
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        userHandlers.remove(this);
        broadcastMessage("SERVER: " + userName + " has left the chat!");
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
