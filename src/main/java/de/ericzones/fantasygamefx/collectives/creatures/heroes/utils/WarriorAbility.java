// Created by Eric B. 01.01.2022 16:19
package de.ericzones.fantasygamefx.collectives.creatures.heroes.utils;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericzones.fantasygamefx.graphics.TextureManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WarriorAbility extends Ability {

    private Timeline timeline;
    private int timerSeconds;

    public WarriorAbility(int heroId) {
        super(heroId, TextureManager.TextureType.ABILITY_WARRIOR, TextureManager.TextureType.ABILITY_WARRIOR_ACTIVE);
    }

    // Override the activation method of the ability
    @Override
    public void setActive() {
        Hero hero = (Hero) Fantasygame.getGameplayManager().getCreatureManager().getCreature(heroId);
        if(hero == null) return;
        // Set the ability to active
        active = true;
        // While the ability is active the warrior gets three times more attack damage
        hero.setHitPoints(hero.getStandardHitPoints()*3);
        // Update the active ability in the visualized info panel
        Fantasygame.getWindowManager().getInfoField().toggleAbilityStatus(true);
        // Timer seconds to 15, because the ability should be active for 15 seconds
        timerSeconds = 15;
        // You need a timeline timer here, because it's manipulating some parts in a JavaFX visualization (A normal timer would be in another thread than the JavaFX process -> errors)
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            if(Fantasygame.getGameplayManager().isFreezed()) return;
            timerSeconds--;
            // If the timer seconds aren't expired return
            if(timerSeconds > 0) return;
            // Stop the ability of this hero
            active = false;
            Hero hero2 = (Hero) Fantasygame.getGameplayManager().getCreatureManager().getCreature(heroId);
            // If the hero is already dead stop the timer
            if(hero2 == null) {
                timeline.stop();
                return;
            }
            // Reset to the standard attack damage
            hero2.setHitPoints(hero2.getStandardHitPoints());
            delayed = true;
            // Update the visualized info panel
            Fantasygame.getWindowManager().getInfoField().toggleAbilityStatus(false);
            Fantasygame.getWindowManager().getInfoField().setAbilityTexture(TextureManager.TextureType.ITEM_NONE);
            timeline.stop();
            // Start the delay scheduler of the ability
            startDelayScheduler();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
