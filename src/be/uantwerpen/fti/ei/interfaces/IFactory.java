package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.components.AVisualComp;
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
    Entity getPlayer(int x, int y);
    Entity getSmallEnemy(int x, int y);
    Entity getEnemy(int x, int y);
    Entity getBigEnemy(int x, int y);

    IVisualiseSystem getVisualiseSystem();

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
