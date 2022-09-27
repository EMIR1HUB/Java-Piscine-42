package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

public class Program {
    public static void main(String[] args) {
        if (args.length < 3 || args[0].length() > 1 || args[1].length() > 1) {
            System.err.println("Incorrect usage!\nUsage: program_name <char for white pixel> <char for black pixel> <full path to image>!");
            System.exit(-1);
        }

        char WHITE_PIXEL = args[0].charAt(0);
        char BLACK_PIXEL = args[1].charAt(0);
        new Logic(WHITE_PIXEL, BLACK_PIXEL, args[2]).printImage();
    }
}
