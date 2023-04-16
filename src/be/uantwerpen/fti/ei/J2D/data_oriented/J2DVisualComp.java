package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.dataStruct.BoolPtr;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;
import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;

import java.awt.*;

public class J2DVisualComp extends AVisualComp {
    GraphicsContext grCtx;
    int r, g, b;
    BoolPtr isHit; // this is used for visual purposes

    public J2DVisualComp(IntPtr x, IntPtr y, int size, int r, int g, int b, BoolPtr isHit, GraphicsContext grCtx) {
        super(x, y, size);
        setR(r); setG(g); setB(b);
        setHit(isHit);
        setGrCtx(grCtx);
    }

    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        if (isHit())    g2d.setColor(new Color(255 - r,255 - g,255 - b)); // invert colors if hit
        else            g2d.setColor(new Color(r,g,b));
        g2d.fillRect(getX(), getY(), getSize(), getSize());
    }

    private void setGrCtx(GraphicsContext grCtx) { this.grCtx = grCtx; }
    private void setR(int r) { this.r = r; }
    private void setG(int g) { this.g = g; }
    private void setB(int b) { this.b = b; }
    private void setHit(BoolPtr isHit) { this.isHit = isHit; }
    private boolean isHit() { return isHit.isValue(); }
}
