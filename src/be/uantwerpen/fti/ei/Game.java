package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.audio.AudioPlayer;
import be.uantwerpen.fti.ei.config.LevelConfig;
import be.uantwerpen.fti.ei.enums.*;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;
import be.uantwerpen.fti.ei.components.*;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.*;

import java.util.*;
import java.util.stream.Collectors;

/** Class that represents the game. */
public class Game {
    /**
     * Factory which will create the entities
     * @see     IFactory
     */
    private final IFactory factory;
    /**
     * AInput that will store and return the (keyboard) inputs
     * @see     AInput
     */
    private final AInput input;
    /**
     * Entity that the player will use
     * @see     Entity
     */
    private final Entity player;
    /**
     * List containing lists of all the entities in a level
     * @see     Entity
     */
    private final ArrayList<ArrayList<Entity>> levelEntities = new ArrayList<>();
    /**
     * Audioplayer that will play background music and sound effects
     * @see     AudioPlayer
     */
    private final AudioPlayer musicPlayer;
    private final int screenWidth, screenHeight;

    /**
     * Class constructor specifying the factory to create entities, the screen-dimensions, the player configuration, the levels and the music.
     * @param   fact a factory which will create the entities
     * @param   screenDimen an array of integers representing the screen-dimensions
     * @param   playerConfig an array of integers representing the player configuration
     * @param   levels a list containing all the different level configurations
     * @param   music a dictionary that binds music clips with a name
     * @see     IFactory
     * @see     LevelConfig
     * @see     MusicType
     */
    public Game(IFactory fact, int[] screenDimen, int[] playerConfig, ArrayList<LevelConfig> levels, Map<MusicType, String> music) {
        // Initiate Factory
        this.factory = fact;
        this.factory.setScreenDimensions(screenDimen);
        this.input = this.factory.getInput();

        // Set screen dimensions
        this.screenWidth = screenDimen[0];
        this.screenHeight = screenDimen[1];

        // Player
        player = factory.getPlayer(screenWidth / 2 - 1, screenHeight * 5 / 6, playerConfig[0], playerConfig[1]);

        // Initiate levels
        createLevels(levels);

        // Load music
        musicPlayer = new AudioPlayer("src/be/uantwerpen/fti/ei/sounds/", music);
    }

