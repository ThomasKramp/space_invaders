package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.EntityType;

public class MovementComp {
    protected PTR<Integer> x, y;
    int vx = 0, vy = 0, size;
    EntityType type;

    public MovementComp(PTR<Integer> x, PTR<Integer> y, int size, EntityType type) {
        setX(x);    setY(y);
        setSize(size);
        setType(type);
    }

    public PTR<Integer> getXPtr() { return x; }
    public int getX() { return x.getValue(); }
    public void setX(PTR<Integer> x) {
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
    }
    public void setX(int x) { this.x.setValue(Math.max(x, 0)); }

    public PTR<Integer> getYPtr() { return y; }
    public int getY() { return y.getValue(); }
    public void setY(PTR<Integer> y) {
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0);
    }
    public void setY(int y) { this.x.setValue(Math.max(y, 0)); }

    public int getVx() { return vx; }
    public void setVx(int vx) { this.vx = vx; }

    public int getVy() { return vy; }
    public void setVy(int vy) { this.vy = vy; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
