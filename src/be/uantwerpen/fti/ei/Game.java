package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.entities.EntityType;
import be.uantwerpen.fti.ei.interfaces.ICollisionDetector;
import be.uantwerpen.fti.ei.interfaces.IHotBar;
import be.uantwerpen.fti.ei.components.*;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.input.Inputs;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.*;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final IFactory factory;
    private final AInput input;
    private boolean isRunning = true, isPaused = false;
    private int score = 0;
    private final int screenWidth, screenHeight;
    long startTime, endTime, duration;

    public Game(IFactory fact, int[] screenDimen) {
        this.factory = fact;
        this.factory.setScreenDimensions(screenDimen);
        this.input = this.factory.getInput();

        this.screenWidth = screenDimen[0];
        this.screenHeight = screenDimen[1];
    }

    // https://stackoverflow.com/a/180191
    private void sleep(long millis) {
        if (millis <= 0) return;
        try {   // fixed delay
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void Start() {
        List<Entity> entities = new ArrayList<>();

        // Player
        Entity player = factory.getPlayer(screenWidth / 2 - 1, screenHeight * 5 / 6);
        entities.add(player);

        // Enemies
        int xStart = screenWidth / 3 - 1, yStart = screenHeight / 3;
        while (entities.size() < 4) {
            entities.add(factory.getEnemy(xStart, yStart));
            xStart += 2;

            if (xStart >= screenWidth / 3 * 2) {
                xStart = screenWidth / 3 - 1;
                yStart++;
            }
        }

        // Boss
        entities.add(factory.getBoss(screenWidth / 2 - 2, 2));

        // Walls
        entities.add(factory.getWall(screenWidth / 3 - 1, screenHeight * 4 / 6));
        entities.add(factory.getWall(screenWidth * 2 / 3 - 1, screenHeight * 4 / 6));

        MovementSystem mover = new MovementSystem();
        ICollisionDetector colDet = factory.getCollisionDetector(screenWidth, screenHeight);
        LifeSystem life = new LifeSystem();
        IVisualiseSystem visualiser = factory.getVisualiseSystem();
        IHotBar hotBarHandler = factory.getHotBarHandler();

        // Variables
        long bonusTimer = 0;            // Variable to keep bonus time in check (Time management)
        boolean boostScore = false;     // Boolean for score increment adjustment (Entity filtering)
        boolean useRockets = false;     // Boolean for activation of rockets (Input handling)

        byte randomValue;               // Random for entity creation (Entity creation)
        boolean enemyAdvance = false;   // Boolean for enemy advancement (Entity movement)
        boolean bossAdvance = false;    // Boolean for boss advancement (Entity movement)

        startTime = System.nanoTime();
        while (isRunning) {
            sleep(50);
            /*--------------------------------------------------------------------------------------------------------*/
            //region Input handling
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
                if (direction == Inputs.ESCAPE)
                    isRunning = false;
                else if (direction == Inputs.SPACE) {
                    if (useRockets) entities.add(factory.getPRocket(player.getMovementComp().getX(), player.getMovementComp().getY()));
                    else            entities.add(factory.getPBullet(player.getMovementComp().getX(), player.getMovementComp().getY()));
                } else
                    switch (direction) {
                        case LEFT  -> { player.getMovementComp().setVx(-1); player.getMovementComp().setVy(0); }
                        case RIGHT -> { player.getMovementComp().setVx(1); player.getMovementComp().setVy(0); }
                        case DOWN  -> { player.getMovementComp().setVx(0); player.getMovementComp().setVy(1); }
                        case UP    -> { player.getMovementComp().setVx(0); player.getMovementComp().setVy(-1); }
                    }
            }
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
            //region List initialization
            // Move
            List<MovementComp> moveList = entities.stream()
                    .map(Entity::getMovementComp)
                    .collect(Collectors.toList());
            // Life
            List<LifeComp> lifeList = entities.stream()
                    .map(Entity::getLifeComp)
                    .collect(Collectors.toList());
            // Visualise
            List<AVisualComp> visualList = entities.stream()
                    .map(Entity::getVisualComp)
                    .collect(Collectors.toList());
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
            //region Entity creation
            randomValue = (byte) (Math.random() * 255);
            for (MovementComp moveComp: moveList) {
                // Add new entities (enemy shoots at random)
                if (moveComp.getType() == EntityType.ENEMY) {
                    if (randomValue % 37 == 8) entities.add(factory.getEBullet(moveComp.getX(), moveComp.getY()));
                } else if (moveComp.getType() == EntityType.BOSS) {
                    if (randomValue % 87 == 3) entities.add(factory.getBRocket(moveComp.getX(), moveComp.getY()));
                }
            }
            // Add bonuses
            if (randomValue % 61 == 23)         entities.add(factory.getBonusLives((int) (Math.random() * screenWidth), 0));
            else if (randomValue % 61 == 29)    entities.add(factory.getBonusScore((int) (Math.random() * screenWidth), 0));
            else if (randomValue % 61 == 31)    entities.add(factory.getBonusRocket((int) (Math.random() * screenWidth), 0));
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
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
                    entities.stream().filter(entity -> entity.getMovementComp().equals(moveComp))
                            .findFirst()
                            .ifPresent(entity -> entity.getLifeComp().setDead(true));
                }

                // Collision detection (entities)
                final MovementComp collider = colDet.checkCollisions(moveComp, moveList);
                if (collider != null) {
                    Optional<Entity> moverEnt, colliderEnt;
                    // Search for mover
                    moverEnt = entities.stream().filter(entity -> entity.getMovementComp().equals(moveComp)).findFirst();
                    // Search for collider
                    colliderEnt = entities.stream().filter(entity -> entity.getMovementComp().equals(collider)).findFirst();
                    // Use Collider
                    if (moverEnt.isPresent() && colliderEnt.isPresent())
                        if (!checkHits(moverEnt.get().getLifeComp(), colliderEnt.get().getLifeComp())) {
                            moveComp.setVx(0);
                            moveComp.setVy(0);
                        }
                }
            }
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
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
            /*--------------------------------------------------------------------------------------------------------*/
            //region Entity filtering (based on lives)
            life.checkLives(lifeList);
            for (LifeComp lifeComp: lifeList) {
                // Filter death entities
                if (lifeComp.isDead()) {
                    // Change score
                    if (lifeComp.getType() == EntityType.ENEMY)     score += (boostScore ? 3 : 1);
                    else if (lifeComp.getType() == EntityType.BOSS) score += (boostScore ? 7 : 4);
                    // Only apply bonuses on hits
                    else if (lifeComp.isHit() || lifeComp.isBigHit()) {
                        if (lifeComp.getType() == EntityType.BONUS_LIFE)
                            player.getLifeComp().setLives(player.getLifeComp().getLives() + 3);
                        else if (lifeComp.getType() == EntityType.BONUS_SCORE) {
                            bonusTimer = System.nanoTime();
                            boostScore = true; useRockets = false;
                        } else if (lifeComp.getType() == EntityType.BONUS_ROCKET) {
                            bonusTimer = System.nanoTime();
                            boostScore = false; useRockets = true;
                        }
                    }
                    // Remove dead entity
                    entities.stream().filter(entity -> entity.getLifeComp().equals(lifeComp))
                            .findFirst()
                            .ifPresent(entities::remove);
                }
            }
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
            //region Visualisation
            // Update hot-bar
            hotBarHandler.updateScore(score);
            hotBarHandler.updateHealth(player.getLifeComp().getLives());

            // Visualise
            visualiser.visualise(visualList);
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
            //region Reset
            life.resetHits(lifeList);
            // if (player.getLifeComp().getLives() <= 0) isRunning = false;
            enemyAdvance = false; bossAdvance = false;
            //endregion
            /*--------------------------------------------------------------------------------------------------------*/
            //region Time management
            endTime = System.nanoTime();
            // Reset bonuses
            if ((endTime - bonusTimer) / 1000000 >= 5000) {
                // Remove bonuses after 5 seconds
                boostScore = false; useRockets = false;
            }
            // Delay
            duration = (endTime - startTime);
            startTime = endTime;
            sleep(50 - duration / 1000000);
            //endregion
        }
        visualiser.end();
    }

    public boolean checkHits(LifeComp comp, LifeComp entity) {
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
}
