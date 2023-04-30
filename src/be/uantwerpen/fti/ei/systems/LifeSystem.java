package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.LifeComp;

import java.util.List;

public class LifeSystem {
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

    public void resetHits(List<LifeComp> components) {
        for (LifeComp component: components) {
            component.setHit(false);
            component.setBigHit(false);
        }
    }
}
