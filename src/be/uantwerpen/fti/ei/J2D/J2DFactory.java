package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.ColDetComp;
import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.SmartMoveComp;
import be.uantwerpen.fti.ei.dataStruct.BoolPtr;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualComp;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualiseSystem;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.entities.EntityType;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public class J2DFactory implements IFactory {
    GraphicsContext grCtx;
    public J2DFactory(int width, int height) { grCtx = new GraphicsContext(width, height); }

    @Override
    public Entity getPlayer(int x, int y) {
        IntPtr xPtr = new IntPtr(x), yPtr = new IntPtr(y);
        IntPtr vxPtr = new IntPtr(), vyPtr = new IntPtr();
        BoolPtr isHit = new BoolPtr(), isDead = new BoolPtr();
        int size = 32;
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr),
                new ColDetComp(xPtr, yPtr, vxPtr, vyPtr, 16, size, isHit, isDead, EntityType.PLAYER),
                new LifeComp(5, isHit, isDead),
                new J2DVisualComp(xPtr, yPtr, size, 128, 64, 32, grCtx)
        );
    }

    @Override
    public Entity getPBullet(int x, int y) {
        IntPtr xPtr = new IntPtr(x), yPtr = new IntPtr(y);
        IntPtr vxPtr = new IntPtr(), vyPtr = new IntPtr();
        BoolPtr isHit = new BoolPtr(), isDead = new BoolPtr();
        int size = 4;
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr),
                new ColDetComp(xPtr, yPtr, vxPtr, vyPtr, -4, size, isHit, isDead, EntityType.P_BULLET),
                new LifeComp(1, isHit, isDead),
                new J2DVisualComp(xPtr, yPtr, size, 128, 64, 32, grCtx)
        );
    }

    @Override
    public Entity getSmallEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x), yPtr = new IntPtr(y);
        IntPtr vxPtr = new IntPtr(), vyPtr = new IntPtr();
        BoolPtr isHit = new BoolPtr(), isDead = new BoolPtr();
        int size = 16;
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, vxPtr, vyPtr),
                new ColDetComp(xPtr, yPtr, vxPtr, vyPtr, 8, size, isHit, isDead, EntityType.ENEMY),
                new LifeComp(1, isHit, isDead),
                new J2DVisualComp(xPtr, yPtr, size, 32, 64, 128, grCtx)
        );
    }

    @Override
    public Entity getEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x), yPtr = new IntPtr(y);
        IntPtr vxPtr = new IntPtr(), vyPtr = new IntPtr();
        BoolPtr isHit = new BoolPtr(), isDead = new BoolPtr();
        int size = 32;
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, vxPtr, vyPtr),
                new ColDetComp(xPtr, yPtr, vxPtr, vyPtr, 16, size, isHit, isDead, EntityType.ENEMY),
                new LifeComp(3, isHit, isDead),
                new J2DVisualComp(xPtr, yPtr, size, 32, 64, 128, grCtx)
        );
    }

    @Override
    public Entity getBigEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x), yPtr = new IntPtr(y);
        IntPtr vxPtr = new IntPtr(), vyPtr = new IntPtr();
        BoolPtr isHit = new BoolPtr(), isDead = new BoolPtr();
        int size = 64;
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, vxPtr, vyPtr),
                new ColDetComp(xPtr, yPtr, vxPtr, vyPtr, 32, size, isHit, isDead, EntityType.ENEMY),
                new LifeComp(5, isHit, isDead),
                new J2DVisualComp(xPtr, yPtr, size, 32, 64, 128, grCtx)
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
