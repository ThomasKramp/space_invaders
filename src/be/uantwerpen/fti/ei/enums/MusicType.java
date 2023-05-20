package be.uantwerpen.fti.ei.enums;

/**
 * Enumeration for classifying a music type
 */
public enum MusicType {
    /** Specifies that the music is for background use */
    BACKGROUND,
    /** Specifies that the music is used if the player is hit */
    PLAYER_HIT,
    /** Specifies that the music is used if an enemy is hit */
    ENEMY_HIT,
    /** Specifies that the music is used if an entity shoots */
    SHOOT,
    /** Specifies that the music is used if a new bonus is dropped */
    BONUS_DROP
}
