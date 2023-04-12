package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.AMovementComp;

import java.util.List;

public class MovementSystem {
    List<AMovementComp> update(List<AMovementComp> components) {
        for (AMovementComp component: components) {
            // Add the velocity to the current position to calculate the new position
            component.setX(component.getX() + component.getVx());
            component.setY(component.getY() + component.getVy());
        }
        return components;
    }
}
