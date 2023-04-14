package be.uantwerpen.fti.ei.components.Movement;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class PBulletMoveComp extends AMovementComp {
    public PBulletMoveComp(IntPtr x, IntPtr y, int weight, int size) {
        super(x, y, weight, size);
        setMovement(-4);
    }
}
