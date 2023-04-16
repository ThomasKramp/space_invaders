package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.IntPtr;

public class MovementComp {
    protected IntPtr x, y, vx, vy;

    public MovementComp(IntPtr x, IntPtr y, IntPtr Vx, IntPtr Vy) {
        setX(x);    setY(y);
        setVx(Vx);   setVy(Vy);
    }

    public IntPtr getXPtr() { return x; }
    public int getX() { return x.getValue(); }
    public void setX(IntPtr x) {
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
    }
    public void setX(int x) { this.x.setValue(Math.max(x, 0)); }

    public IntPtr getYPtr() { return y; }
    public int getY() { return y.getValue(); }
    public void setY(IntPtr y) {
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0);
    }
    public void setY(int y) { this.x.setValue(Math.max(y, 0)); }

    public IntPtr getVxPtr() { return vx; }
    public int getVx() { return vx.getValue(); }
    public void setVx(IntPtr vx) { this.vx = vx; }
    public void setVx(int vx) { this.vx.setValue(vx); }

    public IntPtr getVyPtr() { return vy; }
    public int getVy() { return vy.getValue(); }
    public void setVy(IntPtr vy) { this.vy = vy; }
    public void setVy(int vy) { this.vy.setValue(vy); }
}
