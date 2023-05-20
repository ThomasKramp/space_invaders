package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.images.J2DIFactory;
import be.uantwerpen.fti.ei.J2D.pixels.J2DPFactory;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.config.ConfigReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Space invaders\n");

        ConfigReader config = new ConfigReader();
        config.getScreenSettings("src/be/uantwerpen/fti/ei/config/config.txt");

        boolean useImages = true;
        IFactory fact = useImages ? new J2DIFactory() : new J2DPFactory();
        Game game = new Game(fact, config.getScreenDimen(), config.getPlayerConfig(), config.getLevels(), config.getMusic());
        game.Start();
    }
}