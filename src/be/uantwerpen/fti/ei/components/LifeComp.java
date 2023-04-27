package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.entities.EntityType;

public class LifeComp {
    int lives;
    PTR<Boolean> isHit, isBigHit, isDead;
    EntityType type;

    public LifeComp(int lives, PTR<Boolean> isHit, PTR<Boolean> isBigHit, PTR<Boolean> isDead, EntityType type) {
        setLives(lives);
        setHit(isHit); setBigHit(isBigHit); setDead(isDead);
        setType(type);
    }

    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }

    // public PTR<Boolean> isHitPtr() { return isHit; }
    public boolean isHit() { return isHit.getValue(); }
    public void setHit(PTR<Boolean> isHit) { this.isHit = isHit; }
    public void setHit(boolean isHit) { this.isHit.setValue(isHit); }

    //public PTR<Boolean> isBigHit() { return isBigHit; }
    public boolean isBigHit() { return isBigHit.getValue(); }
    public void setBigHit(PTR<Boolean> isBigHit) { this.isBigHit = isBigHit; }
    public void setBigHit(boolean isBigHit) { this.isBigHit.setValue(isBigHit); }

    // public PTR<Boolean> isDeadPtr() { return isDead; }
    public boolean isDead() { return isDead.getValue(); }
    public void setDead(PTR<Boolean> isDead) { this.isDead = isDead; }
    public void setDead(boolean isDead) { this.isDead.setValue(isDead); }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
