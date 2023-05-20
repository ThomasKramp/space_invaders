package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.enums.EntityType;

/**
 * Child class of the movement component<br>
 * A class implemented for entities that need to move on their own (without input).
 * @see MovementComp
 */
public class SmartMoveComp extends MovementComp {
    /** Counter to edit the entity behaviour after certain intervals. */
    byte counter;
    /** Direction to change the enemy's movement. */
    int direction;

    /**
     * Class constructor specifying the pointers to the coordinates, the width and the type of the entity that will be visualised.
     * @param   x an integer pointer representing the x-coordinate of the entity
     * @param   y an integer pointer representing the y-coordinate of the entity
     * @param   width an integer representing the width of the entity
     * @param   type an enum value (number) representing the type of the entity
     * @see     PTR
     */
    public SmartMoveComp(PTR<Integer> x, PTR<Integer> y, int width, EntityType type) {
        super(x, y, width, type);
        setCounter((byte) 0);
        setDirection(1);
    }

    /**
     * Returns the counter of the smart entity.
     * @return  an integer representing a counter of the entity
     */
    public byte getCounter() { return counter; }
    /**
     * Sets the counter of the smart entity.
     * @param   counter an integer representing a counter
     */
    public void setCounter(byte counter) { this.counter = counter; }

    /**
     * Returns the direction of the smart entity.
     * @return  an integer representing a counter of the smart entity
     */
    public int getDirection() { return direction; }
    /**
     * Sets the direction of the smart entity.
     * @param   direction an integer representing a direction (left = -1 & right = 1)
     */
    public void setDirection(int direction) { this.direction = direction; }
}
