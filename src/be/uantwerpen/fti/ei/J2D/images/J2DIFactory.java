package be.uantwerpen.fti.ei.J2D.images;

import be.uantwerpen.fti.ei.J2D.J2DAFactory;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.components.LifeComp;
import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.SmartMoveComp;
import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.enums.EntityType;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class to crate all Java-2D entities creation and package related system methods using images
 * @see J2DAFactory
 */
public class J2DIFactory extends J2DAFactory {
    // Load images
    /**
     * Method to load an image based on path to its file
     * @param   path a string that represents the location of the image file
     * @return  a BufferedImage representing the loaded image
     */
    private BufferedImage loadImages(String path) {
        BufferedImage backgroundImg = null;
        try { backgroundImg = ImageIO.read(new File(path)); }
        catch (IOException e) { System.out.println("Unable to load " + path); }
        return backgroundImg;
    }

    /**
     * Method to resize an image to certain specifications
     * @param   originalImage a BufferedImage that represents the loaded image
     * @param   targetWidth an integer that represents the desired with of the image
     * @param   targetHeight an integer that represents the desired height of the image
     * @return  a BufferedImage representing the resized image
     */
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

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
     * @param   path a string representing the path to the entity's default image file for the Java-2d visualisation
     * @param   path2 a string representing the path to the entity's image file if hit (this can be null) for the Java-2d visualisation
     * @param   isSmart a boolean that defines if the component will use a SmartMovementComp or MovementComp
     * @param   isHittable a boolean that defines if the path2 variable will be used
     * @return  an entity
     */
    private Entity CreateEntity(int x, int y, int width, EntityType type, int lives, int scale, String path, String path2, boolean isSmart, boolean isHittable) {
        PTR<Integer> xPtr = new PTR<>(x), yPtr = new PTR<>(y);
        PTR<Boolean> isHit = new PTR<>(false), isBigHit = new PTR<>(false), isDead = new PTR<>(false);
        BufferedImage backgroundImg = loadImages(path);
        backgroundImg = resizeImage(backgroundImg, width * scale, scale);
        MovementComp moveComp = isSmart ? new SmartMoveComp(xPtr, yPtr, width, type) : new MovementComp(xPtr, yPtr, width, type);
        AVisualComp visualComp;
        if (isHittable) {
            BufferedImage image2 = loadImages(path2);
            image2 = resizeImage(image2, width * scale, scale);
            visualComp = new J2DIVisualComp(xPtr, yPtr, width, scale, backgroundImg, image2, isHit, isBigHit);
        } else {
            visualComp = new J2DIVisualComp(xPtr, yPtr, width, scale, backgroundImg, isHit, isBigHit);
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
    public Entity getPlayer(int x, int y, int lives, int width) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/player.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/player_hit.png";
        return CreateEntity(x, y, width, EntityType.PLAYER, lives, grCtx.getScale(), imagePath, imagePath2, false, true);
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
    public Entity getWall(int x, int y, int lives, int width) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/wall.png";
        return CreateEntity(x, y, width, EntityType.WALL, lives, grCtx.getScale(), imagePath, "", false, false);
    }
    //endregion
    /*--------------------------------------------------------------------------------------------------------*/
    //region Enemies
    @Override
    public Entity getEnemy(int x, int y, int lives, int width) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/enemy.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/enemy_hit.png";
        return CreateEntity(x, y, width, EntityType.ENEMY, lives, grCtx.getScale(), imagePath, imagePath2, true, true);
    }
    @Override
    public Entity getEBullet(int x, int y) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/bullet.png";
        return CreateEntity(x, y, 1, EntityType.E_BULLET, 1, grCtx.getScale()/4, imagePath, "",false, false);
    }
    @Override
    public Entity getBoss(int x, int y, int lives, int width) {
        String imagePath = "src/be/uantwerpen/fti/ei/sprites/boss.png";
        String imagePath2 = "src/be/uantwerpen/fti/ei/sprites/boss_hit.png";
        return CreateEntity(x, y, width, EntityType.BOSS, lives, grCtx.getScale(), imagePath, imagePath2, true, true);
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
    public IVisualiseSystem getVisualiseSystem() { return new J2DIVisualiseSystem(grCtx); }
    //endregion
}
