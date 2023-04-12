package be.uantwerpen.fti.ei.components;

public class AMovementComp {
    float x, y, vx, vy;

    public AMovementComp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }

    public void setX(float x) { this.x = x; }

    public float getY() { return y; }

    public void setY(float y) { this.y = y; }

    public float getVx() {  return vx; }

    public void setVx(float vx) { this.vx = vx; }

    public float getVy() { return vy; }

    public void setVy(float vy) { this.vy = vy; }
}
