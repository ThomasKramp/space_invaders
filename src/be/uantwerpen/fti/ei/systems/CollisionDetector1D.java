package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;

import java.util.List;

/** Collision detector class that works with 1 extra dimension (component width) on top of the x-& y coordinates */
public class CollisionDetector1D implements ICollisionDetector {

    final int width, height;
    /**
     * Class constructor specifying the width and height of the screen.
     * @param   width an integer representing the width of the screen
     * @param   height an integer representing the height of the screen
     */
    public CollisionDetector1D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean checkVerticalWallCollisions(MovementComp comp) {
        return comp.getX() + comp.getVx() < 0 // Left wall hit
            || comp.getX() + comp.getWidth() + comp.getVx() > width; // Right wall hit
    }

    public boolean checkHorizontalWallCollisions(MovementComp comp) {
        return comp.getY() + comp.getVy() < 0 // Top wall hit
            || comp.getY() + 1 + comp.getVy() > height; // Bottom wall hit
    }

    public MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities) {
        for (MovementComp entity: entities) {
            if (comp != entity) {
                for (int c = 0; c < comp.getWidth(); c++)
                    for (int e = 0; e < entity.getWidth(); e++)
                        if (comp.getX() + c + comp.getVx() == entity.getX() + e
                        &&  comp.getY() + comp.getVy() == entity.getY())
                            return entity;
            }
        }
        return null;
    }
}
