package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public interface IFactory {

    void setScreenDimensions(int[] screenDimen);

    /*--------------------------------------------------------------------------------------------------------*/
    // Player
    Entity getPlayer(int x, int y, int lives, int size);
    Entity getPBullet(int x, int y);
    Entity getPRocket(int x, int y);
    Entity getWall(int x, int y, int lives, int size);

    /*--------------------------------------------------------------------------------------------------------*/
    // Enemies
    Entity getEnemy(int x, int y, int lives, int size);
    Entity getEBullet(int x, int y);
    Entity getBoss(int x, int y, int lives, int size);
    Entity getBRocket(int x, int y);

    /*--------------------------------------------------------------------------------------------------------*/
    // Bonus
    Entity getBonusLives(int x, int y);
    Entity getBonusScore(int x, int y);
    Entity getBonusRocket(int x, int y);

    /*--------------------------------------------------------------------------------------------------------*/
    // System
    ICollisionDetector getCollisionDetector(int width, int height);
    IVisualiseSystem getVisualiseSystem();
    AInput getInput();
}
