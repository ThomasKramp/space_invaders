package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.images.J2DIFactory;
import be.uantwerpen.fti.ei.J2D.pixels.J2DPFactory;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.config.ConfigReader;

/** Main class in which the configuration will be loaded and the game will be initialised */
public class Main {

    /**
     * Main method in which the configuration will be loaded and the game will be initialised
     * @param args an array of strings representing command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Space invaders\n");

        ConfigReader config = new ConfigReader();
        config.readSettings("src/be/uantwerpen/fti/ei/config/config.txt");

        boolean useImages = true;
        IFactory fact = useImages ? new J2DIFactory() : new J2DPFactory();
        Game game = new Game(fact, config.getScreenDimen(), config.getPlayerConfig(), config.getLevels(), config.getMusic());
        game.Start();
    }
}