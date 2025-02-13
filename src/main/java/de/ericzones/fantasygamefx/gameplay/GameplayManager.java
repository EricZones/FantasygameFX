// Created by Eric B. 07.12.2021 21:00
package de.ericzones.fantasygamefx.gameplay;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.creatures.CreatureManager;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericzones.fantasygamefx.collectives.world.GameMap;
import de.ericzones.fantasygamefx.collectives.world.Location;
import de.ericzones.fantasygamefx.graphics.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameplayManager {

    private final CreatureManager creatureManager;
    private final GameMap gameMap;
    private final Timer timer;
    private TimerTask timerTask;

    private boolean isFreezed;
    private boolean manuallyFreezed;

    public GameplayManager() {
        this.creatureManager = new CreatureManager();
        this.gameMap = new GameMap(9, 9);
        this.timer = new Timer();
        this.isFreezed = true;
        this.manuallyFreezed = true;
    }

    // Resetting the whole game visualization and mechanics
    public void restartGame() {
        creatureManager.removeAllCreatures();
        WindowManager windowManager = Fantasygame.getWindowManager();
        windowManager.getInfoField().hide();
        windowManager.getGameField().hide();
        windowManager.getStartField().showFirstField();
    }

    // Initiating a new game (new round)
    public void initiateGame(HeroType heroType, String nickname) {
        // Creating a hero with the creature manager
        Hero hero = creatureManager.summonHero(heroType, new Location(5, 5), nickname);
        // Unfreezing the game
        this.isFreezed = false; this.manuallyFreezed = false;
        // Starting the visualized game field
        Fantasygame.getWindowManager().startVisualizedGame(hero);
        // Stopping the last timer if running and initiating a new one spawning monsters randomly
        if(timerTask != null) timerTask.cancel();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isFreezed()) return;
                int number = new Random().nextInt(10)+1;
                if(number == 1)
                    createStrongMonster();
                else if(number == 2)
                    createDefenseMonster();
                else if(number <= 6)
                    createMonster();
            }
        };
        this.timer.schedule(timerTask, 5 * 1000, 5 * 1000);
    }

    public void freezeGame(boolean manually) {
        this.isFreezed = true;
        this.manuallyFreezed = manually;
        Fantasygame.getWindowManager().getPauseField().show();
    }

    public void resumeGame() {
        this.isFreezed = false;
        Fantasygame.getWindowManager().getPauseField().hide();
    }

    public void closeGame() {
        System.out.println(" ");
        System.out.println(" [i] The game is being stopped...");
        System.exit(0);
    }

    private void createMonster() {
        if(creatureManager.isCreatureLimitReached()) return;
        creatureManager.summonMonster(gameMap.getRandomLocation());
    }

    private void createDefenseMonster() {
        if(creatureManager.isCreatureLimitReached()) return;
        creatureManager.summonMonster(gameMap.getRandomLocation(), 30, 1);
    }

    private void createStrongMonster() {
        if(creatureManager.isCreatureLimitReached()) return;
        creatureManager.summonMonster(gameMap.getRandomLocation(), 10, 4);
    }

    public CreatureManager getCreatureManager() {
        return creatureManager;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public boolean isFreezed() {
        if(Fantasygame.getWindowManager().getStartField().isVisible() || Fantasygame.getWindowManager().getEndingField().isVisible()) return true;
        return isFreezed;
    }

    public boolean isManuallyFreezed() {
        return manuallyFreezed;
    }

    public Timer getGameTimer() {
        return this.timer;
    }
}
