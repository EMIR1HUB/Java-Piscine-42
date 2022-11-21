package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private static BufferedReader reader;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    private static PrintWriter out;
    private String userName;

    public Client(Integer port) {
        try {
            Socket socket = new Socket("localhost", port);
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(System.in));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void start() {
        signUpOrSignIn();
        listenForMessage();

        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Start messaging\n> ");
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                if(messageToSend.equalsIgnoreCase("Exit")){
                    System.exit(0);
                }
                System.out.println(userName + ": " + messageToSend);
                bufferedWriter.write(userName + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgForGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgForGroupChat = bufferedReader.readLine();
                        System.out.println(msgForGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void signUpOrSignIn() {
        try {
            String serverMsg = bufferedReader.readLine();
            System.out.print(serverMsg + "\n> ");
            String signCommand = reader.readLine();   // читает на стороне клиента
            out.println(signCommand);     // отправляем на сервер
            while (!(signCommand.equalsIgnoreCase("SignUp")) && !(signCommand.equalsIgnoreCase("SignIn"))) {
                System.out.print(bufferedReader.readLine() + "\n> ");
                signCommand = reader.readLine();
                out.println(signCommand);
            }

            System.out.print(bufferedReader.readLine() + "\n> ");
            userName = reader.readLine();
            out.println(userName);

            System.out.print(bufferedReader.readLine() + "\n> ");
            String password = reader.readLine();
            out.println(password);


            if (signCommand.equalsIgnoreCase("SignUp")) {
                System.out.println(bufferedReader.readLine());
                signUpOrSignIn();
            }
            if(signCommand.equalsIgnoreCase("SignIn")){
                String info = bufferedReader.readLine();
                if(!(info.equals("Successful!"))){
                    System.out.println(info);
                    signUpOrSignIn();
                }
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
