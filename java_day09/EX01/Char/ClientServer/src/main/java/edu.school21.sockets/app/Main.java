package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length == 1 && args[0].matches("--server-port=\\d++")){
            Client client = new Client(Integer.parseInt(args[0].split("=")[1]));
            client.start();
        }
    }
}
