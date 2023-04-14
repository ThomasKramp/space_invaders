package be.uantwerpen.fti.ei.components.Movement;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class PlayerMoveComp extends AMovementComp {
    public PlayerMoveComp(IntPtr x, IntPtr y, int weight, int size) {
        super(x, y, weight, size);
    }
}
