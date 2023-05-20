package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.enums.EntityType;

/**
 * Component class to store data containing to the moving an entity and colliding with others
 */
public class MovementComp {
    /**
     * Represents the entity's coordinates.
     * @see     PTR
     */
    protected PTR<Integer> x, y;
    int vx = 0, vy = 0;
    final int width;
    /**
     * Represents the entity's type.
     * @see     EntityType
     */
    final EntityType type;

    /**
     * Class constructor specifying the pointers to the coordinates, the width and the type of the entity that will be visualised.
     * @param   x an integer pointer representing the x-coordinate of the entity
     * @param   y an integer pointer representing the y-coordinate of the entity
     * @param   width an integer representing the width of the entity
     * @param   type an enum value (number) representing the type of the entity
     * @see     PTR
     */
    public MovementComp(PTR<Integer> x, PTR<Integer> y, int width, EntityType type) {
        this.width = width;
        this.type = type;
        // Set X
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
        // Set Y
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0);
    }

    /**
     * Returns the x-coordinate of the moving entity.
     * @return  an integer representing the x-coordinate of the entity
     */
    public int getX() { return x.getValue(); }
    /**
     * Sets the x-coordinate of the entity as the value of the x-coordinate pointer.
     * @param   x an integer representing the x-coordinate
     */
    public void setX(int x) { this.x.setValue(Math.max(x, 0)); }

    /**
     * Returns the y-coordinate of the moving entity.
     * @return  an integer representing the y-coordinate of the entity
     */
    public int getY() { return y.getValue(); }
    /**
     * Sets the y-coordinate of the entity as the value of the y-coordinate pointer.
     * @param   y an integer representing the x-coordinate
     */
    public void setY(int y) { this.y.setValue(Math.max(y, 0)); }

    /**
     * Returns the x-speed of the moving entity.
     * @return  an integer representing the x-speed of the entity
     */
    public int getVx() { return vx; }
    /**
     * Sets the x-speed of the entity.
     * @param   vx an integer representing the x-speed
     */
    public void setVx(int vx) { this.vx = vx; }

    /**
     * Returns the y-speed of the moving entity.
     * @return  an integer representing the y-speed of the entity
     */
    public int getVy() { return vy; }
    /**
     * Sets the y-speed of the entity.
     * @param   vy an integer representing the y-speed
     */
    public void setVy(int vy) { this.vy = vy; }

    /**
     * Returns the width of the moving entity.
     * @return  an integer representing the x-coordinate of the entity
     */
    public int getWidth() { return width; }

    /**
     * Returns the type of the entity.
     * @return  an enum value (number) representing the type of the entity
     */
    public EntityType getType() { return type; }
}
