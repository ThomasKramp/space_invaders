package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class SmartMoveComp extends MovementComp {
    byte counter;
    int direction;
    public SmartMoveComp(IntPtr x, IntPtr y, IntPtr Vx, IntPtr Vy, int size) {
        super(x, y, Vx, Vy, size);
        setCounter((byte) 0);
        setDirection(1);
    }

    public byte getCounter() {
        return counter;
    }
    public void setCounter(byte counter) {
        this.counter = counter;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
