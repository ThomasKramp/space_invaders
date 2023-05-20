package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.AVisualComp;

import java.util.List;

/**
 * Base visualisation system interface that must be inherited by other visualisation system classes<br>
 * An interface to define all base visualisation methods
 */
public interface IVisualiseSystem {

    /**
     * Method to visualise the active game screen; containing the entities that must be visualised, the total score and the player lives.
     * @param   components list of all entities their visualisation components
     * @param   score an integer that specifies the total score of the game
     * @param   lives an integer that specifies the players life total
     * @see     AVisualComp
     */
    void visualise(List<AVisualComp> components, int score, int lives);

    /**
     * Method to visualise the intermediate game screen.
     * @param   title a sting that represents the title of the screen
     * @param   lines a list of stings that contain all text lines of the screen
     */
    void visualise(String title, String... lines);

    /** Method to end all visualisation instances. */
    void end();
}
