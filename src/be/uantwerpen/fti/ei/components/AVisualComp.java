package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;

/**
 * Base visualiser component class that must be inherited by other visualiser component classes<br>
 * A class to store data containing to the visualisation of an entity
 */
public abstract class AVisualComp {
    /**
     * Represents the entity's coordinates.
     * @see     PTR
     */
    final PTR<Integer> x, y;
    final int width;

    /**
     * Class constructor specifying the pointers to the coordinates and the width of the entity that will be visualised.
     * @param   x an integer pointer representing the x-coordinate of the entity
     * @param   y an integer pointer representing the y-coordinate of the entity
     * @param   width an integer representing the width of the entity
     * @see     PTR
     */
    public AVisualComp( PTR<Integer> x, PTR<Integer> y, int width) {
        // Set width
        this.width = Math.max(width, 0);
        // Set X
        this.x = x;
        if (x.getValue() <= 0) this.x.setValue(0);
        // Set Y
        this.y = y;
        if (y.getValue() <= 0) this.y.setValue(0);
    }

    /**
     * Returns the x-coordinate of the entity that will be visualised.
     * @return  an integer representing the x-coordinate of the entity
     */
    public int getX() { return x.getValue(); }

    /**
     * Returns the y-coordinate of the entity that will be visualised.
     * @return  an integer representing the y-coordinate of the entity
     */
    public int getY() { return y.getValue(); }

    /**
     * Returns the width of the entity that will be visualised.
     * @return  an integer representing the x-coordinate of the entity
     */
    public int getWidth() { return width; }
}
