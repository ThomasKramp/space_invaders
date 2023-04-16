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
                // component.setHit(false);
            }
            component.setLives(lives);
        }
        components.removeIf(component -> component.getLives() <= 0);
    }

    // Needed for visualisation
    public void resetHits(List<LifeComp> components) {
        for (LifeComp component: components) component.setHit(false);
    }
}
