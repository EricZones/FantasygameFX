// Created by Eric B. 01.01.2022 11:54
package de.ericbagus.fantasygamefx.collectives.creatures.heroes;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.Ability;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericbagus.fantasygamefx.collectives.tools.weapons.Weapon;
import de.ericbagus.fantasygamefx.collectives.world.Location;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class Hero extends Creature {

    private final String name;
    private final HeroType heroType;
    private final Ability ability;
    private Weapon weapon;
    private int kills;

    private Timeline timeline;
    private int healingSeconds;

    public Hero(int id, Location location, TextureManager.TextureType texture, double lifePoints, double hitPoints, String name, HeroType heroType, Ability ability) {
        super(id, location, texture, lifePoints, hitPoints);
        this.name = name;
        this.heroType = heroType;
        this.ability = ability;
        initiateRegenerationTimer();
    }

    public String getName() {
        return name;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public Ability getAbility() {
        return ability;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
        // Update the visualized kill count
        Fantasygame.getWindowManager().getInfoField().setKills(kills);
    }

    public void regenerateLifePoints(double lifePoints) {
        if(getLifePoints() >= getMaxLifePoints()) return;
        // Reset the lifepoints of the hero and visualize it in the game field
        if(getLifePoints()+lifePoints >= getMaxLifePoints()) {
            Fantasygame.getWindowManager().getGameField().getFieldByLocation(getLocation()).showInfoText("+" + (getMaxLifePoints()-getLifePoints()), Color.GREEN);
            setLifePoints(getMaxLifePoints());
        } else {
            Fantasygame.getWindowManager().getGameField().getFieldByLocation(getLocation()).showInfoText("+" + lifePoints, Color.GREEN);
            setLifePoints(getLifePoints()+lifePoints);
        }
    }

    // Reset the seconds until the hero can regenerate lifepoints
    public void resetHealingSeconds() {
        healingSeconds = 10;
    }

    // Timer checking if the hero is able to heal 0.5 lifepoints every second
    private void initiateRegenerationTimer() {
        resetHealingSeconds();
        // You need a timeline timer here, because it's manipulating some parts in a JavaFX visualization (A normal timer would be in another thread than the JavaFX process -> errors)
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            // Don't heal if the game is freezed
            if(Fantasygame.getGameplayManager().isFreezed()) return;
            // If the hero is dead stop the timer
            if(Fantasygame.getGameplayManager().getCreatureManager().getCreature(getId()) == null) {
                timeline.stop();
                return;
            }
            healingSeconds--;
            // If the seconds until healing are expired, start healing lifepoints
            if(healingSeconds <= 0)
                regenerateLifePoints(0.5);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
