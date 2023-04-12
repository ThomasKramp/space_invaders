package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.IntPtr;
import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;

import java.awt.*;

public class J2DVisualComp extends AVisualComp {
    GraphicsContext grCtx;
    int r, g, b;
    public J2DVisualComp(IntPtr x, IntPtr y, int size, int r, int g, int b, GraphicsContext grCtx) {
        super(x, y, size);
        this.r = r;
        this.g = g;
        this.b = b;
        this.grCtx = grCtx;
    }

    public void draw() {
        Graphics2D g2d = grCtx.getG2d();
        g2d.setColor(new Color(r,g,b));
        g2d.fillRect(getX(), getY(), getSize(), getSize());
    }
}
