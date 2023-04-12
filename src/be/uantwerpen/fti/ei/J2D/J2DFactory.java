package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.IntPtr;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualComp;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualiseSystem;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public class J2DFactory implements IFactory {
    GraphicsContext grCtx;
    public J2DFactory(int width, int height) {
        grCtx = new GraphicsContext(width, height);
    }

    @Override
    public Entity getPlayer(int x, int y) {
        IntPtr xPtr = new IntPtr(x);
        IntPtr yPtr = new IntPtr(y);
        return new Entity(
                new AMovementComp(xPtr, yPtr),
                new J2DVisualComp(xPtr, yPtr, 16, 128, 64, 32, grCtx)
        );
    }

    @Override
    public IVisualiseSystem getVisualiseSystem() {
        return new J2DVisualiseSystem(grCtx);
    }
    @Override
    public AInput getInput() {
        return new Input(grCtx);
    }
}
