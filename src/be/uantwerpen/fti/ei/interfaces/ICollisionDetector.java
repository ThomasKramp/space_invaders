package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.components.MovementComp;

import java.util.List;

/**
 * Base collision detector interface that must be inherited by other collision detector classes<br>
 * An interface to define all base collision methods
 */
public interface ICollisionDetector {

    /**
     * Method to check an entity collision with the left and right walls
     * @param   comp movement component of the to be checked entity
     * @return  a boolean representing the entity's hit status compared to the vertical walls
     * @see     MovementComp
     */
    boolean checkVerticalWallCollisions(MovementComp comp);

    /**
     * Method to check an entity collision with the upper and lower walls
     * @param   comp movement component of the to be checked entity
     * @return  a boolean representing the entity's hit status compared to the horizontal walls
     * @see     MovementComp
     */
    boolean checkHorizontalWallCollisions(MovementComp comp);

    /**
     * Method to check an entity collision with another entity
     * @param   comp movement component of the to be checked entity
     * @param   entities list of all entities their movement components
     * @return  a movement component representing entity that was the comp-entity collided with
     * @see     MovementComp
     */
    MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities);
}
