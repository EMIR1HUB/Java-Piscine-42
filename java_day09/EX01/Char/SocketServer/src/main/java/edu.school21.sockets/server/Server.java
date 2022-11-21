package edu.school21.sockets.server;

import edu.school21.sockets.models.UserHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch (IOException e){
            closeServerSocket();
        }
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New client has connected!");

                UserHandler userHandler = new UserHandler(socket);
                Thread thread = new Thread(userHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
