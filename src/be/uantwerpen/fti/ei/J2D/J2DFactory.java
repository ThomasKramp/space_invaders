package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.Movement.EnemyMoveComp;
import be.uantwerpen.fti.ei.components.Movement.PBulletMoveComp;
import be.uantwerpen.fti.ei.dataStruct.IntPtr;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualComp;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualiseSystem;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.components.Movement.PlayerMoveComp;
import be.uantwerpen.fti.ei.entities.EntityType;
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
        int size = 32;
        return new Entity(
                new PlayerMoveComp(xPtr, yPtr, 16, size),
                new J2DVisualComp(xPtr, yPtr, size, 128, 64, 32, grCtx)
        );
    }

    @Override
    public Entity getPBullet(int x, int y) {
        IntPtr xPtr = new IntPtr(x);
        IntPtr yPtr = new IntPtr(y);
        int size = 4;
        return new Entity(
                new PBulletMoveComp(xPtr, yPtr, 8, size),
                new J2DVisualComp(xPtr, yPtr, size, 128, 64, 32, grCtx)
        );
    }

    @Override
    public Entity getSmallEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x);
        IntPtr yPtr = new IntPtr(y);
        int size = 16;
        return new Entity(
                new EnemyMoveComp(xPtr, yPtr, 8, size),
                new J2DVisualComp(xPtr, yPtr, size, 32, 64, 128, grCtx)
        );
    }

    @Override
    public Entity getEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x);
        IntPtr yPtr = new IntPtr(y);
        int size = 32;
        return new Entity(
                new EnemyMoveComp(xPtr, yPtr, 8, size),
                new J2DVisualComp(xPtr, yPtr, size, 32, 64, 128, grCtx)
        );
    }

    @Override
    public Entity getBigEnemy(int x, int y) {
        IntPtr xPtr = new IntPtr(x);
        IntPtr yPtr = new IntPtr(y);
        int size = 64;
        return new Entity(
                new EnemyMoveComp(xPtr, yPtr, 8, size),
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
