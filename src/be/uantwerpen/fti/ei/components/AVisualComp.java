package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.IntPtr;

public class AVisualComp {
    IntPtr x, y;
    int size;

    public AVisualComp(IntPtr x, IntPtr y, int size) {
        setX(x); setY(y);
        setSize(size);
    }

    public int getX() { return x.getValue(); }

    public void setX(IntPtr x) {
        if (x.getValue() >= 0) this.x = x;
        else this.x.setValue(0);
    }

    public void setX(int x) {
        this.x.setValue(Math.max(x, 0));
    }

    public int getY() { return y.getValue(); }

    public void setY(IntPtr y) {
        if (y.getValue() >= 0) this.y = y;
        else this.y.setValue(0); }

    public void setY(int y) { this.x.setValue(Math.max(y, 0)); }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = Math.max(size, 0); }
}
