package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.components.MovementComp;

import java.util.List;

public interface ICollisionDetector {
    boolean checkVerticalWallCollisions(MovementComp comp);

    boolean checkHorizontalWallCollisions(MovementComp comp);

    MovementComp checkCollisions(MovementComp comp, List<MovementComp> entities);
}
