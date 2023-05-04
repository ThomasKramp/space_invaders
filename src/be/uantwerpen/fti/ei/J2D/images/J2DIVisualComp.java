package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.J2DGraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

import java.awt.image.BufferedImage;

public class J2DIVisualComp extends AVisualComp {
    J2DGraphicsContext grCtx;
    int scale;
    private BufferedImage sprite;
    private BufferedImage spriteHit;
    PTR<Boolean> isHit, isBigHit; // this is used for visual purposes

    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit, J2DGraphicsContext grCtx) {
        super(x, y, size);
        setScale(scale);
        setSprite(sprite);
        setHit(isHit); setBigHit(isBigHit);
        setGrCtx(grCtx);
    }
    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite, BufferedImage spriteHit,
                           PTR<Boolean> isHit, PTR<Boolean> isBigHit, J2DGraphicsContext grCtx) {
        super(x, y, size);
        setScale(scale);
        setSprite(sprite); setSpriteHit(spriteHit);
        setHit(isHit); setBigHit(isBigHit);
        setGrCtx(grCtx);
    }

    private void setGrCtx(J2DGraphicsContext grCtx) { this.grCtx = grCtx; }

    private void setScale(int scale) { this.scale = scale; }
    public int getScale() { return scale; }

    public BufferedImage getSprite() { return sprite; }
    public void setSprite(BufferedImage sprite) { this.sprite = sprite; }
    public BufferedImage getSpriteHit() { return spriteHit; }
    public void setSpriteHit(BufferedImage spriteHit) { this.spriteHit = spriteHit; }

    private void setHit(PTR<Boolean> isHit) { this.isHit = isHit; }
    public boolean isHit() { return isHit.getValue(); }
    private void setBigHit(PTR<Boolean> isBigHit) { this.isBigHit = isBigHit; }
    public boolean isBigHit() { return isBigHit.getValue(); }
}
