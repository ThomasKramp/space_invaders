package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.J2DFactory;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.config.ConfigReader;

import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("Space invaders\n");

        String cwd = Path.of("").toAbsolutePath().toString();

        ConfigReader config = new ConfigReader();
        try {
            config.getScreenSettings(cwd + "\\src\\be\\uantwerpen\\fti\\ei\\config\\config.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IFactory fact = new J2DFactory();
        Game game = new Game(fact, config.getScreenDimen());
        game.Start();
    }
}