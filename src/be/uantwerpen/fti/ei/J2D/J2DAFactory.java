package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.CollisionDetector1D;

/**
 * Base Java-2D factory class that must be inherited by other Java-2D factory classes<br>
 * A class to crate all Java-2D entities creation and package related system methods
 * @see IFactory
 */
public abstract class J2DAFactory implements IFactory {

    /**
     * Class object that contains all base drawing data and methods.
     * @see     J2DGraphicsContext
     */
    protected J2DGraphicsContext grCtx;

    @Override
    public void setScreenDimensions(int[] screenDimen) {
        grCtx = new J2DGraphicsContext(screenDimen[0], screenDimen[1], 16);
    }
    /*--------------------------------------------------------------------------------------------------------*/
    //region System
    @Override
    public ICollisionDetector getCollisionDetector(int width, int height) { return new CollisionDetector1D(width, height); }
    @Override
    public AInput getInput() { return new J2DInput(grCtx); }
    //endregion
}
