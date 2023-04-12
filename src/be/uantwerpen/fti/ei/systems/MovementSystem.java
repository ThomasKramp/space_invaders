package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.IntPtr;
import be.uantwerpen.fti.ei.components.AMovementComp;

import java.util.List;

public class MovementSystem {
    public List<AMovementComp> update(List<AMovementComp> components) {
        for (AMovementComp component: components) {
            // Add the velocity to the current position to calculate the new position
            System.out.println("X: " + component.getX().getValue() + ", Y: " + component.getY().getValue());
            component.getX().add(component.getVx());
            component.getY().add(component.getVy());
            component.setVx(0); component.setVy(0);
        }
        return components;
    }
}
