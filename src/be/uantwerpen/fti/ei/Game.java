package be.uantwerpen.fti.ei;

import be.uantwerpen.fti.ei.components.*;
import be.uantwerpen.fti.ei.entities.Entity;
import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.input.Inputs;
import be.uantwerpen.fti.ei.interfaces.IFactory;
import be.uantwerpen.fti.ei.systems.*;

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

        Entity player = factory.getPlayer(240, 400);
        List<Entity> entities = new ArrayList<>();

        int randX, randY;
        while (entities.size() < 1) {
            randX = (int)(Math.random() * (512 - 16 - 64));
            randY = (int)(Math.random() * 256) + 64;
            //entities.add(factory.getSmallEnemy(randX, randY));

            randX = (int)(Math.random() * (512 - 16 - 64));
            randY = (int)(Math.random() * 256) + 64;
            //entities.add(factory.getEnemy(randX, randY));
            entities.add(factory.getEnemy(128, 128));

            randX = (int)(Math.random() * (512 - 16 - 64));
            randY = (int)(Math.random() * 256) + 64;
            //entities.add(factory.getBigEnemy(randX, randY));
        }

        MovementSystem mover = new MovementSystem();
        CollisionDetector colDet = new CollisionDetector(screenWidth, screenHeight);
        LifeSystem life = new LifeSystem();
        IVisualiseSystem visualiser = factory.getVisualiseSystem();

        startTime = System.nanoTime();
        while (isRunning) {
            sleep(50);
            //
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
                if (direction == Inputs.ESCAPE)
                    isRunning = false;
                else if (direction == Inputs.SPACE) {
                    int x = player.getMovementComp().getX();
                    x += player.getColDetComp().getSize() / 2;
                    int y = player.getMovementComp().getY();
                    entities.add(factory.getPBullet(x, y));
                } else
                    switch (direction) {
                        case LEFT  -> { player.getMovementComp().setVx(-player.getColDetComp().getMovement()); player.getMovementComp().setVy(0); }
                        case RIGHT -> { player.getMovementComp().setVx(player.getColDetComp().getMovement()); player.getMovementComp().setVy(0); }
                        case DOWN  -> { player.getMovementComp().setVx(0); player.getMovementComp().setVy(player.getColDetComp().getMovement()); }
                        case UP    -> { player.getMovementComp().setVx(0); player.getMovementComp().setVy(-player.getColDetComp().getMovement()); }
                    }
            }

            for (Entity entity: entities)
                EditEntityBehaviour(entity.getMovementComp(), entity.getColDetComp().getMovement());

            // Collision detection
            List<ColDetComp> colDetList = entities.stream()
                    .map(Entity::getColDetComp)
                    .collect(Collectors.toList());
            colDetList.add(player.getColDetComp());
            for (ColDetComp colDetComp: colDetList) {
                colDet.checkWalls(colDetComp);
                colDet.checkEntities(colDetComp, colDetList);
            }

            // Life
            List<LifeComp> lifeList = entities.stream()
                    .map(Entity::getLifeComp)
                    .collect(Collectors.toList());
            lifeList.add(player.getLifeComp());
            life.update(lifeList);

            // Filter death entities
            entities = entities.stream()
                    .filter(entity -> lifeList.stream()
                            .anyMatch(lifecomp -> lifecomp.equals(entity.getLifeComp())))
                    .collect(Collectors.toList());

            // Move
            List<MovementComp> moveList = entities.stream()
                    .map(Entity::getMovementComp)
                    .collect(Collectors.toList());
            moveList.add(player.getMovementComp());
            mover.update(moveList);

            // Visualize
            List<AVisualComp> visualList = entities.stream()
                    .map(Entity::getVisualComp)
                    .collect(Collectors.toList());
            visualList.add(player.getVisualComp());
            visualiser.visualise(visualList);

            endTime = System.nanoTime();
            duration = (endTime - startTime);
            startTime = endTime;
            // sleep(50 - duration / 1000000);
            sleep(50 - duration / 1000000);
        }
        visualiser.end();
    }

    public void EditEntityBehaviour(MovementComp moveComp, int standardMovement){
        if (moveComp instanceof SmartMoveComp SComp) {
            int counter = SComp.getCounter();

            if (counter == 128) {
                counter = 0;
            } else if ((counter + 1) % 32 == 0) {
                moveComp.setVy(standardMovement);
                SComp.setDirection(-SComp.getDirection());
            } else if ((counter + 1) % 4 == 0) {
                moveComp.setVx(standardMovement * SComp.getDirection());
            }

            SComp.setCounter(counter + 1);
        } else {
            moveComp.setVy(standardMovement);
        }
    }
}
