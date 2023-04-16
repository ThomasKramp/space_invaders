package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class SmartMoveComp extends MovementComp {
    int counter;
    int direction;
    public SmartMoveComp(IntPtr x, IntPtr y, IntPtr Vx, IntPtr Vy) {
        super(x, y, Vx, Vy);
        setCounter(0);
        setDirection(1);
    }

    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
