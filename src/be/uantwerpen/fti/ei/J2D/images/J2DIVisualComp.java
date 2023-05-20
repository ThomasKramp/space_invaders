package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.J2DAVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

import java.awt.image.BufferedImage;

public class J2DIVisualComp extends J2DAVisualComp {
    private BufferedImage sprite, spriteHit;

    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size, scale, isHit, isBigHit);
        setSprite(sprite);
    }
    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite, BufferedImage spriteHit,
                           PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size, scale, isHit, isBigHit);
        setSprite(sprite); setSpriteHit(spriteHit);
    }

    public BufferedImage getSprite() { return sprite; }
    public void setSprite(BufferedImage sprite) { this.sprite = sprite; }
    public BufferedImage getSpriteHit() { return spriteHit; }
    public void setSpriteHit(BufferedImage spriteHit) { this.spriteHit = spriteHit; }
}
