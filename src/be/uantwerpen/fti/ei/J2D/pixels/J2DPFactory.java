package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.J2D.Input;
import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.SmartMoveComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.entities.EntityType;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.interfaces.IHotBar;
import be.uantwerpen.fti.ei.systems.CollisionDetector1D;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public class J2DPFactory implements IFactory {
    J2DPGraphicsContext grCtx;
    public J2DPFactory() {}

    @Override
    public void setScreenDimensions(int[] screenDimen) {
        grCtx = new J2DPGraphicsContext(screenDimen[0], screenDimen[1], 16);
    }

    // TODO: Replace pointers with classes
    /*--------------------------------------------------------------------------------------------------------*/
    //region base entity creation
    private Entity CreateEntity(int x, int y, int size, EntityType type, int lives, int scale, int[] rgb) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        return new Entity(
                new MovementComp(xPtr, yPtr, size, type),
                new LifeComp(lives, isHit, isBigHit, isDead, type),
                new J2DPVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }
    private Entity CreateSmartEntity(int x, int y, int size, EntityType type, int lives, int scale, int[] rgb) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        return new Entity(
                new SmartMoveComp(xPtr, yPtr, size, type),
                new LifeComp(lives, isHit, isBigHit, isDead, type),
                new J2DPVisualComp(xPtr, yPtr, size, scale, rgb, isHit, isBigHit, grCtx)
        );
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Player
    @Override
    public Entity getPlayer(int x, int y) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, 1, EntityType.PLAYER, 5, grCtx.getScale(), rgb);
    }
    @Override
    public Entity getPBullet(int x, int y) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, 1, EntityType.P_BULLET, 1, grCtx.getScale()/4, rgb);
    }
    @Override
    public Entity getPRocket(int x, int y) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, 1, EntityType.P_ROCKET, 1, grCtx.getScale()/2, rgb);
    }
    @Override
    public Entity getWall(int x, int y) {
        int[] rgb = {128, 64, 32};
        return CreateEntity(x, y, 3, EntityType.WALL, 20, grCtx.getScale(), rgb);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Enemies
    @Override
    public Entity getEnemy(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateSmartEntity(x, y, 1, EntityType.ENEMY, 3, grCtx.getScale(), rgb);
    }
    @Override
    public Entity getEBullet(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, 1, EntityType.E_BULLET, 1, grCtx.getScale()/4, rgb);
    }
    @Override
    public Entity getBoss(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateSmartEntity(x, y, 4, EntityType.BOSS, 5, grCtx.getScale(), rgb);
    }
    @Override
    public Entity getBRocket(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, 1, EntityType.B_ROCKET, 1, grCtx.getScale()/2, rgb);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Bonus
    @Override
    public Entity getBonusLives(int x, int y) {
        int[] rgb = {255, 0, 0};
        return CreateEntity(x, y, 1, EntityType.BONUS_LIFE, 1, grCtx.getScale()/2, rgb);
    }
    @Override
    public Entity getBonusScore(int x, int y) {
        int[] rgb = {0, 255, 0};
        return CreateEntity(x, y, 1, EntityType.BONUS_SCORE, 1, grCtx.getScale()/2, rgb);
    }
    @Override
    public Entity getBonusRocket(int x, int y) {
        int[] rgb = {0, 0, 255};
        return CreateEntity(x, y, 1, EntityType.BONUS_ROCKET, 1, grCtx.getScale()/2, rgb);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region System
    @Override
    public ICollisionDetector getCollisionDetector(int width, int height) { return new CollisionDetector1D(width, height); }
    @Override
    public IVisualiseSystem getVisualiseSystem() { return new J2DPVisualiseSystem(grCtx); }
    @Override
    public IHotBar getHotBarHandler() { return grCtx; }
    @Override
    public AInput getInput() { return new Input(grCtx); }
    //endregion
}
