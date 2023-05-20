package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.LifeComp;

import java.util.List;

/** Class that updates the life components of a list of entities */
public class LifeSystem {
    /**
     * Method to update the life count of a list of entities.
     * @param   components list of all entities their life components that need to be updated
     * @see     LifeComp
     */
    public void checkLives(List<LifeComp> components) {
        int lives;
        for (LifeComp component: components) {
            lives = component.getLives();
            if (component.isHit())          lives = lives - 1;
            else if (component.isBigHit())  lives = lives - 3;
            else if (component.isDead())    lives = 0;
            if (lives <= 0) component.setDead(true);
            component.setLives(lives);
        }
    }

    /**
     * Method to reset the hit statuses of a list of entities.
     * @param   components list of all entities their life components that need to be updated
     * @see     LifeComp
     */
    public void resetHits(List<LifeComp> components) {
        for (LifeComp component: components) {
            component.setHit(false);
            component.setBigHit(false);
        }
    }
}
