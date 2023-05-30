package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.J2D.J2DAVisualComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;

/**
 * A class to store data containing to the Java-2D visualisation of an entity using pixel coloring
 * @see J2DAVisualComp
 */
public class J2DPVisualComp extends J2DAVisualComp {
    private final int[] rgb;

    /**
     * Class constructor specifying the RGB values of the pixels on top of the base variables of the entity that will be visualised.
     * @param   x an integer pointer representing the x-coordinate of the entity
     * @param   y an integer pointer representing the y-coordinate of the entity
     * @param   width an integer representing the width of the entity
     * @param   scale an integer representing the scaling factor of the base game compared to the Java-2d visualisation
     * @param   isHit a boolean pointer representing the hit status of a normal hit on the entity
     * @param   isBigHit a boolean pointer representing the hit status of a bigger hit on the entity
     * @param   rgb an array of integers representing the coloring for the Java-2d visualisation
     * @see     J2DAVisualComp
     * @see     PTR
     */
    public J2DPVisualComp(PTR<Integer> x, PTR<Integer> y, int width, int scale, int[] rgb,
                          PTR<Boolean> isHit, PTR<Boolean> isBigHit) {
        super(x, y, width, scale, isHit, isBigHit);
        this.rgb = rgb;
    }

    /**
     * Returns the red-value of the entity that will be visualised.
     * @return  an integer representing the red-value of the entity
     */
    public int getR() { return rgb[0]; }
    /**
     * Returns the green-value of the entity that will be visualised.
     * @return  an integer representing the green-value of the entity
     */
    public int getG() { return rgb[1]; }
    /**
     * Returns the blue-value of the entity that will be visualised.
     * @return  an integer representing the blue-value of the entity
     */
    public int getB() { return rgb[2]; }
}
