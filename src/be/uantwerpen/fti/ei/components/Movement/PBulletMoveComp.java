package be.uantwerpen.fti.ei.components.Movement;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class PBulletMoveComp extends MovementComp {
    public PBulletMoveComp(IntPtr x, IntPtr y, IntPtr Vx, IntPtr Vy) {
        super(x, y, Vx, Vy);
    }
}
