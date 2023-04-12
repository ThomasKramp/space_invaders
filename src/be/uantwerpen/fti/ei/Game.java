package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.components.AMovementComp;
import be.uantwerpen.fti.ei.components.AVisualComp;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.IVisualiseSystem;
import be.uantwerpen.fti.ei.systems.MovementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private IFactory factory;
    private boolean isRunning = true, isPaused = false;
    private int score = 0;
    private int screenWidth, screenHeight;
    long startTime, endTime, duration;

    public Game(IFactory fact, int width, int height) {
        this.factory = fact;
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
