package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;

import java.util.List;

public class CollisionDetector1D implements ICollisionDetector {

    int width, height;
    public CollisionDetector1D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean checkVerticalWallCollisions(MovementComp comp) {
        return comp.getX() + comp.getVx() < 0 // Left wall hit
            || comp.getX() + comp.getSize() + comp.getVx() > width; // Right wall hit
    }

    public boolean checkHorizontalWallCollisions(MovementComp comp) {
        return comp.getY() + comp.getVy() < 0 // Top wall hit
            || comp.getY() + 1 + comp.getVy() > height; // Bottom wall hit
    }

    public MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities) {
        for (MovementComp entity: entities) {
            if (comp != entity) {
                for (int i = 0; i < comp.getSize(); i++)
                    if (comp.getX() + i + comp.getVx() == entity.getX()
                    &&  comp.getY() + comp.getVy() == entity.getY())
                        return entity;
            }
        }
        return null;
    }
}
