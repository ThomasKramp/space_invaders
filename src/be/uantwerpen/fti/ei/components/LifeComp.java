package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.enums.EntityType;

public class LifeComp {
    int lives;
    PTR<Boolean> isHit, isBigHit, isDead;
    EntityType type;

    public LifeComp(int lives, PTR<Boolean> isHit, PTR<Boolean> isBigHit, PTR<Boolean> isDead, EntityType type) {
        setLives(lives);
        setType(type);
        // Set hit boolean pointers
        this.isHit = isHit;
        this.isBigHit = isBigHit;
        this.isDead = isDead;
    }

    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }

    public boolean isHit() { return isHit.getValue(); }
    public void setHit(boolean isHit) { this.isHit.setValue(isHit); }

    public boolean isBigHit() { return isBigHit.getValue(); }
    public void setBigHit(boolean isBigHit) { this.isBigHit.setValue(isBigHit); }

    public boolean isDead() { return isDead.getValue(); }
    public void setDead(boolean isDead) { this.isDead.setValue(isDead); }

    public EntityType getType() { return type; }
    public void setType(EntityType type) { this.type = type; }
}
