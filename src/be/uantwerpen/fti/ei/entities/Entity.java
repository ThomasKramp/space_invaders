package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.ColDetComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class Entity {
    MovementComp movementComp;
    ColDetComp colDetComp;
    AVisualComp visualComp;

    public Entity(MovementComp movementComp, ColDetComp colDetComp, AVisualComp visualComp) {
        this.movementComp = movementComp;
        this.colDetComp = colDetComp;
        this.visualComp = visualComp;
    }

    public MovementComp getMovementComp() {
        return movementComp;
    }

    public ColDetComp getColDetComp() {
        return colDetComp;
    }

    public AVisualComp getVisualComp() {
        return visualComp;
    }
}
