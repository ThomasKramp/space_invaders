package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class Entity {
    AMovementComp movementComp;
    AVisualComp visualComp;

    public Entity(AMovementComp movementComp, AVisualComp visualComp) {
        this.movementComp = movementComp;
        this.visualComp = visualComp;
    }

    public AMovementComp getMovementComp() {
        return movementComp;
    }

    public AVisualComp getVisualComp() {
        return visualComp;
    }
}