    /**
     * Method that will delay the system for a specified amount of time.
     * {@link}  &nbsp;Sleep <a href="https://stackoverflow.com/a/180191">Sleep</a>
     * @param   levels an integer representing the time to delay in millis
     */
    private void createLevels(ArrayList<LevelConfig> levels) {
        for (LevelConfig level: levels) {
            ArrayList<Entity> entities = new ArrayList<>();
            entities.add(player);
            int xStart, yStart, rowTotal, offset;

            // Enemies
            List<Entity> enemies = new ArrayList<>();
            xStart = screenWidth; yStart = screenHeight / 4; rowTotal = 0;
            while (enemies.size() < level.getEnemyTotal()) {
                if (xStart >= screenWidth / 4 * 3 - 1) {
                    // An even number of enemies will lead to a column formation
                    // An uneven number of enemies will lead to a crossed formation
                    offset = level.getEnemyWidth() * (level.getEnemyTotal() % 2 == 0 ? 1 : (yStart % 2));
                    xStart = screenWidth / 4 + offset;
                    yStart++;

                    // Get the amount of enemies per row
                    if (rowTotal == 0) rowTotal = enemies.size();

                    // Center front line
                    if (level.getEnemyTotal() - enemies.size() < rowTotal) {
                        // Get the amount of enemies on the last row
                        int leftovers = level.getEnemyTotal() - enemies.size();
                        // Get the usual amount of enemies for the last row
                        offset = level.getEnemyTotal() % 2 == 0 ? 0 : 1 - (yStart % 2);
                        rowTotal -= offset;
                        // Subtract them and add the difference
                        xStart +=  level.getEnemyWidth() * (rowTotal - leftovers);
                    }
                }

                enemies.add(factory.getEnemy(xStart, yStart, level.getEnemyLives(), level.getEnemyWidth()));
                xStart += level.getEnemyWidth() * 2;
            }
            entities.addAll(enemies);

            // Boss
            // TODO: Remove 4 boss bug
            List<Entity> bosses = new ArrayList<>();
            xStart = screenWidth; yStart = screenHeight / 8; rowTotal = 0;
            while (bosses.size() < level.getBossTotal()) {
                if (xStart >= screenWidth / 3 * 2) {
                    offset = level.getBossWidth() * (level.getBossTotal() % 2 == 0 ? 1 : (yStart % 2));
                    xStart = screenWidth / 3 + offset;
                    yStart++;

                    if (rowTotal == 0) rowTotal = bosses.size();
                    if (level.getBossTotal() - bosses.size() < rowTotal) {
                        int leftovers = level.getBossTotal() - bosses.size();
                        offset = level.getBossTotal() % 2 == 0 ? 0 : 1 - (yStart % 2);
                        rowTotal -= offset;
                        xStart += level.getBossWidth() * (rowTotal - leftovers);
                    }
                }

                bosses.add(factory.getBoss(xStart, yStart, level.getBossLives(), level.getBossWidth()));
                xStart += level.getBossWidth() * 2;
            }
            entities.addAll(bosses);

            // Walls
            // TODO: Make randomised wall placement
            List<Entity> walls = new ArrayList<>();
            xStart = screenWidth / 3; yStart = screenHeight * 4 / 6; rowTotal = 0;
            while (walls.size() < level.getWallTotal()) {
                if (xStart >= screenWidth / 3 * 2) {
                    offset = level.getWallWidth() * (level.getWallTotal() % 2 == 0 ? 1 : (yStart % 2));
                    xStart = screenWidth / 3 + offset;
                    yStart++;

                    if (rowTotal == 0) rowTotal = walls.size();
                    if (level.getWallTotal() - walls.size() < rowTotal) {
                        int leftovers = level.getWallTotal() - walls.size();
                        offset = level.getWallTotal() % 2 == 0 ? 0 : 1 - (yStart % 2);
                        rowTotal -= offset;
                        xStart += level.getWallWidth() * (rowTotal - leftovers);
                    }
                }

                walls.add(factory.getWall(xStart, yStart, level.getWallLives(), level.getWallWidth()));
                xStart += level.getWallWidth() * 3;
            }
            entities.addAll(walls);

            levelEntities.add(entities);
        }
    }

