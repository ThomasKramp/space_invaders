package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.BoolPtr;

public class LifeComp {

    int lives;
    BoolPtr isHit;

    public LifeComp(int lives, BoolPtr isHit) {
        setLives(lives);
        setHit(isHit);
    }

    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }

    public BoolPtr isHitPtr() { return isHit; }
    public boolean isHit() { return isHit.isValue(); }
    public void setHit(BoolPtr isHit) { this.isHit = isHit; }
    public void setHit(boolean isHit) { this.isHit.setValue(isHit); }
}
