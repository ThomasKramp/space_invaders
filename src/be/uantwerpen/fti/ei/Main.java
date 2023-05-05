package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.J2D.images.J2DIFactory;
import be.uantwerpen.fti.ei.J2D.pixels.J2DPFactory;
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
            config.getScreenSettings(cwd + "/src/be/uantwerpen/fti/ei/config/config.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IFactory fact;
        //fact = new J2DPFactory();
        fact = new J2DIFactory();
        Game game = new Game(fact, config.getScreenDimen(), config.getPlayerConfig(), config.getLevels());
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