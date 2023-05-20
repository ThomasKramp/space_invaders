package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.J2D.J2DAVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

public class J2DPVisualComp extends J2DAVisualComp {
    private int[] rgb = new int[3];

    public J2DPVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, int[] rgb,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size, scale, isHit, isBigHit);
        setRGB(rgb);
    }

    private void setRGB(int[] rgb) { this.rgb = rgb; }
    public int getR() { return rgb[0]; }
    public int getG() { return rgb[1]; }
    public int getB() { return rgb[2]; }
}
