package be.uantwerpen.fti.ei.config;

public class LevelConfig {

    // Player
    int player_lives = 0, player_size = 0;
    public int getPlayerLives() { return player_lives; }
    public void setPlayerLives(int player_lives) { this.player_lives = player_lives; }
    public int getPlayerSize() { return player_size; }
    public void setPlayerSize(int player_size) { this.player_size = player_size; }

    // Enemy
    int enemy_total = 0, enemy_lives = 0, enemy_size = 0;
    public int getEnemyTotal() { return enemy_total; }
    public void setEnemyTotal(int enemy_total) { this.enemy_total = enemy_total; }
    public int getEnemyLives() { return enemy_lives; }
    public void setEnemyLives(int enemy_lives) { this.enemy_lives = enemy_lives; }
    public int getEnemySize() { return enemy_size; }
    public void setEnemySize(int enemy_size) { this.enemy_size = enemy_size; }

    // Enemy
    int boss_total = 0, boss_lives = 0, boss_size = 0;
    public int getBossTotal() { return boss_total; }
    public void setBossTotal(int boss_total) { this.boss_total = boss_total; }
    public int getBossLives() { return boss_lives; }
    public void setBossLives(int boss_lives) { this.boss_lives = boss_lives; }
    public int getBossSize() { return boss_size; }
    public void setBossSize(int boss_size) { this.boss_size = boss_size; }

    // Wall
    int wall_total = 0, wall_lives = 0, wall_size = 0;
    public int getWallTotal() { return wall_total; }
    public void setWallTotal(int wall_total) { this.wall_total = wall_total; }
    public int getWallLives() { return wall_lives; }
    public void setWallLives(int wall_lives) { this.wall_lives = wall_lives; }
    public int getWallSize() { return wall_size; }
    public void setWallSize(int wall_size) { this.wall_size = wall_size; }
}
