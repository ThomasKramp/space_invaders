package be.uantwerpen.fti.ei.utilities;

import be.uantwerpen.fti.ei.components.MovementComp;

import java.util.List;

public class CollisionDetector {
    int width, height;
    public CollisionDetector(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void checkWalls(MovementComp comp) {
        // Check if x is within borders
        if (comp.getXVal() + comp.getVx() <= 0) {
            comp.setVx(-comp.getXVal());
        } else if (comp.getXVal() + comp.getSize() + comp.getVx() >= width) {
            comp.setVx(width - (comp.getXVal() + comp.getSize()));
        }

        // Check if y is within borders
        if (comp.getYVal() + comp.getVy() <= 0) {
            comp.setVy(-comp.getYVal());
        } else if (comp.getYVal() + comp.getSize() + comp.getVy() >= height) {
            comp.setVy(height - (comp.getYVal() + comp.getSize()));
        }
    }

    public void checkEntities(MovementComp comp, List<MovementComp> entities) {
        int leftDiff, rightDiff, upperDiff, downDiff;
        boolean b1, b2, b3, b4;
        // System.out.println("X: ");
        for (MovementComp entity: entities) {
            if (comp != entity) {
                leftDiff = entity.getXVal() - (comp.getXVal() + comp.getSize());
                rightDiff = comp.getXVal() - (entity.getXVal() + entity.getSize());
                //System.out.print("Left: " + leftDiff + ", Right: " + rightDiff + "\t");

                upperDiff = entity.getYVal() - (comp.getYVal() + comp.getSize());
                downDiff = comp.getYVal() - (entity.getYVal() + entity.getSize());
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
                    b1 = entity.getXVal() < comp.getXVal() && comp.getXVal() < entity.getXVal() + entity.getSize();
                    b2 = entity.getXVal() < comp.getXVal() + comp.getSize() && comp.getXVal() + comp.getSize() < entity.getXVal() + entity.getSize();
                    b3 = comp.getXVal() < entity.getXVal() && entity.getXVal() < comp.getXVal() + comp.getSize();
                    b4 = comp.getXVal() < entity.getXVal() + entity.getSize() && entity.getXVal() + entity.getSize() < comp.getXVal() + comp.getSize();
                    if (b1 || b2 || b3 || b4) {
                        System.out.println("Vertical True");
                        // Upper collision
                        if ((upperDiff >= 0) && !(downDiff >= 0))
                            if (Math.abs(upperDiff) < Math.abs(comp.getMovement()))
                                if (comp.getVy() > 0) comp.setVy(upperDiff);
                        // Down collision
                        if (!(upperDiff >= 0) && (downDiff >= 0))
                            if (Math.abs(downDiff) < Math.abs(comp.getMovement()))
                                if (comp.getVy() < 0) comp.setVy(-downDiff);
                    }
                } else if (comp.getVx() != 0) {
                    b1 = entity.getYVal() < comp.getYVal() && comp.getYVal() < entity.getYVal() + entity.getSize();
                    b2 = entity.getYVal() < comp.getYVal() + comp.getSize() && comp.getYVal() + comp.getSize() < entity.getYVal() + entity.getSize();
                    b3 = comp.getYVal() <= entity.getYVal() && entity.getYVal() <= comp.getYVal() + comp.getSize();
                    b4 = comp.getYVal() <= entity.getYVal() + entity.getSize() && entity.getYVal() + entity.getSize() <= comp.getYVal() + comp.getSize();
                    if (b1 || b2 || b3 || b4) {
                        System.out.println("Horizontal True");
                        // Left collision
                        if ((leftDiff >= 0) && !(rightDiff >= 0))
                            if (Math.abs(leftDiff) < Math.abs(comp.getMovement()))
                                if (comp.getVx() > 0) comp.setVx(leftDiff);
                        // Right collision
                        if (!(leftDiff >= 0) && (rightDiff >= 0))
                            if (Math.abs(rightDiff) < Math.abs(comp.getMovement()))
                                if (comp.getVx() < 0) comp.setVx(-rightDiff);
                    }
                }

                // Only change the movement if it is smaller and not 0
                // if (Math.abs(movementX) < Math.abs(comp.getMovement()) && movementX != 0) comp.setMovement(movementX);
                //if (collides(comp, entity)) {  comp.setMovement(0); }
            }
        }
    }
}
