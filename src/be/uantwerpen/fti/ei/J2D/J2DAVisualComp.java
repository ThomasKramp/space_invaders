package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

public abstract class J2DAVisualComp extends AVisualComp {
    int scale;
    PTR<Boolean> isHit, isBigHit; // this is used for visual purposes

    public J2DAVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size);
        this.scale = scale;
        this.isHit = isHit;
        this.isBigHit = isBigHit;
    }

    public int getScale() { return scale; }
    public boolean isHit() { return isHit.getValue(); }
    public boolean isBigHit() { return isBigHit.getValue(); }
}
