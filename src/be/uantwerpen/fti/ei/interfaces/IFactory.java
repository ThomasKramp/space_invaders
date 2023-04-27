package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public interface IFactory {

    /**
     * Getter for creating a wall
     * @param x (int)
     * @param y (int)
     * @return AEntity (PlayerShip)
     */
    // Entity getWall(int x, int y);

    /**
     * Getter for creating a player
     * @param x (int)
     * @param y (int)
     * @return AShip (PlayerShip)
     */
    void setScreenDimensions(int[] screenDimen);

    Entity getPlayer(int x, int y);
    Entity getPBullet(int x, int y);
    public Entity getWall(int x, int y);

    Entity getEnemy(int x, int y);
    Entity getBossEnemy(int x, int y);
    Entity getEBullet(int x, int y);

    IVisualiseSystem getVisualiseSystem();
    IHotBar getHotBarHandler();

    AInput getInput();

    /**
     * Getter for creating a ship
     * @param x (int)
     * @param y (int)
     * @return AShip (EnemyShip)
     */
    //AShip getEnemyShip(int x, int y);

    /**
     * Getter for creating a visualiser
     * @param width (int)
     * @param height (int)
     * @return AVisualiser
     */
    //AVisualiser getGetVisualiser(int width, int height);
}
