package be.uantwerpen.fti.ei.J2D.data_oriented;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class J2DVisualComp extends AVisualComp {
    GraphicsContext grCtx;
    PTR<Integer> scale;
    private int[] rgb = new int[3];
    PTR<Boolean> isHit; // this is used for visual purposes

    public J2DVisualComp(PTR<Integer> x, PTR<Integer> y, int size, PTR<Integer> scale, int[] rgb,
                         PTR<Boolean> isHit, GraphicsContext grCtx) {
        super(x, y, size);
        setScale(scale);
        setRGB(rgb);
        setHit(isHit);
        setGrCtx(grCtx);
    }

    private void setGrCtx(GraphicsContext grCtx) { this.grCtx = grCtx; }

    private void setScale(PTR<Integer> scale) { this.scale = scale; }
    public int getScale() { return scale.getValue(); }

    private void setRGB(int[] rgb) { this.rgb = rgb; }
    public int getR() { return rgb[0]; }
    public int getG() { return rgb[1]; }
    public int getB() { return rgb[2]; }

    private void setHit(PTR<Boolean> isHit) { this.isHit = isHit; }
    public boolean isHit() { return isHit.getValue(); }
}