    /**
     * Method that will delay the system for a specified amount of time.
     * {@link}  &nbsp;Sleep <a href="https://stackoverflow.com/a/180191">Sleep</a>
     * @param   millis an integer representing the time to delay in millis
     */
    private void sleep(long millis) {
        if (millis <= 0) return;
        try {   // fixed delay
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Method that will check what type of entity collided with the main entity after a collision.
     * {@link}  &nbsp;Sleep <a href="https://stackoverflow.com/a/180191">Sleep</a>
     * @param   comp a LifeComp that represents the main entity
     * @param   entity a LifeComp that represents the collided entity
     * @return  a boolean that shows if the collision is valid
     */
    private boolean checkHits(LifeComp comp, LifeComp entity) {
        boolean bullet1, bullet2;
        /*--------------------------------------------------------------------------------------------------------*/
        //region Bullets
        // Players can shoot enemies, bosses and bonuses
        bullet1 =  comp.getType() == EntityType.P_BULLET
                && (entity.getType() == EntityType.ENEMY || entity.getType() == EntityType.E_BULLET || entity.getType() == EntityType.BOSS  || entity.getType() == EntityType.B_ROCKET
                || entity.getType() == EntityType.BONUS_LIFE  || entity.getType() == EntityType.BONUS_SCORE || entity.getType() == EntityType.BONUS_ROCKET)

                || entity.getType() == EntityType.P_BULLET
                && (comp.getType() == EntityType.ENEMY || comp.getType() == EntityType.E_BULLET || comp.getType() == EntityType.BOSS || comp.getType() == EntityType.B_ROCKET
                || comp.getType() == EntityType.BONUS_LIFE  || comp.getType() == EntityType.BONUS_SCORE || comp.getType() == EntityType.BONUS_ROCKET);

        // Enemies can shoot players and walls
        bullet2 =  comp.getType() == EntityType.E_BULLET
                && (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.P_BULLET || entity.getType() == EntityType.P_ROCKET || entity.getType() == EntityType.WALL)

                || entity.getType() == EntityType.E_BULLET
                && (comp.getType() == EntityType.PLAYER || comp.getType() == EntityType.P_BULLET || comp.getType() == EntityType.P_ROCKET || comp.getType() == EntityType.WALL);

        if (bullet1 || bullet2) {
            comp.setHit(true);
            entity.setHit(true);
            return true;
        }
        //endregion
        /*--------------------------------------------------------------------------------------------------------*/
        //region Rockets
        bullet1 =  comp.getType() == EntityType.P_ROCKET
                && (entity.getType() == EntityType.ENEMY || entity.getType() == EntityType.E_BULLET || entity.getType() == EntityType.BOSS  || entity.getType() == EntityType.B_ROCKET
                || entity.getType() == EntityType.BONUS_LIFE  || entity.getType() == EntityType.BONUS_SCORE || entity.getType() == EntityType.BONUS_ROCKET)

                || entity.getType() == EntityType.P_ROCKET
                && (comp.getType() == EntityType.ENEMY || comp.getType() == EntityType.E_BULLET || comp.getType() == EntityType.BOSS || comp.getType() == EntityType.B_ROCKET
                || comp.getType() == EntityType.BONUS_LIFE  || comp.getType() == EntityType.BONUS_SCORE || comp.getType() == EntityType.BONUS_ROCKET);

        // Enemies can shoot players and walls
        bullet2 =  comp.getType() == EntityType.B_ROCKET
                && (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.P_BULLET || entity.getType() == EntityType.P_ROCKET || entity.getType() == EntityType.WALL)

                || entity.getType() == EntityType.B_ROCKET
                && (comp.getType() == EntityType.PLAYER || comp.getType() == EntityType.P_BULLET || comp.getType() == EntityType.P_ROCKET || comp.getType() == EntityType.WALL);

        if (bullet1 || bullet2) {
            comp.setBigHit(true);
            entity.setBigHit(true);
            return true;
        }
        //endregion
        /*--------------------------------------------------------------------------------------------------------*/
        //region Enemy collision
        // Enemies blow up with direct contact to players and walls
        if (comp.getType() == EntityType.ENEMY && (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.WALL)) {
            comp.setDead(true);
            entity.setBigHit(true);
            return true;
        }
        //endregion
        /*--------------------------------------------------------------------------------------------------------*/
        //region Boss collision
        // Bosses destroy players and walls with direct contact
        if (comp.getType() == EntityType.BOSS && (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.WALL)) {
            comp.setDead(true);
            entity.setDead(true);
            return true;
        }
        //endregion
        /*--------------------------------------------------------------------------------------------------------*/
        //region Wall hit
        // Wall hits destroy anything except players
        if (entity.getType() == EntityType.WALL && (comp.getType() == EntityType.P_BULLET || comp.getType() == EntityType.P_ROCKET
                ||  comp.getType() == EntityType.BONUS_LIFE  || comp.getType() == EntityType.BONUS_SCORE || comp.getType() == EntityType.BONUS_ROCKET)) {
            comp.setDead(true);
            return true;
        }
        //endregion
        /*--------------------------------------------------------------------------------------------------------*/
        //region Pass Through
        // Player type entities can't shoot players, player bullets or player rockets
        bullet1 =  (comp.getType() == EntityType.PLAYER || comp.getType() == EntityType.P_BULLET || comp.getType() == EntityType.P_ROCKET)
                && (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.P_BULLET || entity.getType() == EntityType.P_ROCKET);

        // Enemy type entities can't shoot enemies, bosses, bonuses, enemy bullets or boss rockets
        bullet2 =  (comp.getType() == EntityType.ENEMY || comp.getType() == EntityType.E_BULLET || comp.getType() == EntityType.BOSS  || comp.getType() == EntityType.B_ROCKET
                || comp.getType() == EntityType.BONUS_LIFE  || comp.getType() == EntityType.BONUS_SCORE || comp.getType() == EntityType.BONUS_ROCKET)
                && (entity.getType() == EntityType.ENEMY || entity.getType() == EntityType.E_BULLET || entity.getType() == EntityType.BOSS  || entity.getType() == EntityType.B_ROCKET
                || entity.getType() == EntityType.BONUS_LIFE  || entity.getType() == EntityType.BONUS_SCORE || entity.getType() == EntityType.BONUS_ROCKET);
        return bullet1 || bullet2;
        //endregion
    }

    /** Method that will start and run the game. */
    public void Start() {
        // Systems
        MovementSystem mover = new MovementSystem();
        ICollisionDetector colDet = factory.getCollisionDetector(screenWidth, screenHeight);
        LifeSystem life = new LifeSystem();
        IVisualiseSystem visualiser = factory.getVisualiseSystem();

        // Variables
        GameState state = GameState.START;
        int levelIndex = 0;
        List<Entity> entities = new ArrayList<>();
        long startTime = System.nanoTime(), endTime, duration;
        int score = 0;

        long bonusTimer = 0;            // Variable to keep bonus time in check (Time management)
        BonusType bonus = BonusType.NONE;

        byte randomValue;               // Random for entity creation (Entity creation)
        boolean enemyAdvance = false;   // Boolean for enemy advancement (Entity movement)
        boolean bossAdvance = false;    // Boolean for boss advancement (Entity movement)

        musicPlayer.playContinues(MusicType.BACKGROUND);

        while (state != GameState.END) {
            sleep(50);
            switch (state) {
                /*----------------------------------------------------------------------------------------------------*/
                case START      -> {
                    // Input handling
                    if (input.inputAvailable() && input.getInput() == InputType.ENTER) state = GameState.NEXT; // Start
                    // Visualisation
                    visualiser.visualise("Space Invaders", "Press Enter to start");
                }
                /*----------------------------------------------------------------------------------------------------*/
                case GAME_WON   -> {
                    // Input handling
                    if (input.inputAvailable() && input.getInput() == InputType.ENTER) state = GameState.END; // Stop
                    // Visualisation
                    visualiser.visualise("Victory!!!", "Press Enter to Leave",
                            "Score: " + score + " - Health: " + player.lifeComp().getLives()
                    );
                }
                /*----------------------------------------------------------------------------------------------------*/
                case GAME_OVER  -> {
                    // Input handling
                    if (input.inputAvailable() && input.getInput() == InputType.ENTER) state = GameState.END; // Stop
                    // Visualisation
                    visualiser.visualise("Game Over!!!", "Press Enter to Leave", "Score: " + score);
                }
                /*----------------------------------------------------------------------------------------------------*/
                case NEXT       -> {
                    // Start new level or end game
                    if (levelIndex == levelEntities.size()) state = GameState.GAME_WON;
                    else {
                        state = GameState.RUN;
                        // Get new entities
                        entities = levelEntities.get(levelIndex);
                        levelIndex++;
                    }
                }
                /*----------------------------------------------------------------------------------------------------*/
                case PAUSE      -> {
                    // Input handling
                    if (input.inputAvailable()) {
                        switch (input.getInput()) {
                            case ESCAPE -> state = GameState.END; // Stop
                            case ENTER  -> state = GameState.RUN; // Continue
                        }
                    }
                    // Visualisation
                    visualiser.visualise("Paused",
                            "Press Enter to continue",
                            "Press Esc to leave",
                            "Score: " + score + " - Health: " + player.lifeComp().getLives()
                    );
                }
                /*----------------------------------------------------------------------------------------------------*/
                case RUN        -> {
                    /*------------------------------------------------------------------------------------------------*/
                    //region Input handling
                    if (input.inputAvailable()) {
                        switch (input.getInput()) {
                            // Game state
                            case ESCAPE -> state = GameState.PAUSE;
                            // Shooting
                            case SPACE  -> {
                                musicPlayer.play(MusicType.SHOOT);
                                if (bonus == BonusType.USE_ROCKET)
                                    entities.add(factory.getPRocket(player.movementComp().getX(), player.movementComp().getY()));
                                else    entities.add(factory.getPBullet(player.movementComp().getX(), player.movementComp().getY()));
                            }
                            // Directions
                            case LEFT   -> { player.movementComp().setVx(-1); player.movementComp().setVy(0); }
                            case RIGHT  -> { player.movementComp().setVx(1); player.movementComp().setVy(0); }
                            case DOWN   -> { player.movementComp().setVx(0); player.movementComp().setVy(1); }
                            case UP     -> { player.movementComp().setVx(0); player.movementComp().setVy(-1); }
                        }
                    }
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region List initialization
                    // Move
                    List<MovementComp> moveList = entities.stream()
                            .map(Entity::movementComp)
                            .collect(Collectors.toList());
                    // Life
                    List<LifeComp> lifeList = entities.stream()
                            .map(Entity::lifeComp)
                            .collect(Collectors.toList());
                    // Visualise
                    List<AVisualComp> visualList = entities.stream()
                            .map(Entity::visualComp)
                            .collect(Collectors.toList());
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Entity creation
                    randomValue = (byte) (Math.random() * 255);
                    for (MovementComp moveComp: moveList) {
                        // Add new entities (enemy shoots at random)
                        if (moveComp.getType() == EntityType.ENEMY && randomValue % 37 == 8)
                            entities.add(factory.getEBullet(moveComp.getX(), moveComp.getY()));
                        else if (moveComp.getType() == EntityType.BOSS && randomValue % 87 == 3)
                            entities.add(factory.getBRocket(moveComp.getX() + moveComp.getWidth() / 2 + 1, moveComp.getY()));
                    }
                    // Add bonuses
                    if (randomValue % 61 == 23) {
                        entities.add(factory.getBonusLives((int) (Math.random() * screenWidth), 0));
                        musicPlayer.play(MusicType.BONUS_DROP);
                    }
                    else if (randomValue % 61 == 29) {
                        entities.add(factory.getBonusScore((int) (Math.random() * screenWidth), 0));
                        musicPlayer.play(MusicType.BONUS_DROP);
                    }
                    else if (randomValue % 61 == 31) {
                        entities.add(factory.getBonusRocket((int) (Math.random() * screenWidth), 0));
                        musicPlayer.play(MusicType.BONUS_DROP);
                    }
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Entity collision detection
                    for (MovementComp moveComp: moveList) {
                        // Collision detection (walls)
                        if (colDet.checkVerticalWallCollisions(moveComp)) {
                            // All entities should stop if they hit the walls
                            moveComp.setVx(0);
                            // Enemy entities should advance if one hits the walls
                            if (moveComp.getType() == EntityType.ENEMY) {
                                enemyAdvance = true;
                                moveList.forEach(enemy -> { if (enemy.getType() == EntityType.ENEMY) enemy.setVx(0); });
                            } else if (moveComp.getType() == EntityType.BOSS) {
                                bossAdvance = true;
                                moveList.forEach(enemy -> { if (enemy.getType() == EntityType.BOSS) enemy.setVx(0); });
                            }
                        }
                        if (colDet.checkHorizontalWallCollisions(moveComp)) {
                            moveComp.setVy(0);
                            // Set entity ass dead if they hit the bottom or top walls
                            entities.stream().filter(entity -> entity.movementComp().equals(moveComp))
                                    .findFirst()
                                    .ifPresent(entity -> entity.lifeComp().setDead(true));
                        }

                        // Collision detection (entities)
                        final MovementComp collider = colDet.checkCollisions(moveComp, moveList);
                        if (collider != null) {
                            Optional<Entity> moverEnt, colliderEnt;
                            // Search for mover
                            moverEnt = entities.stream().filter(entity -> entity.movementComp().equals(moveComp)).findFirst();
                            // Search for collider
                            colliderEnt = entities.stream().filter(entity -> entity.movementComp().equals(collider)).findFirst();
                            // Use Collider
                            if (moverEnt.isPresent() && colliderEnt.isPresent())
                                if (!checkHits(moverEnt.get().lifeComp(), colliderEnt.get().lifeComp())) {
                                    moveComp.setVx(0);
                                    moveComp.setVy(0);
                                }
                        }

                        // Collision sounds
                        for (LifeComp lifeComp: lifeList) {
                            if (lifeComp.isHit() || lifeComp.isBigHit()) {
                                if (lifeComp.getType() == EntityType.ENEMY || lifeComp.getType() == EntityType.BOSS)
                                    musicPlayer.play(MusicType.ENEMY_HIT);
                                else if (lifeComp.getType() == EntityType.PLAYER)
                                    musicPlayer.play(MusicType.PLAYER_HIT);
                            }
                        }
                    }
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Entity movement
                    mover.update(moveList);
                    for (MovementComp moveComp: moveList) {
                        // Enemy movement
                        if (moveComp.getType() == EntityType.ENEMY) {
                            if (enemyAdvance) {
                                assert moveComp instanceof SmartMoveComp;
                                SmartMoveComp SComp = (SmartMoveComp) moveComp;
                                SComp.setVx(0); SComp.setVy(1);
                                SComp.setDirection(-SComp.getDirection());
                            }
                        }

                        // Boss movement
                        if (moveComp.getType() == EntityType.BOSS) {
                            if (bossAdvance) {
                                assert moveComp instanceof SmartMoveComp;
                                SmartMoveComp SComp = (SmartMoveComp) moveComp;
                                SComp.setVx(0); SComp.setVy(1);
                                SComp.setDirection(-SComp.getDirection());
                            }
                        }

                        // Bullet movement
                        else if (moveComp.getType() == EntityType.P_BULLET) moveComp.setVy(-1);
                        else if (moveComp.getType() == EntityType.P_ROCKET) moveComp.setVy(-1);
                        else if (moveComp.getType() == EntityType.E_BULLET) moveComp.setVy(1);
                        else if (moveComp.getType() == EntityType.B_ROCKET) moveComp.setVy(1);

                            // Bonus movement
                        else if (moveComp.getType() == EntityType.BONUS_LIFE)   moveComp.setVy(1);
                        else if (moveComp.getType() == EntityType.BONUS_SCORE)  moveComp.setVy(1);
                        else if (moveComp.getType() == EntityType.BONUS_ROCKET) moveComp.setVy(1);

                        // Enemy & Boss movement
                        if (moveComp instanceof SmartMoveComp SComp) {
                            if ((SComp.getCounter() + 1) % 4 == 0) {
                                moveComp.setVx(SComp.getDirection());
                            }
                            SComp.setCounter((byte) (SComp.getCounter() + 1));
                        }
                    }
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Entity filtering (based on lives)
                    life.checkLives(lifeList);
                    for (LifeComp lifeComp: lifeList) {
                        // Filter death entities
                        if (lifeComp.isDead()) {
                            // Change score
                            if (lifeComp.getType() == EntityType.ENEMY)     score += (bonus == BonusType.BOOST_SCORE ? 3 : 1);
                            else if (lifeComp.getType() == EntityType.BOSS) score += (bonus == BonusType.BOOST_SCORE ? 7 : 4);
                                // Only apply bonuses on hits
                            else if (lifeComp.isHit() || lifeComp.isBigHit()) {
                                if (lifeComp.getType() == EntityType.BONUS_LIFE)
                                    player.lifeComp().setLives(player.lifeComp().getLives() + 3);
                                else if (lifeComp.getType() == EntityType.BONUS_SCORE) {
                                    bonusTimer = System.nanoTime();
                                    bonus = BonusType.BOOST_SCORE;
                                } else if (lifeComp.getType() == EntityType.BONUS_ROCKET) {
                                    bonusTimer = System.nanoTime();
                                    bonus = BonusType.USE_ROCKET;
                                }
                            }
                            // Remove dead entity
                            entities.stream().filter(entity -> entity.lifeComp().equals(lifeComp))
                                    .findFirst()
                                    .ifPresent(entities::remove);
                        }
                    }
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Visualisation
                    // Visualise
                    visualiser.visualise(visualList, score, player.lifeComp().getLives());
                    //endregion
                    /*------------------------------------------------------------------------------------------------*/
                    //region Reset
                    // Remove bonus effect after 5 seconds
                    if ((startTime - bonusTimer) / 1000000 >= 5000) bonus = BonusType.NONE;
                    // Check for dead entities
                    life.resetHits(lifeList);
                    if (player.lifeComp().getLives() <= 0) state = GameState.GAME_OVER;
                    // If all enemies are gone --> continue
                    if (entities.stream().noneMatch(entity -> entity.movementComp().getType() == EntityType.ENEMY || entity.movementComp().getType() == EntityType.BOSS))
                        state = GameState.NEXT;
                    enemyAdvance = false; bossAdvance = false;
                    //endregion
                }
            }
            /*--------------------------------------------------------------------------------------------------------*/
            //region Time management
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            startTime = endTime;
            sleep(50 - duration / 1000000);
            //endregion
        }
        musicPlayer.stop();
        visualiser.end();
    }
}
