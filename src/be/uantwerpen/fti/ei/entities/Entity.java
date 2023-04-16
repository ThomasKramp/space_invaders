package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.ColDetComp;
import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class Entity {
    MovementComp movementComp;
    ColDetComp colDetComp;
    LifeComp lifeComp;
    AVisualComp visualComp;

    public Entity(MovementComp movementComp, ColDetComp colDetComp, LifeComp lifeComp, AVisualComp visualComp) {
        this.movementComp = movementComp;
        this.colDetComp = colDetComp;
        this.lifeComp = lifeComp;
        this.visualComp = visualComp;
    }

    public MovementComp getMovementComp() { return movementComp; }
    public ColDetComp getColDetComp() { return colDetComp; }
    public LifeComp getLifeComp() { return lifeComp; }
    public AVisualComp getVisualComp() { return visualComp; }
}
