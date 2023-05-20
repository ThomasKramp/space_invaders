package be.uantwerpen.fti.ei.enums;

/**
 * Enumeration for classifying an entity's type
 */
public enum EntityType {
    /** Specifies that the entity is a player */
    PLAYER,
    /** Specifies that the entity is a player bullet */
    P_BULLET,
    /** Specifies that the entity is a player rocket */
    P_ROCKET,
    /** Specifies that the entity is a wall */
    WALL,

    /** Specifies that the entity is an enemy */
    ENEMY,
    /** Specifies that the entity is an enemy bullet */
    E_BULLET,

    /** Specifies that the entity is a boss */
    BOSS,
    /** Specifies that the entity is a boss rocket */
    B_ROCKET,

    /** Specifies that the entity is a bonus for extra lives */
    BONUS_LIFE,
    /** Specifies that the entity is a bonus for a boost in score */
    BONUS_SCORE,
    /** Specifies that the entity is a bonus for the usage of rockets */
    BONUS_ROCKET
}
