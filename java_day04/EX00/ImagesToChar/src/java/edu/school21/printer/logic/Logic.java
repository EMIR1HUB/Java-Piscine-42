package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Logic {
    private final char whiteChar;
    private final char blackChar;
    private final String filePath;

    public Logic(char whiteChar, char blackChar, String filePath) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
        this.filePath = filePath;
    }

    public void printImage() {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            for (int x = 0; x < image.getHeight(); x++) {
                for (int y = 0; y < image.getWidth(); y++) {
                    int color = image.getRGB(y, x);
                    if(color == Color.BLACK.getRGB()){
                        System.out.print(blackChar);
                    }else if(color == Color.WHITE.getRGB()){
                        System.out.print(whiteChar);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
