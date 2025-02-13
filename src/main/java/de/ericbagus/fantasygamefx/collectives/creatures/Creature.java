package de.ericbagus.fantasygamefx.collectives.creatures;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.fight.Fight;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericbagus.fantasygamefx.collectives.world.Location;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.scene.paint.Color;

public abstract class Creature {

    private final int id;
    private final Location location;
    private final TextureManager.TextureType texture;
    private final double maxLifePoints, standardHitPoints;
    private double lifePoints, hitPoints;

    public Creature(int id, Location location, TextureManager.TextureType texture, double lifePoints, double hitPoints) {
        this.id = id;
        this.location = location;
        this.texture = texture;
        this.maxLifePoints = lifePoints;
        this.standardHitPoints = hitPoints;
        this.lifePoints = lifePoints;
        this.hitPoints = hitPoints;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public TextureManager.TextureType getTexture() {
        return texture;
    }

    public double getMaxLifePoints() {
        return maxLifePoints;
    }

    public double getStandardHitPoints() {
        return standardHitPoints;
    }

    public double getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;
        if(this instanceof Hero)
            Fantasygame.getWindowManager().getInfoField().setLifePoints(lifePoints);
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setDamage(double damage) {
        if(lifePoints <= 0) return;
        if(lifePoints - damage < 0) {
            Fantasygame.getWindowManager().getGameField().getFieldByLocation(getLocation()).showInfoText("-" + lifePoints, Color.RED);
            setLifePoints(0);
        } else {
            Fantasygame.getWindowManager().getGameField().getFieldByLocation(getLocation()).showInfoText("-" + damage, Color.RED);
            setLifePoints(getLifePoints() - damage);
        }
    }

    public void attackEnemy(Creature creature) {
        if(this instanceof Hero)
            ((Hero) this).resetHealingSeconds();
        if(creature instanceof Hero)
            ((Hero) creature).resetHealingSeconds();
        new Fight(this, creature);
    }

}
