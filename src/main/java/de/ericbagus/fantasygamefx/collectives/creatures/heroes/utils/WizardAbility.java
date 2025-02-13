// Created by Eric B. 01.01.2022 16:37
package de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WizardAbility extends Ability {

    private Timeline timeline;
    private int timerSeconds;

    public WizardAbility(int heroId) {
        super(heroId, TextureManager.TextureType.ABILITY_WIZARD, TextureManager.TextureType.ABILITY_WIZARD_ACTIVE);
    }

    // Override the activation method of the ability
    @Override
    public void setActive() {
        Hero hero = (Hero) Fantasygame.getGameplayManager().getCreatureManager().getCreature(heroId);
        if(hero == null) return;
        // Set the ability to active
        active = true;
        // Update the active ability in the visualized info panel
        Fantasygame.getWindowManager().getInfoField().toggleAbilityStatus(true);
        // Timer seconds to 15, because the ability should be active for 15 seconds
        timerSeconds = 15;
        // You need a timeline timer here, because it's manipulating some parts in a JavaFX visualization (A normal timer would be in another thread than the JavaFX process -> errors)
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            if(Fantasygame.getGameplayManager().isFreezed()) return;
            timerSeconds--;
            Hero hero2 = (Hero) Fantasygame.getGameplayManager().getCreatureManager().getCreature(heroId);
            // If the hero is already dead stop the timer
            if(hero2 == null) {
                timeline.stop();
                return;
            }
            // Regenerate 2.5 lifepoints every second the ability is active
            hero2.regenerateLifePoints(2.5);
            // Check if the ability is over
            if(timerSeconds <= 0) {
                // Update the visualized info panel
                Fantasygame.getWindowManager().getInfoField().toggleAbilityStatus(false);
                Fantasygame.getWindowManager().getInfoField().setAbilityTexture(TextureManager.TextureType.ITEM_NONE);
                // Set the ability to deactivated and start the delay scheduler
                active = false;
                delayed = true;
                timeline.stop();
                startDelayScheduler();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
