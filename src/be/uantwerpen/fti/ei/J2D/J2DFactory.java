package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualComp;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualiseSystem;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public class J2DFactory implements IFactory {
    GraphicsContext grCtx;
    public J2DFactory(int width, int height) {
        grCtx = new GraphicsContext(width, height);
    }

    @Override
    public Entity getPlayer(int x, int y) {
        return new Entity(
                new AMovementComp(x, y),
                new J2DVisualComp(x, y, 16, 128, 64, 32, grCtx)
        );
    }

    @Override
    public IVisualiseSystem getVisualiseSystem() {
        return new J2DVisualiseSystem(grCtx);
    }
}
