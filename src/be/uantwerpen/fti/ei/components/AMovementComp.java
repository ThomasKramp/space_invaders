package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.IntPtr;

public class AMovementComp {
    IntPtr x, y;
    int vx, vy;

    public AMovementComp(IntPtr x, IntPtr y) {
        setX(x);    setY(y);
        setVx(0);   setVy(0);
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
}
