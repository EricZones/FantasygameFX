// Created by Eric B. 01.12.2021 22:05
package de.ericbagus.fantasygamefx.collectives.creatures.monster;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.collectives.creatures.CreatureManager;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.Hero;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

public class MonsterAI {

    private final int monsterId;
    private Timeline timeline;

    public MonsterAI(int monsterId) {
        this.monsterId = monsterId;
        initiateMovingScheduler();
    }

    // Timer to let the monster move randomly every 4 seconds and hit a hero if he is in the range
    private void initiateMovingScheduler() {
        // You need a timeline timer here, because it's manipulating some parts in a JavaFX visualization (A normal timer would be in another thread than the JavaFX process -> errors)
        timeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
            // Don't move if the game is freezed
            if(Fantasygame.getGameplayManager().isFreezed()) return;
            CreatureManager creatureManager = Fantasygame.getGameplayManager().getCreatureManager();
            Monster monster = (Monster) creatureManager.getCreature(monsterId);
            // If the monster of the current MonsterAI is already dead stop the timer
            if(monster == null) {
                timeline.stop();
                return;
            }
            monster.getLocation().moveRandom();
            // Check for near heroes and attack one
            List<Creature> nearCreatures = Fantasygame.getGameplayManager().getGameMap().getNearCreatures(monster.getLocation().getX(), monster.getLocation().getY());
            nearCreatures.removeIf(current -> !(current instanceof Hero));
            if(nearCreatures.size() == 0) return;
            monster.attackEnemy(nearCreatures.get(new Random().nextInt(nearCreatures.size())));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
