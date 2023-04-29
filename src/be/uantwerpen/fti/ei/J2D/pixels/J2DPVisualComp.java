package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.components.AVisualComp;

public class J2DPVisualComp extends AVisualComp {
    J2DPGraphicsContext grCtx;
    int scale;
    private int[] rgb = new int[3];
    PTR<Boolean> isHit, isBigHit; // this is used for visual purposes

    public J2DPVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, int[] rgb,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit, J2DPGraphicsContext grCtx) {
        super(x, y, size);
        setScale(scale);
        setRGB(rgb);
        setHit(isHit); setBigHit(isBigHit);
        setGrCtx(grCtx);
    }

    private void setGrCtx(J2DPGraphicsContext grCtx) { this.grCtx = grCtx; }

    private void setScale(int scale) { this.scale = scale; }
    public int getScale() { return scale; }

    private void setRGB(int[] rgb) { this.rgb = rgb; }
    public int getR() { return rgb[0]; }
    public int getG() { return rgb[1]; }
    public int getB() { return rgb[2]; }

    private void setHit(PTR<Boolean> isHit) { this.isHit = isHit; }
    public boolean isHit() { return isHit.getValue(); }
    private void setBigHit(PTR<Boolean> isBigHit) { this.isBigHit = isBigHit; }
    public boolean isBigHit() { return isBigHit.getValue(); }
}
