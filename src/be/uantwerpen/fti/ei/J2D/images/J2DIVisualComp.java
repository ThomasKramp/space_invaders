package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.J2DAVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

import java.awt.image.BufferedImage;

/**
 * A class to store data containing to the Java-2D visualisation of an entity using images
 * @see J2DAVisualComp
 */
public class J2DIVisualComp extends J2DAVisualComp {
    private final BufferedImage sprite;
    private BufferedImage spriteHit;

    /**
     * Class constructor specifying the image on top of the base variables of the entity that will be visualised.
     * @param   sprite a buffered image representing the image for the Java-2d visualisation
     * @see     J2DAVisualComp
     * @see     PTR
     */
    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size, scale, isHit, isBigHit);
        this.sprite = sprite;
    }
    /**
     * Class constructor specifying the image and image while hit on top of the base variables of the entity that will be visualised.
     * @param   sprite a buffered image representing the image for the Java-2d visualisation
     * @param   spriteHit a buffered image representing the image while hit for the Java-2d visualisation
     * @see     J2DAVisualComp
     * @see     PTR
     */
    public J2DIVisualComp(PTR<Integer> x, PTR<Integer> y, int size, int scale, BufferedImage sprite, BufferedImage spriteHit,
                           PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, size, scale, isHit, isBigHit);
        this.sprite = sprite;
        this.spriteHit = spriteHit;
    }


    /**
     * Returns the image of the entity that will be visualised.
     * @return  a buffered image representing the image of the entity
     */
    public BufferedImage getSprite() { return sprite; }

    /**
     * Returns the image of the entity while hit that will be visualised.
     * @return  a buffered image representing the image of the entity while hit
     */
    public BufferedImage getSpriteHit() { return spriteHit; }
}
