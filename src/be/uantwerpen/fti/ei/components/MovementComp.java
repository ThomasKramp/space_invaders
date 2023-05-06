package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.enums.EntityType;

public class MovementComp {
    protected PTR<Integer> x, y;
    int vx = 0, vy = 0, size;
    EntityType type;

    public MovementComp(PTR<Integer> x, PTR<Integer> y, int size, EntityType type) {
        setSize(size);
        setType(type);
        // Set X
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
        // Set Y
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0);
    }

    public int getX() { return x.getValue(); }
    public void setX(int x) { this.x.setValue(Math.max(x, 0)); }

    public int getY() { return y.getValue(); }
    public void setY(int y) { this.y.setValue(Math.max(y, 0)); }

    public int getVx() { return vx; }
    public void setVx(int vx) { this.vx = vx; }

    public int getVy() { return vy; }
    public void setVy(int vy) { this.vy = vy; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
