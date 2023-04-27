package be.uantwerpen.fti.ei.interfaces;

import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;

public interface IFactory {

    void setScreenDimensions(int[] screenDimen);

    Entity getPlayer(int x, int y);
    Entity getPBullet(int x, int y);
    public Entity getWall(int x, int y);

    Entity getEnemy(int x, int y);
    Entity getBossEnemy(int x, int y);
    Entity getEBullet(int x, int y);


    ICollisionDetector getCollisionDetector(int width, int height);
    IVisualiseSystem getVisualiseSystem();
    IHotBar getHotBarHandler();

    AInput getInput();
}
