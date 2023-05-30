package be.uantwerpen.fti.ei.config;

/** Class to store the loaded data containing to the level configuration */
public class LevelConfig {

    // Enemy
    int enemy_total = 0, enemy_lives = 0, enemy_width = 0;
    /**
     * Returns the starting amount of enemy entities in the level.
     * @return  an integer representing the starting amount of enemy entities in the level
     */
    public int getEnemyTotal() { return enemy_total; }
    /**
     * Sets the starting amount of enemy entities in the level.
     * @param   enemy_total an integer representing the starting amount of enemy entities in the level
     */
    public void setEnemyTotal(int enemy_total) { this.enemy_total = enemy_total; }
    /**
     * Returns the amount of lives an enemy entity starts with within the level.
     * @return  an integer representing the amount of lives an enemy entity starts with within the level
     */
    public int getEnemyLives() { return enemy_lives; }
    /**
     * Sets the amount of lives an enemy entity starts with within the level.
     * @param   enemy_lives an integer representing the amount of lives an enemy entity starts with within the level
     */
    public void setEnemyLives(int enemy_lives) { this.enemy_lives = enemy_lives; }
    /**
     * Returns the width of enemy entities in the level.
     * @return  an integer representing the width of the enemy entities in the level
     */
    public int getEnemyWidth() { return enemy_width; }
    /**
     * Sets the width of enemy entities in the level.
     * @param   enemy_width an integer representing the width of the enemy entities in the level
     */
    public void setEnemyWidth(int enemy_width) { this.enemy_width = enemy_width; }

    // Enemy
    int boss_total = 0, boss_lives = 0, boss_width = 0;
    /**
     * Returns the starting amount of boss type enemy entities in the level.
     * @return  an integer representing the starting amount of boss type enemy entities in the level
     */
    public int getBossTotal() { return boss_total; }
    /**
     * Sets the starting amount of boss type enemy entities in the level.
     * @param   boss_total an integer representing the starting amount of boss type enemy entities in the level
     */
    public void setBossTotal(int boss_total) { this.boss_total = boss_total; }
    /**
     * Returns the amount of lives a boss type enemy entity starts with within the level.
     * @return  an integer representing the amount of lives a boss type enemy entity starts with within the level
     */
    public int getBossLives() { return boss_lives; }
    /**
     * Sets the amount of lives a boss type enemy entity starts with within the level.
     * @param   boss_lives an integer representing the amount of lives a boss type enemy entity starts with within the level
     */
    public void setBossLives(int boss_lives) { this.boss_lives = boss_lives; }
    /**
     * Returns the width of boss type enemy entities in the level.
     * @return  an integer representing the width of boss type the enemy entities in the level
     */
    public int getBossWidth() { return boss_width; }
    /**
     * Sets the width of boss type enemy entities in the level.
     * @param   boss_width an integer representing the width of the boss type enemy entities in the level
     */
    public void setBossWidth(int boss_width) { this.boss_width = boss_width; }

    // Wall
    int wall_total = 0, wall_lives = 0, wall_width = 0;
    /**
     * Returns the starting amount of wall entities in the level.
     * @return  an integer representing the starting amount of wall entities in the level
     */
    public int getWallTotal() { return wall_total; }
    /**
     * Sets the starting amount of wall entities in the level.
     * @param   wall_total an integer representing the starting amount of wall entities in the level
     */
    public void setWallTotal(int wall_total) { this.wall_total = wall_total; }
    /**
     * Returns the amount of lives a wall entity starts with within the level.
     * @return  an integer representing the amount of lives a wall entity starts with within the level
     */
    public int getWallLives() { return wall_lives; }
    /**
     * Sets the amount of lives a wall entity starts with within the level.
     * @param   wall_lives an integer representing the amount of lives a wall entity starts with within the level
     */
    public void setWallLives(int wall_lives) { this.wall_lives = wall_lives; }
    /**
     * Returns the width of wall entities in the level.
     * @return  an integer representing the width of the wall entities in the level
     */
    public int getWallWidth() { return wall_width; }
    /**
     * Sets the width of wall entities in the level.
     * @param   wall_width an integer representing the width of the wall entities in the level
     */
    public void setWallWidth(int wall_width) { this.wall_width = wall_width; }
}
