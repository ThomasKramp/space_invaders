package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.Input;
import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.components.AVisualComp;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class J2DIFactory implements IFactory {
    GraphicsContext grCtx;
    public J2DIFactory() {}

    @Override
    public void setScreenDimensions(int[] screenDimen) {
        grCtx = new GraphicsContext(screenDimen[0], screenDimen[1], 16);
    }

    // TODO: Cleanup image variables & methods
    // Load images
    private BufferedImage loadImages(String path) {
        BufferedImage backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Unable to load " + path);
        }
        return backgroundImg;
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /*--------------------------------------------------------------------------------------------------------*/
    //region base entity creation
    private Entity CreateEntity(int x, int y, int size, EntityType type, int lives, int scale, String path, String path2, boolean isSmart, boolean isHittable) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        BufferedImage backgroundImg = loadImages(path);
        backgroundImg = resizeImage(backgroundImg, size * scale, scale);
        MovementComp moveComp = isSmart ? new SmartMoveComp(xPtr, yPtr, size, type) : new MovementComp(xPtr, yPtr, size, type);
        AVisualComp visualComp;
        if (isHittable) {
            BufferedImage image2 = loadImages(path2);
            image2 = resizeImage(image2, size * scale, scale);
            visualComp = new J2DIVisualComp(xPtr, yPtr, size, scale, backgroundImg, image2, isHit, isBigHit, grCtx);
        } else {
            visualComp = new J2DIVisualComp(xPtr, yPtr, size, scale, backgroundImg, isHit, isBigHit, grCtx);
        }
        return new Entity(moveComp,
                new LifeComp(lives, isHit, isBigHit, isDead, type),
                visualComp
        );
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Player
    @Override
    public Entity getPlayer(int x, int y, int lives, int size) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/player.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/player_hit.png";
        return CreateEntity(x, y, size, EntityType.PLAYER, lives, grCtx.getScale(), imagePath, imagePath2, false, true);
    }
    @Override
    public Entity getPBullet(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bullet.png";
        return CreateEntity(x, y, 1, EntityType.P_BULLET, 1, grCtx.getScale()/4, imagePath, "",false, false);
    }
    @Override
    public Entity getPRocket(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/rocket.png";
        return CreateEntity(x, y, 1, EntityType.P_ROCKET, 1, grCtx.getScale()/2, imagePath, "",false, false);
    }
    @Override
    public Entity getWall(int x, int y, int lives, int size) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/wall.png";
        return CreateEntity(x, y, size, EntityType.WALL, lives, grCtx.getScale(), imagePath, "", false, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Enemies
    @Override
    public Entity getEnemy(int x, int y, int lives, int size) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/enemy.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/enemy_hit.png";
        return CreateEntity(x, y, size, EntityType.ENEMY, lives, grCtx.getScale(), imagePath, imagePath2, true, true);
    }
    @Override
    public Entity getEBullet(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bullet.png";
        return CreateEntity(x, y, 1, EntityType.E_BULLET, 1, grCtx.getScale()/4, imagePath, "",false, false);
    }
    @Override
    public Entity getBoss(int x, int y, int lives, int size) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/boss.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/boss_hit.png";
        return CreateEntity(x, y, size, EntityType.BOSS, lives, grCtx.getScale(), imagePath, imagePath2, true, true);
    }
    @Override
    public Entity getBRocket(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/rocket.png";
        return CreateEntity(x, y, 1, EntityType.B_ROCKET, 1, grCtx.getScale()/2, imagePath, "",false, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Bonus
    @Override
    public Entity getBonusLives(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bonus1.png";
        return CreateEntity(x, y, 1, EntityType.BONUS_LIFE, 1, grCtx.getScale()/2, imagePath, "",false, false);
    }
    @Override
    public Entity getBonusScore(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bonus2.png";
        return CreateEntity(x, y, 1, EntityType.BONUS_SCORE, 1, grCtx.getScale()/2, imagePath, "",false, false);
    }
    @Override
    public Entity getBonusRocket(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bonus3.png";
        return CreateEntity(x, y, 1, EntityType.BONUS_ROCKET, 1, grCtx.getScale()/2, imagePath, "",false, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region System
    @Override
    public ICollisionDetector getCollisionDetector(int width, int height) { return new CollisionDetector1D(width, height); }
    @Override
    public IVisualiseSystem getVisualiseSystem() { return new J2DIVisualiseSystem(grCtx); }
    @Override
    public IHotBar getHotBarHandler() { return grCtx; }
    @Override
    public AInput getInput() { return new Input(grCtx); }
    //endregion
}
