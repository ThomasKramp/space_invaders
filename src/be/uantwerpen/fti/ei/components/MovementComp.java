package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.EntityType;

public class MovementComp {
    protected PTR<Integer> x, y, vx, vy;
    int size;
    EntityType type;

    public MovementComp(PTR<Integer> x, PTR<Integer> y, PTR<Integer> Vx, PTR<Integer> Vy, int size, EntityType type) {
        setX(x);    setY(y);
        setVx(Vx);  setVy(Vy);
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

    public PTR<Integer> getVxPtr() { return vx; }
    public int getVx() { return vx.getValue(); }
    public void setVx(PTR<Integer> vx) { this.vx = vx; }
    public void setVx(int vx) { this.vx.setValue(vx); }

    public PTR<Integer> getVyPtr() { return vy; }
    public int getVy() { return vy.getValue(); }
    public void setVy(PTR<Integer> vy) { this.vy = vy; }
    public void setVy(int vy) { this.vy.setValue(vy); }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
