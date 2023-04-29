package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.EntityType;

public class SmartMoveComp extends MovementComp {
    byte counter;
    int direction;
    public SmartMoveComp(PTR<Integer> x, PTR<Integer> y, int Vx, int Vy, int size, EntityType type) {
        super(x, y, Vx, Vy, size, type);
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
