package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.J2DFactory;
import be.uantwerpen.fti.ei.interfaces.IFactory;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Space invaders\n");

        ConfigReader config = new ConfigReader();
        try {
            config.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IFactory fact = new J2DFactory(config.width, config.height);
        Game game = new Game(fact, config.width, config.height);
        game.Start();
    }
}