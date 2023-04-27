package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.components.MovementComp;

import java.util.List;

public interface ICollisionDetector {
    public boolean checkVerticalWallCollisions(MovementComp comp);

    public boolean checkHorizontalWallCollisions(MovementComp comp);

    public MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities);
}
