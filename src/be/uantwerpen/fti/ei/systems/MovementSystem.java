package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.MovementComp;

import java.util.List;

public class MovementSystem {

    public void update(List<MovementComp> components) {
        for (MovementComp component: components) {
            // Add the velocity to the current position to calculate the new position
            // System.out.println("X: " + component.getX() + ", Y: " + component.getY());
            component.setX(component.getX() + component.getVx());
            component.setY(component.getY() + component.getVy());
            component.setVx(0); component.setVy(0);
        }
    }
}
