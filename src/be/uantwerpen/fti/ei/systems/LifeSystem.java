package be.uantwerpen.fti.ei.systems;

import be.uantwerpen.fti.ei.components.LifeComp;

import java.util.List;

public class LifeSystem {
    public void update(List<LifeComp> components) {
        int lives;
        for (LifeComp component: components) {
            lives = component.getLives();
            if (component.isHit()) lives = lives - 1;
            component.setLives(lives);
        }
        components.removeIf(component -> component.getLives() <= 0);
    }
}
