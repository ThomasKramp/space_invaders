package be.uantwerpen.fti.ei.components;

import be.uantwerpen.fti.ei.dataStruct.PTR;
import be.uantwerpen.fti.ei.enums.EntityType;

/**
 * Component class to store data containing to the lives and subsequent deletion of an entity
 */
public class LifeComp {
    int lives;
    /**
     * Represents the entity's hit status.
     * @see     PTR
     */
    final PTR<Boolean> isHit, isBigHit, isDead;
    /**
     * Represents the entity's type.
     * @see     EntityType
     */
    final EntityType type;

    /**
     * Class constructor specifying the amount of lives, the pointers to the hit statuses and the type of the entity.
     * @param   lives an integer representing the lives of the entity
     * @param   isHit a boolean pointer representing the hit status of a normal hit on the entity
     * @param   isBigHit a boolean pointer representing the hit status of a bigger hit on the entity
     * @param   isDead a boolean pointer representing the hit status of a lethal hit on the entity
     * @param   type an enum value (number) representing the type of the entity
     * @see     PTR
     */
    public LifeComp(int lives, PTR<Boolean> isHit, PTR<Boolean> isBigHit, PTR<Boolean> isDead, EntityType type) {
        this.lives = lives;
        this.type = type;
        // Set hit boolean pointers
        this.isHit = isHit;
        this.isBigHit = isBigHit;
        this.isDead = isDead;
    }

    /**
     * Returns the amount of lives the entity has left.
     * @return  an integer representing the amount of lives the entity has left
     */
    public int getLives() { return lives; }
    /**
     * Sets the amount of lives the entity has.
     * @param   lives an integer representing the amount of lives the entity has left
     */
    public void setLives(int lives) { this.lives = lives; }

    /**
     * Returns the hit status of the entity containing to a normal hit.
     * @return  a boolean representing the hit status of a normal hit on the entity
     */
    public boolean isHit() { return isHit.getValue(); }
    /**
     * Sets the hit status of the entity containing to a normal hit.
     * <br>The method will change the boolean value inside the boolean pointer.
     * @param   isHit a boolean flag to indicate the hit status of the entity containing to a normal hit
     */
    public void setHit(boolean isHit) { this.isHit.setValue(isHit); }

    /**
     * Returns the hit status of the entity containing to a bigger hit.
     * @return  a boolean representing the hit status of a bigger hit on the entity
     */
    public boolean isBigHit() { return isBigHit.getValue(); }
    /**
     * Sets the hit status of the entity containing to a bigger hit.
     * <br>The method will change the boolean value inside the boolean pointer.
     * @param   isBigHit a boolean flag to indicate the hit status of the entity containing to a bigger hit
     */
    public void setBigHit(boolean isBigHit) { this.isBigHit.setValue(isBigHit); }

    /**
     * Returns the hit status of the entity containing to a lethal hit.
     * @return  a boolean representing the hit status of a lethal hit on the entity
     */
    public boolean isDead() { return isDead.getValue(); }
    /**
     * Sets the hit status of the entity containing to a lethal hit.
     * <br>The method will change the boolean value inside the boolean pointer.
     * @param   isDead a boolean flag to indicate the hit status of the entity containing to a lethal hit
     */
    public void setDead(boolean isDead) { this.isDead.setValue(isDead); }

    /**
     * Returns the type of the entity.
     * @return  an enum value (number) representing the type of the entity
     */
    public EntityType getType() { return type; }
}
