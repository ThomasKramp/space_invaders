package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;

public class AVisualComp {
    PTR<Integer> x, y;
    int size;

    public AVisualComp(PTR<Integer> x, PTR<Integer> y, int size) {
        // Set horizontal size
        setSize(size);
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

    public void setY(int y) { this.x.setValue(Math.max(y, 0)); }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = Math.max(size, 0); }
}
