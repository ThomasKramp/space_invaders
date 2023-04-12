package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class Entity {
    EntityType type;
    public MovementComp movementComp;
    public AVisualComp visualComp;

    public Entity(EntityType type, MovementComp movementComp, AVisualComp visualComp) {
        this.type = type;
        this.movementComp = movementComp;
        this.visualComp = visualComp;
    }

    public MovementComp getMovementComp() {
        return movementComp;
    }

    public AVisualComp getVisualComp() {
        return visualComp;
    }
}
