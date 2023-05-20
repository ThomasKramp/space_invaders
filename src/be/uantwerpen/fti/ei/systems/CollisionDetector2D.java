package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;

import java.util.List;

/**
 * Collision detector class that works with 2 extra dimension (component width and height) on top of the x-& y coordinates
 * @see    ICollisionDetector
 */
public class CollisionDetector2D implements ICollisionDetector {

    final int width, height;
    /**
     * Class constructor specifying the width and height of the screen.
     * @param   width an integer representing the width of the screen
     * @param   height an integer representing the height of the screen
     */
    public CollisionDetector2D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean checkVerticalWallCollisions(MovementComp comp) {
        // Check if x is within borders
        if (comp.getX() + comp.getVx() <= 0) {
            comp.setVx(-comp.getX());
            return true;
        } else if (comp.getX() + comp.getWidth() + comp.getVx() >= width) {
            comp.setVx(width - (comp.getX() + comp.getWidth()));
            return true;
        }
        return false;
    }

    @Override
    public boolean checkHorizontalWallCollisions(MovementComp comp) {
        // Check if y is within borders
        if (comp.getY() + comp.getVy() <= 0) {
            comp.setVy(-comp.getY());
            return true;
        } else if (comp.getY() + comp.getWidth() + comp.getVy() >= height) {
            comp.setVy(height - (comp.getY() + comp.getWidth()));
            return true;
        }

        // Check if top or bottom of screen is hit
        return comp.getY() == 0 || comp.getY() + comp.getVy() == height;
    }

    public MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities) {
        int leftDiff, rightDiff, upperDiff, downDiff;
        boolean b1, b2, b3, b4, b5;

        // System.out.println("X: ");
        for (MovementComp entity: entities) {
            if (comp != entity) {
                leftDiff = entity.getX() - (comp.getX() + comp.getWidth());
                rightDiff = comp.getX() - (entity.getX() + entity.getWidth());
                //System.out.print("Left: " + leftDiff + ", Right: " + rightDiff + "\t");

                upperDiff = entity.getY() - (comp.getY() + comp.getWidth());
                downDiff = comp.getY() - (entity.getY() + entity.getWidth());
                //System.out.println("Upper: " + upperDiff + ", Down: " + downDiff);

                // Horizontal align
                //if (upperDiff >= 0) System.out.print("UP \t");
                //if (downDiff >= 0) System.out.print("DOWN \t");
                //if (upperDiff >= 0 || downDiff >= 0) System.out.println("Left: " + leftDiff + ", Right: " + rightDiff);

                // Vertical align
                //if (leftDiff >= 0) System.out.print("LEFT \t");
                //if (rightDiff >= 0) System.out.print("RIGHT \t");
                //if (leftDiff >= 0 || rightDiff >= 0) System.out.println("Upper: " + upperDiff + ", Down: " + downDiff);

                if (comp.getVy() != 0) {
                    b1 = entity.getX() < comp.getX() && comp.getX() < entity.getX() + entity.getWidth();
                    b2 = entity.getX() < comp.getX() + comp.getWidth() && comp.getX() + comp.getWidth() < entity.getX() + entity.getWidth();
                    b3 = comp.getX() < entity.getX() && entity.getX() < comp.getX() + comp.getWidth();
                    b4 = comp.getX() < entity.getX() + entity.getWidth() && entity.getX() + entity.getWidth() < comp.getX() + comp.getWidth();
                    b5 = entity.getX() == comp.getX() && entity.getX() + entity.getWidth() == comp.getX() + comp.getWidth();
                    if (b1 || b2 || b3 || b4 || b5) {
                        // System.out.println("Vertical True");
                        // Upper collision
                        if ((upperDiff >= 0) && !(downDiff >= 0))
                            if (Math.abs(upperDiff) < Math.abs(1))  // if remaining space < movement
                                if (comp.getVy() > 0) {             // set remaining space as speed
                                    comp.setVy(upperDiff);          // else change speed
                                    return entity;
                                }
                        // Down collision
                        if (!(upperDiff >= 0) && (downDiff >= 0))
                            if (Math.abs(downDiff) < Math.abs(1))
                                if (comp.getVy() < 0) {
                                    comp.setVy(-downDiff);
                                    return entity;
                                }
                    }
                } else if (comp.getVx() != 0) {
                    b1 = entity.getY() < comp.getY() && comp.getY() < entity.getY() + entity.getWidth();
                    b2 = entity.getY() < comp.getY() + comp.getWidth() && comp.getY() + comp.getWidth() < entity.getY() + entity.getWidth();
                    b3 = comp.getY() < entity.getY() && entity.getY() < comp.getY() + comp.getWidth();
                    b4 = comp.getY() < entity.getY() + entity.getWidth() && entity.getY() + entity.getWidth() < comp.getY() + comp.getWidth();
                    b5 = entity.getY() == comp.getY() && entity.getY() + entity.getWidth() == comp.getY() + comp.getWidth();
                    if (b1 || b2 || b3 || b4 || b5) {
                        // System.out.println("Horizontal True");
                        // Left collision
                        if ((leftDiff >= 0) && !(rightDiff >= 0))
                            if (Math.abs(leftDiff) < Math.abs(1))
                                if (comp.getVx() > 0) {
                                    comp.setVx(leftDiff);
                                    return entity;
                                }
                        // Right collision
                        if (!(leftDiff >= 0) && (rightDiff >= 0))
                            if (Math.abs(rightDiff) < Math.abs(1))
                                if (comp.getVx() < 0) {
                                    comp.setVx(-rightDiff);
                                    return entity;
                                }
                    }
                }
            }
        }
        return null;
    }
}
