package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader input;
    private static PrintWriter out;

    public Client(Integer port) {
        try {
            clientSocket = new Socket("localhost", port);
            reader = new BufferedReader(new InputStreamReader(System.in));
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            String serverMsg = input.readLine();
            System.out.print(serverMsg + "\n> ");
            String signUpCommand = reader.readLine();   // читает на стороне клиента
            out.println(signUpCommand);     // отправляем на сервер
            while (!(signUpCommand.equalsIgnoreCase("SignUp"))) {
                System.out.print(input.readLine() + "\n> ");
                signUpCommand = reader.readLine();
                out.println(signUpCommand);
            }

            serverMsg = input.readLine();
            System.out.print(serverMsg + "\n> ");
            String userName = reader.readLine();
            out.println(userName);

            serverMsg = input.readLine();
            System.out.print(serverMsg + "\n> ");
            String password = reader.readLine();
            out.println(password);

            serverMsg = input.readLine();
            System.out.println(serverMsg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
