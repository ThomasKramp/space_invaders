package be.uantwerpen.fti.ei.J2D.pixels;

import be.uantwerpen.fti.ei.J2D.J2DAFactory;
import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.SmartMoveComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.enums.EntityType;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

/**
 * A class to crate all Java-2D entities creation and package related system methods using pixel coloring
 * @see J2DAFactory
 */
public class J2DPFactory extends J2DAFactory {
    /*--------------------------------------------------------------------------------------------------------*/
    //region base entity creation
    /**
     * Method to generalise the entity creation
     * @param   x an integer representing the x coordinate of the entity
     * @param   y an integer representing the y coordinate of the entity
     * @param   width an integer representing the width of the entity
     * @param   type an enum representing the type of the entity
     * @param   lives an integer representing the remaining lives of the entity
     * @param   scale an integer representing the scaling factor of the entity compared to the base dimensions
     * @param   rgb an array of integers representing the coloring for the Java-2d visualisation
     * @param   isSmart a boolean that defines if the component will use a SmartMovementComp or MovementComp
     * @return  an entity
     */
    private Entity CreateEntity(int x, int y, int width, EntityType type, int lives, int scale, int[] rgb, boolean isSmart) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        MovementComp moveComp = isSmart ? new SmartMoveComp(xPtr, yPtr, width, type) : new MovementComp(xPtr, yPtr, width, type);
        return new Entity(moveComp,
                new LifeComp(lives, isHit, isBigHit, isDead, type),
                new J2DPVisualComp(xPtr, yPtr, width, scale, rgb, isHit, isBigHit)
        );
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Player
    @Override
    public Entity getPlayer(int x, int y, int lives, int width) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, width, EntityType.PLAYER, lives, grCtx.getScale(), rgb, false);
    }
    @Override
    public Entity getPBullet(int x, int y) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, 1, EntityType.P_BULLET, 1, grCtx.getScale()/4, rgb, false);
    }
    @Override
    public Entity getPRocket(int x, int y) {
        int[] rgb = {192, 96, 64};
        return CreateEntity(x, y, 1, EntityType.P_ROCKET, 1, grCtx.getScale()/2, rgb, false);
    }
    @Override
    public Entity getWall(int x, int y, int lives, int width) {
        int[] rgb = {128, 64, 32};
        return CreateEntity(x, y, width, EntityType.WALL, lives, grCtx.getScale(), rgb, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Enemies
    @Override
    public Entity getEnemy(int x, int y, int lives, int width) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, width, EntityType.ENEMY, lives, grCtx.getScale(), rgb, true);
    }
    @Override
    public Entity getEBullet(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, 1, EntityType.E_BULLET, 1, grCtx.getScale()/4, rgb, false);
    }
    @Override
    public Entity getBoss(int x, int y, int lives, int width) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, width, EntityType.BOSS, lives, grCtx.getScale(), rgb, true);
    }
    @Override
    public Entity getBRocket(int x, int y) {
        int[] rgb = {48, 96, 192};
        return CreateEntity(x, y, 1, EntityType.B_ROCKET, 1, grCtx.getScale()/2, rgb, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Bonus
    @Override
    public Entity getBonusLives(int x, int y) {
        int[] rgb = {255, 0, 0};
        return CreateEntity(x, y, 1, EntityType.BONUS_LIFE, 1, grCtx.getScale()/2, rgb, false);
    }
    @Override
    public Entity getBonusScore(int x, int y) {
        int[] rgb = {0, 255, 0};
        return CreateEntity(x, y, 1, EntityType.BONUS_SCORE, 1, grCtx.getScale()/2, rgb, false);
    }
    @Override
    public Entity getBonusRocket(int x, int y) {
        int[] rgb = {0, 0, 255};
        return CreateEntity(x, y, 1, EntityType.BONUS_ROCKET, 1, grCtx.getScale()/2, rgb, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region System
    @Override
    public IVisualiseSystem getVisualiseSystem() { return new J2DPVisualiseSystem(grCtx); }
    //endregion
}
