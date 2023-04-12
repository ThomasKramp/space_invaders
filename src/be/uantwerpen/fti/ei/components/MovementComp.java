package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class MovementComp {
    IntPtr x, y;
    int vx, vy, movement, size;

    public MovementComp(IntPtr x, IntPtr y, int weight, int size) {
        setX(x);    setY(y);
        setVx(0);   setVy(0);
        setMovement(weight);
        setSize(size);
    }

    public IntPtr getX() { return x; }
    public void setX(IntPtr x) {
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
    }
    public void setX(int x) {
        this.x.setValue(Math.max(x, 0));
    }

    public IntPtr getY() { return y; }
    public void setY(IntPtr y) {
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0); }
    public void setY(int y) { this.x.setValue(Math.max(y, 0)); }

    public int getVx() {  return vx; }
    public void setVx(int vx) { this.vx = vx; }

    public int getVy() { return vy; }
    public void setVy(int vy) { this.vy = vy; }

    public int getMovement() {
        return movement;
    }
    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
