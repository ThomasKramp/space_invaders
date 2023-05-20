package be.uantwerpen.fti.ei.entities;

import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;

/**
 * Class record specifying the components of the entity that contain its data.
 * @param movementComp  Component containing the movement and collisions data of the entity.
 * @param lifeComp      Component containing the hit status and life data of the entity.
 * @param visualComp    Component containing the visualisation data of the entity.
 */
public record Entity(MovementComp movementComp, LifeComp lifeComp, AVisualComp visualComp) {
    /** Class to generically describe any entity */
    public Entity { }

    /**
     * Returns the movement component of the entity.
     * @return  a MovementComp containing the movement and collisions data of the entity
     */
    @Override
    public MovementComp movementComp() { return movementComp; }
    /**
     * Returns the life component of the entity.
     * @return  a LifeComp containing the hit status and life data of the entity
     */
    @Override
    public LifeComp lifeComp() { return lifeComp; }
    /**
     * Returns the visualisation component of the entity.
     * @return  an AVisualComp containing the visualisation data of the entity
     */
    @Override
    public AVisualComp visualComp() { return visualComp; }
}
