package be.uantwerpen.fti.ei.components;

public class AVisualComp {
    int x, y, size;

    public AVisualComp(int x, int y, int size) {
        if (x >= 0) this.x = size;
        else this.x = 0;
        if (y >= 0) this.y = size;
        else this.y = 0;
        if (size >= 0) this.size = size;
        else this.size = 0;
    }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }
}
