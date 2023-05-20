package be.uantwerpen.fti.ei.enums;

/**
 * Enumeration for classifying the game state
 */
public enum GameState {
    /** Specifies that the game is starting */
    START,
    /** Specifies that the game is running */
    RUN,
    /** Specifies that the game is ending */
    END,
    /** Specifies that the game is paused */
    PAUSE,
    /** Specifies that the game is proceeding to the next level */
    NEXT,
    /** Specifies that the game is lost */
    GAME_OVER,
    /** Specifies that the game is won */
    GAME_WON,
}
