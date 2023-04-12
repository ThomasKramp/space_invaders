package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.components.MovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.input.Inputs;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;
import be.uantwerpen.fti.ei.systems.MovementSystem;
import be.uantwerpen.fti.ei.utilities.CollisionDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private IFactory factory;
    private AInput input;
    private boolean isRunning = true, isPaused = false;
    private int score = 0;
    private int screenWidth, screenHeight;
    long startTime, endTime, duration;

    public Game(IFactory fact, int width, int height) {
        this.factory = fact;
        this.input = fact.getInput();
        screenWidth = width;
        screenHeight = height;
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

        Entity player = factory.getPlayer(25, 25);

        List<Entity> enemies = new ArrayList<>();
        enemies.add(factory.getEnemy(128, 128));

        MovementSystem mover = new MovementSystem();
        IVisualiseSystem visualiser = factory.getVisualiseSystem();
        CollisionDetector colDet = new CollisionDetector(screenWidth, screenHeight);

        startTime = System.nanoTime();
        while (isRunning) {
            sleep(50);
            //
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
                if (direction == Inputs.SPACE)
                    isPaused = ! isPaused;
                else
                    switch (direction) {
                        case LEFT  -> { player.movementComp.setVx(-1 * player.movementComp.getMovement()); player.movementComp.setVy(0); }
                        case RIGHT -> { player.movementComp.setVx(1 * player.movementComp.getMovement()); player.movementComp.setVy(0); }
                        case DOWN  -> { player.movementComp.setVx(0); player.movementComp.setVy(1 * player.movementComp.getMovement()); }
                        case UP    -> { player.movementComp.setVx(0); player.movementComp.setVy(-1 * player.movementComp.getMovement()); }
                    }

            }

            // Collision detection
            List<MovementComp> colDetList = enemies.stream()
                    .map(Entity::getMovementComp)
                    .collect(Collectors.toList());
            colDetList.add(player.movementComp);
            for (MovementComp moveComp: colDetList) {
                colDet.checkWalls(moveComp);
            }

            // Move
            List<MovementComp> moveList = enemies.stream()
                    .map(Entity::getMovementComp)
                    .collect(Collectors.toList());
            moveList.add(player.movementComp);
            mover.update(moveList);

            // Visualize
            List<AVisualComp> visualList = enemies.stream()
                    .map(Entity::getVisualComp)
                    .collect(Collectors.toList());
            visualList.add(player.visualComp);
            visualiser.visualise(visualList);

            endTime = System.nanoTime();
            duration = (endTime - startTime);
            startTime = endTime;
            sleep(50 - duration / 1000000);
        }
    }
}
