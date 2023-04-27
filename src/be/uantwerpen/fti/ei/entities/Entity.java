package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class Entity {
    MovementComp movementComp;
    LifeComp lifeComp;
    AVisualComp visualComp;

    public Entity(MovementComp movementComp, LifeComp lifeComp, AVisualComp visualComp) {
        this.movementComp = movementComp;
        this.lifeComp = lifeComp;
        this.visualComp = visualComp;
    }

    public MovementComp getMovementComp() { return movementComp; }
    public LifeComp getLifeComp() { return lifeComp; }
    public AVisualComp getVisualComp() { return visualComp; }
}
