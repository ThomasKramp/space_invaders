package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.input.Inputs;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;
import be.uantwerpen.fti.ei.systems.MovementSystem;

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

        List<Entity> entities = new ArrayList<>();
        entities.add(factory.getPlayer(25, 25));

        MovementSystem mover = new MovementSystem();
        IVisualiseSystem visualiser = factory.getVisualiseSystem();

        startTime = System.nanoTime();
        while (isRunning) {
            sleep(50);
            //
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
                if (direction == Inputs.SPACE)
                    isPaused = ! isPaused;
                else
                    for (Entity entity: entities) {
                        switch (direction) {
                            case LEFT  -> { entity.movementComp.setVx(16 * -1); entity.movementComp.setVy(0); }
                            case RIGHT -> { entity.movementComp.setVx(16 * 1); entity.movementComp.setVy(0); }
                            case DOWN  -> { entity.movementComp.setVx(0); entity.movementComp.setVy(16 * 1); }
                            case UP    -> { entity.movementComp.setVx(0); entity.movementComp.setVy(16 * -1); }
                        }
                    }

            }

            // Move
            List<AMovementComp> moveList = entities.stream()
                    .map(Entity::getMovementComp)
                    .collect(Collectors.toList());
            mover.update(moveList);

            // Visualize
            List<AVisualComp> visualList = entities.stream()
                    .map(Entity::getVisualComp)
                    .collect(Collectors.toList());
            visualiser.visualise(visualList);

            endTime = System.nanoTime();
            duration = (endTime - startTime);
            startTime = endTime;
            sleep(50 - duration / 1000000);
        }
    }
}
