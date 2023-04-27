package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.LifeComp;

import java.util.List;

public class LifeSystem {
    public void checkLives(List<LifeComp> components) {
        int lives;
        for (LifeComp component: components) {
            lives = component.getLives();
            if (component.isDead()) lives = 0;
            else if (component.isHit()) {
                lives = lives - 1;
                if (lives == 0) component.setDead(true);
            }
            component.setLives(lives);
        }
    }

    // Needed for visualisation
    public void resetHits(List<LifeComp> components) {
        for (LifeComp component: components) component.setHit(false);
    }
}
