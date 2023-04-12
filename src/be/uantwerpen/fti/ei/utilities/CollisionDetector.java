package be.uantwerpen.fti.ei.utilities;

import be.uantwerpen.fti.ei.components.MovementComp;

public class CollisionDetector {
    int width, height;
    public CollisionDetector(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void checkWalls(MovementComp comp) {
        // Check if x is within borders
        if (comp.getX().getValue() + comp.getVx() <= 0) {
            comp.setVx(-comp.getX().getValue());
        } else if (comp.getX().getValue() + comp.getSize() + comp.getVx() >= width) {
            comp.setVx(width - (comp.getX().getValue() + comp.getSize()));
        }

        // Check if y is within borders
        if (comp.getY().getValue() + comp.getVy() <= 0) {
            comp.setVy(-comp.getY().getValue());
        } else if (comp.getY().getValue() + comp.getSize() + comp.getVy() >= height) {
            comp.setVy(height - (comp.getY().getValue() + comp.getSize()));
        }
    }
}
