package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

/**
 * Base Java-2D visualiser component class that must be inherited by other Java-2D visualiser component classes<br>
 * A class to store data containing to the Java-2D visualisation of an entity
 */
public abstract class J2DAVisualComp extends AVisualComp {
    final int scale;
    /**
     * Represents the hit statuses.
     * @see     PTR
     */
    final PTR<Boolean> isHit, isBigHit; // this is used for visual purposes

    /**
     * Class constructor specifying the scale and the pointers to the hit statuses on top of the base variables of the entity that will be visualised.
     * @param   scale an integer representing the scaling factor of the base game compared to the Java-2d visualisation
     * @param   isHit a boolean pointer representing the hit status of a normal hit on the entity
     * @param   isBigHit a boolean pointer representing the hit status of a bigger hit on the entity
     * @see     AVisualComp
     * @see     PTR
     */
    public J2DAVisualComp(PTR<Integer> x, PTR<Integer> y, int width, int scale, PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, width);
        this.scale = scale;
        this.isHit = isHit;
        this.isBigHit = isBigHit;
    }

    /**
     * Returns the scale of the entity that will be visualised.
     * @return  an integer representing the scale of the entity
     */
    public int getScale() { return scale; }
    /**
     * Returns the hit status of the entity containing to a normal hit.
     * @return  a boolean representing the hit status of a normal hit on the entity
     */
    public boolean isHit() { return isHit.getValue(); }
    /**
     * Returns the hit status of the entity containing to a bigger hit.
     * @return  a boolean representing the hit status of a bigger hit on the entity
     */
    public boolean isBigHit() { return isBigHit.getValue(); }
}
