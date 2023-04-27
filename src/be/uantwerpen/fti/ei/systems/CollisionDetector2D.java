package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;

import java.util.List;

public class CollisionDetector2D implements ICollisionDetector {

    int width, height;
    public CollisionDetector2D(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean checkVerticalWallCollisions(MovementComp comp) {
        // Check if x is within borders
        if (comp.getX() + comp.getVx() <= 0) {
            //comp.setVx(-comp.getX());
            return true;
        } else if (comp.getX() + comp.getSize() + comp.getVx() >= width) {
            //comp.setVx(width - (comp.getX() + comp.getSize()));
            return true;
        }
        return false;
    }

    @Override
    public boolean checkHorizontalWallCollisions(MovementComp comp) {
        // Check if y is within borders
        if (comp.getY() + comp.getVy() <= 0) {
            //comp.setVy(-comp.getY());
            return true;
        } else if (comp.getY() + comp.getSize() + comp.getVy() >= height) {
            //comp.setVy(height - (comp.getY() + comp.getSize()));
            return true;
        }

        // Check if top or bottom of screen is hit
        if (comp.getY() == 0 || comp.getY() + comp.getVy() == height) {
            //comp.setDead(true);
            return true;
        }

        return false;
    }

    public MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities) {
        int leftDiff, rightDiff, upperDiff, downDiff;
        boolean b1, b2, b3, b4, b5;

        // System.out.println("X: ");
        for (MovementComp entity: entities) {
            if (comp != entity) {
                leftDiff = entity.getX() - (comp.getX() + comp.getSize());
                rightDiff = comp.getX() - (entity.getX() + entity.getSize());
                //System.out.print("Left: " + leftDiff + ", Right: " + rightDiff + "\t");

                upperDiff = entity.getY() - (comp.getY() + comp.getSize());
                downDiff = comp.getY() - (entity.getY() + entity.getSize());
                //System.out.println("Upper: " + upperDiff + ", Down: " + downDiff);

                // Horizontal align
                /*if (upperDiff >= 0) System.out.print("UP \t");
                if (downDiff >= 0) System.out.print("DOWN \t");
                if (upperDiff >= 0 || downDiff >= 0) System.out.println("Left: " + leftDiff + ", Right: " + rightDiff);

                // Vertical align
                if (leftDiff >= 0) System.out.print("LEFT \t");
                if (rightDiff >= 0) System.out.print("RIGHT \t");
                //if (leftDiff >= 0 || rightDiff >= 0) System.out.println("Upper: " + upperDiff + ", Down: " + downDiff);*/

                if (comp.getVy() != 0) {
                    b1 = entity.getX() < comp.getX() && comp.getX() < entity.getX() + entity.getSize();
                    b2 = entity.getX() < comp.getX() + comp.getSize() && comp.getX() + comp.getSize() < entity.getX() + entity.getSize();
                    b3 = comp.getX() < entity.getX() && entity.getX() < comp.getX() + comp.getSize();
                    b4 = comp.getX() < entity.getX() + entity.getSize() && entity.getX() + entity.getSize() < comp.getX() + comp.getSize();
                    b5 = entity.getX() == comp.getX() && entity.getX() + entity.getSize() == comp.getX() + comp.getSize();
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
                    b1 = entity.getY() < comp.getY() && comp.getY() < entity.getY() + entity.getSize();
                    b2 = entity.getY() < comp.getY() + comp.getSize() && comp.getY() + comp.getSize() < entity.getY() + entity.getSize();
                    b3 = comp.getY() < entity.getY() && entity.getY() < comp.getY() + comp.getSize();
                    b4 = comp.getY() < entity.getY() + entity.getSize() && entity.getY() + entity.getSize() < comp.getY() + comp.getSize();
                    b5 = entity.getY() == comp.getY() && entity.getY() + entity.getSize() == comp.getY() + comp.getSize();
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
