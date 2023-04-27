package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.SmartMoveComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualComp;
import be.uantwerpen.fti.ei.J2D.data_oriented.J2DVisualiseSystem;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.entities.EntityType;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.interfaces.IHotBar;
import be.uantwerpen.fti.ei.systems.CollisionDetector1D;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public class J2DFactory implements IFactory {
    GraphicsContext grCtx;
    public J2DFactory() {}

    @Override
    public void setScreenDimensions(int[] screenDimen) {
        grCtx = new GraphicsContext(screenDimen[0], screenDimen[1], 16);
    }

    @Override
    public Entity getPlayer(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.PLAYER;
        int size = 1; PTR<Integer> scale = new PTR<>(grCtx.getScale());
        int[] rgb = {192, 96, 64};
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(5, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public Entity getPBullet(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.P_BULLET;
        int size = 1; PTR<Integer> scale = new PTR<>(grCtx.getScale()/4);
        int[] rgb = {192, 96, 64};
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(1, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public Entity getWall(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.WALL;
        int size = 3; PTR<Integer> scale = new PTR<>(grCtx.getScale());
        int[] rgb = {128, 64, 32};
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(20, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public Entity getEnemy(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.ENEMY;
        int size = 1; PTR<Integer> scale = new PTR<>(grCtx.getScale());
        int[] rgb = {48, 96, 192};
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(3, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public Entity getBossEnemy(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.BOSS;
        int size = 4; PTR<Integer> scale = new PTR<>(grCtx.getScale());
        int[] rgb = {48, 96, 192};
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(5, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public Entity getEBullet(int x, int y) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Integer> vxPtr = new PTR<>(0), vyPtr = new PTR<>(0);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        EntityType type = EntityType.E_BULLET;
        int size = 1; PTR<Integer> scale = new PTR<>(grCtx.getScale()/4);
        int[] rgb = {48, 96, 192};
        return new Entity(
                new MovementComp(xPtr, yPtr, vxPtr, vyPtr, size, type),
                new LifeComp(1, isHit, isBigHit, isDead, type),
                new J2DVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }

    @Override
    public ICollisionDetector getCollisionDetector(int width, int height) { return new CollisionDetector1D(width, height); }

    @Override
    public IVisualiseSystem getVisualiseSystem() { return new J2DVisualiseSystem(grCtx); }

    @Override
    public IHotBar getHotBarHandler() { return grCtx; }

    @Override
    public AInput getInput() { return new Input(grCtx); }
}
