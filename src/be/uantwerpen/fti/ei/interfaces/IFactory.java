package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

/**
 * Base factory interface that must be inherited by other factory classes<br>
 * An interface to define all base entity creation and package related system methods
 */
public interface IFactory {

    /**
     * Method to define the base screen dimensions
     * @param   screenDimen an integer array of size 2 containing width and height (in that order)
     */
    void setScreenDimensions(int[] screenDimen);

    /*--------------------------------------------------------------------------------------------------------*/
    // Player
    /**
     * Method to create a player entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @param   lives an integer representing the remaining lives of the entity
     * @param   width an integer representing the width of the entity
     * @return  an entity representing the player character
     * @see     Entity
     */
    Entity getPlayer(int x, int y, int lives, int width);
    /**
     * Method to create a player bullet entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a player character bullet projectile
     * @see     Entity
     */
    Entity getPBullet(int x, int y);
    /**
     * Method to create a player rocket entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a player character rocket projectile
     * @see     Entity
     */
    Entity getPRocket(int x, int y);
    /**
     * Method to create a wall entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @param   lives an integer representing the remaining lives of the entity
     * @param   width an integer representing the width of the entity
     * @return  an entity representing wall that can protect the player from enemy projectiles
     * @see     Entity
     */
    Entity getWall(int x, int y, int lives, int width);

    /*--------------------------------------------------------------------------------------------------------*/
    // Enemies
    /**
     * Method to create an enemy entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @param   lives an integer representing the remaining lives of the entity
     * @param   width an integer representing the width of the entity
     * @return  an entity representing the enemy character
     * @see     Entity
     */
    Entity getEnemy(int x, int y, int lives, int width);
    /**
     * Method to create an enemy bullet entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing an enemy character bullet projectile
     * @see     Entity
     */
    Entity getEBullet(int x, int y);
    /**
     * Method to create a boss enemy entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @param   lives an integer representing the remaining lives of the entity
     * @param   width an integer representing the width of the entity
     * @return  an entity representing a boss enemy character
     * @see     Entity
     */
    Entity getBoss(int x, int y, int lives, int width);
    /**
     * Method to create a boss enemy rocket entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a boss enemy character rocket projectile
     * @see     Entity
     */
    Entity getBRocket(int x, int y);

    /*--------------------------------------------------------------------------------------------------------*/
    // Bonus
    /**
     * Method to create a bonus entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a bonus for extra lives
     * @see     Entity
     */
    Entity getBonusLives(int x, int y);
    /**
     * Method to create a bonus entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a bonus for a temporary boost when scoring points
     * @see     Entity
     */
    Entity getBonusScore(int x, int y);
    /**
     * Method to create a bonus entity
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @return  an entity representing a bonus for a temporary use of rockets to the player character
     * @see     Entity
     */
    Entity getBonusRocket(int x, int y);

    /*--------------------------------------------------------------------------------------------------------*/
    // System
    /**
     * Method to create a collision detection system
     * @param   width an integer representing the width of the screen
     * @param   height an integer representing the height of the screen
     * @return  an ICollisionDetector to detect the collisions between entities
     * @see     ICollisionDetector
     */
    ICollisionDetector getCollisionDetector(int width, int height);
    /**
     * Method to create a visualisation system
     * @return  an IVisualiseSystem to visualise the entities
     * @see     IVisualiseSystem
     */
    IVisualiseSystem getVisualiseSystem();
    /**
     * Method to create an input system
     * @return  an AInput that will store and return the (keyboard) inputs
     * @see     AInput
     */
    AInput getInput();
}
