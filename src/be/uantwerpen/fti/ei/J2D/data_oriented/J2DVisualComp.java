package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.dataStruct.BoolPtr;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;
import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;

import java.awt.*;

public class J2DVisualComp extends AVisualComp {
    GraphicsContext grCtx;
    IntPtr scale;
    private int[] rgb = new int[3];
    BoolPtr isHit; // this is used for visual purposes

    public J2DVisualComp(IntPtr x, IntPtr y, int size, IntPtr scale, int[] rgb,
                         BoolPtr isHit, GraphicsContext grCtx) {
        super(x, y, size);
        setScale(scale);
        setRGB(rgb);
        setHit(isHit);
        setGrCtx(grCtx);
    }

    private void setGrCtx(GraphicsContext grCtx) { this.grCtx = grCtx; }

    private void setScale(IntPtr scale) { this.scale = scale; }
    public int getScale() { return scale.getValue(); }

    private void setRGB(int[] rgb) { this.rgb = rgb; }
    public int getR() { return rgb[0]; }
    public int getG() { return rgb[1]; }
    public int getB() { return rgb[2]; }

    private void setHit(BoolPtr isHit) { this.isHit = isHit; }
    public boolean isHit() { return isHit.isValue(); }
}
