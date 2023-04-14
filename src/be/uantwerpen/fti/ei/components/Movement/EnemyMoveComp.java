package be.uantwerpen.fti.ei.components.Movement;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class EnemyMoveComp extends AMovementComp {
    int counter;
    int direction;
    public EnemyMoveComp(IntPtr x, IntPtr y, int weight, int size) {
        super(x, y, weight, size);
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
