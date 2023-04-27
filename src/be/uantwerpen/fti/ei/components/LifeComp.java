package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.BoolPtr;
import be.uantwerpen.fti.ei.entities.EntityType;

public class LifeComp {
    int lives;
    BoolPtr isHit, isDead;
    EntityType type;

    public LifeComp(int lives, BoolPtr isHit, BoolPtr isDead, EntityType type) {
        setLives(lives);
        setHit(isHit); setDead(isDead);
        setType(type);
    }

    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }

    // public BoolPtr isHitPtr() { return isHit; }
    public boolean isHit() { return isHit.isValue(); }
    public void setHit(BoolPtr isHit) { this.isHit = isHit; }
    public void setHit(boolean isHit) { this.isHit.setValue(isHit); }

    // public BoolPtr isDeadPtr() { return isDead; }
    public boolean isDead() { return isDead.isValue(); }
    public void setDead(BoolPtr isDead) { this.isDead = isDead; }
    public void setDead(boolean isDead) { this.isDead.setValue(isDead); }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
