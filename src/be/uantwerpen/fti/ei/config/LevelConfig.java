package be.uantwerpen.fti.ei.config;

public class LevelConfig {

    // Player
    int player_lives;
    public int getPlayerLives() { return player_lives; }
    public void setPlayerLives(int player_lives) { this.player_lives = player_lives; }

    // Enemy
    int enemy_total, enemy_lives;
    public int getEnemTotal() { return enemy_total; }
    public void setEnemyTotal(int enemy_total) { this.enemy_total = enemy_total; }
    public int getEnemyLives() { return enemy_lives; }
    public void setEnemyLives(int enemy_lives) { this.enemy_lives = enemy_lives; }

    // Bonus
    int bonus_total;
    public int getBonusTotal() { return bonus_total; }
    public void setBonusTotal(int bonus_total) { this.bonus_total = bonus_total; }

    // Wall
    int wall_total, wall_lives;
    public int getWallTotal() { return wall_total; }
    public void setWallTotal(int wall_total) { this.wall_total = wall_total; }
    public int getWallLives() { return wall_lives; }
    public void setWallLives(int wall_lives) { this.wall_lives = wall_lives; }
}
