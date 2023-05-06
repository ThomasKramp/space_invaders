package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.images.J2DIFactory;
import be.uantwerpen.fti.ei.J2D.pixels.J2DPFactory;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.config.ConfigReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Space invaders\n");

        ConfigReader config = new ConfigReader();
        config.getScreenSettings("src/be/uantwerpen/fti/ei/config/config.txt");

        // IFactory fact = new J2DPFactory();
        IFactory fact = new J2DIFactory();
        Game game = new Game(fact, config.getScreenDimen(), config.getPlayerConfig(), config.getLevels(), config.getMusic());
        game.Start();
     }

    /**
     * @author      Specifies author
     * @deprecated  Specifies deprecation
     * @exception   Specifies exception thrown
     * @link        Creates an in line hyperlink
     * @param       Method parameter
     * @return      Return value
     * @see         Creates a hyperlink
     * @since       Specifies version introduced
     * @throws      Same as @exception
     * @version     States current version
     * @see         reference
     */
}