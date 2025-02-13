package de.ericzones.fantasygamefx.graphics;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.creatures.Creature;
import de.ericzones.fantasygamefx.collectives.creatures.CreatureManager;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericzones.fantasygamefx.gameplay.GameplayManager;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Random;

public class ControlListener {

    // Initializing the key pressing event
    private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            CreatureManager creatureManager = Fantasygame.getGameplayManager().getCreatureManager();
            // Check if there is already a hero to be controlled
            if(creatureManager.getHeroes().size() == 0) return;
            // Checking if the WASD and arrow keys are pressed and let the hero move
            if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                creatureManager.getHeroes().get(0).getLocation().moveUp();
            } else if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                creatureManager.getHeroes().get(0).getLocation().moveDown();
            } else if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                creatureManager.getHeroes().get(0).getLocation().moveRight();
            } else if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                creatureManager.getHeroes().get(0).getLocation().moveLeft();
            // Check if Escape is pressed (pause and unpause the game)
            } else if(event.getCode() == KeyCode.ESCAPE) {
                GameplayManager gameplayManager = Fantasygame.getGameplayManager();
                // If the game didn't start yet or the game over screen is shown there is no way to pause
                if(Fantasygame.getWindowManager().getEndingField().isVisible() || Fantasygame.getWindowManager().getStartField().isVisible()) return;
                if(gameplayManager.isFreezed())
                    gameplayManager.resumeGame();
                else
                    gameplayManager.freezeGame(true);
            // Check if the Space key is pressed
            } else if(event.getCode() == KeyCode.SPACE) {
                if(Fantasygame.getGameplayManager().isFreezed()) return;
                // Check if there is already a hero to attack
                Hero hero = Fantasygame.getGameplayManager().getCreatureManager().getHeroes().get(0);
                // Check if there are creatures next to the hero to be attacked
                List<Creature> nearCreatures = Fantasygame.getGameplayManager().getGameMap().getNearCreatures(hero.getLocation().getX(), hero.getLocation().getY());
                if(nearCreatures.size() == 0) return;
                // If there are multiple creatures next to the hero attack a random one
                hero.attackEnemy(nearCreatures.get(new Random().nextInt(nearCreatures.size())));
            // Check if the C key is pressed
            } else if(event.getCode() == KeyCode.C) {
                if(Fantasygame.getGameplayManager().isFreezed()) return;
                // Check if there is already a hero to attack
                Hero hero = Fantasygame.getGameplayManager().getCreatureManager().getHeroes().get(0);
                // Check if the ability is already active or the ability is currently delayed
                if(hero.getAbility().isActive()) return;
                if(hero.getAbility().isDelayed()) return;
                // Set the ability to active
                hero.getAbility().setActive();
            }
            event.consume();
        }
    };

    public EventHandler<KeyEvent> getKeyEventListener() {
        return keyListener;
    }
}
